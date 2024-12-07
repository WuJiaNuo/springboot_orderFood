<template>
  <div>
    <el-dialog
      title="分配用户"
      width="1000px"
      :close-on-click-modal="false"
      :append-to-body="true"
      v-dialogDrag
      :visible.sync="visible">
           <span slot="title" class="dialog-footer">
          <el-button
            size="small"
            @click="visible = false"
            icon="el-icon-circle-close"
          >关闭</el-button>
          <el-button
            size="small"
            type="primary"
            icon="el-icon-circle-check"
            @click="doSubmit()"
          >确定</el-button>
        </span>
      <div>
        <div class="search-div" style="height: 55px;margin-top: -25px">
          <el-form
            size="small"
            ref="searchForm"
            :model="searchForm">
            <el-row>
              <el-col :span="5">
                <el-form-item prop="username">
                  <el-input
                    size="small"
                    v-model="searchForm.username"
                    placeholder="用户名"
                    @input="changeInput($event)"
                    clearable>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5"  style="margin-left: 5px">
                <el-form-item prop="name">
                  <el-input
                    size="small"
                    v-model="searchForm.name"
                    placeholder="姓名"
                    @input="changeInput($event)"
                    clearable>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5"  style="margin-left: 5px">
                <el-form-item prop="phone">
                  <el-input
                    size="small"
                    v-model="searchForm.phone"
                    placeholder="手机"
                    @input="changeInput($event)"
                    clearable>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5"  style="margin-left: 5px">
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="refreshList()"
                    size="small"
                    icon="el-icon-search">搜索
                  </el-button>
                  <el-button
                    @click="resetSearch()"
                    size="small"
                    icon="el-icon-refresh-right">重置
                  </el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <el-table
          :data="dataList"
          v-loading="loading"
          size="small"
          stripe
          border
          ref="sysUserTable"
          :row-key="getRowKeys"
          @select="handleSelectionChange"
          @select-all="handleSelectionChange"
          @selection-change="propertySelectionChange"
          style="width: 100%;margin-top: 10px;">
          <el-table-column
            type="selection"
            :reserve-selection="true"
            header-align="center"
            align="center"
            width="50"
          >
          </el-table-column>
          <el-table-column sortable prop="deptName" label="部门名称" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="username" label="用户名" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="name" label="姓名" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="phone" label="手机" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="postName" label="岗位" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="description" label="描述" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="status" label="状态" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="createTime" label="创建时间" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="updateTime" label="更新时间" show-overflow-tooltip="true"/>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageNo"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pageSize"
          :total="total"
          style="padding: 30px 0; text-align: center;"
          layout="total, sizes, prev, pager, next, jumper"
        >
        </el-pagination>

      </div>

    </el-dialog>
  </div>
</template>
<script>
import sysUserApi from '@/api/system/sysUser'

export default {
  data() {
    return {
      searchForm: {},
      dataListAllSelections: [], // 所有选中的数据包含跨页数据
      idKey: "id", // 标识列表数据中每一行的唯一键的名称(需要按自己的数据改一下)
      dataList: [],
      pageNo: 1,
      pageSize: 5,
      total: 0,
      loading: false,
      visible: false,
    };
  },
  props: {
    selectData: {
      type: Array,
      default: () => {
        return [];
      },
    },
    // 是否启用单选
    single: {
      type: Boolean,
      default: false
    },
    where: {
      type: String,
      default: ''
    }
  },
  methods: {
    changeInput() {
      this.$forceUpdate();
    },
    getRowKeys(row) {
      return row.id
    },

    init() {
      this.visible = true;
      this.$nextTick(() => {
        this.dataListAllSelections = JSON.parse(JSON.stringify(this.selectData));
        this.$refs.sysUserTable.clearSelection()
        this.resetSearch();
      });
    },
    // 获取数据列表
    refreshList() {
      this.loading = true;
      if (this.where != null && this.where != '') {
        let queryObj = JSON.parse(this.where)
        this.searchForm = Object.assign(queryObj, this.searchForm)
      }
      sysUserApi.getPageList(this.pageNo, this.pageSize, this.searchForm).then(
        response => {
          //this.list = response.data.list
          this.dataList = response.data.records
          this.total = response.data.total
          if (this.selectData && this.selectData.length <= 10000) {
            //test begin
            this.$nextTick(function () {
              let arr = []
              // this.$refs.studentTable.clearSelection()
              console.log('selectData')
              console.log(this.selectData)
              this.dataList.forEach((item) => {
                this.selectData.forEach(val => {  //遍历接口返回的id数组
                  if (val.id === item.id) {
                    arr.push(item)
                  }
                })
              })
              if (arr) {
                arr.forEach((row) => {
                  this.$refs.sysUserTable.toggleRowSelection(row, true)
                })
              }
            })
            //test end
          }

          // 数据加载并绑定成功
          this.loading = false
        }
      )
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageNo = 1;
      this.refreshList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageNo = val;
      this.refreshList();
    },
    // 排序
    resetSearch() {
      this.$refs.searchForm.resetFields();
      this.refreshList();
    },
    //监听选中
    propertySelectionChange(selection) {
      this.dataListAllSelections = selection
    },
    // 手动选中数据事件
    handleSelectionChange(selection, row) {
      if (row) {
        if (this.selectData && this.selectData.length <= 10000) {
          this.$nextTick(function () {
            let selected = selection.length && selection.indexOf(row) !== -1;
            if (!selected) { //取消选中把默认值移除掉
              this.selectData.forEach((val, index) => {
                if (val.id == row.id) {
                  this.selectData[index] = {id: ''};
                  console.log("has removed " + val.name)
                }
              })

            }
          })
        }
      } else {
        let isCheckAll = this.$refs.sysUserTable.store.states.isAllSelected;
        //反选的时候 移除默认
        if (isCheckAll == false) {
          console.log('点击了反选')
          this.$nextTick(function () {
            if (this.selectData && this.selectData.length <= 10000) {
              this.dataList.forEach((item) => {
                this.selectData.forEach((val, index) => {
                  if (val.id == item.id) {
                    this.selectData[index] = {id: ''};
                    console.log("has removed " + val.name)
                  }
                })
              })
            }
          })
        } else {
          console.log('点击了全选')
        }
      }
    },
    doSubmit() {
      this.visible = false;
      //翻页的问题 反选的时候存在默认值不能保留的问题
      if (this.selectData && this.selectData.length <= 10000) {
        this.$nextTick(function () {
          this.selectData.forEach((item) => {
            let isIn = false;
            this.dataListAllSelections.forEach((val, index) => {
              if (val.id == item.id) {
                isIn = true;
              }
            })
            if (isIn == false && item.id != '') {
              this.dataListAllSelections.push(item);
              console.log("has push")
              console.log(item);
            }
          })
          this.$emit("doSubmit", this.dataListAllSelections);
        })
      }
    },
  },
};
</script>
<style lang="scss">
</style>
