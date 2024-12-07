<template>
    <div>
        <el-button type="primary"  size="small" @click="showUserSelect">查看</el-button>
        <SysUserViewDialog
                ref="sysUserViewDialog"
                :examId="examId"
        />
    </div>
</template>
<script>
    import SysUserViewDialog from './ExamSysUserViewDialog'
    export default {
        props: {
            examId: {
                type: String,
                default: ''
            }
        },
        components: {
            SysUserViewDialog
        },
        methods: {
            // 显示列表
            showUserSelect () {
                this.$refs.sysUserViewDialog.init()
            }
        }
    }
</script>
<style>

</style>
