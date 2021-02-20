<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="关键字" prop="keyword">
        <el-input
          v-model="queryParams.keyword"
          placeholder="请输入关键字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跳转链接" prop="url">
        <el-input
          v-model="queryParams.url"
          placeholder="请输入关键字的跳转链接"
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
          v-hasPermi="['shop:keyword:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:keyword:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:keyword:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:keyword:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="keywordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column align="center" width="150px" label="关键词ID" prop="id" sortable/>

      <el-table-column align="center" min-width="100px" label="关键词" prop="keyword"/>

      <el-table-column align="center" min-width="300px" label="跳转链接" prop="url"/>

      <el-table-column align="center" min-width="100px" label="是否推荐" prop="isHot">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isHot ? 'success' : 'error' ">{{ scope.row.isHot ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" min-width="100px" label="是否默认" prop="isDefault">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isDefault ? 'success' : 'error' ">{{ scope.row.isDefault ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:keyword:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:keyword:remove']"
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

    <!-- 添加或修改关键字对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关键字" prop="keyword">
          <el-input v-model="form.keyword" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="跳转链接" prop="url">
          <el-input v-model="form.url" placeholder="请输入关键字的跳转链接" />
        </el-form-item>
        <el-form-item label="是否推荐" prop="isHot">
          <el-select v-model="form.isHot" placeholder="请选择">
            <el-option :value="1" label="推荐"/>
            <el-option :value="0" label="普通"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否默认" prop="isDefault">
          <el-select v-model="form.isDefault" placeholder="请选择">
            <el-option :value="1" label="默认"/>
            <el-option :value="0" label="非默认"/>
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

<script>
import { listKeyword, getKeyword, delKeyword, addKeyword, updateKeyword, exportKeyword } from "@/api/shop/keyword";

export default {
  name: "Keyword",
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
      // 关键字表格数据
      keywordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        keyword: undefined,
        url: undefined,
        isHot: undefined,
        isDefault: undefined,
        sortOrder: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        keyword: [
          { required: true, message: "关键字不能为空", trigger: "blur" }
        ],
        url: [
          { required: true, message: "关键字的跳转链接不能为空", trigger: "blur" }
        ],
        isHot: [
          { required: true, message: "是否是热门关键字不能为空", trigger: "blur" }
        ],
        isDefault: [
          { required: true, message: "是否是默认关键字不能为空", trigger: "blur" }
        ],
        sortOrder: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询关键字列表 */
    getList() {
      this.loading = true;
      listKeyword(this.queryParams).then(response => {
        this.keywordList = response.rows;
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
        keyword: undefined,
        url: undefined,
        isHot: undefined,
        isDefault: undefined,
        sortOrder: undefined,
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
      this.title = "添加关键字";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getKeyword(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改关键字";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateKeyword(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addKeyword(this.form).then(response => {
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
      this.$confirm('是否确认删除关键字编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delKeyword(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有关键字数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportKeyword(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
