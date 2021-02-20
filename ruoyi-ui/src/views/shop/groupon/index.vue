<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">


      <el-form-item label="团购规则ID" prop="rulesId">
        <el-input
          v-model="queryParams.rulesId"
          placeholder="请输入团购规则ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="团购活动状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择" clearable size="small">
          <el-option label="开团未支付" value="0" />
          <el-option label="开团中" value="1" />
          <el-option label="开团失败" value="2" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>

        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:groupon:export']"
        >导出</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="grouponList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单ID" align="center" prop="orderId" />
      <el-table-column label="团购活动ID" align="center" prop="grouponId" />
      <el-table-column label="团购规则ID" align="center" prop="rulesId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="团购分享图片地址" align="center" prop="shareUrl" />
      <el-table-column label="开团用户ID" align="center" prop="creatorUserId" />
      <el-table-column label="开团时间" align="center" prop="creatorUserTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.creatorUserTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="团购活动状态" align="center" prop="status" />
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
            v-hasPermi="['shop:groupon:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:groupon:remove']"
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

    <!-- 添加或修改团购活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联的订单ID" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入关联的订单ID" />
        </el-form-item>
        <el-form-item label="如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID" prop="grouponId">
          <el-input v-model="form.grouponId" placeholder="请输入如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID" />
        </el-form-item>
        <el-form-item label="团购规则ID，关联litemall_groupon_rules表ID字段" prop="rulesId">
          <el-input v-model="form.rulesId" placeholder="请输入团购规则ID，关联litemall_groupon_rules表ID字段" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="团购分享图片地址" prop="shareUrl">
          <el-input v-model="form.shareUrl" placeholder="请输入团购分享图片地址" />
        </el-form-item>
        <el-form-item label="开团用户ID" prop="creatorUserId">
          <el-input v-model="form.creatorUserId" placeholder="请输入开团用户ID" />
        </el-form-item>
        <el-form-item label="开团时间" prop="creatorUserTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.creatorUserTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开团时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="团购活动状态，开团未支付则0，开团中则1，开团失败则2">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
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
import { listGroupon, getGroupon, delGroupon, addGroupon, updateGroupon, exportGroupon } from "@/api/shop/groupon";

export default {
  name: "Groupon",
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
      // 团购活动表格数据
      grouponList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: undefined,
        grouponId: undefined,
        rulesId: undefined,
        userId: undefined,
        shareUrl: undefined,
        creatorUserId: undefined,
        creatorUserTime: undefined,
        status: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: "关联的订单ID不能为空", trigger: "blur" }
        ],
        rulesId: [
          { required: true, message: "团购规则ID，关联litemall_groupon_rules表ID字段不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        creatorUserId: [
          { required: true, message: "开团用户ID不能为空", trigger: "blur" }
        ],
        addTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询团购活动列表 */
    getList() {
      this.loading = true;
      listGroupon(this.queryParams).then(response => {
        this.grouponList = response.rows;
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
        orderId: undefined,
        grouponId: undefined,
        rulesId: undefined,
        userId: undefined,
        shareUrl: undefined,
        creatorUserId: undefined,
        creatorUserTime: undefined,
        status: "0",
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
      this.title = "添加团购活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getGroupon(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改团购活动";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateGroupon(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addGroupon(this.form).then(response => {
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
      this.$confirm('是否确认删除团购活动编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delGroupon(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有团购活动数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportGroupon(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
