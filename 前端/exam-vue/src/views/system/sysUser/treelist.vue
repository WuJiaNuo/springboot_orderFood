<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-input v-model="searchDeptValue" placeholder="请输入关键字过滤" style="margin-bottom:10px;"/>
      <el-tag>当前选择：{{ selectedDeptName }}</el-tag>
      <el-tree
        ref="eltree"
        :data="dataDeptTree"
        node-key="id"
        :default-expand-all="true"
        :expand-on-click-node="false"
        :default-expanded-keys="defaultDeptExpandedKeys"
        :filter-node-method="filterDeptNode"
        @current-change="handleDeptTreeSelectChange"
      />
    </el-aside>
    <el-container>
      <el-main>
        <div class="app-container">
          <div class="search-div">
            <el-form label-width="70px" size="small">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="关 键 字">
                    <el-input style="width: 95%" v-model="searchObj.keyword" placeholder="用户名/姓名/手机号码"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="操作时间">
                    <el-date-picker
                      v-model="createTimes"
                      type="datetimerange"
                      range-separator="至"
                      start-placeholder="开始时间"
                      end-placeholder="结束时间"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      style="margin-right: 10px;width: 100%;"
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
            <el-button type="success" :disabled="$hasBP('bnt.sysUser.add')  === false" icon="el-icon-plus" size="mini"
                         @click="add">添 加
            </el-button>
            <el-button class="btn-add" size="mini" @click="batchRemove()" :disabled="$hasBP('bnt.sysUser.remove')  === false">批量删除</el-button>
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

<!--              <el-button size="mini" :disabled="$hasBP('bnt.sysUser.add')  === false" type="primary">导入数据</el-button>-->

<!--              <span style="margin-left:4px" slot="tip" class="el-upload__tip"><a :href="dowTempPath" target="_blank">下载模板</a></span>-->
            </el-upload>
            <el-button style="margin-left:8px" size="mini" :disabled="$hasBP('bnt.sysUser.list')  === false" type="primary" @click="exportData">导出数据</el-button>
          </div>


          <!-- 列表 -->
          <el-table
            v-loading="listLoading"
            :data="list"
            stripe
            border
            :row-key="getRowKeys"
            style="width: 100%;margin-top: 10px;"  @selection-change="handleSelectionChange">

            <el-table-column
              label="序号"
              width="70"
              align="center">
              <template slot-scope="scope">
                {{ (page - 1) * limit + scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column type="selection"  :reserve-selection="true"/>

            <el-table-column prop="username" label="用户名"  width="65px" show-overflow-tooltip="true" />
            <el-table-column prop="name" label="姓名" width="65px"  show-overflow-tooltip="true" />
            <el-table-column prop="phone" label="手机" width="100px" show-overflow-tooltip="true"/>
            <el-table-column prop="deptName" label="所属部门" show-overflow-tooltip="true" />
            <el-table-column label="状态" width="65px" show-overflow-tooltip="true" >
              <template slot-scope="scope">
                <el-switch
                  v-model="scope.row.status===1"
                  @change="switchStatus(scope.row)">
                </el-switch>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"  show-overflow-tooltip="true"/>

            <el-table-column label="操作" align="center" fixed="right">
              <template slot-scope="scope">
                <el-button type="primary" :disabled="$hasBP('bnt.sysUser.update')  === false" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)"
                           :disabled="$hasBP('bnt.sysUser.remove')  === false"   title="删除"/>
                <el-button type="warning" icon="el-icon-baseball" size="mini" @click="showAssignRole(scope.row)"
                           :disabled="$hasBP('bnt.sysUser.update')  === false"  title="分配角色"/>
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

          <el-dialog title="分配角色" :visible.sync="dialogRoleVisible">
            <el-form label-width="80px">
              <el-form-item label="用户名">
                <el-input disabled :value="sysUser.username"></el-input>
              </el-form-item>


              <el-form-item label="角色列表">
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选
                </el-checkbox>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="userRoleIds" @change="handleCheckedChange">
                  <el-checkbox v-for="role in allRoles" :key="role.id" :label="role.id">{{role.roleName}}</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-form>
            <div slot="footer">
              <el-button type="primary" @click="assignRole" size="small">保存</el-button>
              <el-button @click="dialogRoleVisible = false" size="small">取消</el-button>
            </div>
          </el-dialog>

          <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
            <el-form ref="dataForm" :model="sysUser" :rules="rules" label-width="100px" size="small"
                     style="padding-right: 40px;">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="sysUser.username"/>
              </el-form-item>
              <el-form-item v-if="!sysUser.id" label="密码" prop="password">
                <el-input v-model="sysUser.password" type="password"/>
              </el-form-item>
              <el-form-item v-else label="新密码" prop="newpassword">
                <el-input v-model="sysUser.newpassword" type="password"/>
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input v-model="sysUser.name"/>
              </el-form-item>
              <el-form-item label="手机" prop="phone">
                <el-input v-model="sysUser.phone"/>
              </el-form-item>

              <el-form-item label="所属部门" prop="deptIds">
                <el-tree
                  ref="eltreeDept"
                  :data="dataDeptTree"
                  node-key="id"
                  show-checkbox
                  :expand-on-click-node="false"
                  check-strictly="true"
                  :default-checked-keys="sysUser.deptIds"
                />
              </el-form-item>
              <el-form-item label="所属岗位" prop="postId">
                <el-select
                  v-model="sysUser.postId"
                  placeholder="所属岗位">
                  <el-option
                    v-for="sysPost in sysPostX0d96aa0bList"
                    :key="sysPost.id"
                    :label="sysPost.name"
                    :value="sysPost.id"/>
                </el-select>
              </el-form-item>


            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
          </el-dialog>

        </div>
      </el-main>
    </el-container>
    </el-container>
</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }

  .upload-demo {
    display: inline-block;
    margin-left: 8px;
  }
</style>

<script>
    import api from '@/api/system/sysUser'
    import roleApi from '@/api/system/sysRole'
    import deptApi from '@/api/system/sysDept'
    import {getToken} from '@/utils/auth'
    import sysPostApi from '@/api/system/sysPost'

    export default {
        components: {
            //导入树形选择组件
            // SelectDeptTree: () => import('@/components/Custom/TreeSelect/TreeSelect')
        },
        data() {
            let validOrgan = (rule, value, callback) => {
                // 获取选中节点数组
                let arr = this.$refs.eltreeDept.getCheckedKeys();
                if(!arr || arr.length === 0) {
                    callback(new Error('请选择关联机构'))
                } else {
                    callback();
                }
            }
            return {
                listLoading: false, // 数据是否正在加载
                list: null, // 列表
                total: 0, // 数据库中的总记录数
                page: 1, // 默认页码
                limit: 5, // 每页记录数
                searchObj: {}, // 查询表单对象

                createTimes: [],

                dialogVisible: false,
                sysUser: {},

                dialogRoleVisible: false,
                allRoles: [], // 所有角色列表
                userRoleIds: [], // 用户的角色ID的列表
                isIndeterminate: false, // 是否是不确定的
                checkAll: false, // 是否全选
                //////
                dataDeptTree: [], //树形选择数据

                //左侧树begin
                searchDeptValue: '', //左侧部门搜索绑定
                defaultDeptExpandedKeys: [],
                selectedDeptValue: '',
                selectedDeptName: '',
                //左侧树end
                 sysPostX0d96aa0bList:[],
                selectValue:[], //复选框选择内容封装数组

                fileList: [],
                dowTempPath: process.env.VUE_APP_BASE_API + '/temp/temp.xlsx',

                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, message: '长度在 2个字符以上', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, message: '请输入手机', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                    deptIds: [
                        {required: true,validator:validOrgan, trigger: 'change' }
                    ]

                }


            }
        },
        created() {
            //调用列表方法
            this.fetchData()
            this.fetchPopDept()
            this.getAllSysPostX0d96aa0b()
        },
        watch: {
            //监听左侧树查询事件
            searchDeptValue(value) {
                this.$refs.eltree.filter(value)
            }
        },
        methods: {
            //回调保存选择的部门id
            // saveDeptId(treeId) {
            //     this.sysUser.deptId = treeId
            //     this.sysUser.deptName= treeName
            //     this.$refs["dataForm"].clearValidate("deptId")
            //     this.rules.deptId=[]
            // },

            getRowKeys(row) {
                return row.id
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

            //导出数据
            exportData() {
                window.open(process.env.VUE_APP_BASE_API + '/admin/system/sysUser/export?token=' + getToken());
            },

            getAllSysPostX0d96aa0b() {
                let queryObj = {}
                sysPostApi.getAllSysPost(queryObj)
                    .then(response => {
                        this.sysPostX0d96aa0bList = response.data
                    })
            },

            fetchPopDept() {
                //加载选择部门数据
                deptApi.findSelectNodes().then(response => {
                    this.dataDeptTree = response.data
                })

            },


            //左侧树形方法begin
            // 树节点选中改变
            handleDeptTreeSelectChange(data) {
                this.selectedDeptValue = data.id
                this.selectedDeptName = data.label
                //重新加载主数据
                this.searchObj.deptId = data.id
                this.fetchData(this.page)
            },
            filterDeptNode(value, data) {
                if (!value) return true
                return data.label.indexOf(value) !== -1
            },
            //左侧树形方法end

            //上传文件begin
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`一次只能选择一个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${file.name}？`);
            },

            //参数必须是param，才能获取到内容
            uploadFile(param) {
                // 获取上传的文件名
                var file = param.file
                //发送请求的参数格式为FormData
                const formData = new FormData();
                formData.append("file", file)
                api.uploadFile(formData)
                    .then((res) => {
                        if (res.code == 200) {
                            //提示
                            this.$message({
                                type: 'success',
                                message: '导入成功!'
                            });
                            this.fetchData(this.page);
                            this.fileList = []
                        }
                        //this.refs.upload.clearFiles();
                    })
                    .catch((err) => {
                        this.fileList = []
                    });
            },


            //上传文件end


            //列表
            fetchData(page = 1) {
                this.page = page
                if (this.createTimes && this.createTimes.length == 2) {
                    this.searchObj.createTimeBegin = this.createTimes[0]
                    this.searchObj.createTimeEnd = this.createTimes[1]
                }
                api.getPageList(this.page, this.limit, this.searchObj)
                    .then(response => {
                        this.list = response.data.records
                        this.total = response.data.total

                    })
            },

            //展示分配角色
            showAssignRole(row) {
                this.sysUser = row
                this.dialogRoleVisible = true
                roleApi.getRolesByUserId(row.id).then(response => {
                    this.allRoles = response.data.allRoles
                    console.log(this.allRoles)
                    this.userRoleIds = response.data.userRoleIds
                    console.log(this.userRoleIds)
                    this.checkAll = this.userRoleIds.length === this.allRoles.length
                    this.isIndeterminate = this.userRoleIds.length > 0 && this.userRoleIds.length < this.allRoles.length
                })
            },

            /*
            全选勾选状态发生改变的监听
            */
            handleCheckAllChange(value) {// value 当前勾选状态true/false
                // 如果当前全选, userRoleIds就是所有角色id的数组, 否则是空数组
                this.userRoleIds = value ? this.allRoles.map(item => item.id) : []
                // 如果当前不是全选也不全不选时, 指定为false
                this.isIndeterminate = false
            },

            /*
            角色列表选中项发生改变的监听
            */
            handleCheckedChange(value) {
                const {userRoleIds, allRoles} = this
                this.checkAll = userRoleIds.length === allRoles.length && allRoles.length > 0
                this.isIndeterminate = userRoleIds.length > 0 && userRoleIds.length < allRoles.length
            },

            //分配角色
            assignRole() {
                let assginRoleVo = {
                    userId: this.sysUser.id,
                    roleIdList: this.userRoleIds
                }
                roleApi.assignRoles(assginRoleVo).then(response => {
                    this.$message.success(response.message || '分配角色成功')
                    this.dialogRoleVisible = false
                    this.fetchData(this.page)
                })
            },
            //更改用户状态
            switchStatus(row) {
                //判断，如果当前用户可用，修改禁用
                row.status = row.status === 1 ? 0 : 1
                api.updateStatus(row.id, row.status)
                    .then(response => {
                        this.$message.success(response.message || '操作成功')
                        this.fetchData()
                    })
            },
            //删除
            removeDataById(id) {
                this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //调用方法删除
                    api.removeById(id)
                        .then(response => {
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
            //根据id查询，数据回显
            edit(id) {
                //弹出框
                this.dialogVisible = true
                //调用接口查询
                api.getUserId(id)
                    .then(response => {
                        this.$nextTick(function () {
                            this.$refs.eltreeDept.setCheckedKeys([]);
                            this.sysUser = response.data
                        })
                    })
            },
            //添加或者修改方法
            saveOrUpdate() {

                // 第三步,点击提交表单按钮的时候判断是否符合校验规则,符合才会往下运行,不符合会
                // 按照你定义的校验规则提示警告
                this.$refs.dataForm.validate((valid) => {
                    if (valid) {
                        this.sysUser.deptIds = this.$refs.eltreeDept.getCheckedKeys();
                        if (!this.sysUser.id) {
                            this.save()
                        } else {
                            this.update()
                        }

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //修改
            update() {
                api.update(this.sysUser)
                    .then(response => {
                        //提示
                        this.$message.success('操作成功')
                        //关闭弹框
                        this.dialogVisible = false
                        //刷新
                        this.fetchData()
                    })
            },
            //添加
            save() {
                api.save(this.sysUser)
                    .then(response => {
                        //提示
                        this.$message.success('操作成功')
                        //关闭弹框
                        this.dialogVisible = false
                        //刷新
                        this.fetchData()
                    })
            },
            //添加弹框的方法
            add() {
                this.dialogVisible = true
                this.sysUser = {}
                this.$nextTick(function () {
                    this.$refs.eltreeDept.setCheckedKeys([]);
                })
            },
            // 重置查询表单
            resetData() {
                console.log('重置查询表单')
                this.searchObj = {}
                this.createTimes = []
                this.fetchData()
            }

        }
    }
</script>
