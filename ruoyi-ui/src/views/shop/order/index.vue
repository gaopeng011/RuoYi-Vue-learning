<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="用户表的用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户表的用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单编号" prop="orderSn">
        <el-input
          v-model="queryParams.orderSn"
          placeholder="请输入订单编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="收货人名称" prop="consignee">
        <el-input
          v-model="queryParams.consignee"
          placeholder="请输入收货人名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货人手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入收货人手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货具体地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入收货具体地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品总费用" prop="goodsPrice">
        <el-input
          v-model="queryParams.goodsPrice"
          placeholder="请输入商品总费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配送费用" prop="freightPrice">
        <el-input
          v-model="queryParams.freightPrice"
          placeholder="请输入配送费用"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="优惠券减免" prop="couponPrice">
        <el-input
          v-model="queryParams.couponPrice"
          placeholder="请输入优惠券减免"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户积分减免" prop="integralPrice">
        <el-input
          v-model="queryParams.integralPrice"
          placeholder="请输入用户积分减免"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="团购优惠价减免" prop="grouponPrice">
        <el-input
          v-model="queryParams.grouponPrice"
          placeholder="请输入团购优惠价减免"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单费用， = goods_price + freight_price - coupon_price" prop="orderPrice">
        <el-input
          v-model="queryParams.orderPrice"
          placeholder="请输入订单费用， = goods_price + freight_price - coupon_price"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实付费用， = order_price - integral_price" prop="actualPrice">
        <el-input
          v-model="queryParams.actualPrice"
          placeholder="请输入实付费用， = order_price - integral_price"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信付款编号" prop="payId">
        <el-input
          v-model="queryParams.payId"
          placeholder="请输入微信付款编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信付款时间" prop="payTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.payTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择微信付款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发货编号" prop="shipSn">
        <el-input
          v-model="queryParams.shipSn"
          placeholder="请输入发货编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发货快递公司" prop="shipChannel">
        <el-input
          v-model="queryParams.shipChannel"
          placeholder="请输入发货快递公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发货开始时间" prop="shipTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.shipTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择发货开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际退款金额，" prop="refundAmount">
        <el-input
          v-model="queryParams.refundAmount"
          placeholder="请输入实际退款金额，"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款方式" prop="refundType">
        <el-select v-model="queryParams.refundType" placeholder="请选择退款方式" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="退款备注" prop="refundContent">
        <el-input
          v-model="queryParams.refundContent"
          placeholder="请输入退款备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="退款时间" prop="refundTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.refundTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择退款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="用户确认收货时间" prop="confirmTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.confirmTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择用户确认收货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="待评价订单商品数量" prop="comments">
        <el-input
          v-model="queryParams.comments"
          placeholder="请输入待评价订单商品数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单关闭时间" prop="endTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择订单关闭时间">
        </el-date-picker>
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
          v-hasPermi="['shop:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:order:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="逻辑删除" align="center" prop="id" />
      <el-table-column label="用户表的用户ID" align="center" prop="userId" />
      <el-table-column label="订单编号" align="center" prop="orderSn" />
      <el-table-column label="订单状态" align="center" prop="orderStatus" />
      <el-table-column label="收货人名称" align="center" prop="consignee" />
      <el-table-column label="收货人手机号" align="center" prop="mobile" />
      <el-table-column label="收货具体地址" align="center" prop="address" />
      <el-table-column label="用户订单留言" align="center" prop="message" />
      <el-table-column label="商品总费用" align="center" prop="goodsPrice" />
      <el-table-column label="配送费用" align="center" prop="freightPrice" />
      <el-table-column label="优惠券减免" align="center" prop="couponPrice" />
      <el-table-column label="用户积分减免" align="center" prop="integralPrice" />
      <el-table-column label="团购优惠价减免" align="center" prop="grouponPrice" />
      <el-table-column label="订单费用， = goods_price + freight_price - coupon_price" align="center" prop="orderPrice" />
      <el-table-column label="实付费用， = order_price - integral_price" align="center" prop="actualPrice" />
      <el-table-column label="微信付款编号" align="center" prop="payId" />
      <el-table-column label="微信付款时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发货编号" align="center" prop="shipSn" />
      <el-table-column label="发货快递公司" align="center" prop="shipChannel" />
      <el-table-column label="发货开始时间" align="center" prop="shipTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shipTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际退款金额，" align="center" prop="refundAmount" />
      <el-table-column label="退款方式" align="center" prop="refundType" />
      <el-table-column label="退款备注" align="center" prop="refundContent" />
      <el-table-column label="退款时间" align="center" prop="refundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refundTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户确认收货时间" align="center" prop="confirmTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.confirmTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="待评价订单商品数量" align="center" prop="comments" />
      <el-table-column label="订单关闭时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['shop:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:order:remove']"
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户表的用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户表的用户ID" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderSn">
          <el-input v-model="form.orderSn" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="订单状态">
          <el-radio-group v-model="form.orderStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="收货人名称" prop="consignee">
          <el-input v-model="form.consignee" placeholder="请输入收货人名称" />
        </el-form-item>
        <el-form-item label="收货人手机号" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入收货人手机号" />
        </el-form-item>
        <el-form-item label="收货具体地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入收货具体地址" />
        </el-form-item>
        <el-form-item label="用户订单留言" prop="message">
          <el-input v-model="form.message" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商品总费用" prop="goodsPrice">
          <el-input v-model="form.goodsPrice" placeholder="请输入商品总费用" />
        </el-form-item>
        <el-form-item label="配送费用" prop="freightPrice">
          <el-input v-model="form.freightPrice" placeholder="请输入配送费用" />
        </el-form-item>
        <el-form-item label="优惠券减免" prop="couponPrice">
          <el-input v-model="form.couponPrice" placeholder="请输入优惠券减免" />
        </el-form-item>
        <el-form-item label="用户积分减免" prop="integralPrice">
          <el-input v-model="form.integralPrice" placeholder="请输入用户积分减免" />
        </el-form-item>
        <el-form-item label="团购优惠价减免" prop="grouponPrice">
          <el-input v-model="form.grouponPrice" placeholder="请输入团购优惠价减免" />
        </el-form-item>
        <el-form-item label="订单费用， = goods_price + freight_price - coupon_price" prop="orderPrice">
          <el-input v-model="form.orderPrice" placeholder="请输入订单费用， = goods_price + freight_price - coupon_price" />
        </el-form-item>
        <el-form-item label="实付费用， = order_price - integral_price" prop="actualPrice">
          <el-input v-model="form.actualPrice" placeholder="请输入实付费用， = order_price - integral_price" />
        </el-form-item>
        <el-form-item label="微信付款编号" prop="payId">
          <el-input v-model="form.payId" placeholder="请输入微信付款编号" />
        </el-form-item>
        <el-form-item label="微信付款时间" prop="payTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择微信付款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发货编号" prop="shipSn">
          <el-input v-model="form.shipSn" placeholder="请输入发货编号" />
        </el-form-item>
        <el-form-item label="发货快递公司" prop="shipChannel">
          <el-input v-model="form.shipChannel" placeholder="请输入发货快递公司" />
        </el-form-item>
        <el-form-item label="发货开始时间" prop="shipTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.shipTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发货开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际退款金额，" prop="refundAmount">
          <el-input v-model="form.refundAmount" placeholder="请输入实际退款金额，" />
        </el-form-item>
        <el-form-item label="退款方式">
          <el-select v-model="form.refundType" placeholder="请选择退款方式">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="退款备注" prop="refundContent">
          <el-input v-model="form.refundContent" placeholder="请输入退款备注" />
        </el-form-item>
        <el-form-item label="退款时间" prop="refundTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.refundTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择退款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户确认收货时间" prop="confirmTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.confirmTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择用户确认收货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="待评价订单商品数量" prop="comments">
          <el-input v-model="form.comments" placeholder="请输入待评价订单商品数量" />
        </el-form-item>
        <el-form-item label="订单关闭时间" prop="endTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择订单关闭时间">
          </el-date-picker>
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/shop/order";

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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        orderSn: undefined,
        orderStatus: undefined,
        consignee: undefined,
        mobile: undefined,
        address: undefined,
        message: undefined,
        goodsPrice: undefined,
        freightPrice: undefined,
        couponPrice: undefined,
        integralPrice: undefined,
        grouponPrice: undefined,
        orderPrice: undefined,
        actualPrice: undefined,
        payId: undefined,
        payTime: undefined,
        shipSn: undefined,
        shipChannel: undefined,
        shipTime: undefined,
        refundAmount: undefined,
        refundType: undefined,
        refundContent: undefined,
        refundTime: undefined,
        confirmTime: undefined,
        comments: undefined,
        endTime: undefined,
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
        orderSn: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
        orderStatus: [
          { required: true, message: "订单状态不能为空", trigger: "blur" }
        ],
        consignee: [
          { required: true, message: "收货人名称不能为空", trigger: "blur" }
        ],
        mobile: [
          { required: true, message: "收货人手机号不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "收货具体地址不能为空", trigger: "blur" }
        ],
        message: [
          { required: true, message: "用户订单留言不能为空", trigger: "blur" }
        ],
        goodsPrice: [
          { required: true, message: "商品总费用不能为空", trigger: "blur" }
        ],
        freightPrice: [
          { required: true, message: "配送费用不能为空", trigger: "blur" }
        ],
        couponPrice: [
          { required: true, message: "优惠券减免不能为空", trigger: "blur" }
        ],
        integralPrice: [
          { required: true, message: "用户积分减免不能为空", trigger: "blur" }
        ],
        grouponPrice: [
          { required: true, message: "团购优惠价减免不能为空", trigger: "blur" }
        ],
        orderPrice: [
          { required: true, message: "订单费用， = goods_price + freight_price - coupon_price不能为空", trigger: "blur" }
        ],
        actualPrice: [
          { required: true, message: "实付费用， = order_price - integral_price不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        userId: undefined,
        orderSn: undefined,
        orderStatus: "0",
        consignee: undefined,
        mobile: undefined,
        address: undefined,
        message: undefined,
        goodsPrice: undefined,
        freightPrice: undefined,
        couponPrice: undefined,
        integralPrice: undefined,
        grouponPrice: undefined,
        orderPrice: undefined,
        actualPrice: undefined,
        payId: undefined,
        payTime: undefined,
        shipSn: undefined,
        shipChannel: undefined,
        shipTime: undefined,
        refundAmount: undefined,
        refundType: undefined,
        refundContent: undefined,
        refundTime: undefined,
        confirmTime: undefined,
        comments: undefined,
        endTime: undefined,
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
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>