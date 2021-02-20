<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="支付订单" prop="payOrderId">
        <el-input
          v-model="queryParams.payOrderId"
          placeholder="请输入支付订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="渠道编码" prop="channelId">
        <el-input
          v-model="queryParams.channelId"
          placeholder="请输入渠道编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户订单" prop="mchOrderNo">
        <el-input
          v-model="queryParams.mchOrderNo"
          placeholder="请输入商户订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="支付状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
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

    <el-row :gutter="10" class="mb8" >
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pay:payorder:edit']"
        >退款</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pay:payorder:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="支付订单" width="200" align="center" prop="payOrderId" />
      <el-table-column label="商户订单" align="center" prop="mchOrderNo" :show-overflow-tooltip="true"/>
      <el-table-column label="渠道编码" width="100" align="center" prop="channelId" />
      <el-table-column label="支付金额" align="center" prop="amount" :show-overflow-tooltip="true"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>{{ statusFormat(scope.row,scope.column) }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{statusFormat(scope.row,scope.column)}}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
<!--      <el-table-column label="支付场景" align="center" prop="scene" :show-overflow-tooltip="true"/>-->
<!--      <el-table-column label="授权码" align="center" prop="authCode" :show-overflow-tooltip="true"/>-->
      <el-table-column label="货币代码" align="center" prop="currency" :show-overflow-tooltip="true"/>
      <el-table-column label="商品标题" align="center" prop="subject" :show-overflow-tooltip="true"/>
      <el-table-column label="商品描述" align="center" prop="body" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="120" align="center" fixed="right" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:payorder:edit']"
          >退款</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pay:payorder:edit']"
          >详情</el-button>
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

    <!-- 添加或修改岗位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-form-item label="商户编码" prop="mchId" >
            <el-input v-model="form.mchId" placeholder="请输入商户编码" disabled="true"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="渠道名称" prop="channelName">
              <el-select v-model="form.channelName" placeholder="请选择">
                <el-option
                  v-for="dict in channelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="渠道编码" prop="channelId">
              <el-select v-model="form.channelId" placeholder="请选择">
                <el-option
                  v-for="dict in channelIdOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="商户渠道编码" prop="channelMchId">
          <el-input v-model="form.channelMchId" controls-position="right" placeholder="请输入商户渠道编码" />
        </el-form-item>
        <el-form-item label="商户状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="配置参数" prop="param">
          <el-input v-model="form.param" type="textarea" placeholder="请输入内容" :rows="4"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
  import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/pay/payorder";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Order",
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
        // 订单表格数据
        orderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          payOrderId: undefined,
          channelId: undefined,
          mchOrderNo: undefined,
          status: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          status: [
            { required: true, message: "订单状态不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_payorder_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      /** 查询商户列表 */
      getList() {
        this.loading = true;
        listOrder(this.queryParams).then(response => {
          this.orderList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 订单状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          payOrderId: undefined,
          status: "0",
          mchId: undefined
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
        this.ids = selection.map(item => item.payOrderId)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "查看订单";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        // this.reset();
        // const id = row.id || this.ids
        // getChannel(id).then(response => {
        //   this.form = response.data;
        //   this.open = true;
        //   this.title = "修改渠道";
        // });
      },
      /** 提交按钮 */
      submitForm: function() {
        // this.$refs["form"].validate(valid => {
        //   if (valid) {
        //     if (this.form.id != undefined) {
        //       updateChannel(this.form).then(response => {
        //         if (response.code === 200) {
        //           this.msgSuccess("修改成功");
        //           this.open = false;
        //           this.getList();
        //         } else {
        //           this.msgError(response.msg);
        //         }
        //       });
        //     } else {
        //       addChannel(this.form).then(response => {
        //         if (response.code === 200) {
        //           this.msgSuccess("新增成功");
        //           this.open = false;
        //           this.getList();
        //         } else {
        //           this.msgError(response.msg);
        //         }
        //       });
        //     }
        //   }
        // });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
      //   const ids = row.id || this.ids;
      //   this.$confirm('是否确认删除商户编号为"' + ids + '"的数据项?', "警告", {
      //     confirmButtonText: "确定",
      //     cancelButtonText: "取消",
      //     type: "warning"
      //   }).then(function() {
      //     return delChannel(ids);
      //   }).then(() => {
      //     this.getList();
      //     this.msgSuccess("删除成功");
      //   }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有商户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportChannel(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
