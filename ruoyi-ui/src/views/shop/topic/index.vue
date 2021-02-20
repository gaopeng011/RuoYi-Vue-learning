<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="专标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入专题标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专题子标题" prop="subtitle">
        <el-input
          v-model="queryParams.subtitle"
          placeholder="请输入专题子标题"
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
          v-hasPermi="['shop:topic:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:topic:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:topic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:topic:export']"
        >导出</el-button>
      </el-col>
    </el-row>




    <el-table v-loading="loading" :data="topicList" border fit highlight-current-row @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column align="center" label="ID" prop="id" />
      <el-table-column align="center" label="标题" prop="title" />

      <el-table-column align="center" label="子标题" min-width="200" prop="subtitle" />

      <el-table-column align="center" property="picUrl" label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.picUrl" width="80">
        </template>
      </el-table-column>

      <el-table-column align="center" label="专题详情" prop="content">
        <template slot-scope="scope">
          <el-dialog :visible.sync="contentDialogVisible" title="专题详情">
            <div v-html="contentDetail" />
          </el-dialog>
          <el-button type="primary" size="mini" @click="showContent(scope.row.content)">查看</el-button>
        </template>
      </el-table-column>

      <el-table-column align="center" label="底价" prop="price" />

      <el-table-column align="center" label="阅读数量" prop="readCount" />
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
            v-hasPermi="['shop:topic:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:topic:remove']"
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

  </div>
</template>
<style>
  .el-dialog {
    width: 800px;
  }
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
import { listTopic, getTopic, delTopic, addTopic, updateTopic, exportTopic } from "@/api/shop/topic";

export default {
  name: "Topic",
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
      // 专题表格数据
      topicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        subtitle: undefined,
        content: undefined,
        price: undefined,
        readCount: undefined,
        picUrl: undefined,
        sortOrder: undefined,
        goods: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},

      multipleSelection: [],
      contentDetail: '',
      contentDialogVisible: false,
      // 表单校验
      rules: {
        title: [
          { required: true, message: "专题标题不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询专题列表 */
    getList() {
      this.loading = true;
      listTopic(this.queryParams).then(response => {
        this.topicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    showContent(content) {
      this.contentDetail = content
      this.contentDialogVisible = true
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
        title: undefined,
        subtitle: undefined,
        content: undefined,
        price: undefined,
        readCount: undefined,
        picUrl: undefined,
        sortOrder: undefined,
        goods: undefined,
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
      this.$router.push({path:'/push/topicCreate'})
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$router.push({path:'/push/topicEdit',query: { id: row.id }})
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除专题编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTopic(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有专题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTopic(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
