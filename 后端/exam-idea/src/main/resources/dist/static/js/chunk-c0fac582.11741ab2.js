(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c0fac582","chunk-6fd7333a","chunk-63041761","chunk-4db7b7d7","chunk-42fda684"],{"0b38":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-input",{staticClass:"input-with-select",staticStyle:{"line-hight":"40px"},attrs:{placeholder:"请选择",size:e.size,disabled:e.inpDisabled},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}},[a("el-button",{attrs:{slot:"append",disabled:e.btnDisabled,icon:"el-icon-search"},on:{click:e.showUserSelect},slot:"append"})],1),a("TbExamSelectDialog",{ref:"tbExamSelect",attrs:{selectData:e.selectData,single:e.single,where:e.where},on:{doSubmit:e.selectionsToInput}})],1)},i=[],l=(a("b0c0"),a("a15b"),a("d81d"),a("392b")),n=a("20c5"),s={data:function(){return{name:"",selectData:[],tbExamService:null}},props:{size:{type:String,default:"small"},myvals:{type:Array,default:function(){return[]}},btnDisabled:{type:Boolean,default:!1},inpDisabled:{type:Boolean,default:!0},single:{type:Boolean,default:!1},where:{type:String,default:""}},components:{TbExamSelectDialog:l["default"]},created:function(){},watch:{myvals:{handler:function(e){var t=this;this.selectData=[],e&&e.length>0&&n["a"].getByIds(e).then((function(e){e&&e.data&&(t.selectData=e.data)}))},immediate:!0,deep:!1},selectData:{handler:function(e){this.name=e.map((function(e){return e.title})).join(",")},immediate:!1,deep:!1}},methods:{selectionsToInput:function(e){this.selectData=e;for(var t=[],a=0;a<this.selectData.length;a++){var o=this.selectData[a],i=o.id;t.push(i)}this.$emit("getInfo",t)},showUserSelect:function(){this.$refs.tbExamSelect.init()}}},r=s,c=(a("d3c3"),a("2877")),u=Object(c["a"])(r,o,i,!1,null,null,null);t["default"]=u.exports},"0d01":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"选择题目",width:"1000px","close-on-click-modal":!1,"append-to-body":!0,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("span",{staticClass:"dialog-footer",attrs:{slot:"title"},slot:"title"},[a("el-button",{attrs:{size:"small",icon:"el-icon-circle-close"},on:{click:function(t){e.visible=!1}}},[e._v("关闭")])],1),a("div",[a("div",{staticClass:"search-div",staticStyle:{height:"55px","margin-top":"-25px"}},[a("el-form",{ref:"searchForm",attrs:{size:"small",model:e.searchForm}},[a("el-row",[a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{prop:"title"}},[a("el-input",{attrs:{size:"small",placeholder:"标题",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.title,callback:function(t){e.$set(e.searchForm,"title",t)},expression:"searchForm.title"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",{attrs:{prop:"remark"}},[a("el-input",{attrs:{size:"small",placeholder:"备注",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.remark,callback:function(t){e.$set(e.searchForm,"remark",t)},expression:"searchForm.remark"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-search"},on:{click:function(t){return e.refreshList()}}},[e._v("搜索 ")]),a("el-button",{attrs:{size:"small",icon:"el-icon-refresh-right"},on:{click:function(t){return e.resetSearch()}}},[e._v("重置 ")])],1)],1)],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"tbExamTable",staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:e.dataList,size:"small",border:""}},[a("el-table-column",{attrs:{sortable:"",prop:"deptName",label:"部门名称","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"title",label:"标题","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"duration",label:"时长","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"criteria",label:"及格标准","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"startTime",label:"开始时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"endTime",label:"结束时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"remark",label:"备注","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"createTime",label:"创建时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"updateTime",label:"修改时间","show-overflow-tooltip":"true"}})],1),a("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":e.pageNo,"page-sizes":[5,10,50,100],"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}})],1)])],1)},i=[],l=a("20c5"),n={data:function(){return{searchForm:{},idKey:"id",dataList:[],pageNo:1,pageSize:5,total:0,loading:!1,visible:!1}},props:{questionId:{type:String,default:""}},methods:{changeInput:function(){this.$forceUpdate()},init:function(){var e=this;this.visible=!0,this.$nextTick((function(){e.resetSearch()}))},refreshList:function(){var e=this;this.loading=!0,this.searchForm.questionId=this.questionId,l["a"].getPageList(this.pageNo,this.pageSize,this.searchForm).then((function(t){e.dataList=t.data.pageModel.records,e.total=t.data.pageModel.total,e.loading=!1}))},sizeChangeHandle:function(e){this.pageSize=e,this.pageNo=1,this.refreshList()},currentChangeHandle:function(e){this.pageNo=e,this.refreshList()},resetSearch:function(){this.$refs.searchForm.resetFields(),this.refreshList()}}},s=n,r=a("2877"),c=Object(r["a"])(s,o,i,!1,null,null,null);t["default"]=c.exports},"121d":function(e,t,a){},"17f4":function(e,t,a){"use strict";a("99af");var o=a("b775"),i="/system/sysDicItem";t["a"]={getPageList:function(e,t,a){return Object(o["a"])({url:"".concat(i,"/").concat(e,"/").concat(t),method:"get",params:a})},getAllSysDicItem:function(e){return Object(o["a"])({url:"".concat(i,"/list"),method:"get",params:e})},getById:function(e){return Object(o["a"])({url:"".concat(i,"/get/").concat(e),method:"get"})},save:function(e){return Object(o["a"])({url:"".concat(i,"/save"),method:"post",data:e})},updateById:function(e){return Object(o["a"])({url:"".concat(i,"/update"),method:"put",data:e})},removeById:function(e){return Object(o["a"])({url:"".concat(i,"/remove/").concat(e),method:"delete"})},updateStatus:function(e,t){return Object(o["a"])({url:"".concat(i,"/updateStatus/").concat(e,"/").concat(t),method:"get"})}}},"20c5":function(e,t,a){"use strict";a("99af");var o=a("b775"),i="/Exam";t["a"]={getPageList:function(e,t,a){return Object(o["a"])({url:"".concat(i,"/").concat(e,"/").concat(t),method:"get",params:a})},getMyExamist:function(e,t,a){return Object(o["a"])({url:"".concat(i,"/myExam/").concat(e,"/").concat(t),method:"get",params:a})},getAllTbExam:function(e){return Object(o["a"])({url:"".concat(i,"/list"),method:"get",params:e})},getById:function(e){return Object(o["a"])({url:"".concat(i,"/get/").concat(e),method:"get"})},getByIds:function(e){return Object(o["a"])({url:"".concat(i,"/getByIds"),method:"post",data:e})},save:function(e){return Object(o["a"])({url:"".concat(i,"/save"),method:"post",data:e})},updateById:function(e){return Object(o["a"])({url:"".concat(i,"/update"),method:"put",data:e})},removeById:function(e){return Object(o["a"])({url:"".concat(i,"/remove/").concat(e),method:"delete"})},batchRemove:function(e){return Object(o["a"])({url:"".concat(i,"/batchRemove"),method:"delete",data:e})},updateStatus:function(e,t){return Object(o["a"])({url:"".concat(i,"/updateStatus/").concat(e,"/").concat(t),method:"get"})}}},"26ce":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.showUserSelect}},[e._v("查看")]),a("TbExamViewDialog",{ref:"tbExamViewDialog",attrs:{questionId:e.questionId}})],1)},i=[],l=a("0d01"),n={props:{questionId:{type:String,default:""}},components:{TbExamViewDialog:l["default"]},methods:{showUserSelect:function(){this.$refs.tbExamViewDialog.init()}}},s=n,r=a("2877"),c=Object(r["a"])(s,o,i,!1,null,null,null);t["default"]=c.exports},"392b":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"选择题目",width:"1000px","close-on-click-modal":!1,"append-to-body":!0,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("span",{staticClass:"dialog-footer",attrs:{slot:"title"},slot:"title"},[a("el-button",{attrs:{size:"small",icon:"el-icon-circle-close"},on:{click:function(t){e.visible=!1}}},[e._v("关闭")]),a("el-button",{attrs:{size:"small",type:"primary",icon:"el-icon-circle-check"},on:{click:function(t){return e.doSubmit()}}},[e._v("确定")])],1),a("div",[a("div",{staticClass:"search-div",staticStyle:{height:"55px","margin-top":"-25px"}},[a("el-form",{ref:"searchForm",attrs:{size:"small",model:e.searchForm}},[a("el-row",[a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{prop:"title"}},[a("el-input",{attrs:{size:"small",placeholder:"标题",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.title,callback:function(t){e.$set(e.searchForm,"title",t)},expression:"searchForm.title"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",{attrs:{prop:"remark"}},[a("el-input",{attrs:{size:"small",placeholder:"备注",clearable:""},on:{input:function(t){return e.changeInput(t)}},model:{value:e.searchForm.remark,callback:function(t){e.$set(e.searchForm,"remark",t)},expression:"searchForm.remark"}})],1)],1),a("el-col",{staticStyle:{"margin-left":"5px"},attrs:{span:5}},[a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-search"},on:{click:function(t){return e.refreshList()}}},[e._v("搜索 ")]),a("el-button",{attrs:{size:"small",icon:"el-icon-refresh-right"},on:{click:function(t){return e.resetSearch()}}},[e._v("重置 ")])],1)],1)],1)],1)],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"tbExamTable",staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:e.dataList,size:"small",stripe:"",border:"","row-key":e.getRowKeys},on:{select:e.handleSelectionChange,"select-all":e.handleSelectionChange,"selection-change":e.propertySelectionChange}},[a("el-table-column",{attrs:{type:"selection","reserve-selection":!0,"header-align":"center",align:"center",width:"50"}}),a("el-table-column",{attrs:{sortable:"",prop:"deptName",label:"部门名称","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"title",label:"标题","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"duration",label:"时长","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"criteria",label:"及格标准","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"startTime",label:"开始时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"endTime",label:"结束时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"remark",label:"备注","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"createTime",label:"创建时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"updateTime",label:"修改时间","show-overflow-tooltip":"true"}})],1),a("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":e.pageNo,"page-sizes":[5,10,50,100],"page-size":e.pageSize,total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.currentChangeHandle}})],1)])],1)},i=[],l=(a("e9c4"),a("d3b7"),a("159b"),a("20c5")),n={data:function(){return{searchForm:{},dataListAllSelections:[],idKey:"id",dataList:[],pageNo:1,pageSize:5,total:0,loading:!1,visible:!1}},props:{selectData:{type:Array,default:function(){return[]}},single:{type:Boolean,default:!1},where:{type:String,default:""}},methods:{changeInput:function(){this.$forceUpdate()},getRowKeys:function(e){return e.id},init:function(){var e=this;this.visible=!0,this.$nextTick((function(){e.dataListAllSelections=JSON.parse(JSON.stringify(e.selectData)),e.$refs.tbExamTable.clearSelection(),e.resetSearch()}))},refreshList:function(){var e=this;if(this.loading=!0,null!=this.where&&""!=this.where){var t=JSON.parse(this.where);this.searchForm=Object.assign(t,this.searchForm)}l["a"].getPageList(this.pageNo,this.pageSize,this.searchForm).then((function(t){e.dataList=t.data.pageModel.records,e.total=t.data.pageModel.total,e.selectData&&e.selectData.length<=1e4&&e.$nextTick((function(){var e=this,t=[];console.log("selectData"),console.log(this.selectData),this.dataList.forEach((function(a){e.selectData.forEach((function(e){e.id===a.id&&t.push(a)}))})),t&&t.forEach((function(t){e.$refs.tbExamTable.toggleRowSelection(t,!0)}))})),e.loading=!1}))},sizeChangeHandle:function(e){this.pageSize=e,this.pageNo=1,this.refreshList()},currentChangeHandle:function(e){this.pageNo=e,this.refreshList()},resetSearch:function(){this.$refs.searchForm.resetFields(),this.refreshList()},propertySelectionChange:function(e){this.dataListAllSelections=e},handleSelectionChange:function(e,t){if(t)this.selectData&&this.selectData.length<=1e4&&this.$nextTick((function(){var a=this,o=e.length&&-1!==e.indexOf(t);console.log("selected="+o),o||this.selectData.forEach((function(e,o){e.id==t.id&&(a.selectData[o]={id:""},console.log("has removed "+e.title))}))}));else{console.log("全选还是反选");var a=this.$refs.tbExamTable.store.states.isAllSelected;0==a?(console.log("点击了反选"),this.$nextTick((function(){var e=this;this.selectData&&this.selectData.length<=1e4&&this.dataList.forEach((function(t){e.selectData.forEach((function(a,o){a.id==t.id&&(e.selectData[o]={id:""},console.log("has removed "+a.title))}))}))}))):console.log("点击了全选")}},doSubmit:function(){this.visible=!1,this.selectData&&this.selectData.length<=1e4&&this.$nextTick((function(){var e=this;console.log("last selectData"),console.log(this.selectData),this.selectData.forEach((function(t){var a=!1;e.dataListAllSelections.forEach((function(e,o){e.id==t.id&&(a=!0)})),0==a&&""!=t.id&&(e.dataListAllSelections.push(t),console.log("has push"),console.log(t))})),this.$emit("doSubmit",this.dataListAllSelections)}))}}},s=n,r=a("2877"),c=Object(r["a"])(s,o,i,!1,null,null,null);t["default"]=c.exports},a15b:function(e,t,a){"use strict";var o=a("23e7"),i=a("44ad"),l=a("fc6a"),n=a("a640"),s=[].join,r=i!=Object,c=n("join",",");o({target:"Array",proto:!0,forced:r||!c},{join:function(e){return s.call(l(this),void 0===e?",":e)}})},a434:function(e,t,a){"use strict";var o=a("23e7"),i=a("23cb"),l=a("a691"),n=a("50c4"),s=a("7b0b"),r=a("65f0"),c=a("8418"),u=a("1dde"),d=a("ae40"),p=u("splice"),h=d("splice",{ACCESSORS:!0,0:0,1:2}),m=Math.max,f=Math.min,b=9007199254740991,g="Maximum allowed length exceeded";o({target:"Array",proto:!0,forced:!p||!h},{splice:function(e,t){var a,o,u,d,p,h,v=s(this),y=n(v.length),w=i(e,y),x=arguments.length;if(0===x?a=o=0:1===x?(a=0,o=y-w):(a=x-2,o=f(m(l(t),0),y-w)),y+a-o>b)throw TypeError(g);for(u=r(v,o),d=0;d<o;d++)p=w+d,p in v&&c(u,d,v[p]);if(u.length=o,a<o){for(d=w;d<y-o;d++)p=d+o,h=d+a,p in v?v[h]=v[p]:delete v[h];for(d=y;d>y-o+a;d--)delete v[d-1]}else if(a>o)for(d=y-o;d>w;d--)p=d+o-1,h=d+a-1,p in v?v[h]=v[p]:delete v[h];for(d=0;d<a;d++)v[d+w]=arguments[d+2];return v.length=y-o+a,u}})},a85f:function(e,t,a){"use strict";a("121d")},bc16:function(e,t,a){"use strict";a("99af");var o=a("b775"),i="/Question";t["a"]={getPageList:function(e,t,a){return Object(o["a"])({url:"".concat(i,"/").concat(e,"/").concat(t),method:"get",params:a})},getAllTbQuestion:function(e){return Object(o["a"])({url:"".concat(i,"/list"),method:"get",params:e})},getById:function(e){return Object(o["a"])({url:"".concat(i,"/get/").concat(e),method:"get"})},getByIds:function(e){return Object(o["a"])({url:"".concat(i,"/getByIds"),method:"post",data:e})},save:function(e){return Object(o["a"])({url:"".concat(i,"/save"),method:"post",data:e})},updateById:function(e){return Object(o["a"])({url:"".concat(i,"/update"),method:"put",data:e})},removeById:function(e){return Object(o["a"])({url:"".concat(i,"/remove/").concat(e),method:"delete"})},batchRemove:function(e){return Object(o["a"])({url:"".concat(i,"/batchRemove"),method:"delete",data:e})},uploadFile:function(e){return Object(o["a"])({url:"".concat(i,"/import"),method:"post",data:e})},updateStatus:function(e,t){return Object(o["a"])({url:"".concat(i,"/updateStatus/").concat(e,"/").concat(t),method:"get"})}}},bcc8:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"search-div"},[a("el-form",{attrs:{"label-width":"70px",size:"small"}},[a("el-row",[a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{label:"题目内容"}},[a("el-input",{attrs:{placeholder:"题目内容",clearable:""},model:{value:e.searchObj.content,callback:function(t){e.$set(e.searchObj,"content",t)},expression:"searchObj.content"}})],1)],1),a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{label:"题目类型"}},[a("el-select",{attrs:{placeholder:"题目类型"},model:{value:e.searchObj.type,callback:function(t){e.$set(e.searchObj,"type",t)},expression:"searchObj.type"}},e._l(e.sysDicItemXdicCode4000List,(function(e){return a("el-option",{key:e.code,attrs:{label:e.name,value:e.code}})})),1)],1)],1),a("el-col",{attrs:{span:5}},[a("el-form-item",{attrs:{label:"部门"}},[a("el-input",{attrs:{placeholder:"部门名称",clearable:""},model:{value:e.searchObj.deptName,callback:function(t){e.$set(e.searchObj,"deptName",t)},expression:"searchObj.deptName"}})],1)],1)],1),a("el-row",{staticStyle:{display:"flex"}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:function(t){return e.fetchData()}}},[e._v("搜索")]),a("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetData}},[e._v("重置")])],1)],1)],1),a("div",{staticClass:"tools-div"},[a("el-button",{attrs:{type:"success",icon:"el-icon-plus",size:"mini",disabled:!1===e.$hasBP("bnt.Question.add")},on:{click:e.add}},[e._v("添 加 ")]),a("el-button",{staticClass:"btn-add",attrs:{size:"mini",disabled:!1===e.$hasBP("bnt.Question.remove")},on:{click:function(t){return e.batchRemove()}}},[e._v("批量删除 ")]),a("el-upload",{staticClass:"upload-demo",attrs:{action:"#","on-preview":e.handlePreview,"on-remove":e.handleRemove,"before-remove":e.beforeRemove,multiple:"",limit:1,"on-exceed":e.handleExceed,"file-list":e.fileList,"show-file-list":!1,"http-request":e.uploadFile}},[a("el-button",{attrs:{size:"mini",disabled:!1===e.$hasBP("bnt.sysUser.add"),type:"primary"}},[e._v("导入数据")]),a("span",{staticClass:"el-upload__tip",staticStyle:{"margin-left":"4px",color:"red"},attrs:{slot:"tip"},slot:"tip"},[a("a",{attrs:{href:e.dowTempPath,target:"_blank"}},[e._v("下载导入模板")])])],1),a("el-button",{staticStyle:{"margin-left":"8px"},attrs:{size:"mini",disabled:!1===e.$hasBP("bnt.Question.list"),type:"primary"},on:{click:e.exportData}},[e._v("导出数据 ")])],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:e.list,"row-key":e.getRowKeys,stripe:"",border:""},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{label:"序号",width:"50%",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s((e.page-1)*e.limit+t.$index+1)+" ")]}}])}),a("el-table-column",{attrs:{type:"selection","reserve-selection":!0}}),a("el-table-column",{attrs:{sortable:"",prop:"deptName",label:"部门名称","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"score",label:"题目分数","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"typeName",label:"题目类型","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"content",label:"题目内容","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"remark",label:"备注","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",prop:"createTime",label:"创建时间","show-overflow-tooltip":"true"}}),a("el-table-column",{attrs:{sortable:"",label:"所属试卷","show-overflow-tooltip":"true"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("TbExamView",{attrs:{questionId:e.row.id}})]}}])}),a("el-table-column",{attrs:{type:"expand",label:"..."},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"部门名称"}},[a("span",[e._v(e._s(t.row.deptName))])]),a("el-form-item",{attrs:{label:"题目分数"}},[a("span",[e._v(e._s(t.row.score))])]),a("el-form-item",{attrs:{label:"题目类型"}},[a("span",[e._v(e._s(t.row.typeName))])]),a("el-form-item",{attrs:{label:"题目内容"}},[a("span",[e._v(e._s(t.row.content))])]),a("el-form-item",{attrs:{label:"备注"}},[a("span",[e._v(e._s(t.row.remark))])]),a("el-form-item",{attrs:{label:"创建时间"}},[a("span",[e._v(e._s(t.row.createTime))])]),a("el-form-item",{attrs:{label:"更新时间"}},[a("span",[e._v(e._s(t.row.updateTime))])]),a("el-form-item",{attrs:{label:"所属试卷"}},[a("span",[a("TbExamView",{attrs:{questionId:t.row.id}})],1)])],1)]}}])}),a("el-table-column",{attrs:{label:"操作",align:"center",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",icon:"el-icon-edit",size:"mini",disabled:!1===e.$hasBP("bnt.Question.update"),title:"修改"},on:{click:function(a){return e.edit(t.row.id)}}}),a("el-button",{attrs:{type:"danger",icon:"el-icon-delete",size:"mini",disabled:!1===e.$hasBP("bnt.Question.remove"),title:"删除"},on:{click:function(a){return e.removeDataById(t.row.id)}}})]}}])})],1),a("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":e.page,total:e.total,"page-size":e.limit,"page-sizes":[5,10,50,100],layout:"total,sizes, prev, pager, next, jumper"},on:{"size-change":e.sizeChangeHandle,"current-change":e.fetchData}}),a("el-dialog",{attrs:{title:"添加/修改","close-on-click-modal":!1,visible:e.dialogVisible,width:"40%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{"padding-right":"40px"},attrs:{model:e.tbQuestion,rules:e.rules,"label-width":"100px",size:"small"}},[a("el-form-item",{attrs:{label:"部门",prop:"deptId"}},[a("select-dept-tree",{ref:"zdept",attrs:{treeTitle:"选择部门",isLazy:!1,dataTree:e.dataDeptTree,treeVal:e.tbQuestion.deptName,treeId:e.tbQuestion.deptId},on:{saveTreeId:e.saveDeptId}})],1),a("el-form-item",{attrs:{label:"题目分数",prop:"score"}},[a("el-input-number",{attrs:{min:0,"controls-position":"right",clearable:""},model:{value:e.tbQuestion.score,callback:function(t){e.$set(e.tbQuestion,"score",t)},expression:"tbQuestion.score"}})],1),a("el-form-item",{attrs:{label:"题目类型",prop:"type"}},[a("el-radio-group",{attrs:{size:"small"},model:{value:e.tbQuestion.type,callback:function(t){e.$set(e.tbQuestion,"type",t)},expression:"tbQuestion.type"}},e._l(e.sysDicItemXdicCode4000List,(function(t){return a("el-radio",{attrs:{label:t.code}},[e._v(e._s(t.name)+" ")])})),1)],1),a("el-form-item",{attrs:{label:"题目内容",prop:"content"}},[a("el-input",{attrs:{type:"textarea",rows:4,clearable:""},model:{value:e.tbQuestion.content,callback:function(t){e.$set(e.tbQuestion,"content",t)},expression:"tbQuestion.content"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"remark"}},[a("el-input",{attrs:{clearable:""},model:{value:e.tbQuestion.remark,callback:function(t){e.$set(e.tbQuestion,"remark",t)},expression:"tbQuestion.remark"}})],1),a("el-form-item",{attrs:{label:"选择试卷",prop:"examId"}},[a("TbExamSelect",{attrs:{myvals:e.tbQuestion.examIdList,single:!1,where:""},on:{getInfo:e.getTbExamX90785f25}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"4004"!=e.tbQuestion.type,expression:"tbQuestion.type!='4004'"}],attrs:{id:"xzxx"}},[a("el-button",{on:{click:e.addItem}},[e._v("新增选项")])],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"4004"==e.tbQuestion.type,expression:"tbQuestion.type=='4004'"}],attrs:{label:"正确答案",id:"tkRight"}},[a("el-input",{attrs:{type:"textarea",rows:3,clearable:""},model:{value:e.tbQuestion.answer,callback:function(t){e.$set(e.tbQuestion,"answer",t)},expression:"tbQuestion.answer"}})],1),e._l(e.tbQuestion.optionList,(function(t,o){return a("el-form-item",{attrs:{label:"选项"+o}},[a("el-input",{attrs:{placeholder:"选项"},model:{value:t.options,callback:function(a){e.$set(t,"options",a)},expression:"item.options"}}),a("el-input",{staticStyle:{"margin-top":"5px"},attrs:{placeholder:"选项内容"},model:{value:t.name,callback:function(a){e.$set(t,"name",a)},expression:"item.name"}}),a("el-radio-group",{staticStyle:{"margin-top":"5px"},model:{value:t.isRight,callback:function(a){e.$set(t,"isRight",a)},expression:"item.isRight"}},[a("el-radio",{attrs:{label:1}},[e._v("正确")]),a("el-radio",{attrs:{label:0}},[e._v("错误")])],1),a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-delete"},on:{click:function(t){return e.deleteItem(o)}}})],1)}))],2),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small",icon:"el-icon-refresh-right"},on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"small"},on:{click:function(t){return e.saveOrUpdate()}}},[e._v("确 定")])],1)],1)],1)},i=[],l=(a("d3b7"),a("3ca3"),a("ddb0"),a("b0c0"),a("a434"),a("5f87")),n=a("bc16"),s=a("17f4"),r=a("c2d0"),c=a("0b38"),u=a("26ce"),d={components:{TbExamSelect:c["default"],TbExamView:u["default"],SelectDeptTree:function(){return a.e("chunk-7795b7c1").then(a.bind(null,"2143"))}},data:function(){return{dowTempPath:"http://localhost:5240/temp/tbquestion.xlsx",listLoading:!0,list:[],total:0,page:1,limit:5,searchObj:{},dialogVisible:!1,tbQuestion:{optionList:[]},saveBtnDisabled:!1,selectValue:[],sysDicItemXdicCode4000List:[],dataDeptTree:[],curDept:"",curDeptName:"",rules:{content:[{required:!0,message:"请输入题目内容",trigger:"blur"}],score:[{required:!0,message:"请输入题目分数",trigger:"blur"}],type:[{required:!0,message:"请选择题目类型",trigger:"blur"}],deptId:[{required:!0,message:"请输入部门",trigger:"blur"}]}}},created:function(){console.log("list created......"),this.fetchData(),this.getAllSysDicItemXdicCode4000(),this.fetchPopDept()},mounted:function(){console.log("list mounted......")},methods:{handleRemove:function(e,t){console.log(e,t)},handlePreview:function(e){console.log(e)},handleExceed:function(e,t){this.$message.warning("一次只能选择一个文件")},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},uploadFile:function(e){var t=this,a=e.file,o=new FormData;o.append("file",a),n["a"].uploadFile(o).then((function(e){200==e.code&&(t.$message({type:"success",message:"导入成功!"}),t.fetchData(t.page),t.fileList=[])})).catch((function(e){t.fileList=[]}))},getRowKeys:function(e){return e.id},deleteItem:function(e){this.tbQuestion.optionList.splice(e,1)},questionTypeChange:function(e){"4004"==e?(document.getElementById("xzxx").style.display="none",document.getElementById("tkRight").style.display="block"):(document.getElementById("xzxx").style.display="block",document.getElementById("tkRight").style.display="none")},addItem:function(){this.tbQuestion.optionList.push({option:"",name:"",isRight:""})},exportData:function(){window.open("http://localhost:5240/Question/export?token="+Object(l["a"])())},sizeChangeHandle:function(e){this.limit=e,this.page=1,this.fetchData(this.page)},getAllSysDicItemXdicCode4000:function(){var e=this,t={};t=JSON.parse('{"dicCode":"4000"}'),s["a"].getAllSysDicItem(t).then((function(t){e.sysDicItemXdicCode4000List=t.data}))},getTbExamX90785f25:function(e){this.tbQuestion.examIdList=e},saveDeptId:function(e,t){this.tbQuestion.deptId=e,this.$refs["dataForm"].clearValidate("deptId"),this.rules.deptId=[]},fetchPopDept:function(){var e=this;r["a"].findSelectNodes().then((function(t){e.dataDeptTree=t.data}))},fetchData:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.page=t,n["a"].getPageList(this.page,this.limit,this.searchObj).then((function(t){e.list=t.data.pageModel.records,e.total=t.data.pageModel.total,e.curDept=t.data.curDept,e.curDeptName=t.data.curDeptName,e.listLoading=!1}))},resetData:function(){console.log("重置查询表单"),this.searchObj={},this.fetchData()},removeDataById:function(e){var t=this;this.$confirm("此操作将永久删除该记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return n["a"].removeById(e)})).then((function(e){t.fetchData(t.page),t.$message.success(e.message||"删除成功")})).catch((function(){t.$message.info("取消删除")}))},handleSelectionChange:function(e){this.selectValue=e},batchRemove:function(){var e=this;0!=this.selectValue.length?this.$confirm("此操作将永久删除, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){for(var t=[],a=0;a<e.selectValue.length;a++){var o=e.selectValue[a],i=o.id;t.push(i)}n["a"].batchRemove(t).then((function(t){e.$message({type:"success",message:"删除成功!"}),e.fetchData()}))})):this.$message.warning("请选择要删除的记录！")},add:function(e){this.dialogVisible=!0,this.tbQuestion={optionList:[]},this.$nextTick((function(){this.$refs.zdept.reset_data(),this.tbQuestion={deptId:this.curDept,deptName:this.curDeptName,optionList:[]}})),this.rules.content=[{required:!0,message:"请输入题目内容",trigger:"blur"}],this.rules.score=[{required:!0,message:"请输入题目分数",trigger:"blur"}],this.rules.type=[{required:!0,message:"请选择题目类型",trigger:"blur"}],this.rules.deptId=[{required:!0,message:"请输入部门",trigger:"blur"}]},edit:function(e){var t=this;this.dialogVisible=!0,this.rules.content=[{required:!0,message:"请输入题目内容",trigger:"blur"}],this.rules.score=[{required:!0,message:"请输入题目分数",trigger:"blur"}],this.rules.type=[{required:!0,message:"请选择题目类型",trigger:"blur"}],this.rules.deptId=[{required:!0,message:"请输入部门",trigger:"blur"}],n["a"].getById(e).then((function(e){t.$nextTick((function(){this.tbQuestion=e.data,this.$refs.zdept.reset_data(this.tbQuestion.deptName),this.$refs.eltreeDept&&this.$refs.eltreeDept.setCheckedKeys(this.tbQuestion.deptIdList)}))}))},saveOrUpdate:function(){var e=this;this.$refs.dataForm.validate((function(t){t&&(e.tbQuestion.id?e.update():e.save())}))},save:function(){var e=this;n["a"].save(this.tbQuestion).then((function(t){e.$message.success("操作成功"),e.dialogVisible=!1,e.fetchData(e.page)}))},update:function(){var e=this;n["a"].updateById(this.tbQuestion).then((function(t){e.$message.success(t.message||"操作成功"),e.dialogVisible=!1,e.fetchData(e.page)}))}}},p=d,h=(a("a85f"),a("2877")),m=Object(h["a"])(p,o,i,!1,null,null,null);t["default"]=m.exports},c2d0:function(e,t,a){"use strict";var o=a("b775"),i="/admin/system/sysDept";t["a"]={findNodes:function(){return Object(o["a"])({url:"".concat(i,"/findNodes"),method:"get"})},findSelectNodes:function(){return Object(o["a"])({url:"".concat(i,"/findSelectNodes"),method:"get"})},findNodesByParent:function(e){return Object(o["a"])({url:"".concat(i,"/findNodesByParent/").concat(e),method:"get"})},getById:function(e){return Object(o["a"])({url:"".concat(i,"/get/").concat(e),method:"get"})},removeById:function(e){return Object(o["a"])({url:"".concat(i,"/remove/").concat(e),method:"delete"})},save:function(e){return Object(o["a"])({url:"".concat(i,"/save"),method:"post",data:e})},updateById:function(e){return Object(o["a"])({url:"".concat(i,"/update"),method:"put",data:e})}}},d3c3:function(e,t,a){"use strict";a("d402")},d402:function(e,t,a){},e9c4:function(e,t,a){var o=a("23e7"),i=a("d066"),l=a("d039"),n=i("JSON","stringify"),s=/[\uD800-\uDFFF]/g,r=/^[\uD800-\uDBFF]$/,c=/^[\uDC00-\uDFFF]$/,u=function(e,t,a){var o=a.charAt(t-1),i=a.charAt(t+1);return r.test(e)&&!c.test(i)||c.test(e)&&!r.test(o)?"\\u"+e.charCodeAt(0).toString(16):e},d=l((function(){return'"\\udf06\\ud834"'!==n("\udf06\ud834")||'"\\udead"'!==n("\udead")}));n&&o({target:"JSON",stat:!0,forced:d},{stringify:function(e,t,a){var o=n.apply(null,arguments);return"string"==typeof o?o.replace(s,u):o}})}}]);