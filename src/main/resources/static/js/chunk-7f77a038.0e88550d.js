(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7f77a038"],{"26d6":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",{ref:"listQuery",staticStyle:{"padding-top":"10px"},attrs:{model:e.listQuery,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"厂家名称",prop:"companyName"}},[a("company-name-select",{model:{value:e.listQuery.companyName,callback:function(t){e.$set(e.listQuery,"companyName",t)},expression:"listQuery.companyName"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"设备编号",prop:"devicNum"}},[a("el-input",{model:{value:e.listQuery.devicNum,callback:function(t){e.$set(e.listQuery,"devicNum",t)},expression:"listQuery.devicNum"}})],1)],1)],1),e._v(" "),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.btnQuery()}}},[e._v("查询")]),e._v(" "),a("el-button",{attrs:{type:"info",icon:"el-icon-magic-stick"},on:{click:function(t){return e.resetQuery("listQuery")}}},[e._v("重置")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-edit"},on:{click:function(t){return e.btnCreate()}}},[e._v("新增")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-edit"},on:{click:function(t){return e.btnCreate()}}},[e._v("导入")])],1)],1)],1),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{prop:"deviceNum",label:"设备编号",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{prop:"companyName",label:"厂家名称",width:"180"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.btnEdit(n)}}},[e._v("\n          修改\n        ")]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.btnDel(n)}}},[e._v("\n          删除\n        ")])]}}])})],1),e._v(" "),a("div",{staticStyle:{margin:"auto",width:"60%"}},[a("el-pagination",{attrs:{layout:"total, prev, pager, next, jumper","page-size":e.tablePage.pageSize,total:e.tablePage.total},on:{"current-change":function(t){e.tablePage.pageNumber=t,e.renderTable()}}})],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.temp,"label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"设备编号",prop:"deviceNum"}},[a("el-input",{attrs:{disabled:"create"!==e.dialogStatus},model:{value:e.temp.deviceNum,callback:function(t){e.$set(e.temp,"deviceNum",t)},expression:"temp.deviceNum"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"厂家名称",prop:"companyName"}},[a("company-name-select",{model:{value:e.temp.companyName,callback:function(t){e.$set(e.temp,"companyName",t)},expression:"temp.companyName"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("\n        取消\n      ")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v("\n        确认\n      ")])],1)],1)],1)},i=[],o=a("2f75"),c=a("8d4d"),l={components:{CompanyNameSelect:c["a"]},data:function(){return{dialogFormVisible:!1,dialogStatus:"",textMap:{edit:"修改",create:"新增"},temp:{companyName:void 0,deviceNum:void 0},listQuery:{companyName:void 0,deviceNum:void 0},tablePage:{total:0,pageSize:10,pageNumber:1},tableData:[]}},created:function(){this.btnQuery()},methods:{resetQuery:function(e){this.$refs[e].resetFields()},btnQuery:function(){var e=this;Object(o["e"])(this.listQuery,this.tablePage).then((function(t){e.tableData=t.data.infos,e.tablePage.total=t.data.total}))},btnDel:function(e){var t=this;Object(o["b"])(e.deviceNum).then((function(e){t.btnQuery()}))},btnEdit:function(e){this.temp=Object.assign({},e),this.dialogFormVisible=!0,this.dialogStatus="edit"},btnCreate:function(){this.resetTemp(),this.dialogFormVisible=!0,this.dialogStatus="create"},resetTemp:function(){this.temp={companyName:void 0,deviceNum:void 0}},createData:function(){var e=this;Object(o["a"])(this.temp).then((function(t){e.btnQuery(),e.dialogFormVisible=!1}))},updateData:function(){var e=this;Object(o["c"])(this.temp).then((function(t){e.btnQuery(),e.dialogFormVisible=!1}))}}},r=l,u=a("2877"),s=Object(u["a"])(r,n,i,!1,null,null,null);t["default"]=s.exports},"2f75":function(e,t,a){"use strict";a.d(t,"e",(function(){return i})),a.d(t,"f",(function(){return o})),a.d(t,"a",(function(){return c})),a.d(t,"b",(function(){return l})),a.d(t,"c",(function(){return r})),a.d(t,"d",(function(){return u}));var n=a("b775");function i(e,t){return Object(n["a"])({url:"/device/list/admin",method:"post",data:{companyName:e.companyName,deviceNum:e.deviceNum,pageNum:t.pageNumber,pageSize:t.pageSize}})}function o(e,t){return Object(n["a"])({url:"/device/list/user",method:"post",data:{deviceName:e.deviceName,deviceNum:e.deviceNum,pageNum:t.pageNumber,pageSize:t.pageSize}})}function c(e){return Object(n["a"])({url:"/device/add",method:"post",data:{companyName:e.companyName,deviceNum:e.deviceNum}})}function l(e){return Object(n["a"])({url:"/device/delete",method:"post",data:{deviceNum:e}})}function r(e){return Object(n["a"])({url:"/device/edit/admin",method:"post",data:{companyName:e.companyName,deviceNum:e.deviceNum}})}function u(e){return Object(n["a"])({url:"/device/edit/user",method:"post",data:{img:e.img,deviceNum:e.deviceNum,deviceName:e.deviceName,collectSpace:e.collectSpace,attributeInfo:e.attributeInfo}})}},4870:function(e,t,a){"use strict";a.d(t,"a",(function(){return i})),a.d(t,"b",(function(){return o}));var n=a("b775");function i(e){return Object(n["a"])({url:"/base/list/companyName",method:"post",data:{}})}function o(e){return Object(n["a"])({url:"/base/list/task/device",method:"post",data:{}})}},"8d4d":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-select",{attrs:{placeholder:"全部",filterable:"",clearable:""},model:{value:e.svalue,callback:function(t){e.svalue=t},expression:"svalue"}},e._l(e.options,(function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),1)},i=[],o=a("4870"),c={name:"CompanyNameSelect",props:{value:{type:String,default:"全部"}},data:function(){return{options:[],svalue:this.value}},watch:{value:function(e){this.svalue=e},svalue:function(e,t){e!==t&&this.$emit("input",e)}},mounted:function(){this.renderCompanyName()},methods:{renderCompanyName:function(){var e=this;Object(o["a"])().then((function(t){e.options=t.data}))}}},l=c,r=a("2877"),u=Object(r["a"])(l,n,i,!1,null,null,null);t["a"]=u.exports}}]);