<template>
    <div>
        <el-button type="primary"  size="small" @click="showUserSelect">查看</el-button>
        <TbExamViewDialog
                ref="tbExamViewDialog"
                :questionId="questionId"
        />
    </div>
</template>
<script>
    import TbExamViewDialog from './QuestionTbExamViewDialog'
    export default {
        props: {
            questionId: {
                type: String,
                default: ''
            }
        },
        components: {
            TbExamViewDialog
        },
        methods: {
            // 显示列表
            showUserSelect () {
                this.$refs.tbExamViewDialog.init()
            }
        }
    }
</script>
<style>

</style>
