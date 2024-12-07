<template>
  <div>
    <el-input v-model="treeName" :disabled="true">
      <el-button slot="append" icon="el-icon-s-grid" @click="openModal"/>
    </el-input>
    <tree-modal ref="treeModel" :dataTree="dataTree" :treeTitle="treeTitle" :treeId="treeId" :props="props"
                :isLazy="isLazy" @ok="handleOK"/>
  </div>
</template>
<script>
    import TreeModal from './TreeModal'

    export default {
        name: 'treeSingleSelect',
        components: {
            TreeModal
        },
        watch: {
            treeVal(newVal, oldVal) {
                this.treeName = newVal
            },
          deep:true
        },
        model: {
            prop: 'value',
            event: 'change'
        },
        props: {
            dataTree: {
                type: Array,
                required: false
            },
            width: {
                type: Number,
                default: 500,
                required: false
            },
            value: {
                type: String,
                default: '',
                required: false
            },
            treeVal: {
                type: String,
                default: '',
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
            disabled: {
                type: Boolean,
                required: false,
                default: false
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
                visible: false,
                treeName: ''
            }
        },
      methods: {
            openModal() {
                this.$refs.treeModel.show(this.value, this.treeName)
            },
            handleOK(id, name) {
                // 更新父组件绑定值
                this.treeName = name
                this.$emit('saveTreeId', id)
            },
           reset_data(treeTest){
              if(treeTest){
                this.treeName = treeTest
              }else{
                this.treeName=''
              }
           }
        }
    }
</script>
<style scoped>
</style>


