(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-054f2fde"],{"330b":function(e,t,a){},4870:function(e,t,a){"use strict";a.d(t,"a",(function(){return r})),a.d(t,"b",(function(){return l}));var i=a("b775");function r(e){return Object(i["a"])({url:"/base/list/companyName",method:"post",data:{}})}function l(e){return Object(i["a"])({url:"/base/list/task/device",method:"post",data:{taskState:e}})}},"504a":function(e,t,a){"use strict";var i=a("330b"),r=a.n(i);r.a},"822b":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"dashboard-editor-container"},[a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("div",[a("el-form",{ref:"listTime",attrs:{model:e.listTime,rules:e.rules,"label-width":"130px"}},[a("el-row",{attrs:{gutter:5}},[a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"起点:",prop:"startTime"}},[a("el-date-picker",{attrs:{value:e.listTime.startTime,type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"恒温点:",prop:"stableTime"}},[a("el-date-picker",{attrs:{value:e.listTime.stableTime,type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"降温点:",prop:"downTime"}},[a("el-date-picker",{attrs:{value:e.listTime.downTime,type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"终点:",prop:"endTime"}},[a("el-date-picker",{attrs:{value:e.listTime.endTime,type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"设备编号:",prop:"deviceNum"}},[a("el-cascader",{attrs:{options:e.options,placeholder:"请选择",clearable:""},model:{value:e.listTime.deviceNum,callback:function(t){e.$set(e.listTime,"deviceNum",t)},expression:"listTime.deviceNum"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"间隔(分钟):",prop:"timeSpace"}},[a("el-input",{model:{value:e.listTime.timeSpace,callback:function(t){e.$set(e.listTime,"timeSpace",t)},expression:"listTime.timeSpace"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"随机分钟:",prop:"randomTime"}},[a("el-input",{model:{value:e.listTime.randomTime,callback:function(t){e.$set(e.listTime,"randomTime",t)},expression:"listTime.randomTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.btnQuery()}}},[e._v("查询")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.simulationData()}}},[e._v("模拟数据")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.copyDataToDiglog()}}},[e._v("复制数据")])],1)],1)],1)],1)]),e._v(" "),a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("el-row",{staticClass:"panel-group"},[a("el-col",{attrs:{span:3,offset:21}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-picture"},on:{click:function(t){return e.changeShow()}}},[e._v("\n          切换视图\n        ")])],1)],1),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.chartShow,expression:"chartShow"}],staticClass:"chart-container",attrs:{id:"chartDiv"}},[a("div",{staticStyle:{width:"100%",height:"400px"},attrs:{id:"mnsjChart"}})]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:!e.chartShow,expression:"!chartShow"}]},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[a("el-table-column",{attrs:{type:"index",label:"序号","min-width":"5%"}}),e._v(" "),a("el-table-column",{attrs:{prop:"time",label:"时间","min-width":"10%"}}),e._v(" "),e._l(e.tableHeader,(function(t,i){return a("el-table-column",{key:i,attrs:{label:t,"min-width":"5%"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{directives:[{name:"show",rawName:"v-show",value:t.row.show,expression:"scope.row.show"}],attrs:{placeholder:"请输入内容"},model:{value:t.row.values[i],callback:function(a){e.$set(t.row.values,i,a)},expression:"scope.row.values[key]"}}),e._v(" "),a("span",{directives:[{name:"show",rawName:"v-show",value:!t.row.show,expression:"!scope.row.show"}]},[e._v(e._s(t.row.values[i]))])]}}],null,!0)})})),e._v(" "),a("el-table-column",{attrs:{label:"操作","min-width":"20%"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{on:{click:function(e){t.row.show=!0}}},[e._v("编辑")]),e._v(" "),a("el-button",{on:{click:function(a){return e.saveData(t.row)}}},[e._v("保存")])]}}])})],2),e._v(" "),a("div",{staticStyle:{margin:"auto",width:"60%"}},[a("el-pagination",{attrs:{layout:"total, prev, pager, next, jumper","page-size":e.tablePage.pageSize,total:e.tablePage.total},on:{"current-change":function(t){e.tablePage.pageNumber=t,e.queryDataTable()}}})],1)],1),e._v(" "),a("div",[a("el-row",{attrs:{gutter:5}},e._l(e.listTemp,(function(t){return a("el-col",{key:t.name,attrs:{span:3}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[e._v("\n              "+e._s(t.name)+"\n            ")]),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-checkbox",{model:{value:t.effective,callback:function(a){e.$set(t,"effective",a)},expression:"item.effective"}},[e._v("\n                有效性\n              ")])],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"浮动值",clearable:""},model:{value:t.randomData,callback:function(a){e.$set(t,"randomData",a)},expression:"item.randomData"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"起点",clearable:""},model:{value:t.startTemp,callback:function(a){e.$set(t,"startTemp",a)},expression:"item.startTemp"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"恒温点",clearable:""},model:{value:t.stableTemp,callback:function(a){e.$set(t,"stableTemp",a)},expression:"item.stableTemp"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"降温点",clearable:""},model:{value:t.downTemp,callback:function(a){e.$set(t,"downTemp",a)},expression:"item.downTemp"}})],1),e._v(" "),a("div",{staticClass:"card-panel-description",staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"终点",clearable:""},model:{value:t.endTemp,callback:function(a){e.$set(t,"endTemp",a)},expression:"item.endTemp"}})],1)])],1)})),1)],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"数据复制",visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:8}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.copyDataInput.fromAttr,callback:function(t){e.$set(e.copyDataInput,"fromAttr",t)},expression:"copyDataInput.fromAttr"}},e._l(e.tableHeader,(function(t,i){return a("el-option",{key:i,attrs:{label:i,value:i}},[e._v(e._s(t))])})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-input",{attrs:{placeholder:"固定增加数",clearable:""},model:{value:e.copyDataInput.addData,callback:function(t){e.$set(e.copyDataInput,"addData",t)},expression:"copyDataInput.addData"}}),e._v(" "),a("el-input",{attrs:{placeholder:"随机变动数",clearable:""},model:{value:e.copyDataInput.randomData,callback:function(t){e.$set(e.copyDataInput,"randomData",t)},expression:"copyDataInput.randomData"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-checkbox-group",{model:{value:e.copyDataInput.toAttr,callback:function(t){e.$set(e.copyDataInput,"toAttr",t)},expression:"copyDataInput.toAttr"}},e._l(e.tableHeader,(function(t,i){return a("el-checkbox",{key:i,attrs:{label:i}},[e._v(e._s(t))])})),1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("\n        取消\n      ")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.copyData()}}},[e._v("\n        确认\n      ")])],1)],1)],1)},r=[],l=(a("ac6a"),a("7f7f"),a("b85c")),o=a("b117"),n=a("313e"),s=a.n(n),c=a("4870"),d=a("de52"),u=a.n(d),m={data:function(){return{rules:{startTime:[{required:!0,message:"请输入起点时间",trigger:"change"}],stableTime:[{required:!0,message:"请输入恒温点时间",trigger:"change"}],downTime:[{required:!0,message:"请输入降温点时间",trigger:"change"}],endTime:[{required:!0,message:"请输入终点点时间",trigger:"change"}],deviceNum:[{type:"array",required:!0,message:"请输入设备编号",trigger:"change"}],timeSpace:[{required:!0,message:"请输入时间间隔",trigger:"blur"}],randomTime:[{required:!0,message:"请输入随机分钟",trigger:"blur"}]},tableHeader:{},dialogFormVisible:!1,chart:null,tableData:[],chartShow:!1,listTime:{startTime:u()().format("yyyy-MM-DD 00:00:00"),stableTime:"",downTime:"",endTime:"",deviceNum:[],timeSpace:"",randomTime:""},listTemp:[],copyDataInput:{fromAttr:"",toAttr:[],addData:"",randomData:""},options:[],tablePage:{total:0,pageSize:10,pageNumber:1}}},mounted:function(){this.getDeviceNum()},methods:{getDeviceNum:function(){var e=this;Object(c["b"])().then((function(t){e.options=t.data,e.listTime.deviceNum.push(t.data[0].value),e.listTime.deviceNum.push(t.data[0].children[0].value),e.queryDataTable()}))},copyData:function(){var e=this;Object(o["b"])(this.copyDataInput,this.listTime).then((function(t){e.dialogFormVisible=!1,e.btnQuery(),e.copyDataInput={fromAttr:"",toAttr:[],addData:"",randomData:""}}))},copyDataToDiglog:function(){this.dialogFormVisible=!0},saveData:function(e){e.show=!1,Object(o["a"])(e,this.listTime)},changeShow:function(){!0===this.chartShow?(this.chartShow=!1,this.queryDataTable()):(this.chartShow=!0,this.initChart())},btnQuery:function(){!1===this.chartShow?this.queryDataTable():this.queryDataChart()},queryDataTable:function(){var e=this;Object(o["d"])(this.listTime,this.tablePage).then((function(t){for(var a in e.tableData=t.data.datas,e.tableHeader=t.data.tableHeader,e.listTemp=[],e.tableHeader){var i={name:e.tableHeader[a],code:a,startTemp:"",stableTemp:"",downTemp:"",endTemp:"",randomData:"",effective:!0};e.listTemp.push(i)}e.tablePage.total=t.data.total}))},initChart:function(){var e=document.getElementById("mnsjChart");e.style.width=window.innerWidth-300+"px",e.style.height="300px",this.chart=s.a.init(e),this.queryDataChart()},queryDataChart:function(){var e=this;Object(o["c"])(this.listTime).then((function(t){for(var a in e.tableHeader=t.data.tableHeader,e.listTemp=[],e.tableHeader){var i={name:e.tableHeader[a],code:a,startTemp:"",stableTemp:"",downTemp:"",endTemp:"",randomData:"",effective:!0};e.listTemp.push(i)}var r,o=[],n=[],s=Object(l["a"])(t.data.yDatas);try{for(s.s();!(r=s.n()).done;){var c=r.value,d={name:c.name,type:"line",data:c.values};o.push(d),n.push(c.name)}}catch(u){s.e(u)}finally{s.f()}e.setOptionData(t.data.xDatas,o,n)}))},setOptionData:function(e,t,a){this.chart.setOption({tooltip:{trigger:"axis"},legend:{data:a},toolbox:{feature:{saveAsImage:{show:!0}}},grid:{top:100,left:"2%",right:"2%",bottom:"2%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:e}],yAxis:[{type:"value",name:"(℃)",axisTick:{show:!1},axisLabel:{margin:10,textStyle:{fontSize:14}}}],series:t})},simulationData:function(){var e=this;this.$refs.listTime.validate((function(t){if(!t)return console.log("error submit!!"),!1;for(var a=[],i=0;i<e.listTemp.length;i++)!0===e.listTemp[i]["effective"]&&a.push(e.listTemp[i]);Object(o["e"])(e.listTime,a).then((function(t){e.btnQuery()}))}))}}},p=m,v=(a("504a"),a("2877")),b=Object(v["a"])(p,i,r,!1,null,"5728de7e",null);t["default"]=b.exports},b117:function(e,t,a){"use strict";a.d(t,"c",(function(){return r})),a.d(t,"d",(function(){return l})),a.d(t,"e",(function(){return o})),a.d(t,"a",(function(){return n})),a.d(t,"b",(function(){return s}));a("ac6a");var i=a("b775");function r(e){return Object(i["a"])({url:"/data/query/char",method:"post",data:{startTime:e.startTime,endTime:e.endTime,taskNum:e.deviceNum[0],deviceNum:e.deviceNum[1]}})}function l(e,t){return Object(i["a"])({url:"/data/query/table",method:"post",data:{startTime:e.startTime,endTime:e.endTime,taskNum:e.deviceNum[0],deviceNum:e.deviceNum[1],pageNum:t.pageNumber,pageSize:t.pageSize}})}function o(e,t){return Object(i["a"])({url:"/data/simulation",method:"post",data:{startTime:e.startTime,stableTime:e.stableTime,downTime:e.downTime,endTime:e.endTime,taskNum:e.deviceNum[0],deviceNum:e.deviceNum[1],timeSpace:e.timeSpace,randomTime:e.randomTime,listTemp:t}})}function n(e,t){return Object(i["a"])({url:"/data/change",method:"post",data:{taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1],time:e.time,values:e.values}})}function s(e,t){return Object(i["a"])({url:"/data/copy",method:"post",data:{taskNum:t.deviceNum[0],deviceNum:t.deviceNum[1],fromAttr:e.fromAttr,toAttr:e.toAttr,addData:e.addData,randomData:e.randomData,startTime:t.startTime,endTime:e.endTime}})}}}]);