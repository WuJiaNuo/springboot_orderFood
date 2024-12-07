<template>
  <div class="app-container">
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="5">
            <el-form-item label="标题">
              <el-input v-model="searchObj.title" placeholder="标题" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="备注">
              <el-input v-model="searchObj.remark" placeholder="备注" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="部门">
              <el-input v-model="searchObj.deptName"
                        placeholder="部门名称" clearable
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchData()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>
    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" icon="el-icon-plus" size="mini" @click="add"
                 :disabled="$hasBP('bnt.Exam.add')  === false"
      >添 加
      </el-button>
      <el-button class="btn-add" size="mini" @click="batchRemove()" :disabled="$hasBP('bnt.Exam.remove')  === false">
        批量删除
      </el-button>
      <el-button style="margin-left:8px" size="mini" :disabled="$hasBP('bnt.Exam.list')  === false" type="primary"
                 @click="exportData"
      >导出数据
      </el-button>
    </div>
    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      :row-key="getRowKeys"
      stripe
      border
      style="width: 100%;margin-top: 10px;"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        label="序号"
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column type="selection" :reserve-selection="true"/>
      <el-table-column sortable prop="deptName" label="部门名称" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="title" label="标题" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="typeName" label="试卷类型" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="getTypeName" label="选题方式" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="duration" label="时长" show-overflow-tooltip="true"/>
      <!--      <el-table-column sortable prop="criteria" label="及格标准" show-overflow-tooltip="true"/>
            <el-table-column sortable prop="startTime" label="开始时间" show-overflow-tooltip="true"/>
            <el-table-column sortable prop="endTime" label="结束时间" show-overflow-tooltip="true"/>
            <el-table-column sortable prop="remark" label="备注" show-overflow-tooltip="true"/>-->
      <!--      <el-table-column sortable prop="createTime" label="创建时间" show-overflow-tooltip="true"/>-->
      <el-table-column type="expand" label="...">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="部门名称">
              <span>{{ props.row.deptName }}</span>
            </el-form-item>
            <el-form-item label="标题">
              <span>{{ props.row.title }}</span>
            </el-form-item>
            <el-form-item label="试卷类型">
              <span>{{ props.row.typeName }}</span>
            </el-form-item>
            <el-form-item label="选题方式">
              <span>{{ props.row.getTypeName }}</span>
            </el-form-item>
            <el-form-item label="时长">
              <span>{{ props.row.duration }}</span>
            </el-form-item>
            <el-form-item label="及格标准">
              <span>{{ props.row.criteria }}</span>
            </el-form-item>
            <el-form-item label="开始时间">
              <span>{{ props.row.startTime }}</span>
            </el-form-item>
            <el-form-item label="结束时间">
              <span>{{ props.row.endTime }}</span>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="修改时间">
              <span>{{ props.row.updateTime }}</span>
            </el-form-item>
            <el-form-item label="题目内容">
              <span><TbQuestionView :examId="props.row.id"></TbQuestionView></span>
            </el-form-item>
            <el-form-item label="已分配用户">
              <span><SysUserView :examId="props.row.id"></SysUserView></span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)"
                     :disabled="$hasBP('bnt.Exam.update')  === false" title="修改"
          />
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)"
                     :disabled="$hasBP('bnt.Exam.remove')  === false" title="删除"
          />
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      @size-change="sizeChangeHandle"
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      :page-sizes="[5, 10, 50, 100]"
      layout="total,sizes, prev, pager, next, jumper"
      @current-change="fetchData"
    />
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="tbExam" :rules="rules" label-width="100px" size="small"
               style="padding-right: 40px;"
      >
        <el-form-item label="部门" prop="deptId">
          <select-dept-tree ref="zdept" @saveTreeId="saveDeptId" treeTitle="选择部门" :isLazy="false"
                            :dataTree="dataDeptTree"
                            :treeVal="tbExam.deptName" :treeId="tbExam.deptId"
          ></select-dept-tree>
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="tbExam.title" clearable/>
        </el-form-item>
        <el-form-item label="试卷类型" prop="type">
          <el-radio-group v-model="tbExam.type" size="small">
            <el-radio v-for="l in sysDicItemXdicCode5000List" :label="l.code">{{ l.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="选题方式" prop="getType">
          <el-radio-group v-model="tbExam.getType" size="small">
            <el-radio v-for="l in sysDicItemXdicCode6000List" :label="l.code">{{ l.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item id="inputNum" label="随机抽题数量" prop="getNum" v-if="tbExam.getType=='6002'">
          <el-input-number :min="0" v-model="tbExam.getNum" controls-position="right" clearable/>
        </el-form-item>
        <el-form-item label="时长(分)" prop="duration">
          <el-input-number :min="0" v-model="tbExam.duration" controls-position="right" clearable/>
        </el-form-item>
        <el-form-item label="及格标准" prop="criteria">
          <el-input-number :min="0" v-model="tbExam.criteria" controls-position="right" clearable/>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="tbExam.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="tbExam.remark" clearable/>
        </el-form-item>
        <el-form-item label="选择题目" prop="questionId">
          <TbQuestionSelect
            :myvals="tbExam.questionIdList"
            @getInfo="getTbQuestionX28f007e1" :single="false" where=""
          />
        </el-form-item>
        <el-form-item label="分配用户" prop="userId">
          <SysUserSelect
            :myvals="tbExam.userIdList"
            @getInfo="getSysUserX04bb5120" :single="false" where=""
          />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
import api from '@/api/Exam'
import sysDeptApi from '@/api/system/sysDept'
import TbQuestionSelect from './ExamTbQuestionSelect'
import TbQuestionView from './ExamTbQuestionView'

import SysUserSelect from './ExamSysUserSelect'
import SysUserView from './ExamSysUserView'
import sysDicItemApi from '@/api/system/sysDicItem'

const defaultForm = {}
export default {
  components: {
    TbQuestionSelect,
    TbQuestionView,
    SysUserSelect,
    SysUserView,
    SelectDeptTree: () => import('@/components/Custom/TreeSelect/TreeSelect')
  },
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: [], // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 5, // 每页记录数
      searchObj: {}, // 查询表单对象
      dialogVisible: false,
      tbExam: defaultForm,
      saveBtnDisabled: false,
      curDept: '',
      curDeptName: '',
      selectValue: [] //复选框选择内容封装数组
      , dataDeptTree: []//树形选择数据
      , sysDicItemXdicCode5000List: []
      , sysDicItemXdicCode6000List: []
      , rules: {

        title:
          [
            { required: true, message: '请输入标题', trigger: 'blur' }
          ]
        ,
        type:
          [
            { required: true, message: '请选择试卷类型', trigger: 'blur' }
          ]
        ,
        getType:
          [
            { required: true, message: '请选择选题方式', trigger: 'blur' }
          ]
        ,
        duration:
          [
            { required: true, message: '请输入时长', trigger: 'blur' }
          ]
        ,
        criteria:
          [
            { required: true, message: '请输入及格标准', trigger: 'blur' }
          ]
        ,
        deptId:
          [
            { required: true, message: '请输入部门', trigger: 'blur' }
          ]
        ,
        startTime:
          [
            { required: true, message: '请输入开始时间', trigger: 'blur' }
          ]
        ,
        endTime:
          [
            { required: true, message: '请输入结束时间', trigger: 'blur' }
          ]
      }
    }
  },
  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    console.log('list created......')
    this.fetchData()
    this.fetchPopDept()
    this.getAllSysDicItemXdicCode5000()
    this.getAllSysDicItemXdicCode6000()
  },
  // 生命周期函数：内存准备完毕，页面渲染成功
  mounted() {
    console.log('list mounted......')
  },
  methods: {
    getRowKeys(row) {
      return row.id
    },
    getAllSysDicItemXdicCode5000() {
      let queryObj = {}
      queryObj = JSON.parse('{"dicCode":"5000"}')
      sysDicItemApi.getAllSysDicItem(queryObj)
        .then(response => {
          this.sysDicItemXdicCode5000List = response.data
        })
    },
    getAllSysDicItemXdicCode6000() {
      let queryObj = {}
      queryObj = JSON.parse('{"dicCode":"6000"}')
      sysDicItemApi.getAllSysDicItem(queryObj)
        .then(response => {
          this.sysDicItemXdicCode6000List = response.data
        })
    },
    //导出数据
    exportData() {
      window.open(process.env.VUE_APP_BASE_API + '/Exam/export?token=' + getToken())
    },
    // 每页数
    sizeChangeHandle(val) {
      this.limit = val
      this.page = 1
      this.fetchData(this.page)
    },
    getTbQuestionX28f007e1(selections) {
      this.tbExam.questionIdList = selections
    },
    getSysUserX04bb5120(selections) {
      this.tbExam.userIdList = selections
    },
    //回调保存选择的部门id
    saveDeptId(treeId, treeName) {
      this.tbExam.deptId = treeId
      this.$refs['dataForm'].clearValidate('deptId')
      this.rules.deptId = []
    },
    fetchPopDept() {
      //加载弹出选择部门数据
      sysDeptApi.findSelectNodes().then(response => {
        this.dataDeptTree = response.data
      })
    },
    // 加载banner列表数据
    fetchData(page = 1) {
      debugger
      this.page = page
      api.getPageList(this.page, this.limit, this.searchObj).then(
        response => {
          //this.list = response.data.list
          this.list = response.data.pageModel.records
          this.total = response.data.pageModel.total
          this.curDept = response.data.curDept
          this.curDeptName = response.data.curDeptName
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    // 重置查询表单
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {}
      this.fetchData()
    },
    // 根据id删除数据
    removeDataById(id) {
      // debugger
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return api.removeById(id)
      }).then((response) => {
        this.fetchData(this.page)
        this.$message.success(response.message || '删除成功')
      }).catch(() => {
        this.$message.info('取消删除')
      })
    },
    //复选框发生变化执行方法
    handleSelectionChange(selection) {
      this.selectValue = selection
      //console.log(this.selectValue)
    },
    //批量删除
    batchRemove() {
      //判断
      if (this.selectValue.length == 0) {
        this.$message.warning('请选择要删除的记录！')
        return
      }
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //数组
        var idList = []
        //获取多个复选框对应id，封装到数组里面
        // [1,2,3]
        for (var i = 0; i < this.selectValue.length; i++) {
          var obj = this.selectValue[i]
          //id值
          var id = obj.id
          //放到数组里面
          idList.push(id)
        }
        api.batchRemove(idList).then(response => {
          //提示
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          //刷新
          this.fetchData()
        })
      })
    },
    //弹出添加表单
    add(row) {
      this.dialogVisible = true

      this.rules.title = [{ required: true, message: '请输入标题', trigger: 'blur' }]
      this.rules.type = [{ required: true, message: '请选择试卷类型', trigger: 'blur' }]
      this.rules.getType = [{ required: true, message: '请选择选题方式', trigger: 'blur' }]
      this.rules.duration = [{ required: true, message: '请输入时长', trigger: 'blur' }]
      this.rules.criteria = [{ required: true, message: '请输入及格标准', trigger: 'blur' }]
      this.rules.deptId = [{ required: true, message: '请输入部门', trigger: 'blur' }]
      this.rules.startTime = [{ required: true, message: '请输入开始时间', trigger: 'blur' }]
      this.rules.endTime = [{ required: true, message: '请输入结束时间', trigger: 'blur' }]

      this.$nextTick(function() {
        this.$refs.zdept.reset_data()
        this.tbExam = { deptId: this.curDept, deptName: this.curDeptName }
      })
    },
    //编辑
    edit(id) {
      this.dialogVisible = true
      this.rules.title = [{ required: true, message: '请输入标题', trigger: 'blur' }]
      this.rules.type = [{ required: true, message: '请选择试卷类型', trigger: 'blur' }]
      this.rules.getType = [{ required: true, message: '请选择选题方式', trigger: 'blur' }]
      this.rules.duration = [{ required: true, message: '请输入时长', trigger: 'blur' }]
      this.rules.criteria = [{ required: true, message: '请输入及格标准', trigger: 'blur' }]
      this.rules.deptId = [{ required: true, message: '请输入部门', trigger: 'blur' }]
      this.rules.startTime = [{ required: true, message: '请输入开始时间', trigger: 'blur' }]
      this.rules.endTime = [{ required: true, message: '请输入结束时间', trigger: 'blur' }]
      api.getById(id).then(response => {
        this.$nextTick(function() {
          this.tbExam = response.data
          this.$refs.zdept.reset_data(this.tbExam.deptName)
          if (this.$refs.eltreeDept) {
            this.$refs.eltreeDept.setCheckedKeys(this.tbExam.deptIdList)
          }
        })
      })
    },
    //添加或更新
    saveOrUpdate() {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          if (!this.tbExam.id) {
            this.save()
          } else {
            this.update()
          }
        }
      })
    },
    //添加
    save() {
      api.save(this.tbExam).then(response => {
        this.$message.success('操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },
    //更新
    update() {
      api.updateById(this.tbExam).then(response => {
        this.$message.success(response.message || '操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    }
  }
}
</script>
<style>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
