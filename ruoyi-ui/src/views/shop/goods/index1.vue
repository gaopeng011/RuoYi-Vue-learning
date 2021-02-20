<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="商品编号" prop="goodsSn">
        <el-input
          v-model="queryParams.goodsSn"
          placeholder="请输入商品编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入商品名称"
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
          v-hasPermi="['shop:goods:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:goods:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品编号" align="center" prop="goodsSn" />
      <el-table-column label="商品名称" align="center" prop="name" />
      <el-table-column label="商品所属类目ID" align="center" prop="categoryId" />
      <el-table-column label="商品所属类目ID" align="center" prop="brandId" />
      <el-table-column label="商品关键字" align="center" prop="keywords" />
      <el-table-column label="商品简介" align="center" prop="brief" />
      <el-table-column label="是否上架" align="center" prop="isOnSale" />
      <el-table-column label="是否上架" align="center" prop="sortOrder" />
      <el-table-column label="商品页面商品图片" align="center" prop="picUrl" />
      <el-table-column label="是否新品首发" align="center" prop="isNew" />
      <el-table-column label="是否人气推荐" align="center" prop="isHot" />
      <el-table-column label="商品单位" align="center" prop="unit" />
      <el-table-column label="专柜价格" align="center" prop="counterPrice" />
      <el-table-column label="零售价格" align="center" prop="retailPrice" />
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
            v-hasPermi="['shop:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:goods:remove']"
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

    <!-- 添加或修改商品基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品编号" prop="goodsSn">
          <el-input v-model="form.goodsSn" placeholder="请输入商品编号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品所属类目ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入商品所属类目ID" />
        </el-form-item>
        <el-form-item label="商品所属类目ID" prop="brandId">
          <el-input v-model="form.brandId" placeholder="请输入商品所属类目ID" />
        </el-form-item>
        <el-form-item label="商品宣传图片列表，采用JSON数组格式" prop="gallery">
          <el-input v-model="form.gallery" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商品关键字，采用逗号间隔" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入商品关键字，采用逗号间隔" />
        </el-form-item>
        <el-form-item label="商品简介" prop="brief">
          <el-input v-model="form.brief" placeholder="请输入商品简介" />
        </el-form-item>
        <el-form-item label="是否上架" prop="isOnSale">
          <el-input v-model="form.isOnSale" placeholder="请输入是否上架" />
        </el-form-item>
        <el-form-item label="是否上架" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入是否上架" />
        </el-form-item>
        <el-form-item label="商品页面商品图片" prop="picUrl">
          <el-input v-model="form.picUrl" placeholder="请输入商品页面商品图片" />
        </el-form-item>
        <el-form-item label="商品分享朋友圈图片" prop="shareUrl">
          <el-input v-model="form.shareUrl" placeholder="请输入商品分享朋友圈图片" />
        </el-form-item>
        <el-form-item label="是否新品首发，如果设置则可以在新品首发页面展示" prop="isNew">
          <el-input v-model="form.isNew" placeholder="请输入是否新品首发，如果设置则可以在新品首发页面展示" />
        </el-form-item>
        <el-form-item label="是否人气推荐，如果设置则可以在人气推荐页面展示" prop="isHot">
          <el-input v-model="form.isHot" placeholder="请输入是否人气推荐，如果设置则可以在人气推荐页面展示" />
        </el-form-item>
        <el-form-item label="商品单位，例如件、盒" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入商品单位，例如件、盒" />
        </el-form-item>
        <el-form-item label="专柜价格" prop="counterPrice">
          <el-input v-model="form.counterPrice" placeholder="请输入专柜价格" />
        </el-form-item>
        <el-form-item label="零售价格" prop="retailPrice">
          <el-input v-model="form.retailPrice" placeholder="请输入零售价格" />
        </el-form-item>
        <el-form-item label="商品详细介绍，是富文本格式" prop="detail">
          <el-input v-model="form.detail" placeholder="请输入商品详细介绍，是富文本格式" />
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
import { listGoods, getGoods, delGoods, addGoods, updateGoods, exportGoods } from "@/api/shop/goods";
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination'
export default {
  name: "Goods",
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
      // 商品基本信息表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsSn: undefined,
        name: undefined,
        categoryId: undefined,
        brandId: undefined,
        gallery: undefined,
        keywords: undefined,
        brief: undefined,
        isOnSale: undefined,
        sortOrder: undefined,
        picUrl: undefined,
        shareUrl: undefined,
        isNew: undefined,
        isHot: undefined,
        unit: undefined,
        counterPrice: undefined,
        retailPrice: undefined,
        detail: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsSn: [
          { required: true, message: "商品编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品基本信息列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
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
        goodsSn: undefined,
        name: undefined,
        categoryId: undefined,
        brandId: undefined,
        gallery: undefined,
        keywords: undefined,
        brief: undefined,
        isOnSale: undefined,
        sortOrder: undefined,
        picUrl: undefined,
        shareUrl: undefined,
        isNew: undefined,
        isHot: undefined,
        unit: undefined,
        counterPrice: undefined,
        retailPrice: undefined,
        detail: undefined,
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
      this.title = "添加商品基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGoods(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品基本信息";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateGoods(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGoods(this.form).then(response => {
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
      this.$confirm('是否确认删除商品基本信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGoods(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商品基本信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGoods(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
