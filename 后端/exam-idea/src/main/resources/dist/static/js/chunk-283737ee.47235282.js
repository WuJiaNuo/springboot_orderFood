(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-283737ee"],{"13d5":function(e,t,n){"use strict";var r=n("23e7"),i=n("d58f").left,o=n("a640"),c=n("ae40"),a=o("reduce"),s=c("reduce",{1:0});r({target:"Array",proto:!0,forced:!a||!s},{reduce:function(e){return i(this,e,arguments.length,arguments.length>1?arguments[1]:void 0)}})},"5a78":function(e,t,n){"use strict";var r=n("b775"),i="/admin/system/sysMenu";t["a"]={findNodes:function(){return Object(r["a"])({url:"".concat(i,"/findNodes"),method:"get"})},findDir:function(){return Object(r["a"])({url:"".concat(i,"/findDir"),method:"get"})},getById:function(e){return Object(r["a"])({url:"".concat(i,"/get/").concat(e),method:"get"})},removeById:function(e){return Object(r["a"])({url:"".concat(i,"/remove/").concat(e),method:"delete"})},save:function(e){return Object(r["a"])({url:"".concat(i,"/save"),method:"post",data:e})},updateById:function(e){return Object(r["a"])({url:"".concat(i,"/update"),method:"post",data:e})},toAssign:function(e){return Object(r["a"])({url:"".concat(i,"/toAssign/").concat(e),method:"get"})},doAssign:function(e){return Object(r["a"])({url:"".concat(i,"/doAssign"),method:"post",data:e})}}},"96bf":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("div",{staticStyle:{padding:"20px 20px 0 20px"}},[e._v(" 授权角色："+e._s(e.$route.query.roleName)+" ")]),n("el-tree",{ref:"tree",staticStyle:{margin:"20px 0"},attrs:{data:e.sysMenuList,"node-key":"id","show-checkbox":"","default-expand-all":"",props:e.defaultProps}}),n("div",{staticStyle:{padding:"20px 20px"}},[n("el-button",{attrs:{loading:e.loading,type:"primary",icon:"el-icon-check",size:"mini"},on:{click:e.save}},[e._v("保存")]),n("el-button",{attrs:{size:"mini",icon:"el-icon-refresh-right"},on:{click:function(t){return e.$router.push("/sysRole")}}},[e._v("返回")])],1)],1)},i=[],o=(n("13d5"),n("d3b7"),n("d81d"),n("5a78")),c={name:"roleAuth",data:function(){return{loading:!1,sysMenuList:[],defaultProps:{children:"children",label:"name"}}},created:function(){this.fetchData()},methods:{fetchData:function(){var e=this,t=this.$route.query.id;o["a"].toAssign(t).then((function(t){var n=t.data;e.sysMenuList=n;var r=e.getCheckedIds(n);console.log("getPermissions() checkedIds",r),e.$refs.tree.setCheckedKeys(r)}))},getCheckedIds:function(e){var t=this,n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[];return e.reduce((function(e,r){return r.select&&0===r.children.length?e.push(r.id):r.children&&t.getCheckedIds(r.children,n),e}),n)},save:function(){var e=this,t=this.$refs.tree.getCheckedNodes(!1,!0),n=t.map((function(e){return e.id}));console.log(n);var r={roleId:this.$route.query.id,menuIdList:n};this.loading=!0,o["a"].doAssign(r).then((function(t){e.loading=!1,e.$message.success(t.$message||"分配权限成功"),e.$router.push("/system/sysRole")}))}}},a=c,s=n("2877"),d=Object(s["a"])(a,r,i,!1,null,null,null);t["default"]=d.exports},d58f:function(e,t,n){var r=n("1c0b"),i=n("7b0b"),o=n("44ad"),c=n("50c4"),a=function(e){return function(t,n,a,s){r(n);var d=i(t),u=o(d),l=c(d.length),h=e?l-1:0,f=e?-1:1;if(a<2)while(1){if(h in u){s=u[h],h+=f;break}if(h+=f,e?h<0:l<=h)throw TypeError("Reduce of empty array with no initial value")}for(;e?h>=0:l>h;h+=f)h in u&&(s=n(s,u[h],h,d));return s}};e.exports={left:a(!1),right:a(!0)}}}]);