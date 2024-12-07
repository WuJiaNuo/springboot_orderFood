<template>
  <div>
    <el-dialog :title="treeTitle" :visible="visible" :class="$style.ElDialog" width="400px" append-to-body
               @close="close">
      <el-input v-model="searchValue" placeholder="请输入关键字过滤" style="margin-bottom:10px;"/>
      <el-tag>当前选择：{{ selectedName }}</el-tag>
      <el-tree
        ref="tree"
        :data="treeData"
        node-key="id"
        :default-expanded-keys="defaultExpandedKeys"
        :filter-node-method="filterNode"
        :expand-on-click-node="false"
        @current-change="handleTreeSelectChange"
        :props="props"
        v-if="isLazy!=true"
      />
      <el-tree
        ref="tree"
        node-key="id"
        :props="treeProps"
        :load="loadNodeByParent"
        lazy
        @current-change="handleTreeSelectChange"
        v-if="isLazy==true"
      >
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirm">确定</el-button>
        <el-button @click="close">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
    import sysDeptApi from '@/api/system/sysDept'
    export default {
        props: {
            dataTree: {
                type: Array,
                required: false
            },
            treeId: {
                type: String,
                default: '',
                required: false
            },
            treeTitle: {
                type: String,
                default: '',
                required: false
            },
            isLazy: {
                type: Boolean,
                default: false
            },
            props: {
                type: Object,
                default: function () {
                    return {
                        label: 'name',
                        children: 'children',
                        isLeaf: 'isLeaf'
                    }
                }
            }
        },
        data() {
            return {
                treeProps: {
                    label: 'name',
                    id:'id',
                    children: 'childlist',
                    isLeaf: 'leaf'
                },
                visible: false,
                treeData: [],
                searchValue: '',
                defaultExpandedKeys: [],
                selectedValue: '',
                selectedName: ''
            }
        },
        watch: {
            searchValue(value) {
                this.$refs.tree.filter(value)
            }
        },
        methods: {
            show(id, name) {
                this.searchValue = ''
                this.defaultExpandedKeys = []
                this.selectedValue = id
                this.selectedName = name
                if(this.isLazy==false){
                    this.loadTree()
                }
                this.visible = true
            },
            loadNodeByParent(node, resolve) {
                if (node.level === 0) {
                    //调用后台查询第一级别
                    sysDeptApi.findNodesByParent('0').then(response => {
                        resolve(response.data);
                    })
                }else{
                    let parentId = node.data.id;
                    sysDeptApi.findNodesByParent(parentId).then(response => {
                        resolve(response.data);
                    })
                }
            },
            loadTree() {
                this.treeData = this.dataTree
                //   this.$api.system.organization.tree()
                //     .then(res => {
                //       this.treeData = res.data
                //       // 默认展开根节点
                //       this.defaultExpandedKeys.push(this.treeData[0].id)
                //       // 默认展开当前节点
                //       this.defaultExpandedKeys.push(this.selectedValue)
                //     })

                //this.treeData = [{id:'1',label:'一级',children:[{id:'2',label:'二级1'},{id:'3',label:'二级2'}]}]
                // 默认展开根节点
                if(this.isLazy != 'true'){
                    this.defaultExpandedKeys.push(this.treeData[0].id)
                    // 默认展开当前节点
                    this.defaultExpandedKeys.push(this.treeId)
                }
            },
            close() {
                this.visible = false
            },
            confirm() {
                this.$emit('ok', this.selectedValue, this.selectedName)
                this.visible = false
            },
            // 树节点选中改变
            handleTreeSelectChange(data) {
                this.selectedValue = data.id
                if(data.label) {
                    this.selectedName = data.label
                }else{
                    this.selectedName = data.name
                }
            },
            filterNode(value, data) {
                if (!value) return true
                if(data.label){
                    return data.label.indexOf(value) !== -1
                }else{
                    return data.name.indexOf(value) !== -1
                }
            }

        }
    }

</script>

<style lang="scss" module>
  .ElDialog {
    :global .el-dialog__body {
      padding-top: 0px !important;
    }
  }
</style>

