<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="类目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入类目名称"
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
          v-hasPermi="['shop:category:add']"
        >新增</el-button>
      </el-col>
    </el-row>
    <el-table
      v-loading="loading"
      :data="categoryList"
      row-key="id"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
    <!--<el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">-->
      <el-table-column label="类目名称" align="center" prop="name" />
      <el-table-column label="类目级别" align="center" prop="level" />
      <el-table-column label="类目关键字" align="center" prop="keywords" />
      <el-table-column label="类目广告语介绍" align="center" prop="desc" />
      <el-table-column label="类目图标" align="center" property="iconUrl" >
        <template slot-scope="scope">
          <img v-if="scope.row.iconUrl" :src="scope.row.iconUrl" width="40">
        </template>
      </el-table-column>
      <el-table-column label="类目图片" align="center" property="picUrl">
        <template slot-scope="scope">
          <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="40">
        </template>
      </el-table-column>

      <el-table-column label="排序" align="center" prop="sortOrder" />
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:category:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:category:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改类目对话框 -->

    <el-dialog :title="title" :visible.sync="open" width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>

          <el-col :span="12">
            <el-form-item label="类目级别" prop="level">
              <el-select   v-model="form.level" @change="onLevelChange" :disabled="form.selectEable==='修改'?true:false" placeholder="请输入类目级别">
                <el-option label="一级类目" value="L1"/>
                <el-option label="二级类目" value="L2"/>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="24" v-if="form.level ==='L2'">
            <el-form-item     label="父类目" prop="parentId">
              <el-select v-model="form.parentId"  :disabled="form.selectEable==='修改'?true:false" >
                <el-option label="请选择" value=""/>
                <el-option v-for="item in catL1"  :key="item.id"   :label="item.name"   :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="类目名称" prop="keywords">
              <el-input v-model="form.name" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <!--<el-col :span="24" v-if="form.parentId !== 0">-->
            <!--<el-form-item label="上级部门" prop="parentId">-->
              <!--<treeselect v-model="form.parentId" :options="cateOptions" :normalizer="normalizer" placeholder="选择上级部门" />-->
            <!--</el-form-item>-->
          <!--</el-col>-->
          <el-col :span="12">
            <el-form-item label="类目级别" prop="level">
              <el-input v-model="form.level" readonly  placeholder="请输入类目图片" />
            </el-form-item>
          </el-col>
          <!--<el-col :span="12">-->
            <!--<el-form-item label="类目关键" prop="keywords">-->
              <!--<el-input v-model="form.keywords" type="textarea" placeholder="请输入内容" />-->
            <!--</el-form-item>-->
          <!--</el-col>-->
          <!--<el-col :span="12">-->
            <!--<el-form-item label="类目广告" prop="desc">-->
              <!--<el-input v-model="form.desc" placeholder="请输入类目广告语介绍" />-->
            <!--</el-form-item>-->
          <!--</el-col>-->
          <el-col :span="16">
            <el-form-item label="类目图标" prop="iconUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="uploadIconUrl"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="form.iconUrl" :src="form.iconUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="类目图片" prop="picUrl">
              <el-upload
                :headers="headers"
                :action="uploadPath"
                :show-file-list="false"
                :on-success="uploadPicUrl"
                class="avatar-uploader"
                accept=".jpg,.jpeg,.png,.gif">
                <img v-if="form.picUrl" :src="form.picUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-col>


          <el-col :span="16">
            <el-form-item label="排序" prop="sortOrder">
              <el-input v-model="form.sortOrder" placeholder="请输入排序" />
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>
<script>
import { listCategory, listCatL1,getCategory, delCategory, addCategory, updateCategory, exportCategory } from "@/api/shop/category";
import { createStorage, uploadPath } from '@/api/shop/storage'
import { getToken } from '@/utils/auth'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "Category",
  components: { Treeselect },
  data() {
    return {
      uploadPath,
      // 遮罩层
      loading: true,
      // 类目表格数据
      categoryList: [],
      cateOptions:[],
      catL1 : [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      defaultProps: {
        children: "children",
        label: "label"
      },
      // 查询参数
      queryParams: {
        name: undefined,
      },
      // 表单参数
      form: {selectEable:'增加'},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "类目名称不能为空", trigger: "blur" }
        ],
        parentId: [
          { required: true, message: "父类目ID不能为空", trigger: "blur" }
        ],
      }
    };
  },


  created() {
    this.getList();
    this.getCatL1();

  },
  computed: {
    headers() {
      return {
        headers: {Authorization: 'Bearer ' + getToken()},
      }
    }
  },
  methods: {
    /** 查询类目列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        this.categoryList = this.handleTree(response.data, "id");

        this.loading = false;
      });
    },

    // 获取分类的一级类目

    getCatL1() {
      listCatL1().then(response => {
        this.catL1 = response.cataL1;
        console.log(response.cataL1);
      });
    },
    getTreeselect() {
      listCategory().then(response => {
        this.cateOptions = this.handleTree(response.data, "id");

      });
    },
    /** 转换部门数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },

    onLevelChange: function(value) {
      if (value === 'L1') {
        this.form.parentId = 0
      }
    },


    uploadPicUrl: function(response) {
      this.form.picUrl = response.data.url
    },

    uploadIconUrl: function(response) {
      this.form.iconUrl = response.data.url
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
        name: undefined,
        keywords: undefined,
        desc: undefined,
        parentId: undefined,
        iconUrl: undefined,
        picUrl: undefined,
        level: undefined,
        sortOrder: undefined,
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

    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getCatL1();
      this.getTreeselect();

      this.open = true;
      this.title = "添加类目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id
      getCategory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改类目";
        this.form.selectEable='修改';
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCategory(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCategory(this.form).then(response => {
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
      this.$confirm('是否确认删除类目编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCategory(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有类目数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCategory(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
