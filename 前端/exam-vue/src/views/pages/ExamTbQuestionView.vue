<template>
    <div>
        <el-button type="primary"  size="small" @click="showUserSelect">查看</el-button>
        <TbQuestionViewDialog
                ref="tbQuestionViewDialog"
                :examId="examId"
        />
    </div>
</template>
<script>
    import TbQuestionViewDialog from './ExamTbQuestionViewDialog'
    export default {
        props: {
            examId: {
                type: String,
                default: ''
            }
        },
        components: {
            TbQuestionViewDialog
        },
        methods: {
            // 显示列表
            showUserSelect () {
                this.$refs.tbQuestionViewDialog.init()
            }
        }
    }
</script>
<style>

</style>
