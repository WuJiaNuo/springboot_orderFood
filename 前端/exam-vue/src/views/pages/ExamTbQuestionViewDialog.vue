<template>
  <div>
    <el-dialog
      title="选择题目"
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
                <el-form-item prop="content">
                  <el-input
                    size="small"
                    v-model="searchForm.content"
                    placeholder="题目内容"
                    @input="changeInput($event)"
                    clearable>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5"  style="margin-left: 5px">
                <el-form-item prop="remark">
                  <el-input
                    size="small"
                    v-model="searchForm.remark"
                    placeholder="备注"
                    @input="changeInput($event)"
                    clearable>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5"  style="margin-left: 5px">
                <el-form-item prop="type">
<!--                  <el-radio v-model="searchForm.type"
                            v-for="l in sysDicItemXdicCode4000List"
                            :label="l.code">{{ l.name }}
                  </el-radio>-->
                  <el-select v-model="searchForm.type" @change="changeInput" placeholder="题目类型">
                     <el-option  v-for="l in sysDicItemXdicCode4000List" :label="l.name" :value="l.code" :key="l.code"></el-option>
                  </el-select>
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
          ref="tbQuestionTable"
          style="width: 100%;margin-top: 10px;">
          <el-table-column sortable prop="deptName" label="部门名称" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="content" label="题目内容" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="score" label="题目分数" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="typeName" label="题目类型" show-overflow-tooltip="true"/>
          <el-table-column sortable prop="remark" label="备注" show-overflow-tooltip="true"/>
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
import tbQuestionApi from '@/api/Question'
import sysDicItemApi from "@/api/system/sysDicItem";

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
      sysDicItemXdicCode4000List: []
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
        this.getAllSysDicItemXdicCode4000()
      });
    },
    // 获取数据列表
    refreshList() {
      this.loading = true;
      this.searchForm.examId = this.examId
      tbQuestionApi.getPageList(this.pageNo, this.pageSize, this.searchForm).then(
        response => {
          //this.list = response.data.list
          this.dataList = response.data.pageModel.records
          this.total = response.data.pageModel.total
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
    getAllSysDicItemXdicCode4000() {
      let queryObj = {}
      queryObj = JSON.parse('{"dicCode":"4000"}')
      sysDicItemApi.getAllSysDicItem(queryObj)
        .then(response => {
          this.sysDicItemXdicCode4000List = response.data
        })
    }
  },
};
</script>
<style lang="scss">
</style>
