(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d216b44"],{c430:function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",{ref:"listQuery",staticStyle:{"padding-top":"10px"},attrs:{model:t.listQuery,"label-width":"130px"}},[a("el-row",[a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"名称",prop:"taskName"}},[a("el-input",{model:{value:t.listQuery.taskName,callback:function(e){t.$set(t.listQuery,"taskName",e)},expression:"listQuery.taskName"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-form-item",{attrs:{label:"编号",prop:"taskNum"}},[a("el-input",{model:{value:t.listQuery.taskNum,callback:function(e){t.$set(t.listQuery,"taskNum",e)},expression:"listQuery.taskNum"}})],1)],1)],1),t._v(" "),a("el-row",{attrs:{type:"flex",justify:"end"}},[a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(e){return t.btnQuery()}}},[t._v("查询")]),t._v(" "),a("el-button",{attrs:{type:"info",icon:"el-icon-magic-stick"},on:{click:function(e){return t.resetQuery("listQuery")}}},[t._v("重置")]),t._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-edit"},on:{click:function(e){return t.btnCreate()}}},[t._v("新增")])],1)],1)],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{prop:"taskName",label:"名称",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"taskNum",label:"编号",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"taskStatus",label:"状态",width:"90"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(t._f("taskStatusFunc")(e.row.taskStatus))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"devices",label:"设备列表",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(t._f("splitDevice")(e.row.devices))+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"startTime",label:"开始时间",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{prop:"endTime",label:"结束时间",width:"180"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.btnEdit(i)}}},[t._v("\n          修改\n        ")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.btnDel(i)}}},[t._v("\n          删除\n        ")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.btnChangeStatus(i,1)}}},[t._v("\n          开启\n        ")]),t._v(" "),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(e){return t.btnChangeStatus(i,2)}}},[t._v("\n          结束\n        ")])]}}])})],1),t._v(" "),a("div",{staticStyle:{margin:"auto",width:"60%"}},[a("el-pagination",{attrs:{layout:"total, prev, pager, next, jumper","page-size":t.tablePage.pageSize,total:t.tablePage.total},on:{"current-change":function(e){t.tablePage.pageNumber=e,t.btnQuery()}}})],1),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.temp,"label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"名称",prop:"taskName"}},[a("el-input",{model:{value:t.temp.taskName,callback:function(e){t.$set(t.temp,"taskName",e)},expression:"temp.taskName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"编号",prop:"taskNum"}},[a("el-input",{model:{value:t.temp.taskNum,callback:function(e){t.$set(t.temp,"taskNum",e)},expression:"temp.taskNum"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"设备列表",prop:"devices"}},[a("el-checkbox-group",{model:{value:t.temp.deviceNums,callback:function(e){t.$set(t.temp,"deviceNums",e)},expression:"temp.deviceNums"}},[a("el-row",t._l(t.deviceList,(function(e){return a("el-col",{key:e.deviceNum,attrs:{span:12}},[a("el-checkbox",{attrs:{label:e.deviceNum}},[t._v("\n                "+t._s(e.deviceName)+"\n              ")])],1)})),1)],1)],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("\n        取消\n      ")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updateData()}}},[t._v("\n        确认\n      ")])],1)],1)],1)},s=[],n=a("b775");function l(t,e){return Object(n["a"])({url:"/task/list",method:"post",data:{taskName:t.taskName,taskNum:t.taskNum,pageNum:e.pageNumber,pageSize:e.pageSize}})}function r(t){return Object(n["a"])({url:"/device/list/task",method:"post",data:{taskNum:t}})}function o(t){return Object(n["a"])({url:"/task/edit",method:"post",data:{taskNum:t.taskNum,devices:t.deviceNums,state:t.state}})}function u(t){return Object(n["a"])({url:"/task/add",method:"post",data:{taskNum:t.taskNum,devices:t.deviceNums,taskName:t.taskName}})}function c(t){return Object(n["a"])({url:"/task/delete",method:"post",data:{taskNum:t.taskNum}})}var m={filters:{splitDevice:function(t){for(var e=[],a=0;a<t.length;a++)e.push(t[a].deviceNum);return e.join(";")},taskStatusFunc:function(t){return 0===t?"创建中":1===t?"执行中":"结束"}},data:function(){return{dialogFormVisible:!1,dialogStatus:"",textMap:{edit:"编辑",create:"增加"},temp:{taskName:void 0,taskNum:void 0,deviceNums:[]},listQuery:{taskName:void 0,taskNum:void 0},tablePage:{total:0,pageSize:10,pageNumber:1},tableData:[],deviceList:[]}},created:function(){this.btnQuery()},methods:{btnDel:function(t){var e=this;c(t).then((function(t){e.btnQuery()}))},btnChangeStatus:function(t,e){for(var a=this,i=[],s=0;s<t.devices.length;s++)i.push(t.devices[s].deviceNum);var n={state:e,taskNum:t.taskNum,deviceNums:i};o(n).then((function(t){a.btnQuery(),a.dialogFormVisible=!1}))},updateData:function(){var t=this;o(this.temp).then((function(e){t.btnQuery(),t.dialogFormVisible=!1}))},createData:function(){var t=this;u(this.temp).then((function(e){t.btnQuery(),t.dialogFormVisible=!1}))},queryDeviceList:function(t){var e=this;r(t).then((function(t){e.deviceList=t.data}))},resetQuery:function(t){this.$refs[t].resetFields()},btnQuery:function(){var t=this;l(this.listQuery,this.tablePage).then((function(e){t.tableData=e.data.tasks,t.tablePage.total=e.data.total}))},btnEdit:function(t){this.temp.taskName=t.taskName,this.temp.taskNum=t.taskNum;for(var e=0;e<t.devices.length;e++)this.temp.deviceNums.push(t.devices[e].deviceNum);this.dialogFormVisible=!0,this.dialogStatus="edit",this.queryDeviceList(t.taskNum)},btnCreate:function(){this.resetTemp(),this.dialogFormVisible=!0,this.dialogStatus="create",this.queryDeviceList()},resetTemp:function(){this.temp={taskName:void 0,taskNum:void 0,deviceNums:[]}}}},d=m,p=a("2877"),b=Object(p["a"])(d,i,s,!1,null,null,null);e["default"]=b.exports}}]);