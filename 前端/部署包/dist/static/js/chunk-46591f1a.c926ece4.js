(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-46591f1a"],{"17f4":function(t,e,s){"use strict";s("99af");var a=s("b775"),i="/system/sysDicItem";e["a"]={getPageList:function(t,e,s){return Object(a["a"])({url:"".concat(i,"/").concat(t,"/").concat(e),method:"get",params:s})},getAllSysDicItem:function(t){return Object(a["a"])({url:"".concat(i,"/list"),method:"get",params:t})},getById:function(t){return Object(a["a"])({url:"".concat(i,"/get/").concat(t),method:"get"})},save:function(t){return Object(a["a"])({url:"".concat(i,"/save"),method:"post",data:t})},updateById:function(t){return Object(a["a"])({url:"".concat(i,"/update"),method:"put",data:t})},removeById:function(t){return Object(a["a"])({url:"".concat(i,"/remove/").concat(t),method:"delete"})},updateStatus:function(t,e){return Object(a["a"])({url:"".concat(i,"/updateStatus/").concat(t,"/").concat(e),method:"get"})}}},7928:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"app-container"},[s("div",{staticClass:"search-div"},[s("el-form",{attrs:{"label-width":"70px",size:"small"}},[s("el-row",[s("el-col",{attrs:{span:8}},[s("el-form-item",{attrs:{label:"岗位编码"}},[s("el-input",{attrs:{placeholder:"岗位编码",clearable:""},model:{value:t.searchObj.postCode,callback:function(e){t.$set(t.searchObj,"postCode",e)},expression:"searchObj.postCode"}})],1)],1),s("el-col",{attrs:{span:8}},[s("el-form-item",{attrs:{label:"岗位名称"}},[s("el-input",{attrs:{placeholder:"岗位名称",clearable:""},model:{value:t.searchObj.name,callback:function(e){t.$set(t.searchObj,"name",e)},expression:"searchObj.name"}})],1)],1),s("el-col",{attrs:{span:8}},[s("el-form-item",{attrs:{label:"描述"}},[s("el-input",{attrs:{placeholder:"描述",clearable:""},model:{value:t.searchObj.description,callback:function(e){t.$set(t.searchObj,"description",e)},expression:"searchObj.description"}})],1)],1)],1),s("el-row",{staticStyle:{display:"flex"}},[s("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:function(e){return t.fetchData()}}},[t._v("搜索")]),s("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:t.resetData}},[t._v("重置")])],1)],1)],1),s("div",{staticClass:"tools-div"},[s("el-button",{attrs:{type:"success",icon:"el-icon-plus",disabled:!1===t.$hasBP("bnt.sysPost.add"),size:"mini"},on:{click:t.add}},[t._v("添 加")]),s("el-button",{staticClass:"btn-add",attrs:{size:"mini",disabled:!1===t.$hasBP("bnt.sysPost.remove")},on:{click:function(e){return t.batchRemove()}}},[t._v("批量删除")])],1),s("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticStyle:{width:"100%","margin-top":"10px"},attrs:{data:t.list,"row-key":t.getRowKeys,stripe:"",border:""},on:{"selection-change":t.handleSelectionChange}},[s("el-table-column",{attrs:{label:"序号",width:"70",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s((t.page-1)*t.limit+e.$index+1)+" ")]}}])}),s("el-table-column",{attrs:{type:"selection","reserve-selection":!0}}),s("el-table-column",{attrs:{sortable:"",prop:"postCode",label:"岗位编码"}}),s("el-table-column",{attrs:{sortable:"",prop:"name",label:"岗位名称"}}),s("el-table-column",{attrs:{sortable:"",prop:"description",label:"描述"}}),s("el-table-column",{attrs:{sortable:"",prop:"statusName",label:"状态"}}),s("el-table-column",{attrs:{sortable:"",prop:"createTime",label:"创建时间"}}),s("el-table-column",{attrs:{sortable:"",prop:"updateTime",label:"修改时间"}}),s("el-table-column",{attrs:{type:"expand",label:"..."},scopedSlots:t._u([{key:"default",fn:function(e){return[s("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[s("el-form-item",{attrs:{label:"岗位编码"}},[s("span",[t._v(t._s(e.row.postCode))])]),s("el-form-item",{attrs:{label:"岗位名称"}},[s("span",[t._v(t._s(e.row.name))])]),s("el-form-item",{attrs:{label:"描述"}},[s("span",[t._v(t._s(e.row.description))])]),s("el-form-item",{attrs:{label:"状态"}},[s("span",[t._v(t._s(e.row.statusName))])]),s("el-form-item",{attrs:{label:"创建时间"}},[s("span",[t._v(t._s(e.row.createTime))])]),s("el-form-item",{attrs:{label:"修改时间"}},[s("span",[t._v(t._s(e.row.updateTime))])])],1)]}}])}),s("el-table-column",{attrs:{label:"操作",align:"center",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("el-button",{attrs:{type:"primary",disabled:!1===t.$hasBP("bnt.sysPost.update"),icon:"el-icon-edit",size:"mini",title:"修改"},on:{click:function(s){return t.edit(e.row.id)}}}),s("el-button",{attrs:{type:"danger",disabled:!1===t.$hasBP("bnt.sysPost.remove"),icon:"el-icon-delete",size:"mini",title:"删除"},on:{click:function(s){return t.removeDataById(e.row.id)}}})]}}])})],1),s("el-pagination",{staticStyle:{padding:"30px 0","text-align":"center"},attrs:{"current-page":t.page,total:t.total,"page-size":t.limit,"page-sizes":[5,10,50,100],layout:"total,sizes, prev, pager, next, jumper"},on:{"size-change":t.sizeChangeHandle,"current-change":t.fetchData}}),s("el-dialog",{attrs:{title:"添加/修改",visible:t.dialogVisible,width:"40%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[s("el-form",{ref:"dataForm",staticStyle:{"padding-right":"40px"},attrs:{model:t.sysPost,rules:t.rules,"label-width":"100px",size:"small"}},[s("el-form-item",{attrs:{label:"岗位编码",prop:"postCode"}},[s("el-input",{attrs:{clearable:""},model:{value:t.sysPost.postCode,callback:function(e){t.$set(t.sysPost,"postCode",e)},expression:"sysPost.postCode"}})],1),s("el-form-item",{attrs:{label:"岗位名称",prop:"name"}},[s("el-input",{attrs:{clearable:""},model:{value:t.sysPost.name,callback:function(e){t.$set(t.sysPost,"name",e)},expression:"sysPost.name"}})],1),s("el-form-item",{attrs:{label:"描述",prop:"description"}},[s("el-input",{attrs:{clearable:""},model:{value:t.sysPost.description,callback:function(e){t.$set(t.sysPost,"description",e)},expression:"sysPost.description"}})],1),s("el-form-item",{attrs:{label:"状态",prop:"status"}},t._l(t.sysDicItemXdicCode3000List,(function(e){return s("el-radio",{attrs:{label:e.code},model:{value:t.sysPost.status,callback:function(e){t.$set(t.sysPost,"status",e)},expression:"sysPost.status"}},[t._v(t._s(e.name))])})),1)],1),s("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{attrs:{size:"small",icon:"el-icon-refresh-right"},on:{click:function(e){t.dialogVisible=!1}}},[t._v("取 消")]),s("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"small"},on:{click:function(e){return t.saveOrUpdate()}}},[t._v("确 定")])],1)],1)],1)},i=[],o=(s("b0c0"),s("a4d3"),s("e01a"),s("ccd5")),n=s("17f4"),l={},r={components:{},data:function(){return{listLoading:!0,list:[],total:0,page:1,limit:5,searchObj:{},dialogVisible:!1,sysPost:l,saveBtnDisabled:!1,selectValue:[],sysDicItemXdicCode3000List:[],rules:{postCode:[{required:!0,message:"请输入岗位编码",trigger:"blur"}],name:[{required:!0,message:"请输入岗位名称",trigger:"blur"}],description:[{required:!0,message:"请输入描述",trigger:"blur"}],status:[{required:!0,message:"请输入状态（1正常 0停用）",trigger:"blur"}]}}},created:function(){console.log("list created......"),this.fetchData(),this.getAllSysDicItemXdicCode3000()},mounted:function(){console.log("list mounted......")},methods:{getRowKeys:function(t){return t.id},sizeChangeHandle:function(t){this.limit=t,this.page=1,this.fetchData(this.page)},getAllSysDicItemXdicCode3000:function(){var t=this,e=JSON.parse('{"dicCode":"3000"}');n["a"].getAllSysDicItem(e).then((function(e){t.sysDicItemXdicCode3000List=e.data}))},fetchData:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;this.page=e,o["a"].getPageList(this.page,this.limit,this.searchObj).then((function(e){t.list=e.data.records,t.total=e.data.total,t.listLoading=!1}))},resetData:function(){console.log("重置查询表单"),this.searchObj={},this.fetchData()},removeDataById:function(t){var e=this;this.$confirm("此操作将永久删除该记录, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){return o["a"].removeById(t)})).then((function(t){e.fetchData(e.page),e.$message.success(t.message||"删除成功")})).catch((function(){e.$message.info("取消删除")}))},handleSelectionChange:function(t){this.selectValue=t},batchRemove:function(){var t=this;0!=this.selectValue.length?this.$confirm("此操作将永久删除, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){for(var e=[],s=0;s<t.selectValue.length;s++){var a=t.selectValue[s],i=a.id;e.push(i)}o["a"].batchRemove(e).then((function(e){t.$message({type:"success",message:"删除成功!"}),t.fetchData()}))})):this.$message.warning("请选择要删除的记录！")},add:function(){this.dialogVisible=!0,this.sysPost={},this.rules.postCode=[{required:!0,message:"请输入岗位编码",trigger:"blur"}],this.rules.name=[{required:!0,message:"请输入岗位名称",trigger:"blur"}],this.rules.description=[{required:!0,message:"请输入描述",trigger:"blur"}],this.rules.status=[{required:!0,message:"请输入状态（1正常 0停用）",trigger:"blur"}]},edit:function(t){var e=this;this.dialogVisible=!0,this.rules.postCode=[{required:!0,message:"请输入岗位编码",trigger:"blur"}],this.rules.name=[{required:!0,message:"请输入岗位名称",trigger:"blur"}],this.rules.description=[{required:!0,message:"请输入描述",trigger:"blur"}],this.rules.status=[{required:!0,message:"请输入状态（1正常 0停用）",trigger:"blur"}],o["a"].getById(t).then((function(t){e.$nextTick((function(){this.sysPost=t.data,this.$refs.eltreeDept&&this.$refs.eltreeDept.setCheckedKeys(this.sysPost.deptIdList)}))}))},saveOrUpdate:function(){var t=this;this.$refs.dataForm.validate((function(e){e&&(t.sysPost.id?t.update():t.save())}))},save:function(){var t=this;o["a"].save(this.sysPost).then((function(e){t.$message.success("操作成功"),t.dialogVisible=!1,t.fetchData(t.page)}))},update:function(){var t=this;o["a"].updateById(this.sysPost).then((function(e){t.$message.success(e.message||"操作成功"),t.dialogVisible=!1,t.fetchData(t.page)}))}}},c=r,u=(s("ba2e"),s("2877")),d=Object(u["a"])(c,a,i,!1,null,null,null);e["default"]=d.exports},"812a":function(t,e,s){},ba2e:function(t,e,s){"use strict";s("812a")},ccd5:function(t,e,s){"use strict";s("99af");var a=s("b775"),i="/system/sysPost";e["a"]={getPageList:function(t,e,s){return Object(a["a"])({url:"".concat(i,"/").concat(t,"/").concat(e),method:"get",params:s})},getAllSysPost:function(t){return Object(a["a"])({url:"".concat(i,"/list"),method:"get",params:t})},getById:function(t){return Object(a["a"])({url:"".concat(i,"/get/").concat(t),method:"get"})},getByIds:function(t){return Object(a["a"])({url:"".concat(i,"/getByIds"),method:"post",data:t})},save:function(t){return Object(a["a"])({url:"".concat(i,"/save"),method:"post",data:t})},updateById:function(t){return Object(a["a"])({url:"".concat(i,"/update"),method:"put",data:t})},removeById:function(t){return Object(a["a"])({url:"".concat(i,"/remove/").concat(t),method:"delete"})},batchRemove:function(t){return Object(a["a"])({url:"".concat(i,"/batchRemove"),method:"delete",data:t})},updateStatus:function(t,e){return Object(a["a"])({url:"".concat(i,"/updateStatus/").concat(t,"/").concat(e),method:"get"})}}}}]);