<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="广告标题" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入广告标题"
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
          v-hasPermi="['shop:ad:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:ad:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:ad:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:ad:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="adList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="广告ID" align="center" prop="id" />
      <el-table-column label="广告标题" align="center" prop="name" />
      <el-table-column label="活动内容" align="center" prop="content" />
      <el-table-column label="广告图片" align="center" prop="url" >
        <template slot-scope="scope">
          <img v-if="scope.row.url" :src="scope.row.url" width="80">
        </template>
      </el-table-column>
      <el-table-column label="广告位置" align="center" prop="position" :formatter="visibleFormat" >
      </el-table-column>
      <el-table-column label="活动链接" align="center" prop="link" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="启用" align="center" prop="enabled" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.enabled ? 'success' : 'error' ">{{ scope.row.enabled===1 ? '启用' : '不启用' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:ad:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:ad:remove']"
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

    <!-- 添加或修改广告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="广告标题" prop="name">
          <el-input v-model="form.name" placeholder="请输入广告标题" />
        </el-form-item>
        <el-form-item label="活动内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入活动内容" />
        </el-form-item>
        <el-form-item label="广告图片" prop="url">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadUrl"
            :before-upload="checkFileSize"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="form.url" :src="form.url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过1024kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="广告位置" prop="position">
          <el-select v-model="form.position" placeholder="请选择">
            <el-option :value="1" label="首页"/>
            <el-option :value="2" label="中间"/>
            <el-option :value="3" label="底部"/>
          </el-select>
        </el-form-item>

        <el-form-item label="广告开始时间" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择广告开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="广告结束时间" prop="endTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择广告结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否启动" prop="enabled">
          <el-select v-model="form.enabled" placeholder="请选择">
            <el-option :value="1" label="启用"/>
            <el-option :value="0" label="不启用"/>
          </el-select>
        </el-form-item>

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
import { listAd, getAd, delAd, addAd, updateAd, exportAd } from "@/api/shop/ad";
import { createStorage, uploadPath } from '@/api/shop/storage';
import { getToken } from '@/utils/auth';
export default {
  name: "Ad",
  data() {
    return {
      uploadPath,
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
      // 广告表格数据
      adList: [],
      statusOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        link: undefined,
        url: undefined,
        position: undefined,
        content: undefined,
        startTime: undefined,
        endTime: undefined,
        enabled: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "广告标题不能为空", trigger: "blur" }
        ],
        link: [
          { required: true, message: "所广告的商品页面或者活动页面链接地址不能为空", trigger: "blur" }
        ],
        url: [
          { required: true, message: "广告宣传图片不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_ad_type").then(response => {
      this.statusOptions = response.data;
    });
  },

  computed: {
    headers() {
      return {
        headers: {Authorization: 'Bearer ' + getToken()},
      }
    }
  },
  methods: {
    /** 查询广告列表 */
    getList() {
      this.loading = true;
      listAd(this.queryParams).then(response => {
        this.adList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 菜单显示状态字典翻译
    visibleFormat(row, column) {

      return this.selectDictLabel(this.statusOptions, row.position);
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
        link: undefined,
        url: undefined,
        position: undefined,
        content: undefined,
        startTime: undefined,
        endTime: undefined,
        enabled: undefined,
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

    uploadUrl: function(response) {
      this.form.url = response.data.url
    },
    checkFileSize: function(file) {
      if (file.size > 1048576) {
        this.$message.error(`${file.name}文件大于1024KB，请选择小于1024KB大小的图片`)
        return false
      }
      return true
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
      this.title = "添加广告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAd(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改广告";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAd(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAd(this.form).then(response => {
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
      this.$confirm('是否确认删除广告编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAd(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有广告数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAd(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
