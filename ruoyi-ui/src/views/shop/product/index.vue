<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="商品表的商品ID" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品表的商品ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入商品货品价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品数量" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入商品货品数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品货品图片" prop="url">
        <el-input
          v-model="queryParams.url"
          placeholder="请输入商品货品图片"
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
          v-hasPermi="['shop:product:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:product:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:product:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="逻辑删除" align="center" prop="id" />
      <el-table-column label="商品表的商品ID" align="center" prop="goodsId" />
      <el-table-column label="商品规格值列表，采用JSON数组格式" align="center" prop="specifications" />
      <el-table-column label="商品货品价格" align="center" prop="price" />
      <el-table-column label="商品货品数量" align="center" prop="number" />
      <el-table-column label="商品货品图片" align="center" prop="url" />
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
            v-hasPermi="['shop:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:product:remove']"
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

    <!-- 添加或修改商品货品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品表的商品ID" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品表的商品ID" />
        </el-form-item>
        <el-form-item label="商品规格值列表，采用JSON数组格式" prop="specifications">
          <el-input v-model="form.specifications" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商品货品价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入商品货品价格" />
        </el-form-item>
        <el-form-item label="商品货品数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入商品货品数量" />
        </el-form-item>
        <el-form-item label="商品货品图片" prop="url">
          <el-input v-model="form.url" placeholder="请输入商品货品图片" />
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
import { listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct } from "@/api/shop/product";

export default {
  name: "Product",
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
      // 商品货品表格数据
      productList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsId: undefined,
        specifications: undefined,
        price: undefined,
        number: undefined,
        url: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品表的商品ID不能为空", trigger: "blur" }
        ],
        specifications: [
          { required: true, message: "商品规格值列表，采用JSON数组格式不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "商品货品价格不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "商品货品数量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品货品列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
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
        goodsId: undefined,
        specifications: undefined,
        price: undefined,
        number: undefined,
        url: undefined,
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
      this.title = "添加商品货品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品货品";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateProduct(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addProduct(this.form).then(response => {
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
      this.$confirm('是否确认删除商品货品编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProduct(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品货品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProduct(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>