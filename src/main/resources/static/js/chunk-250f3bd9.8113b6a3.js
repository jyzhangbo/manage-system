(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-250f3bd9"],{"0a38":function(t,e,l){},"26bc":function(t,e,l){"use strict";var a=l("0a38"),o=l.n(a);o.a},4870:function(t,e,l){"use strict";l.d(e,"a",(function(){return o})),l.d(e,"b",(function(){return r}));var a=l("b775");function o(t){return Object(a["a"])({url:"/base/list/companyName",method:"post",data:{}})}function r(t){return Object(a["a"])({url:"/base/list/task/device",method:"post",data:{taskState:t}})}},"5bac":function(t,e,l){"use strict";l.r(e);var a=function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",{staticClass:"dashboard-editor-container"},[l("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:12}},[t._v("\n        设备编号：\n        "),l("el-cascader",{staticStyle:{width:"300px"},attrs:{options:t.options,placeholder:"请选择",filterable:"",clearable:""},on:{change:t.handleChange},model:{value:t.deviceNum,callback:function(e){t.deviceNum=e},expression:"deviceNum"}})],1),t._v(" "),l("el-col",{attrs:{span:12}},[l("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success"},on:{click:function(e){return t.btnControl()}}},[t._v("控制下发")])],1)],1)],1),t._v(" "),l("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[l("el-form",{ref:"listTime",attrs:{model:t.listTime,"label-width":"100px"}},[l("el-tabs",{on:{"tab-click":t.handleClick},model:{value:t.active,callback:function(e){t.active=e},expression:"active"}},[l("el-tab-pane",{attrs:{label:"温度模式选择",name:"0"}},[l("div",{staticStyle:{"text-align":"center"}},[l("el-form-item",[l("el-radio-group",{model:{value:t.listTime.modelType,callback:function(e){t.$set(t.listTime,"modelType",e)},expression:"listTime.modelType"}},[l("el-radio",{attrs:{label:"00"}},[t._v("采集器模式")]),t._v(" "),l("el-radio",{attrs:{label:"01"}},[t._v("阀门控制器模式")])],1)],1)],1)]),t._v(" "),l("el-tab-pane",{attrs:{label:"阀门控制",name:"1"}},[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:24}},[l("el-form-item",{attrs:{label:"阀门1:"}},[l("el-radio-group",{model:{value:t.listTime.tapControl1,callback:function(e){t.$set(t.listTime,"tapControl1",e)},expression:"listTime.tapControl1"}},[l("el-radio",{attrs:{label:"00"}},[t._v("关闭")]),t._v(" "),l("el-radio",{attrs:{label:"01"}},[t._v("启动（或重新启动），等待温度到达")]),t._v(" "),l("el-radio",{attrs:{label:"02"}},[t._v("等恒温状态时间到达")]),t._v(" "),l("el-radio",{attrs:{label:"03"}},[t._v("升温过程，等待升温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"04"}},[t._v("高温恒定状态，等待恒温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"05"}},[t._v("降温过程，等待降温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"06"}},[t._v("等待降温温度到")])],1)],1)],1)],1),t._v(" "),l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:24}},[l("el-form-item",{attrs:{label:"阀门2:"}},[l("el-radio-group",{model:{value:t.listTime.tapControl2,callback:function(e){t.$set(t.listTime,"tapControl2",e)},expression:"listTime.tapControl2"}},[l("el-radio",{attrs:{label:"00"}},[t._v("关闭")]),t._v(" "),l("el-radio",{attrs:{label:"01"}},[t._v("启动（或重新启动），等待温度到达")]),t._v(" "),l("el-radio",{attrs:{label:"02"}},[t._v("等恒温状态时间到达")]),t._v(" "),l("el-radio",{attrs:{label:"03"}},[t._v("升温过程，等待升温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"04"}},[t._v("高温恒定状态，等待恒温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"05"}},[t._v("降温过程，等待降温时间到")]),t._v(" "),l("el-radio",{attrs:{label:"06"}},[t._v("等待降温温度到")])],1)],1)],1)],1)],1),t._v(" "),l("el-tab-pane",{attrs:{label:"选择探头",name:"2"}},[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:24}},[l("el-form-item",{attrs:{label:"阀门1:"}},[l("el-checkbox-group",{model:{value:t.listTime.probeType1,callback:function(e){t.$set(t.listTime,"probeType1",e)},expression:"listTime.probeType1"}},[l("el-checkbox",{attrs:{label:"1"}},[t._v("T0")]),t._v(" "),l("el-checkbox",{attrs:{label:"2"}},[t._v("T1")]),t._v(" "),l("el-checkbox",{attrs:{label:"4"}},[t._v("T2")]),t._v(" "),l("el-checkbox",{attrs:{label:"8"}},[t._v("T3")]),t._v(" "),l("el-checkbox",{attrs:{label:"16"}},[t._v("T4")]),t._v(" "),l("el-checkbox",{attrs:{label:"32"}},[t._v("T5")]),t._v(" "),l("el-checkbox",{attrs:{label:"64"}},[t._v("T6")]),t._v(" "),l("el-checkbox",{attrs:{label:"128"}},[t._v("T7")])],1)],1)],1)],1),t._v(" "),l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:24}},[l("el-form-item",{attrs:{label:"阀门2:"}},[l("el-checkbox-group",{model:{value:t.listTime.probeType2,callback:function(e){t.$set(t.listTime,"probeType2",e)},expression:"listTime.probeType2"}},[l("el-checkbox",{attrs:{label:"1"}},[t._v("T0")]),t._v(" "),l("el-checkbox",{attrs:{label:"2"}},[t._v("T1")]),t._v(" "),l("el-checkbox",{attrs:{label:"4"}},[t._v("T2")]),t._v(" "),l("el-checkbox",{attrs:{label:"8"}},[t._v("T3")]),t._v(" "),l("el-checkbox",{attrs:{label:"16"}},[t._v("T4")]),t._v(" "),l("el-checkbox",{attrs:{label:"32"}},[t._v("T5")]),t._v(" "),l("el-checkbox",{attrs:{label:"64"}},[t._v("T6")]),t._v(" "),l("el-checkbox",{attrs:{label:"128"}},[t._v("T7")])],1)],1)],1)],1)],1),t._v(" "),l("el-tab-pane",{attrs:{label:"设置温度",name:"3"}},[l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"静停温度(℃):",prop:"startTemp"}},[l("el-input",{attrs:{placeholder:"静停温度",clearable:""},model:{value:t.listTime.tempControl.startTemp,callback:function(e){t.$set(t.listTime.tempControl,"startTemp",e)},expression:"listTime.tempControl.startTemp"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"静停时长(h):",prop:"startTime"}},[l("el-input",{attrs:{placeholder:"静停时长",clearable:""},model:{value:t.listTime.tempControl.startTime,callback:function(e){t.$set(t.listTime.tempControl,"startTime",e)},expression:"listTime.tempControl.startTime"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"升温速度(℃/h):",prop:"upSpeed"}},[l("el-input",{attrs:{placeholder:"升温速度",clearable:""},model:{value:t.listTime.tempControl.upSpeed,callback:function(e){t.$set(t.listTime.tempControl,"upSpeed",e)},expression:"listTime.tempControl.upSpeed"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"恒温温度(℃):",prop:"constantTemp"}},[l("el-input",{attrs:{placeholder:"恒温温度",clearable:""},model:{value:t.listTime.tempControl.constantTemp,callback:function(e){t.$set(t.listTime.tempControl,"constantTemp",e)},expression:"listTime.tempControl.constantTemp"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"恒温时长(h):",prop:"constantTime"}},[l("el-input",{attrs:{placeholder:"恒温时长",clearable:""},model:{value:t.listTime.tempControl.constantTime,callback:function(e){t.$set(t.listTime.tempControl,"constantTime",e)},expression:"listTime.tempControl.constantTime"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"降温速度(℃/h):",prop:"downSpeed"}},[l("el-input",{attrs:{placeholder:"降温速度",clearable:""},model:{value:t.listTime.tempControl.downSpeed,callback:function(e){t.$set(t.listTime.tempControl,"downSpeed",e)},expression:"listTime.tempControl.downSpeed"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:6}},[l("el-form-item",{attrs:{label:"结束温度(℃):",prop:"endTemp"}},[l("el-input",{attrs:{placeholder:"结束温度",clearable:""},model:{value:t.listTime.tempControl.endTemp,callback:function(e){t.$set(t.listTime.tempControl,"endTemp",e)},expression:"listTime.tempControl.endTemp"}})],1)],1),t._v(" "),l("el-col",{attrs:{span:12}},[l("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success"},on:{click:function(e){return t.btnQuery()}}},[t._v("查看数据图像")])],1)],1),t._v(" "),l("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[l("div",{staticClass:"chart-container",attrs:{id:"chartDiv"}},[l("div",{staticStyle:{width:"100%",height:"200px"},attrs:{id:"mnsjChart"}})])])],1)],1)],1)],1)])},o=[],r=(l("c5f6"),l("313e")),i=l.n(r),n=l("4870"),s=l("b775");function c(t,e){return Object(s["a"])({url:"/device/control",method:"post",data:{deviceNum:e,modelType:t.modelType,tapControl1:t.tapControl1,tapControl2:t.tapControl2,probeType1:t.probeType1,probeType2:t.probeType2,tempControl:t.tempControl}})}function p(t){return Object(s["a"])({url:"/device/control/info/query",method:"post",data:{deviceNum:t}})}var m={data:function(){return{active:"0",chart:null,deviceNum:[],listTime:{modelType:"",tapControl1:"",tapControl2:"",probeType1:[],probeType2:[],tempControl:{startTemp:"",startTime:"",upSpeed:"",constantTemp:"",constantTime:"",downSpeed:"",endTemp:""}},options:[],taskState:1}},mounted:function(){this.getDeviceNum()},methods:{getDeviceNum:function(){var t=this;Object(n["b"])(this.taskState).then((function(e){t.options=e.data,t.deviceNum.push(e.data[0].value),t.deviceNum.push(e.data[0].children[0].value),t.controlInfo(e.data[0].children[0].value)}))},handleChange:function(t){this.controlInfo(this.deviceNum[1])},controlInfo:function(t){var e=this;p(t).then((function(t){e.listTime=t.data,e.btnQuery()}))},btnControl:function(){c(this.listTime,this.deviceNum[1]).then((function(t){alert("控制成功")}))},handleClick:function(t,e){},btnQuery:function(){var t=this.listTime.tempControl,e=[0,t.startTime],l=Number((t.constantTemp-t.startTemp)/t.upSpeed)+Number(t.startTime);e.push(l);var a=l+Number(t.constantTime);e.push(a);var o=Number((t.constantTemp-t.endTemp)/t.downSpeed)+Number(a);e.push(o);var r=[t.startTemp,t.startTemp,t.constantTemp,t.constantTemp,t.endTemp];this.initChart(e,r)},initChart:function(t,e){var l=document.getElementById("mnsjChart");l.style.width=window.innerWidth-300+"px",l.style.height="200px",this.chart=i.a.init(l),this.setOptionData(t,e)},setOptionData:function(t,e){this.chart.setOption({tooltip:{trigger:"axis"},grid:{top:10,left:"2%",right:"2%",bottom:"2%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:t}],yAxis:[{type:"value",name:"(℃)",axisTick:{show:!1},axisLabel:{margin:10,textStyle:{fontSize:14}}}],series:[{data:e,type:"line"}]})}}},b=m,u=(l("26bc"),l("2877")),d=Object(u["a"])(b,a,o,!1,null,"5212d2fe",null);e["default"]=d.exports}}]);