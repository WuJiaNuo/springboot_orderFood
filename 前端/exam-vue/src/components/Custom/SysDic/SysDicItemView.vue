<template>
  <div>
    <el-button type="primary"  size="small" @click="showUserSelect">查看</el-button>
    <SysDicItemViewDialog
      ref="sysDicItemViewDialog"
      :dicCodes="dicCodes"
    />
  </div>
</template>
<script>
    import SysDicItemViewDialog from './SysDicItemViewDialog'
    export default {
        props: {
            dicCodes: {
                type: String,
                default: ''
            }
        },
        components: {
            SysDicItemViewDialog
        },
        methods: {
            // 显示列表
            showUserSelect () {
                this.$refs.sysDicItemViewDialog.init()
            }
        }
    }
</script>
<style>

</style>
