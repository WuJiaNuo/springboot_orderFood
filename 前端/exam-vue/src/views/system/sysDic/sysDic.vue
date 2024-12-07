<template>
  <div class="app-container">
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="8">
            <el-form-item label="名称">
              <el-input style="width: 95%" v-model="searchObj.name" placeholder="名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="编码">
              <el-input style="width: 95%" v-model="searchObj.code" placeholder="编码"></el-input>
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
      <el-button type="success" icon="el-icon-plus" size="mini" :disabled="$hasBP('bnt.sysDic.add')  === false" @click="add">添 加</el-button>
    </div>
    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 10px;">

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column sortale prop="name" label="名称"/>
      <el-table-column sortale prop="code" label="编码"/>
      <el-table-column sortale prop="createTime" label=""/>
      <el-table-column sortale prop="updateTime" label=""/>

      <el-table-column label="操作" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" :disabled="$hasBP('bnt.sysDic.update')  === false" @click="edit(scope.row.id)" title="修改"/>
          <el-button type="danger" icon="el-icon-delete" :disabled="$hasBP('bnt.sysDic.remove')  === false" size="mini" @click="removeDataById(scope.row.id)" title="删除"/>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />

    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysDic" :rules="rules" label-width="100px" size="small"
               style="padding-right: 40px;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="sysDic.name"/>
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="sysDic.code"/>
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
    import api from '@/api/system/sysDic'

    const defaultForm = {}
    export default {
        components: {},
        data() {
            return {
                listLoading: true, // 数据是否正在加载
                list: null, // banner列表
                total: 0, // 数据库中的总记录数
                page: 1, // 默认页码
                limit: 10, // 每页记录数
                searchObj: {}, // 查询表单对象
                dialogVisible: false,
                sysDic: defaultForm,
                saveBtnDisabled: false

                , rules: {

                    name: [
                        {required: true, message: '请输入名称', trigger: 'blur'}
                    ]
                    ,
                    code: [
                        {required: true, message: '请输入编码', trigger: 'blur'}
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
            // 加载banner列表数据
            fetchData(page = 1) {
                debugger
                this.page = page
                api.getPageList(this.page, this.limit, this.searchObj).then(
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

            //弹出添加表单
            add() {
                this.dialogVisible = true
                this.sysDic = {}
            },
            //编辑
            edit(id) {
                this.dialogVisible = true
                api.getById(id).then(response => {
                    this.sysDic = response.data
                })
            },

            //添加或更新
            saveOrUpdate() {
                this.$refs.dataForm.validate(valid => {
                    if (valid) {
                        if (!this.sysDic.id) {
                            this.save()
                        } else {
                            this.update()
                        }
                    }
                })
            },

            //添加
            save() {
                api.save(this.sysDic).then(response => {
                    this.$message.success('操作成功')
                    this.dialogVisible = false
                    this.fetchData(this.page)
                })
            },

            //更新
            update() {
                api.updateById(this.sysDic).then(response => {
                    this.$message.success(response.message || '操作成功')
                    this.dialogVisible = false
                    this.fetchData(this.page)
                })
            }
        }
    }
</script>
