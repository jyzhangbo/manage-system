(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-10aaa54c"],{"1e89":function(t,e,a){"use strict";var i=a("9840"),n=a.n(i);n.a},4870:function(t,e,a){"use strict";a.d(e,"a",(function(){return n})),a.d(e,"b",(function(){return r}));var i=a("b775");function n(t){return Object(i["a"])({url:"/base/list/companyName",method:"post",data:{}})}function r(t){return Object(i["a"])({url:"/base/list/task/device",method:"post",data:{taskState:t}})}},"856d":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"dashboard-editor-container"},[a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("div",[a("el-form",{ref:"listTime",attrs:{model:t.listTime,rules:t.rules,"label-width":"auto"}},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"设备编号:",prop:"deviceNum"}},[a("el-cascader",{staticStyle:{width:"400px"},attrs:{options:t.options,placeholder:"请选择",filterable:"",clearable:""},model:{value:t.listTime.deviceNum,callback:function(e){t.$set(t.listTime,"deviceNum",e)},expression:"listTime.deviceNum"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:12}},[a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(e){return t.btnQuery()}}},[t._v("查询")]),t._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-download"},on:{click:t.exportTableData}},[t._v("导出")])],1)],1)],1)],1)]),t._v(" "),a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("el-row",{staticClass:"panel-group"},[a("el-col",{attrs:{span:3,offset:21}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-picture"},on:{click:function(e){return t.changeShow()}}},[t._v("\n          切换视图\n        ")])],1)],1),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:t.chartShow,expression:"chartShow"}],staticClass:"chart-container",attrs:{id:"chartDiv"}},[a("div",{staticStyle:{width:"100%",height:"400px"},attrs:{id:"mnsjChart"}})]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:!t.chartShow,expression:"!chartShow"}]},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号","min-width":"5%"}}),t._v(" "),a("el-table-column",{attrs:{prop:"time",label:"时间","min-width":"10%"}}),t._v(" "),t._l(t.tableHeader,(function(e,i){return a("el-table-column",{key:i,attrs:{label:e,"min-width":"10%"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.values[i]))])]}}],null,!0)})}))],2),t._v(" "),a("div",{staticStyle:{margin:"auto",width:"60%"}},[a("el-pagination",{attrs:{layout:"total, prev, pager, next, jumper","page-size":t.tablePage.pageSize,total:t.tablePage.total},on:{"current-change":function(e){t.tablePage.pageNumber=e,t.queryDataTable()}}})],1)],1)],1)])},n=[],r=(a("ac6a"),a("7f7f"),a("b85c")),o=a("b117"),c=a("313e"),s=a.n(c),u=a("4870"),l={name:"LineChart",data:function(){return{rules:{deviceNum:[{type:"array",required:!0,message:"请输入设备编号",trigger:"change"}]},tableHeader:{},chartShow:!1,listTime:{deviceNum:[]},options:[],chart:null,tableData:[],tablePage:{total:0,pageSize:10,pageNumber:1}}},mounted:function(){this.getDeviceNum()},methods:{exportTableData:function(){var t=this;this.$refs.listTime.validate((function(e){if(!e)return console.log("error submit!!"),!1;t.$confirm("是否导出excel?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"success"}).then((function(){Object(o["c"])(t.listTime)}))}))},getDeviceNum:function(){var t=this;Object(u["b"])().then((function(e){t.options=e.data,t.listTime.deviceNum.push(e.data[0].value),t.listTime.deviceNum.push(e.data[0].children[0].value),t.queryDataTable()}))},changeShow:function(){!0===this.chartShow?(this.chartShow=!1,this.queryDataTable()):(this.chartShow=!0,this.initChart())},initChart:function(){var t=document.getElementById("mnsjChart");t.style.width=window.innerWidth-300+"px",t.style.height="300px",this.chart=s.a.init(t),this.btnQuery()},queryDataTable:function(){var t=this;Object(o["e"])(this.listTime,this.tablePage).then((function(e){t.tableData=e.data.datas,t.tableHeader=e.data.tableHeader,t.tablePage.total=e.data.total}))},btnQuery:function(){var t=this;this.$refs.listTime.validate((function(e){if(!e)return console.log("error submit!!"),!1;!1===t.chartShow?t.queryDataTable():Object(o["d"])(t.listTime).then((function(e){var a,i=[],n=[],o=Object(r["a"])(e.data.ydatas);try{for(o.s();!(a=o.n()).done;){var c=a.value,s={name:c.name,type:"line",data:c.values,smooth:!0};i.push(s),n.push(c.name)}}catch(u){o.e(u)}finally{o.f()}t.setOptionData(e.data.xdatas,i,n)}))}))},setOptionData:function(t,e,a){this.chart.setOption({tooltip:{trigger:"axis"},legend:{data:a},color:["#c12e34","#e6b600","#0098d9","#2b821d","#005eaa","#339ca8","#cda819","#32a487"],toolbox:{itemSize:30,feature:{saveAsImage:{show:!0}}},grid:{top:100,left:"2%",right:"2%",bottom:"2%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:t}],yAxis:[{type:"value",name:"(℃)",axisTick:{show:!1},axisLabel:{margin:10,textStyle:{fontSize:14}}}],series:e})}}},d=l,m=(a("1e89"),a("2877")),h=Object(m["a"])(d,i,n,!1,null,"57ed0ae3",null);e["default"]=h.exports},9840:function(t,e,a){},b117:function(t,e,a){"use strict";a.d(e,"d",(function(){return n})),a.d(e,"e",(function(){return r})),a.d(e,"f",(function(){return o})),a.d(e,"a",(function(){return c})),a.d(e,"b",(function(){return s})),a.d(e,"c",(function(){return u}));a("ac6a");var i=a("b775");function n(t){return Object(i["a"])({url:"/data/query/char",method:"post",data:{taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1]}})}function r(t,e){return Object(i["a"])({url:"/data/query/table",method:"post",data:{taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1],pageNum:e.pageNumber,pageSize:e.pageSize}})}function o(t,e){return Object(i["a"])({url:"/data/simulation",method:"post",data:{startTime:t.startTime,stableTime:t.stableTime,downTime:t.downTime,endTime:t.endTime,taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1],timeSpace:t.timeSpace,randomTime:t.randomTime,listTemp:e}})}function c(t,e){return Object(i["a"])({url:"/data/change",method:"post",data:{taskNum:e.deviceNum[0],deviceNum:e.deviceNum[1],time:t.time,values:t.values}})}function s(t,e){return Object(i["a"])({url:"/data/copy",method:"post",data:{taskNum:e.deviceNum[0],deviceNum:e.deviceNum[1],fromAttr:t.fromAttr,toAttr:t.toAttr,addData:t.addData,randomData:t.randomData,startTime:e.startTime,endTime:t.endTime}})}function u(t){var e={taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1]};window.location.href="/data/export/excel?param="+encodeURIComponent(JSON.stringify(e),"utf-8")}}}]);