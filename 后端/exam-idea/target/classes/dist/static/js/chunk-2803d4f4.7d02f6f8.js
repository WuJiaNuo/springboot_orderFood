(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2803d4f4","chunk-47aab411"],{"2d46":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"分配用户",width:"1000px","close-on-click-modal":!1,"append-to-body":!0,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("span",{staticClass:"dialog-footer",attrs:{slot:"title"},slot:"title"},[a("el-button",{attrs:{size:"small",icon:"el-icon-circle-close"},on:{click:function(t){e.visible=!1}}},[e._v("关闭")])],1),a("div",[a("div",{staticClass:"search-div",staticStyle:{height:"55px","margin-top":"-25px"}},[a("el-form",{ref:"searchForm",attrs:{size:"small",model:e.searchForm}},[a("el-row",[a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{prop:"username"}},[a("el-input",{attrs:{size:"small",placeholder:"用户名",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.username,callback:function(t){e.$set(e.searchForm,"username",t)},expression:"searchForm.username"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",{attrs:{prop:"name"}},[a("el-input",{attrs:{size:"small",placeholder:"姓名",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.name,callback:function(t){e.$set(e.searchForm,"name",t)},expression:"searchForm.name"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",{attrs:{prop:"phone"}},[a("el-input",{attrs:{size:"small",placeholder:"手机",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.phone,callback:function(t){e.$set(e.searchForm,"phone",t)},expression:"searchForm.phone"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-search"},on:{click:function(t){return e.refreshList()}}},[e._v("搜索 ")]),a("el-button",{attrs:{size:"small",icon:"el-icon-refresh-right"},on:{click:function(t){return e.resetSearch()}}},[e._v("重置 ")])],1)],1)],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"sysUserTable",staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:e.dataList,size:"small",border:""}},[a("el-table-column",{attrs:{sortable:"",prop:"deptName",label:"部门名称","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"username",label:"用户名","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"name",label:"姓名","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"phone",label:"手机","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"postName",label:"岗位名称","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"description",label:"描述","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"status",label:"状态","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"createTime",label:"创建时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"updateTime",label:"更新时间","show-overflow-tooltip":"true"}})],1),a("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":e.pageNo,"page-sizes":[5,10,50,100],"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}})],1)])],1)},r=[],l=a("7e5c"),s={data:function(){return{searchForm:{},idKey:"id",dataList:[],pageNo:1,pageSize:5,total:0,loading:!1,visible:!1}},props:{examId:{type:String,default:""}},methods:{changeInput:function(){this.$forceUpdate()},init:function(){var e=this;this.visible=!0,this.$nextTick((function(){e.resetSearch()}))},refreshList:function(){var e=this;this.loading=!0,this.searchForm.examId=this.examId,l["a"].getPageList(this.pageNo,this.pageSize,this.searchForm).then((function(t){e.dataList=t.data.records,e.total=t.data.total,e.loading=!1}))},sizeChangeHandle:function(e){this.pageSize=e,this.pageNo=1,this.refreshList()},currentChangeHandle:function(e){this.pageNo=e,this.refreshList()},resetSearch:function(){this.$refs.searchForm.resetFields(),this.refreshList()}}},n=s,i=a("2877"),c=Object(i["a"])(n,o,r,!1,null,null,null);t["default"]=c.exports},"7e5c":function(e,t,a){"use strict";a("99af");var o=a("b775"),r="/admin/system/sysUser";t["a"]={getPageList:function(e,t,a){return Object(o["a"])({url:"".concat(r,"/").concat(e,"/").concat(t),method:"get",params:a})},save:function(e){return Object(o["a"])({url:"".concat(r,"/save"),method:"post",data:e})},getUserId:function(e){return Object(o["a"])({url:"".concat(r,"/getUser/").concat(e),method:"get"})},getByIds:function(e){return Object(o["a"])({url:"".concat(r,"/getByIds"),method:"post",data:e})},update:function(e){return Object(o["a"])({url:"".concat(r,"/update"),method:"post",data:e})},removeById:function(e){return Object(o["a"])({url:"".concat(r,"/remove/").concat(e),method:"delete"})},batchRemove:function(e){return Object(o["a"])({url:"".concat(r,"/batchRemove"),method:"delete",data:e})},updateStatus:function(e,t){return Object(o["a"])({url:"".concat(r,"/updateStatus/").concat(e,"/").concat(t),method:"get"})},uploadFile:function(e){return Object(o["a"])({url:"".concat(r,"/import"),method:"post",data:e})}}},d4a9:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.showUserSelect}},[e._v("查看")]),a("SysUserViewDialog",{ref:"sysUserViewDialog",attrs:{examId:e.examId}})],1)},r=[],l=a("2d46"),s={props:{examId:{type:String,default:""}},components:{SysUserViewDialog:l["default"]},methods:{showUserSelect:function(){this.$refs.sysUserViewDialog.init()}}},n=s,i=a("2877"),c=Object(i["a"])(n,o,r,!1,null,null,null);t["default"]=c.exports}}]);