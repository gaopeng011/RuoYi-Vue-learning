<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="管理员名称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入管理员名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="管理员密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入管理员密码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最近一次登录IP地址" prop="lastLoginIp">
        <el-input
          v-model="queryParams.lastLoginIp"
          placeholder="请输入最近一次登录IP地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最近一次登录时间" prop="lastLoginTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.lastLoginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择最近一次登录时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="头像图片" prop="avatar">
        <el-input
          v-model="queryParams.avatar"
          placeholder="请输入头像图片"
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
      <el-form-item label="角色列表" prop="roleIds">
        <el-input
          v-model="queryParams.roleIds"
          placeholder="请输入角色列表"
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
          v-hasPermi="['shop:admin:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:admin:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:admin:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:admin:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="adminList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="角色列表" align="center" prop="id" />
      <el-table-column label="管理员名称" align="center" prop="username" />
      <el-table-column label="管理员密码" align="center" prop="password" />
      <el-table-column label="最近一次登录IP地址" align="center" prop="lastLoginIp" />
      <el-table-column label="最近一次登录时间" align="center" prop="lastLoginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastLoginTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="头像图片" align="center" prop="avatar" />
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逻辑删除" align="center" prop="deleted" />
      <el-table-column label="角色列表" align="center" prop="roleIds" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:admin:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:admin:remove']"
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

    <!-- 添加或修改管理员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="管理员名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入管理员名称" />
        </el-form-item>
        <el-form-item label="管理员密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入管理员密码" />
        </el-form-item>
        <el-form-item label="最近一次登录IP地址" prop="lastLoginIp">
          <el-input v-model="form.lastLoginIp" placeholder="请输入最近一次登录IP地址" />
        </el-form-item>
        <el-form-item label="最近一次登录时间" prop="lastLoginTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.lastLoginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最近一次登录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="头像图片" prop="avatar">
          <el-input v-model="form.avatar" placeholder="请输入头像图片" />
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
        <el-form-item label="角色列表" prop="roleIds">
          <el-input v-model="form.roleIds" placeholder="请输入角色列表" />
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
import { listAdmin, getAdmin, delAdmin, addAdmin, updateAdmin, exportAdmin } from "@/api/shop/admin";

export default {
  name: "Admin",
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
      // 管理员表格数据
      adminList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: undefined,
        password: undefined,
        lastLoginIp: undefined,
        lastLoginTime: undefined,
        avatar: undefined,
        addTime: undefined,
        deleted: undefined,
        roleIds: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "管理员名称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "管理员密码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询管理员列表 */
    getList() {
      this.loading = true;
      listAdmin(this.queryParams).then(response => {
        this.adminList = response.rows;
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
        username: undefined,
        password: undefined,
        lastLoginIp: undefined,
        lastLoginTime: undefined,
        avatar: undefined,
        addTime: undefined,
        updateTime: undefined,
        deleted: undefined,
        roleIds: undefined
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
      this.title = "添加管理员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAdmin(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改管理员";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAdmin(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAdmin(this.form).then(response => {
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
      this.$confirm('是否确认删除管理员编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAdmin(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有管理员数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAdmin(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>