<template>
  <div>
    <el-button type="primary"  size="small" @click="showUserSelect">查看</el-button>
    <tb-exam-log-dailog
      ref="tbExamLogDailog"
      :resultId="resultId"
    />
  </div>
</template>
<script>
import tbExamLogDailog from './ExamLogDailog.vue'
export default {
  props: {
    resultId: {
      type: String,
      default: ''
    }
  },
  components: {
    tbExamLogDailog
  },
  methods: {
    // 显示列表
    showUserSelect () {
      this.$refs.tbExamLogDailog.init()
    }
  }
}
</script>
<style>

</style>
