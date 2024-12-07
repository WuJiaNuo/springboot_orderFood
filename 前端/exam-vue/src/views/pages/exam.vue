<template>
  <el-form ref="form" :model="form" size="small" style="margin-left:30px;margin-right:500px">
    <div style="text-align: center;margin-top: 10px"><span style="font-size: 25px;"> {{
        this.$route.query.examTitle
      }}</span>
      <span v-if="this.examType=='5002'" style="color: red;margin-left: 10px">剩余时间:{{hr}}小时 {{ min }}分钟 {{ sec }}秒 </span></div>
    <div style="font-size: 20px;margin-bottom: 10px" v-if="form.sigList.length>0">(一) 单选题</div>
    <el-form-item v-for="(item, index) in form.sigList"
                  :label="'('+(index+1)+')   '+item.content+':'">
      <br/>
      <el-radio-group v-if="item.type=='4001'" v-model="item.myCheck">
        <el-radio v-for="opitem in item.optionList" :label="opitem.options">{{opitem.options}}:{{ opitem.name }}</el-radio>
      </el-radio-group>
    </el-form-item>

    <div style="font-size: 20px;margin-bottom: 10px" v-if="form.mulList.length>0">(二) 多选题</div>
    <el-form-item v-for="(item, index) in form.mulList"
                  :label="'('+(index+1)+')   '+item.content+':'">
      <br/>
      <el-checkbox-group  v-if="item.type==='4002'" v-model="item.myCheckList">
        <el-checkbox  v-for="opitem in item.optionList"
                     :label="opitem.options">{{opitem.options}}:{{ opitem.name }}</el-checkbox>
      </el-checkbox-group>
    </el-form-item>


    <div style="font-size: 20px;margin-bottom: 10px" v-if="form.pdList.length>0">(三) 判断题</div>
    <el-form-item v-for="(item, index) in form.pdList"
                  :label="'('+(index+1)+')   '+item.content+':'">
      <br/>
      <el-radio-group v-if="item.type=='4003'" v-model="item.myCheck">
        <el-radio v-for="opitem in item.optionList" :label="opitem.options">{{opitem.options}}:{{ opitem.name }}</el-radio>
      </el-radio-group>
    </el-form-item>

    <div style="font-size: 20px;margin-bottom: 10px" v-if="form.wdList.length>0">(四) 问答题</div>
    <el-form-item v-for="(item, index) in form.wdList"
                  :label="'('+(index+1)+')   '+item.content+':'">
      <el-input  v-if="item.type=='4004'" style="width: 500px"  v-model="item.myAnswer"  />
    </el-form-item>

    <div style="margin-top: 20px"/>
    <el-button type="primary" size="mini" @click="onSubmit" v-loading.fullscreen.lock="loading">提交</el-button>
    <el-button v-if="this.fromType==1" @click="$router.push('/myExam')" size="mini" icon="el-icon-refresh-right">返回</el-button>
    <el-button v-else-if="this.fromType==2" @click="$router.push('/myScore')" size="mini" icon="el-icon-refresh-right">返回</el-button>
  </el-form>
</template>
<script>
import request from '@/utils/request'

export default {
  data() {
    return {
      loading: false,
      form: {
        sigList: [],
        mulList: [],
        pdList: [],
        wdList: [],
        examId: '',
        resultId:'',
        testList:[]
      },
      min: '',
      sec: '',
      examType:'',
      fromType:''
    }
  },
  methods: {
    countDown() {
      //const end = Date.parse(new Date('2023-10-02 13:30:00'))
      const end = Date.parse(new Date(this.$route.query.endTime));
      const now = Date.parse(new Date())
      const msec = end - now
      if (msec < 0) return;
      let day = parseInt(msec / 1000 / 60 / 60 / 24)
      let hr = parseInt(msec / 1000 / 60 / 60 % 24)
      let min = parseInt(msec / 1000 / 60 % 60)
      let sec = parseInt(msec / 1000 % 60)
      this.day = day
      this.hr = hr > 9 ? hr : '0' + hr
      this.min = min > 9 ? min : '0' + min
      this.sec = sec > 9 ? sec : '0' + sec
      const that = this
      if (min >= 0 && sec >= 0) {
        //倒计时结束关闭订单
        if (min == 0 && sec == 0) {
          this.subEx(true)
          return
        }
        setTimeout(function () {
          that.countDown()
        }, 1000)
      }
    },
    save(data) {
      return request({
        url: '/Question/saveExam',
        method: 'post',
        data: data
      })
    },
    getExam(searchObj) {
      return request({
        url: '/Question/myList',
        method: 'get',
        params: searchObj
      })
    },
    subEx(auto) {
      this.loading = true
      this.save(this.form).then(response => {
        this.loading = false
        if (response.code == '200') {
          if(auto){
            this.$message.success('考试时间已过，已自动提交，请前往我的成绩中查看成绩')
          }else{
            this.$message.success('考试提交成功，请前往我的成绩中查看成绩')
          }
        }
      }).catch(() => {
        this.loading = false
      })
    },
    onSubmit() {
      this.$confirm('确认提交?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.subEx()
      })
    }

  }
  ,
  mounted() {
    const eid = this.$route.query.examId;
    this.examType = this.$route.query.type;
    this.fromType = this.$route.query.fromType;
    this.form.resultId = this.$route.query.resultId;
    this.form.examId = eid
    let queryObj = {examId: eid,resultId:this.form.resultId}
    this.getExam(queryObj).then(
      response => {
        //this.list = response.data.list
        this.form.sigList = response.data.sigList
        this.form.mulList = response.data.mulList
        this.form.pdList = response.data.pdList
        this.form.wdList = response.data.wdList
        // 数据加载并绑定成功
      }
    )
    if(this.examType=='5002'){
      this.countDown()
    }
  }
}
</script>
