<template>
  <div class="app-container">

    <el-table v-loading="loading" :data="regionList"  element-loading-text="正在查询中。。。" row-key="id"   :tree-props="{children: 'children', hasChildren: 'hasChildren'}" style="width: 100%;margin-bottom: 20px;" border="">

      <el-table-column label="区域名称" prop="name"/>

      <el-table-column label="区域类型" prop="type">
        <template slot-scope="scope">
          {{ scope.row.type | typeFilter }}
        </template>
      </el-table-column>

      <el-table-column label="区域编码" prop="code"/>
    </el-table>

  </div>
</template>

<script>
import { listRegion, getRegion, delRegion, addRegion, updateRegion, exportRegion } from "@/api/shop/region";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "Region",
  components: { Treeselect },
  filters: {
    typeFilter(status) {
      const typeMap = {
        '1': '省',
        '2': '市',
        '3': '区'
      }
      return typeMap[status]
    }
  },
  data() {
    return {
      // 选中数组
      // 行政区域表格数据
      regionList: [],
      loading: true,
      downloadLoading: false

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询行政区域列表 */
    getList() {
      this.loading = true;
      listRegion(this.queryParams).then(response => {
        this.regionList = this.handleTree(response.data, "id");

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
        pid: undefined,
        name: undefined,
        type: undefined,
        code: undefined
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
      this.title = "添加行政区域";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRegion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改行政区域";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateRegion(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addRegion(this.form).then(response => {
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
      this.$confirm('是否确认删除行政区域编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRegion(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有行政区域数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRegion(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
