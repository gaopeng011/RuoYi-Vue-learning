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
          @click="handleCreate"
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

    <el-table v-loading="loading" :data="goodsList"   element-loading-text="正在查询中。。。" border fit highlight-current-row>


      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" class="table-expand">
            <el-form-item label="商品编号">
              <span>{{ props.row.goodsSn }}</span>
            </el-form-item>
            <el-form-item label="宣传画廊">
              <img v-for="pic in props.row.gallery" :key="pic" :src="pic" class="gallery">
            </el-form-item>
            <el-form-item label="商品介绍">
              <span>{{ props.row.brief }}</span>
            </el-form-item>
            <el-form-item label="商品单位">
              <span>{{ props.row.unit }}</span>
            </el-form-item>
            <el-form-item label="关键字">
              <span>{{ props.row.keywords }}</span>
            </el-form-item>
            <el-form-item label="类目ID">
              <span>{{ props.row.categoryId }}</span>
            </el-form-item>
            <el-form-item label="品牌商ID">
              <span>{{ props.row.brandId }}</span>
            </el-form-item>

          </el-form>
        </template>
      </el-table-column>

      <el-table-column align="center" label="商品ID" prop="id" />

      <el-table-column align="center" min-width="100" label="名称" prop="name" />

      <el-table-column align="center" property="iconUrl" label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.picUrl" width="40">
        </template>
      </el-table-column>

      <el-table-column align="center" property="iconUrl" label="分享图">
        <template slot-scope="scope">
          <img :src="scope.row.shareUrl" width="40">
        </template>
      </el-table-column>

      <el-table-column align="center" label="详情" prop="detail">
        <template slot-scope="scope">
          <el-dialog :visible.sync="detailDialogVisible" title="商品详情">
            <div class="goods-detail-box" v-html="goodsDetail" />
          </el-dialog>
          <el-button type="primary" size="mini" @click="showDetail(scope.row.detail)">查看</el-button>
        </template>
      </el-table-column>

      <el-table-column align="center" label="市场售价" prop="counterPrice" />

      <el-table-column align="center" label="当前价格" prop="retailPrice" />

      <el-table-column align="center" label="是否新品" prop="isNew">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isNew ? 'success' : 'error' ">{{ scope.row.isNew ? '新品' : '非新品' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否热品" prop="isHot">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isHot ? 'success' : 'error' ">{{ scope.row.isHot ? '热品' : '非热品' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否在售" prop="isOnSale">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isOnSale ? 'success' : 'error' ">{{ scope.row.isOnSale ? '在售' : '未售' }}</el-tag>
        </template>
      </el-table-column>
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
    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

  </div>
</template>
<style>
  .table-expand {
    font-size: 0;
  }
  .table-expand label {
    width: 100px;
    color: #99a9bf;
  }
  .table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
  }
  .gallery {
    width: 80px;
    margin-right: 10px;
  }
  .goods-detail-box img {
    width: 100%;
  }
</style>
<script>
import { listGoods, getGoods, delGoods, addGoods, updateGoods, exportGoods } from "@/api/shop/goods";
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination'
export default {
  name: "Goods",
  components: { BackToTop, Pagination },
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
      },
      goodsDetail: '',
      detailDialogVisible: false,
      downloadLoading: false,
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

    handleCreate() {

      this.$router.push('/goods/create')
    },


    handleUpdate(row) {
      console.log(row.id)
      this.$router.push({ path: '/goods/edit', query: { id: row.id }})
    },

    showDetail(detail) {
      this.goodsDetail = detail
      this.detailDialogVisible = true
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品基本信息";
    },
    /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const id = row.id || this.ids
    //   getGoods(id).then(response => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改商品基本信息";
    //   });
    // },
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
      const ids = row.id;
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
