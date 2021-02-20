<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="商品ID" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品表的商品ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
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
          v-hasPermi="['shop:rules:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:rules:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:rules:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="rulesList" @selection-change="handleSelectionChange"  border fit highlight-current-row>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column align="center" label="团购规则ID" prop="id" />

      <el-table-column align="center" label="商品ID" prop="goodsId" />

      <el-table-column align="center" min-width="100" label="名称" prop="goodsName" />

      <el-table-column align="center" property="picUrl" label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.picUrl" width="40">
        </template>
      </el-table-column>

      <el-table-column align="center" label="团购优惠" prop="discount" />

      <el-table-column align="center" label="团购要求" prop="discountMember" />

      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 0 ? 'success' : 'error' ">{{ statusMap[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="结束时间" prop="expireTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['shop:rules:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:rules:remove']"
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

    <!-- 添加或修改团购规则对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="商品ID" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品表的商品ID" >
          <el-button slot="append" icon="el-icon-thumb" @click="queryGoods()">
            选择
          </el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="专柜售价" prop="counterPrice">
          <el-input v-model="form.counterPrice"  readonly placeholder="专柜售价" />
        </el-form-item>
        <el-form-item label="零售价格" prop="retailPrice">
          <el-input v-model="form.retailPrice"   readonly  placeholder="零售价格" />
        </el-form-item>

        <el-form-item label="优惠金额" prop="discount">
          <el-input v-model="form.discount" placeholder="团购折扣" />
        </el-form-item>

        <el-form-item label="团购人数要求" prop="discountMember">
          <el-input v-model="form.discountMember" placeholder="团购人数要求" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expireTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.expireTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择团购过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="规则状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择">
            <el-option :value="0" label="正常上线"/>
            <el-option :value="1" label="到期自动"/>
            <el-option :value="2" label="手动下线"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="addVisiable" title="添加商品">
      <div class="search">
        <el-input v-model="listQuery.goodsSn" clearable class="filter-item" style="width: 200px;" placeholder="请输入商品编号"/>
        <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入商品名称"/>
        <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
        <el-table v-loading="listLoading" :data="list"   element-loading-text="正在查询中。。。" border fit highlight-current-row @selection-change="handleSelectionChange">
          <el-table-column label="" width="65">
            <!--<template slot-scope="scope">-->
              <!--<el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;</el-radio>-->
            <!--</template>-->
          </el-table-column>

          <el-table-column align="center" label="商品ID" prop="id"/>
          <el-table-column align="center" label="商品编号" prop="goodsSn"/>
          <el-table-column align="center" label="专柜价格" prop="counterPrice"/>
          <el-table-column align="center" label="零售价格" prop="retailPrice"/>
          <el-table-column align="center" property="picUrl" label="图片">
            <template slot-scope="scope">
              <img :src="scope.row.picUrl" width="40">
            </template>
          </el-table-column>
          <el-table-column align="center" label="商品名称" prop="name"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="confirmAdd(scope.row)">选择</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="listQuery.pageNum"
          :limit.sync="listQuery.pageSize"
          @pagination="getGoodList"
        />
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listRules, getRules, delRules, addRules, updateRules, exportRules } from "@/api/shop/rules";
import { listGoods,getGoods } from '@/api/shop/goods'
export default {
  name: "Rules",
  data() {
    return {

      templateSelection: '',
      // 遮罩层
      loading: true,
      listLoading: false,
      // 选中数组
      ids: [],
      templateRadio:'',
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 团购规则表格数据
      rulesList: [],

      addVisiable: false,
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      listQuery: {
        pageNum: 1,
        pageSize: 5,

      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsId: undefined,
        goodsName: undefined,
        picUrl: undefined,
        discount: undefined,
        discountMember: undefined,
        expireTime: undefined,
        status: undefined,
        addTime: undefined,
        deleted: undefined
      },
      statusMap: [
        '正常',
        '到期下线',
        '提前下线'
      ],
      // 表单参数
      form: {
        counterPrice:'',
        retailPrice:''
      },
      // 表单校验
      rules: {
        goodsId: [
          { required: true, message: "商品表的商品ID不能为空", trigger: "blur" }
        ],

        discount: [
          { required: true, message: "优惠金额不能为空", trigger: "blur" }
        ],
        discountMember: [
          { required: true, message: "达到优惠条件的人数不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "规则状态", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getGoodList();
  },
  methods: {

    getGoodList() {
      this.listLoading = true
      listGoods(this.listQuery).then(response => {
        this.list = response.rows
        this.total = response.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },

    // singleElection (row) {
    //   this.templateSelection = this.list.indexOf(row);
    //   this.templateRadio = row.id;
    // },
    handleFilter() {
      this.listQuery.pageNum = 1
      this.getGoodList()
    },

    queryGoods() {
      this.listQuery = {
        pageNum: 1,
        pageSize: 5,
        id: undefined,
        name: undefined,
        sort: 'add_time',
        order: 'desc'
      }
      this.list = []
      this.total = 0
      this.addVisiable = true
    },
    /** 查询团购规则列表 */
    getList() {
      this.loading = true;
      listRules(this.queryParams).then(response => {
        this.rulesList = response.rows;
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
        goodsId: undefined,
        goodsName: undefined,
        picUrl: undefined,
        discount: undefined,
        discountMember: undefined,
        expireTime: undefined,
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
      this.title = "添加团购规则";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const goodId=row.goodsId;

      const id = row.id || this.ids
      getRules(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改团购规则";

        getGoods(goodId).then(res => {

          this.form.counterPrice=res.data.counterPrice;
          this.form.retailPrice=res.data.retailPrice
          console.log(this.form)
        });
      });

    },

    confirmAdd(row) {
      this.form.goodsId=row.id;
      this.addVisiable = false;
      this.form.counterPrice=row.counterPrice;
      this.form.retailPrice=row.retailPrice
    },


    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateRules(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addRules(this.form).then(response => {
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
      this.$confirm('是否确认删除团购规则编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRules(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有团购规则数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRules(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
