<template>
        <div class="app-container">
        <div class="search-div">
            <el-form label-width="70px" size="small">
                <el-row>
                            <el-col :span="8">
                                <el-form-item label="考试名称">
                                        <el-input v-model="searchObj.examTitle" placeholder="考试名称" clearable/>
                                </el-form-item>
                            </el-col>
                </el-row>

                <el-row style="display:flex">
                    <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchData()">搜索</el-button>
                    <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
                </el-row>
            </el-form>
        </div>
        <!-- 列表 -->
            <el-table
                    v-loading="listLoading"
                    :data="list"
                    :row-key="getRowKeys"
                    stripe
                    border
                    style="width: 100%;margin-top: 10px;"
                    @selection-change="handleSelectionChange">
            <el-table-column
                    label="序号"
                    width="70"
                    align="center">
                <template slot-scope="scope">
                    {{ (page - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>
            <el-table-column type="selection"  :reserve-selection="true"/>
              <el-table-column sortable prop="id" label="id"/>
                        <el-table-column sortable prop="examTitle" label="考试名称"/>
                        <el-table-column sortable prop="userName" label="用户名称"/>
                        <el-table-column sortable prop="score" label="得分"/>
                        <el-table-column sortable prop="createTime" label="创建时间"/>
                        <el-table-column sortable label="答题记录" show-overflow-tooltip="true">
                          <template slot-scope="scope">
                            <TbMyExamLog :resultId="scope.row.id"></TbMyExamLog>
                          </template>
                        </el-table-column>
                                <el-table-column type="expand" label="...">
                                    <template slot-scope="props">
                                        <el-form label-position="left" inline class="demo-table-expand">
                                                    <el-form-item label="考试名称">
                                                        <span>{{ props.row.examTitle }}</span>
                                                    </el-form-item>
                                                    <el-form-item label="用户名称">
                                                        <span>{{ props.row.userName }}</span>
                                                    </el-form-item>
                                                    <el-form-item label="得分">
                                                        <span>{{ props.row.score }}</span>
                                                    </el-form-item>
                                                    <el-form-item label="答题记录">
                                                      <span> <TbMyExamLog :resultId="props.row.id"></TbMyExamLog></span>
                                                    </el-form-item>
                                                    <el-form-item label="创建时间">
                                                        <span>{{ props.row.createTime }}</span>
                                                    </el-form-item>
                                                    <el-form-item label="修改时间">
                                                        <span>{{ props.row.updateTime }}</span>
                                                    </el-form-item>
                                        </el-form>
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
            <el-form ref="dataForm" :model="tbExamResult" :rules="rules" label-width="100px" size="small"
                     style="padding-right: 40px;">
                    <el-form-item label="考试id" prop="examId">
                                <el-input v-model="tbExamResult.examId" clearable/>
                    </el-form-item>
                    <el-form-item label="用户id" prop="userId">
                                <el-input v-model="tbExamResult.userId" clearable/>
                    </el-form-item>
                    <el-form-item label="得分" prop="score">
                                <el-input-number :min="0" v-model="tbExamResult.score" controls-position="right"  clearable/>
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
    import {getToken} from '@/utils/auth'
    import api from '@/api/ExamResult'
    import TbMyExamLog from "@/views/pages/ExamLogView.vue";
    const defaultForm = {
    }
    export default {
        components: {
          TbMyExamLog
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
                tbExamResult:defaultForm,
                saveBtnDisabled:false,
                selectValue:[] //复选框选择内容封装数组
                , rules:{

                examId:
                [
                    {required: true, message: '请输入考试id', trigger: 'blur'}
                ]
                ,
                userId:
                [
                    {required: true, message: '请输入用户id', trigger: 'blur'}
                ]
                ,
                score:
                [
                    {required: true, message: '请输入得分', trigger: 'blur'}
                ]
            }
        }
        },
        // 生命周期函数：内存准备完毕，页面尚未渲染
        created() {
            console.log('list created......')
            this.fetchData()
        },
        // 生命周期函数：内存准备完毕，页面渲染成功
        mounted() {
            console.log('list mounted......')
        },
        methods: {
            getRowKeys(row) {
                return row.id
            },
            //导出数据
            exportData() {
                window.open(process.env.VUE_APP_BASE_API + '/business/tbExamResult/export?token=' + getToken());
            },
            // 每页数
            sizeChangeHandle(val) {
                this.limit = val;
                this.page = 1;
                this.fetchData(this.page);
            },
            // 加载banner列表数据
            fetchData(page = 1) {
                debugger
                this.page = page
                api.getPageListAll(this.page, this.limit, this.searchObj).then(
                    response => {
                        //this.list = response.data.list
                        this.list = response.data.records
                        this.total = response.data.total
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
                        });
                        //刷新
                        this.fetchData()
                    })
                })
            },
            //弹出添加表单
            add(row) {
                this.dialogVisible = true
                this.tbExamResult = {
                }
                this.rules.examId = [{required: true, message: '请输入考试id', trigger: 'blur'}]
                this.rules.userId = [{required: true, message: '请输入用户id', trigger: 'blur'}]
                this.rules.score = [{required: true, message: '请输入得分', trigger: 'blur'}]
            },
            //编辑
            edit(id) {
                this.dialogVisible = true
                this.rules.examId = [{required: true, message: '请输入考试id', trigger: 'blur'}]
                this.rules.userId = [{required: true, message: '请输入用户id', trigger: 'blur'}]
                this.rules.score = [{required: true, message: '请输入得分', trigger: 'blur'}]
                api.getById(id).then(response => {
                    this.$nextTick(function () {
                        this.tbExamResult = response.data
                        if(this.$refs.eltreeDept){
                            this.$refs.eltreeDept.setCheckedKeys(this.tbExamResult.deptIdList);
                        }
                    })
                })
            },
            //添加或更新
            saveOrUpdate() {
                this.$refs.dataForm.validate(valid => {
                    if (valid) {
                        if (!this.tbExamResult.id) {
                            this.save()
                        } else {
                            this.update()
                        }
                    }
                })
            },
            //添加
            save() {
                api.save(this.tbExamResult).then(response => {
                    this.$message.success('操作成功')
                    this.dialogVisible = false
                    this.fetchData(this.page)
                })
            },
            //更新
            update() {
                api.updateById(this.tbExamResult).then(response => {
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
