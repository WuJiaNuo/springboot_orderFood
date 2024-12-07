<template>
  <div class="app-container">
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="5">
            <el-form-item label="题目内容">
              <el-input v-model="searchObj.content" placeholder="题目内容" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="题目类型">
              <!--              <el-radio v-model="searchObj.type"
                                      v-for="l in sysDicItemXdicCode4000List"
                                      :label="l.code">{{ l.name }}
                            </el-radio>-->
              <el-select v-model="searchObj.type" placeholder="题目类型">
                <el-option v-for="l in sysDicItemXdicCode4000List" :label="l.name" :value="l.code"
                           :key="l.code"
                ></el-option>
              </el-select>
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
                 :disabled="$hasBP('bnt.Question.add')  === false"
      >添 加
      </el-button>
      <el-button class="btn-add" size="mini" @click="batchRemove()"
                 :disabled="$hasBP('bnt.Question.remove')  === false"
      >批量删除
      </el-button>

      <!--上传文件-->
      <el-upload
        class="upload-demo"
        action="#"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="1"
        :on-exceed="handleExceed"
        :file-list="fileList"
        :show-file-list="false"
        :http-request="uploadFile"
      >

        <el-button size="mini" :disabled="$hasBP('bnt.sysUser.add')  === false" type="primary">导入数据</el-button>

        <span style="margin-left:4px;color: red" slot="tip" class="el-upload__tip"><a :href="dowTempPath"
                                                                                      target="_blank"
        >下载导入模板</a></span>
      </el-upload>

      <el-button style="margin-left:8px" size="mini" :disabled="$hasBP('bnt.Question.list')  === false" type="primary"
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
        width="50%"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column type="selection" :reserve-selection="true"/>
      <el-table-column sortable prop="deptName" label="部门名称" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="score" label="题目分数" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="typeName" label="题目类型" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="content" label="题目内容" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="remark" label="备注" show-overflow-tooltip="true"/>
      <el-table-column sortable prop="createTime" label="创建时间" show-overflow-tooltip="true"/>
      <el-table-column sortable label="所属试卷" show-overflow-tooltip="true">
        <template slot-scope="scope">
          <TbExamView :questionId="scope.row.id"></TbExamView>
        </template>
      </el-table-column>
      <el-table-column type="expand" label="...">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="部门名称">
              <span>{{ props.row.deptName }}</span>
            </el-form-item>
            <el-form-item label="题目分数">
              <span>{{ props.row.score }}</span>
            </el-form-item>
            <el-form-item label="题目类型">
              <span>{{ props.row.typeName }}</span>
            </el-form-item>
            <el-form-item label="题目内容">
              <span>{{ props.row.content }}</span>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ props.row.updateTime }}</span>
            </el-form-item>
            <el-form-item label="所属试卷">
              <span><TbExamView :questionId="props.row.id"></TbExamView></span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)"
                     :disabled="$hasBP('bnt.Question.update')  === false" title="修改"
          />
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)"
                     :disabled="$hasBP('bnt.Question.remove')  === false" title="删除"
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
    <el-dialog title="添加/修改" :close-on-click-modal="false" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="tbQuestion" :rules="rules" label-width="100px" size="small"
               style="padding-right: 40px;"
      >
        <el-form-item label="部门" prop="deptId">
          <select-dept-tree ref="zdept" @saveTreeId="saveDeptId" treeTitle="选择部门" :isLazy="false"
                            :dataTree="dataDeptTree"
                            :treeVal="tbQuestion.deptName" :treeId="tbQuestion.deptId"
          ></select-dept-tree>
        </el-form-item>
        <el-form-item label="题目分数" prop="score">
          <el-input-number :min="0" v-model="tbQuestion.score" controls-position="right" clearable/>
        </el-form-item>
        <el-form-item label="题目类型" prop="type">
          <el-radio-group v-model="tbQuestion.type" size="small">
            <el-radio v-for="l in sysDicItemXdicCode4000List" :label="l.code">{{ l.name }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="题目内容" prop="content">
          <el-input v-model="tbQuestion.content" type="textarea" :rows="4" clearable/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="tbQuestion.remark" clearable/>
        </el-form-item>
        <el-form-item label="选择试卷" prop="examId">
          <TbExamSelect
            :myvals="tbQuestion.examIdList"
            @getInfo="getTbExamX90785f25" :single="false" where=""
          />
        </el-form-item>
        <el-form-item id="xzxx" v-show="tbQuestion.type!='4004'">
          <el-button @click="addItem">新增选项</el-button>
        </el-form-item>
        <el-form-item label="正确答案" id="tkRight" v-show="tbQuestion.type=='4004'">
          <el-input type="textarea" :rows="3" v-model="tbQuestion.answer" clearable/>
        </el-form-item>
        <el-form-item v-for="(item, index) in tbQuestion.optionList"
                      :label="'选项' + index"
        >
          <el-input v-model="item.options" placeholder="选项"></el-input>
          <el-input style="margin-top: 5px" v-model="item.name" placeholder="选项内容"></el-input>
          <el-radio-group style="margin-top: 5px" v-model="item.isRight">
            <el-radio :label="1">正确</el-radio>
            <el-radio :label="0">错误</el-radio>
          </el-radio-group>
          <el-button style="margin-left: 10px" @click="deleteItem(index)" type="primary"
                     icon="el-icon-delete"
          ></el-button>
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
import api from '@/api/Question'
import sysDicItemApi from '@/api/system/sysDicItem'
import sysDeptApi from '@/api/system/sysDept'
import TbExamSelect from './QuestionTbExamSelect'
import TbExamView from './QuestionTbExamView'

export default {
  components: {
    TbExamSelect,
    TbExamView,
    SelectDeptTree: () => import('@/components/Custom/TreeSelect/TreeSelect')
  },
  data() {
    return {
      dowTempPath: process.env.VUE_APP_BASE_API + '/temp/tbquestion.xlsx',
      listLoading: true, // 数据是否正在加载
      list: [], // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 5, // 每页记录数
      searchObj: {}, // 查询表单对象
      dialogVisible: false,
      tbQuestion: { optionList: [] },
      saveBtnDisabled: false,
      selectValue: [] //复选框选择内容封装数组
      , sysDicItemXdicCode4000List: []
      , dataDeptTree: [],//树形选择数据
      curDept: '',
      curDeptName: ''
      , rules: {

        content:
          [
            { required: true, message: '请输入题目内容', trigger: 'blur' }
          ]
        ,
        score:
          [
            { required: true, message: '请输入题目分数', trigger: 'blur' }
          ]
        ,
        type:
          [
            { required: true, message: '请选择题目类型', trigger: 'blur' }
          ]
        ,
        deptId:
          [
            { required: true, message: '请输入部门', trigger: 'blur' }
          ]
      }

    }
  },
  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    console.log('list created......')
    this.fetchData()
    this.getAllSysDicItemXdicCode4000()
    this.fetchPopDept()
  },
  // 生命周期函数：内存准备完毕，页面渲染成功
  mounted() {
    console.log('list mounted......')
  },
  methods: {

    //上传文件begin
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`一次只能选择一个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },

    //参数必须是param，才能获取到内容
    uploadFile(param) {
      // 获取上传的文件名
      var file = param.file
      //发送请求的参数格式为FormData
      const formData = new FormData()
      formData.append('file', file)
      api.uploadFile(formData)
        .then((res) => {
          if (res.code == 200) {
            //提示
            this.$message({
              type: 'success',
              message: '导入成功!'
            })
            this.fetchData(this.page)
            this.fileList = []
          }
          //this.refs.upload.clearFiles();
        })
        .catch((err) => {
          this.fileList = []
        })
    },

    //上传文件end

    getRowKeys(row) {
      return row.id
    },
    deleteItem(i) {
      this.tbQuestion.optionList.splice(i, 1)
    },
    questionTypeChange(type) {
      if (type == '4004') {
        document.getElementById('xzxx').style.display = 'none'
        document.getElementById('tkRight').style.display = 'block'
        //this.tbQuestion.optionList = [];
      } else {
        document.getElementById('xzxx').style.display = 'block'
        document.getElementById('tkRight').style.display = 'none'
      }

    },
    addItem() {
      this.tbQuestion.optionList.push({
        option: '',
        name: '',
        isRight: ''
      })
    },
    //导出数据
    exportData() {
      window.open(process.env.VUE_APP_BASE_API + '/Question/export?token=' + getToken())
    },
    // 每页数
    sizeChangeHandle(val) {
      this.limit = val
      this.page = 1
      this.fetchData(this.page)
    },
    getAllSysDicItemXdicCode4000() {
      let queryObj = {}
      queryObj = JSON.parse('{"dicCode":"4000"}')
      sysDicItemApi.getAllSysDicItem(queryObj)
        .then(response => {
          this.sysDicItemXdicCode4000List = response.data
        })
    },
    getTbExamX90785f25(selections) {
      this.tbQuestion.examIdList = selections
    },
    //回调保存选择的部门id
    saveDeptId(treeId, treeName) {
      this.tbQuestion.deptId = treeId
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
      this.tbQuestion = { optionList: [] }
      this.$nextTick(function() {
        this.$refs.zdept.reset_data()
        this.tbQuestion = { deptId: this.curDept, deptName: this.curDeptName, optionList: [] }
      })
      this.rules.content = [{ required: true, message: '请输入题目内容', trigger: 'blur' }]
      this.rules.score = [{ required: true, message: '请输入题目分数', trigger: 'blur' }]
      this.rules.type = [{ required: true, message: '请选择题目类型', trigger: 'blur' }]
      this.rules.deptId = [{ required: true, message: '请输入部门', trigger: 'blur' }]
    },
    //编辑
    edit(id) {
      this.dialogVisible = true
      this.rules.content = [{ required: true, message: '请输入题目内容', trigger: 'blur' }]
      this.rules.score = [{ required: true, message: '请输入题目分数', trigger: 'blur' }]
      this.rules.type = [{ required: true, message: '请选择题目类型', trigger: 'blur' }]
      this.rules.deptId = [{ required: true, message: '请输入部门', trigger: 'blur' }]
      api.getById(id).then(response => {
        this.$nextTick(function() {
          this.tbQuestion = response.data
          this.$refs.zdept.reset_data(this.tbQuestion.deptName)
          // console.log('optionList')
          // console.log(JSON.stringify(this.tbQuestion.optionList))
          if (this.$refs.eltreeDept) {
            this.$refs.eltreeDept.setCheckedKeys(this.tbQuestion.deptIdList)
          }
        })
      })
    },
    //添加或更新
    saveOrUpdate() {
      this.$refs.dataForm.validate(valid => {
        if (valid) {
          if (!this.tbQuestion.id) {
            this.save()
          } else {
            this.update()
          }
        }
      })
    },
    //添加
    save() {
      api.save(this.tbQuestion).then(response => {
        this.$message.success('操作成功')
        this.dialogVisible = false
        this.fetchData(this.page)
      })
    },
    //更新
    update() {
      api.updateById(this.tbQuestion).then(response => {
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
