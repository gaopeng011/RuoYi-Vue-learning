<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ID" prop="valueId">
        <el-input
          v-model="queryParams.valueId"
          placeholder="请输入如果type=0，则是商品ID；如果type=1，则是专题ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收藏类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择收藏类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--<el-row :gutter="10" class="mb8">-->
      <!--<el-col :span="1.5">-->
        <!--<el-button-->
          <!--type="primary"-->
          <!--icon="el-icon-plus"-->
          <!--size="mini"-->
          <!--@click="handleAdd"-->
          <!--v-hasPermi="['shop:collect:add']"-->
        <!--&gt;新增</el-button>-->
      <!--</el-col>-->
      <!--<el-col :span="1.5">-->
        <!--<el-button-->
          <!--type="success"-->
          <!--icon="el-icon-edit"-->
          <!--size="mini"-->
          <!--:disabled="single"-->
          <!--@click="handleUpdate"-->
          <!--v-hasPermi="['shop:collect:edit']"-->
        <!--&gt;修改</el-button>-->
      <!--</el-col>-->
      <!--<el-col :span="1.5">-->
        <!--<el-button-->
          <!--type="danger"-->
          <!--icon="el-icon-delete"-->
          <!--size="mini"-->
          <!--:disabled="multiple"-->
          <!--@click="handleDelete"-->
          <!--v-hasPermi="['shop:collect:remove']"-->
        <!--&gt;删除</el-button>-->
      <!--</el-col>-->
      <!--<el-col :span="1.5">-->
        <!--<el-button-->
          <!--type="warning"-->
          <!--icon="el-icon-download"-->
          <!--size="mini"-->
          <!--@click="handleExport"-->
          <!--v-hasPermi="['shop:collect:export']"-->
        <!--&gt;导出</el-button>-->
      <!--</el-col>-->
    <!--</el-row>-->

    <el-table v-loading="loading" :data="collectList" @selection-change="handleSelectionChange">
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="商品ID" align="center" prop="valueId" />
      <el-table-column label="收藏类型" align="center" prop="type" :formatter="typeFormat"/>
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime) }}</span>
        </template>
      </el-table-column>
      <!--<el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
        <!--<template slot-scope="scope">-->
          <!--<el-button-->
            <!--size="mini"-->
            <!--type="text"-->
            <!--icon="el-icon-edit"-->
            <!--@click="handleUpdate(scope.row)"-->
            <!--v-hasPermi="['shop:collect:edit']"-->
          <!--&gt;修改</el-button>-->
          <!--<el-button-->
            <!--size="mini"-->
            <!--type="text"-->
            <!--icon="el-icon-delete"-->
            <!--@click="handleDelete(scope.row)"-->
            <!--v-hasPermi="['shop:collect:remove']"-->
          <!--&gt;删除</el-button>-->
        <!--</template>-->
      <!--</el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改收藏对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户表的用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户表的用户ID" />
        </el-form-item>
        <el-form-item label="如果type=0，则是商品ID；如果type=1，则是专题ID" prop="valueId">
          <el-input v-model="form.valueId" placeholder="请输入如果type=0，则是商品ID；如果type=1，则是专题ID" />
        </el-form-item>
        <el-form-item label="收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID">
          <el-select v-model="form.type" placeholder="请选择收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID">
            <el-option label="请选择字典生成" value="" />
          </el-select>
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
import { listCollect, getCollect, delCollect, addCollect, updateCollect, exportCollect } from "@/api/shop/collect";

export default {
  name: "Collect",
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
      // 收藏表格数据
      collectList: [],
      typeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        valueId: undefined,
        type: undefined,
        addTime: undefined,
        deleted: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户表的用户ID不能为空", trigger: "blur" }
        ],
        valueId: [
          { required: true, message: "如果type=0，则是商品ID；如果type=1，则是专题ID不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("member_collect_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询收藏列表 */
    getList() {
      this.loading = true;
      listCollect(this.queryParams).then(response => {
        this.collectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 参数系统内置字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.configType);
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
        valueId: undefined,
        type: undefined,
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
      this.title = "添加收藏";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCollect(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收藏";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCollect(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCollect(this.form).then(response => {
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
      this.$confirm('是否确认删除收藏编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCollect(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有收藏数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCollect(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
