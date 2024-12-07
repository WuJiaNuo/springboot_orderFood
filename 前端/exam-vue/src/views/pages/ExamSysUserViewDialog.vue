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
          border
          ref="sysUserTable"
          style="width: 100%;margin-top: 10px;">
          <el-table-column sortable prop="deptName" label="部门名称" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="username" label="用户名" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="name" label="姓名" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="phone" label="手机" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="postName" label="岗位名称" show-overflow-tooltip="true"/>
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
    examId: {
      type: String,
      default: ''
    }
  },
  methods: {
    changeInput() {
      this.$forceUpdate();
    },
    init() {
      this.visible = true;
      this.$nextTick(() => {
        this.resetSearch();
      });
    },
    // 获取数据列表
    refreshList() {
      this.loading = true;
      this.searchForm.examId = this.examId
      sysUserApi.getPageList(this.pageNo, this.pageSize, this.searchForm).then(
        response => {
          //this.list = response.data.list
          this.dataList = response.data.records
          this.total = response.data.total
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
    }
  },
};
</script>
<style lang="scss">
</style>
