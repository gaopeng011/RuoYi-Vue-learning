<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="用户表的用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户表的用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品表的商品ID" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品表的商品ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品编号" prop="goodsSn">
        <el-input
          v-model="queryParams.goodsSn"
          placeholder="请输入商品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品表的货品ID" prop="productId">
        <el-input
          v-model="queryParams.productId"
          placeholder="请输入商品货品表的货品ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品的价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入商品货品的价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品的数量" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入商品货品的数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购物车中商品是否选择状态" prop="checked">
        <el-input
          v-model="queryParams.checked"
          placeholder="请输入购物车中商品是否选择状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品图片或者商品货品图片" prop="picUrl">
        <el-input
          v-model="queryParams.picUrl"
          placeholder="请输入商品图片或者商品货品图片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="addTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.addTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="逻辑删除" prop="deleted">
        <el-input
          v-model="queryParams.deleted"
          placeholder="请输入逻辑删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['shop:cart:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:cart:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:cart:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:cart:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="cartList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="逻辑删除" align="center" prop="id" />
      <el-table-column label="用户表的用户ID" align="center" prop="userId" />
      <el-table-column label="商品表的商品ID" align="center" prop="goodsId" />
      <el-table-column label="商品编号" align="center" prop="goodsSn" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品货品表的货品ID" align="center" prop="productId" />
      <el-table-column label="商品货品的价格" align="center" prop="price" />
      <el-table-column label="商品货品的数量" align="center" prop="number" />
      <el-table-column label="商品规格值列表，采用JSON数组格式" align="center" prop="specifications" />
      <el-table-column label="购物车中商品是否选择状态" align="center" prop="checked" />
      <el-table-column label="商品图片或者商品货品图片" align="center" prop="picUrl" />
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除" align="center" prop="deleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:cart:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:cart:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改购物车商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户表的用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户表的用户ID" />
        </el-form-item>
        <el-form-item label="商品表的商品ID" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品表的商品ID" />
        </el-form-item>
        <el-form-item label="商品编号" prop="goodsSn">
          <el-input v-model="form.goodsSn" placeholder="请输入商品编号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品货品表的货品ID" prop="productId">
          <el-input v-model="form.productId" placeholder="请输入商品货品表的货品ID" />
        </el-form-item>
        <el-form-item label="商品货品的价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入商品货品的价格" />
        </el-form-item>
        <el-form-item label="商品货品的数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入商品货品的数量" />
        </el-form-item>
        <el-form-item label="商品规格值列表，采用JSON数组格式" prop="specifications">
          <el-input v-model="form.specifications" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="购物车中商品是否选择状态" prop="checked">
          <el-input v-model="form.checked" placeholder="请输入购物车中商品是否选择状态" />
        </el-form-item>
        <el-form-item label="商品图片或者商品货品图片" prop="picUrl">
          <el-input v-model="form.picUrl" placeholder="请输入商品图片或者商品货品图片" />
        </el-form-item>
        <el-form-item label="创建时间" prop="addTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="逻辑删除" prop="deleted">
          <el-input v-model="form.deleted" placeholder="请输入逻辑删除" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCart, getCart, delCart, addCart, updateCart, exportCart } from "@/api/shop/cart";

export default {
  name: "Cart",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 购物车商品表格数据
      cartList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        goodsId: undefined,
        goodsSn: undefined,
        goodsName: undefined,
        productId: undefined,
        price: undefined,
        number: undefined,
        specifications: undefined,
        checked: undefined,
        picUrl: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询购物车商品列表 */
    getList() {
      this.loading = true;
      listCart(this.queryParams).then(response => {
        this.cartList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        userId: undefined,
        goodsId: undefined,
        goodsSn: undefined,
        goodsName: undefined,
        productId: undefined,
        price: undefined,
        number: undefined,
        specifications: undefined,
        checked: undefined,
        picUrl: undefined,
        addTime: undefined,
        updateTime: undefined,
        deleted: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加购物车商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCart(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改购物车商品";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCart(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCart(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除购物车商品编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCart(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有购物车商品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCart(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>