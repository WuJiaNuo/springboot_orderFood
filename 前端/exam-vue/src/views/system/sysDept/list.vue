<template>
  <div class="app-container">

    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" icon="el-icon-plus" :disabled="$hasBP('bnt.sysDept.add')  === false" size="mini" @click="add()">添 加</el-button>
    </div>
    <el-table
      :data="sysDeptList"
      style="width: 100%;margin-bottom: 20px;margin-top: 10px;"
      row-key="id"
      border
      :default-expand-all="false"
      :tree-props="{children: 'children'}">

      <el-table-column prop="name" label="部门名称"/>
      <el-table-column prop="leader" label="负责人"/>
      <el-table-column prop="phone" label="联系电话"/>
      <el-table-column prop="sortValue" label="排序"/>
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status === 1" :disabled="true">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column label="操作" width="180" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="success" v-if="scope.row.type !== 2" icon="el-icon-plus" size="mini" @click="add(scope.row)"
                     title="添加下级部门"/>
          <el-button type="primary" icon="el-icon-edit" size="mini" :disabled="$hasBP('bnt.sysDept.update')  === false" @click="edit(scope.row)" title="修改"/>
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="$hasBP('bnt.sysDept.remove')  === false || scope.row.children.length > 0 " @click="removeDataById(scope.row.id)" title="删除"
                     />
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysDept" :rules="rules" label-width="150px" size="small"
               style="padding-right: 40px;">
        <el-form-item label="上级部门">
          <select-dept-tree @saveTreeId="saveDeptId" treeTitle="选择部门" :dataTree="dataDeptTree"
                            :treeVal="sysDept.parentName" :treeId="sysDept.parentId"></select-dept-tree>
        </el-form-item>

        <el-form-item label="部门名称" prop="name">
          <el-input v-model="sysDept.name"/>
        </el-form-item>

        <el-form-item label="负责人" prop="leader">
          <el-input v-model="sysDept.leader"/>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="sysDept.phone"/>
        </el-form-item>

        <el-form-item label="排序">
          <el-input-number v-model="sysDept.sortValue" controls-position="right" :min="0"/>
        </el-form-item>

        <el-form-item label="状态" prop="type">
          <el-radio-group v-model="sysDept.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
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
    import api from '@/api/system/sysDept'

    const defaultForm = {
        id: '',
        parentId: '',
        name: '',
        treePath: '',
        leader: '',
        phone: '',
        sortValue: 1,
        status: 1
    }
    export default {
        components: {
            //导入树形选择组件
            SelectDeptTree: () => import('@/components/Custom/TreeSelect/TreeSelect')
        },
        // 定义数据
        data() {
            return {
                sysDeptList: [],
                expandKeys: [], // 需要自动展开的项
                dialogTitle: '',
                dialogVisible: false,
                sysDept: defaultForm,
                saveBtnDisabled: false,
                rules: {
                    name: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
                },
                dataDeptTree: [] //树形选择数据
            }
        },

        //当页面加载时获取数据
        created() {
            this.fetchData()
            this.fetchPopDept()
        },

        methods: {
            //调用api层获取数据库中的数据
            fetchData() {
                console.log('加载列表')
                api.findNodes().then(response => {
                    this.sysDeptList = response.data
                })
            },

            //回调保存选择的部门id
            saveDeptId(treeId) {
                if (treeId == this.sysDept.id) {
                    this.$alert('上级部门不能和当前部门相同', '提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.sysDept.parentId = '';
                            this.sysDept.parentName = '';
                            api.getById(this.sysDept.id).then(response => {
                                this.sysDept = response.data
                                //this.sysDept.parentName = row.name
                            })
                        }
                    });
                } else {
                    this.sysDept.parentId = treeId
                    this.sysDept.parentName= treeName
                }

            },

            fetchPopDept() {
                //加载弹出选择部门数据
                api.findSelectNodes().then(response => {
                    this.dataDeptTree = response.data
                    console.log('loadDeptData')
                    console.log(JSON.stringify(this.dataDeptTree))
                })

            },

            //根据id删除数据
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
                    this.fetchData()
                    this.$message({
                        type: 'success',
                        message: response.message
                    })
                }).catch(() => {
                    this.$message.info('取消删除')
                })
            },

            //弹出添加或更新的表单
            add(row) {
                debugger
                this.typeDisabled = false
                this.dialogTitle = '添加下级部门'
                this.dialogVisible = true

                this.sysDept = Object.assign({}, defaultForm)
                this.sysDept.id = ''
                if (row) {
                    this.sysDept.parentId = row.id
                    this.sysDept.parentName = row.name
                    //this.sysDept.component = 'ParentView'
                } else {
                    this.dialogTitle = '添加顶级部门'
                    this.sysDept.parentId = '0'
                }
            },

            //编辑
            edit(row) {
                debugger
                this.dialogTitle = '修改部门'
                this.dialogVisible = true
                //this.sysDept = Object.assign({}, row)
                api.getById(row.id).then(response => {
                    this.sysDept = response.data
                    //this.sysDept.parentName = row.name
                })

            },

            //添加或更新
            saveOrUpdate() {
                this.$refs.dataForm.validate(valid => {
                    if (valid) {
                        this.saveBtnDisabled = true // 防止表单重复提交
                        if (!this.sysDept.id) {
                            this.save()
                        } else {
                            this.update()
                        }
                    }
                })
            },

            //添加
            save() {
                api.save(this.sysDept).then(response => {
                    this.$message.success(response.message || '操作成功')
                    this.dialogVisible = false
                    this.fetchData(this.page)
                })
            },

            //更新
            update() {
                api.updateById(this.sysDept).then(response => {
                    this.$message.success(response.message || '操作成功')
                    this.dialogVisible = false
                    this.fetchData()
                })
            }
        }
    }
</script>
