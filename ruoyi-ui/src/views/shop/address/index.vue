<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="收货人名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入收货人名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="手机号码" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入手机号码"
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
          v-hasPermi="['shop:address:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:address:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="addressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收货人名称" align="center" prop="name" />
      <el-table-column label="手机号码" align="center" prop="tel" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="行政省/市/县区" align="center" min-width="300px" >
        <template slot-scope="scope">
          {{ scope.row.provinceName + scope.row.cityName + scope.row.countyName }}
        </template>
      </el-table-column>
      <el-table-column label="详细收货地址" align="center" prop="addressDetail" />
      <el-table-column label="地区编码" align="center" prop="areaCode" />
      <el-table-column label="邮政编码" align="center" prop="postalCode" />

      <el-table-column label="默认" align="center" prop="isDefault" >
        <template slot-scope="scope">
          {{ scope.row.isDefault==1 ? '是' : '否' }}
        </template>
      </el-table-column>
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
            v-hasPermi="['shop:address:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['shop:address:remove']"
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

    <!-- 添加或修改收货地址对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收货人名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入收货人名称" />
        </el-form-item>
        <el-form-item label="手机号码" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="省" prop="province">
          <el-select v-model="form.province"  value-key="id" a @change="onLevel1Change" placeholder="请输入行政区域表的省ID">
            <el-option label="请选择" value=""/>
            <el-option     v-for="item in provinceList"  :key="item.id+''"   :label="item.name"   :value="item.id+''"/>

          </el-select>
        </el-form-item>

        <el-form-item label="市" prop="city">
          <el-select v-model="form.city" @change="onLevel2Change" placeholder="请输入行政区域表的市ID">
            <el-option label="请选择" value=""/>
            <el-option v-for="item in cityList"  :key="item.id+''"   :label="item.name"   :value="item.id+''"/>
          </el-select>
        </el-form-item>
        <el-form-item label="区/县" prop="county">
          <el-select v-model="form.county"  placeholder="请输入行政区域表的区县ID">
            <el-option label="请选择" value=""/>
            <el-option v-for="item in countyList"  :key="item.id+''"   :label="item.name"   :value="item.id+''"/>
          </el-select>
        </el-form-item>
        <el-form-item label="详细收货地址" prop="addressDetail">
          <el-input v-model="form.addressDetail" placeholder="请输入详细收货地址" />
        </el-form-item>
        <el-form-item label="地区编码" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入地区编码" />
        </el-form-item>
        <el-form-item label="邮政编码" prop="postalCode">
          <el-input v-model="form.postalCode" placeholder="请输入邮政编码" />
        </el-form-item>

        <el-form-item label="是否默认地址" prop="isDefault">
          <el-select v-model="form.isDefault"  placeholder="请输入是否默认地址">
            <el-option label="是" value="1"/>
            <el-option label="否" value="0"/>
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
import { listAddress, getAddress, delAddress, addAddress, updateAddress, exportAddress } from "@/api/shop/address";
import { listRegionJl } from "@/api/shop/region";
export default {
  name: "Address",
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
      // 收货地址表格数据
      addressList: [],
      provinceList:[],
      cityList:[],
      countyList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        userId: undefined,
        province: undefined,
        city: undefined,
        county: undefined,
        provinceName: undefined,
        cityName: undefined,
        countyName: undefined,
        addressDetail: undefined,
        areaCode: undefined,
        postalCode: undefined,
        tel: undefined,
        isDefault: undefined,
        addTime: undefined,
        deleted: undefined
      },
      queryProvince: {
        pid:0,
        type:1
      },
      queryCity: {
        pid:undefined,
        type:2
      },
      queryCounty: {
        pid:undefined,
        type:3
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "收货人名称不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户表的用户ID不能为空", trigger: "blur" }
        ],
        province: [
          { required: true, message: "行政区域表的省ID不能为空", trigger: "blur" }
        ],
        city: [
          { required: true, message: "行政区域表的市ID不能为空", trigger: "blur" }
        ],
        county: [
          { required: true, message: "行政区域表的区县ID不能为空", trigger: "blur" }
        ],
        addressDetail: [
          { required: true, message: "详细收货地址不能为空", trigger: "blur" }
        ],
        tel: [
          { required: true, message: "手机号码不能为空", trigger: "blur" }
        ],
        isDefault: [
          { required: true, message: "是否默认地址不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getRegionListL1();
    this.onLevel1Change();
    this.onLevel2Change();
  },
  methods: {
    /** 查询收货地址列表 */
    getList() {
      this.loading = true;
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    getRegionListL1(){
      listRegionJl(this.queryProvince).then(response => {
        this.provinceList=response.data

      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    onLevel1Change: function(value) {
        this.queryCity.pid = value
      listRegionJl(this.queryCity).then(response => {
        this.cityList=response.data
      });
    },

    onLevel2Change: function(value) {
      this.queryCounty.pid = value
      listRegionJl(this.queryCounty).then(response => {
        this.countyList=response.data
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        userId: undefined,
        province: undefined,
        city: undefined,
        county: undefined,
        addressDetail: undefined,
        areaCode: undefined,
        postalCode: undefined,
        tel: undefined,
        isDefault: undefined,
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
      this.title = "添加收货地址";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAddress(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收货地址";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAddress(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAddress(this.form).then(response => {
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
      this.$confirm('是否确认删除收货地址编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAddress(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有收货地址数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAddress(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
