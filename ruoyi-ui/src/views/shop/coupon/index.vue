<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-input v-model="queryParams.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入优惠券标题"/>
        <el-select v-model="queryParams.type" clearable style="width: 200px" class="filter-item" placeholder="请选择优惠券类型">
          <el-option v-for="type in typeOptions" :key="type.value" :label="type.label" :value="type.value"/>
        </el-select>
        <el-select v-model="queryParams.status" clearable style="width: 200px" class="filter-item" placeholder="请选择优惠券状态">
          <el-option v-for="type in statusOptions" :key="type.value" :label="type.label" :value="type.value"/>
        </el-select>

          <el-button type="primary" icon="el-icon-search" class="filter-item"  @click="handleQuery">搜索</el-button>
          <el-button v-hasPermi="['shop:coupon:add']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleAdd">添加</el-button>
          <el-button icon="el-icon-refresh"  class="filter-item"   @click="resetQuery">重置</el-button>

      </el-form>
    </div>

    <el-table v-loading="loading" :data="couponList" element-loading-text="正在查询中。。。" border fit highlight-current-row @selection-change="handleSelectionChange">
      <el-table-column align="center" label="优惠券ID" prop="id" sortable/>

      <el-table-column align="center" label="优惠券名称" prop="name"/>

      <el-table-column align="center" label="介绍" prop="desc"/>

      <el-table-column align="center" label="标签" prop="tag"/>

      <el-table-column align="center" label="最低消费" prop="min">
        <template slot-scope="scope">满{{ scope.row.min }}元可用</template>
      </el-table-column>

      <el-table-column align="center" label="满减金额" prop="discount">
        <template slot-scope="scope">减免{{ scope.row.discount }}元</template>
      </el-table-column>

      <el-table-column align="center" label="每人限领" prop="limit">
        <template slot-scope="scope">{{ scope.row.limit != 0 ? scope.row.limit : "不限" }}</template>
      </el-table-column>

      <el-table-column align="center" label="商品使用范围" prop="goodsType">
        <template slot-scope="scope">{{ scope.row.goodsType | formatGoodsType }}</template>
      </el-table-column>

      <el-table-column align="center" label="优惠券类型" prop="type">
        <template slot-scope="scope">{{ scope.row.type | formatType }}</template>
      </el-table-column>

      <el-table-column align="center" label="优惠券数量" prop="total">
        <template slot-scope="scope">{{ scope.row.total != 0 ? scope.row.total : "不限" }}</template>
      </el-table-column>

      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">{{ scope.row.status | formatStatus }}</template>
      </el-table-column>
      <el-table-column label="使用券开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用券截至时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center"  width="300" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-hasPermi="['shop:coupon:query']" type="text" size="mini" icon="el-icon-info" @click="handleDetail(scope.row)">详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:coupon:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:coupon:remove']"
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

    <!-- 添加或修改优惠券信息及规则对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="优惠券名称" prop="name">
          <el-input v-model="form.name"/>
        </el-form-item>
        <el-form-item label="介绍" prop="desc">
          <el-input v-model="form.desc"/>
        </el-form-item>
        <el-form-item label="标签" prop="tag">
          <el-input v-model="form.tag"/>
        </el-form-item>
        <el-form-item label="最低消费" prop="min">
          <el-input v-model="form.min">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="满减金额" prop="discount">
          <el-input v-model="form.discount">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="每人限领" prop="limit">
          <el-input v-model="form.limit">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="分发类型" prop="type">
          <el-select v-model="form.type">
            <el-option
              v-for="type in typeOptions"
              :key="type.value"
              :label="type.label"
              :value="type.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="优惠券数量" prop="total">
          <el-input v-model="form.total">
            <template slot="append">张</template>
          </el-input>
        </el-form-item>
        <el-form-item label="有效期">
          <el-radio-group v-model="form.timeType">
            <el-radio-button :label="0">领券相对天数</el-radio-button>
            <el-radio-button :label="1">指定绝对时间</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="form.timeType === 0">
          <el-input v-model="form.days">
            <template slot="append">天</template>
          </el-input>
        </el-form-item>
        <el-form-item v-show="form.timeType === 1">
          <el-col :span="11">
            <el-date-picker v-model="form.startTime" type="datetime" placeholder="选择日期" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%;"/>
          </el-col>
          <el-col :span="2" class="line">至</el-col>
          <el-col :span="11">
            <el-date-picker v-model="form.endTime" type="datetime" placeholder="选择日期" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%;"/>
          </el-col>
        </el-form-item>
        <el-form-item label="商品限制范围">
          <el-radio-group v-model="form.goodsType">
            <el-radio-button :label="0">全场通用</el-radio-button>
            <el-radio-button :label="1">指定分类</el-radio-button>
            <el-radio-button :label="2">指定商品</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-show="form.goodsType === 1">
          目前不支持
        </el-form-item>
        <el-form-item v-show="form.goodsType === 2">
          目前不支持
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
import { listCoupon, getCoupon, delCoupon, addCoupon, updateCoupon, exportCoupon } from "@/api/shop/coupon";
const defaultTypeOptions = [
  {
    label: '通用领券',
    value: 0
  },
  {
    label: '注册赠券',
    value: 1
  },
  {
    label: '兑换码',
    value: 2
  }
]

const defaultStatusOptions = [
  {
    label: '正常',
    value: 0
  },
  {
    label: '已过期',
    value: 1
  },
  {
    label: '已下架',
    value: 2
  }
]
export default {
  name: "Coupon",
  filters: {
    formatType(type) {
      for (let i = 0; i < defaultTypeOptions.length; i++) {
        if (type === defaultTypeOptions[i].value) {
          return defaultTypeOptions[i].label
        }
      }
      return ''
    },
    formatGoodsType(goodsType) {
      if (goodsType === 0) {
        return '全场通用'
      } else if (goodsType === 1) {
        return '指定分类'
      } else {
        return '指定商品'
      }
    },
    formatStatus(status) {
      if (status === 0) {
        return '正常'
      } else if (status === 1) {
        return '已过期'
      } else {
        return '已下架'
      }
    }
  },
  data() {
    return {
      typeOptions: Object.assign({}, defaultTypeOptions),
      statusOptions: Object.assign({}, defaultStatusOptions),

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
      // 优惠券信息及规则表格数据
      couponList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        desc: undefined,
        tag: undefined,
        total: undefined,
        discount: undefined,
        min: undefined,
        limit: undefined,
        type: undefined,
        status: undefined,
        goodsType: undefined,
        goodsValue: [],
        code: undefined,
        timeType: undefined,
        days: undefined,
        startTime: undefined,
        endTime: undefined,
        addTime: undefined,
        deleted: undefined
      },

      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      // 表单参数
      form: {
        id: undefined,
        name: undefined,
        desc: undefined,
        tag: undefined,
        total: 0,
        discount: 0,
        min: 0,
        limit: 1,
        type: 0,
        status: 0,
        goodsType: 0,
        goodsValue: [],
        timeType: 0,
        days: 0,
        startTime: null,
        endTime: null
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "优惠券名称不能为空", trigger: "blur" }
        ],
        total: [
          { required: true, message: "优惠券数量，如果是0，则是无限量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询优惠券信息及规则列表 */
    getList() {
      this.loading = true;
      listCoupon(this.queryParams).then(response => {
        this.couponList = response.rows;
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
        name: undefined,
        desc: undefined,
        tag: undefined,
        total: undefined,
        discount: undefined,
        min: undefined,
        limit: undefined,
        type: undefined,
        status: "0",
        goodsType: undefined,
        goodsValue: undefined,
        code: undefined,
        timeType: undefined,
        days: undefined,
        startTime: undefined,
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
    handleDetail(row) {
      this.$router.push({ path: '/push/couponDetail', query: { id: row.id }})
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加优惠券";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCoupon(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改优惠券";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCoupon(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCoupon(this.form).then(response => {
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
      this.$confirm('是否确认删除编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCoupon(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有优惠券信息及规则数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCoupon(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
