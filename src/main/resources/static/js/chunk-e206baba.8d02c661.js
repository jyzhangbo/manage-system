(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e206baba"],{2402:function(e,t,a){"use strict";var i=a("8994"),s=a.n(i);s.a},"822b":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"dashboard-editor-container"},[a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("div",[a("el-form",{ref:"listTime",attrs:{model:e.listTime,"label-width":"130px"}},[a("el-row",{attrs:{gutter:5}},[a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"起点:",prop:"alarmObject"}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:e.listTime.startTime,callback:function(t){e.$set(e.listTime,"startTime",t)},expression:"listTime.startTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"恒温点:",prop:"alarmTime"}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:e.listTime.stableTime,callback:function(t){e.$set(e.listTime,"stableTime",t)},expression:"listTime.stableTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"降温点:",prop:"alarmTime"}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:e.listTime.downTime,callback:function(t){e.$set(e.listTime,"downTime",t)},expression:"listTime.downTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"终点:",prop:"alarmTime"}},[a("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:e.listTime.endTime,callback:function(t){e.$set(e.listTime,"endTime",t)},expression:"listTime.endTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"设备编号:",prop:"deviceNum"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.listTime.deviceNum,callback:function(t){e.$set(e.listTime,"deviceNum",t)},expression:"listTime.deviceNum"}},e._l(e.options,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"间隔:",prop:"dataSpace"}},[a("el-input",{model:{value:e.listTemp.dataSpace,callback:function(t){e.$set(e.listTemp,"dataSpace",t)},expression:"listTemp.dataSpace"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"随机分钟:",prop:"randomTime"}},[a("el-input",{model:{value:e.listTemp.randomTime,callback:function(t){e.$set(e.listTemp,"randomTime",t)},expression:"listTemp.randomTime"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.simulationData()}}},[e._v("模拟数据")]),e._v(" "),a("el-button",{staticStyle:{"background-color":"#42b983"},attrs:{type:"success",icon:"el-icon-search"},on:{click:function(t){return e.copyData()}}},[e._v("复制数据")])],1)],1)],1)],1)]),e._v(" "),a("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[a("el-row",{staticClass:"panel-group"},[a("el-col",{attrs:{span:3,offset:21}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-picture"},on:{click:function(t){return e.changeShow()}}},[e._v("\n          切换视图\n        ")])],1)],1),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:e.chartShow,expression:"chartShow"}],staticClass:"chart-container",attrs:{id:"chartDiv"}},[a("div",{staticStyle:{width:"100%",height:"400px"},attrs:{id:"mnsjChart"}})]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:!e.chartShow,expression:"!chartShow"}]},[a("el-table",{attrs:{data:e.tabledatas,border:""}},[a("el-table-column",{attrs:{label:"tab1"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{directives:[{name:"show",rawName:"v-show",value:t.row.show,expression:"scope.row.show"}],attrs:{placeholder:"请输入内容"},model:{value:t.row.tab1,callback:function(a){e.$set(t.row,"tab1",a)},expression:"scope.row.tab1"}}),e._v(" "),a("span",{directives:[{name:"show",rawName:"v-show",value:!t.row.show,expression:"!scope.row.show"}]},[e._v(e._s(t.row.tab1))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"tab2"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{directives:[{name:"show",rawName:"v-show",value:t.row.show,expression:"scope.row.show"}],attrs:{placeholder:"请输入内容"},model:{value:t.row.tab2,callback:function(a){e.$set(t.row,"tab2",a)},expression:"scope.row.tab2"}}),e._v(" "),a("span",{directives:[{name:"show",rawName:"v-show",value:!t.row.show,expression:"!scope.row.show"}]},[e._v(e._s(t.row.tab2))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{on:{click:function(e){t.row.show=!0}}},[e._v("编辑")]),e._v(" "),a("el-button",{on:{click:function(e){t.row.show=!1}}},[e._v("保存")])]}}])})],1)],1),e._v(" "),a("div",[a("el-row",{attrs:{gutter:5}},e._l(e.listTemp,(function(t){return a("el-col",{key:t.name,attrs:{span:3}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[e._v("\n              "+e._s(t.name)+"\n            ")]),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-checkbox",{model:{value:t.effective,callback:function(a){e.$set(t,"effective",a)},expression:"item.effective"}},[e._v("\n                有效性\n              ")])],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"浮动值",clearable:""},model:{value:t.randomData,callback:function(a){e.$set(t,"randomData",a)},expression:"item.randomData"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"起点",clearable:""},model:{value:t.startTemp,callback:function(a){e.$set(t,"startTemp",a)},expression:"item.startTemp"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"恒温点",clearable:""},model:{value:t.stableTemp,callback:function(a){e.$set(t,"stableTemp",a)},expression:"item.stableTemp"}})],1),e._v(" "),a("div",{staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"降温点",clearable:""},model:{value:t.downTemp,callback:function(a){e.$set(t,"downTemp",a)},expression:"item.downTemp"}})],1),e._v(" "),a("div",{staticClass:"card-panel-description",staticStyle:{"font-size":"13px","padding-top":"10px"}},[a("el-input",{attrs:{placeholder:"终点",clearable:""},model:{value:t.endTemp,callback:function(a){e.$set(t,"endTemp",a)},expression:"item.endTemp"}})],1)])],1)})),1)],1)],1),e._v(" "),a("el-dialog",{attrs:{title:"数据复制",visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-row",{attrs:{gutter:10}},[a("el-col",{attrs:{span:"8"}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.listTime.deviceNum,callback:function(t){e.$set(e.listTime,"deviceNum",t)},expression:"listTime.deviceNum"}},e._l(e.options,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:"8"}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-input",{attrs:{placeholder:"固定增加数",clearable:""},model:{value:e.copyDataInput.addData,callback:function(t){e.$set(e.copyDataInput,"addData",t)},expression:"copyDataInput.addData"}}),e._v(" "),a("el-input",{attrs:{placeholder:"随机变动数",clearable:""},model:{value:e.copyDataInput.randomData,callback:function(t){e.$set(e.copyDataInput,"randomData",t)},expression:"copyDataInput.randomData"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:"8"}},[a("el-card",{staticClass:"box-card",staticStyle:{"border-radius":"20px"}},[a("el-checkbox-group",{model:{value:e.checkedCities,callback:function(t){e.checkedCities=t},expression:"checkedCities"}},e._l(e.cities,(function(t){return a("el-checkbox",{key:t,attrs:{label:t}},[e._v(e._s(t))])})),1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("\n        Cancel\n      ")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v("\n        Confirm\n      ")])],1)],1)],1)},s=[],l=(a("ac6a"),a("7f7f"),a("b85c")),n=a("b775");function o(e,t){return Object(n["a"])({url:"/data/simulation",method:"post",data:{startTime:e.startTime,stableTime:e.stableTime,downTime:e.downTime,endTime:e.endTime}})}var r=a("b117"),c=a("313e"),d=a.n(c),p=a("ed08"),m={data:function(){return{$_sidebarElm:null,$_resizeHandler:null}},mounted:function(){this.initListener()},activated:function(){this.$_resizeHandler||this.initListener(),this.resize()},beforeDestroy:function(){this.destroyListener()},deactivated:function(){this.destroyListener()},methods:{$_sidebarResizeHandler:function(e){"width"===e.propertyName&&this.$_resizeHandler()},initListener:function(){var e=this;this.$_resizeHandler=Object(p["b"])((function(){e.resize()}),100),window.addEventListener("resize",this.$_resizeHandler),this.$_sidebarElm=document.getElementsByClassName("sidebar-container")[0],this.$_sidebarElm&&this.$_sidebarElm.addEventListener("transitionend",this.$_sidebarResizeHandler)},destroyListener:function(){window.removeEventListener("resize",this.$_resizeHandler),this.$_resizeHandler=null,this.$_sidebarElm&&this.$_sidebarElm.removeEventListener("transitionend",this.$_sidebarResizeHandler)},resize:function(){var e=this.chart;e&&e.resize()}}},u={name:"LineChart",mixins:[m],data:function(){return{dialogFormVisible:!1,copyDataInput:{addData:"",randomData:""},listTime:{startTime:"",stableTime:"",downTime:"",endTime:""},listTemp:[{startTemp:"",stableTemp:"",downTemp:"",endTemp:"",randomData:"",deviceNum:"",effective:!0,name:"T1"},{startTemp:"",stableTemp:"",downTemp:"",endTemp:"",randomData:"",deviceNum:"",effective:!0,name:"T2"}],options:[{value:"选项1",label:"黄金糕"}],chart:null,tabledatas:[{tab1:"111",tab2:"2222",show:!0},{tab1:"aaa",tab2:"bbb",show:!1}],chartShow:!1,checkedCities:["上海","北京"],cities:["上海","北京","广州","深圳"]}},methods:{copyData:function(){this.dialogFormVisible=!0},changeShow:function(){!0===this.chartShow?this.chartShow=!1:(this.chartShow=!0,this.initChart())},initChart:function(){var e=this,t=document.getElementById("mnsjChart");t.style.width=window.innerWidth-300+"px",t.style.height="300px",this.chart=d.a.init(t),Object(r["a"])(this.listTime).then((function(t){var a,i=[],s=[],n=Object(l["a"])(t.data.yDatas);try{for(n.s();!(a=n.n()).done;){var o=a.value,r={name:o.name,type:"line",data:o.values};i.push(r),s.push(o.name)}}catch(c){n.e(c)}finally{n.f()}e.setOptionData(t.data.xDatas,i,s)}))},setOptionData:function(e,t,a){this.chart.setOption({tooltip:{trigger:"axis"},legend:{data:a},toolbox:{feature:{saveAsImage:{show:!0}}},grid:{top:100,left:"2%",right:"2%",bottom:"2%",containLabel:!0},xAxis:[{type:"category",boundaryGap:!1,data:e}],yAxis:[{type:"value",name:"(℃)",axisTick:{show:!1},axisLabel:{margin:10,textStyle:{fontSize:14}}}],series:t})},simulationData:function(){var e=this;o(this.listTime,this.listTemp).then((function(t){var a,i=[],s=[],n=Object(l["a"])(t.data.yDatas);try{for(n.s();!(a=n.n()).done;){var o=a.value,r={name:o.name,type:"line",data:o.values};i.push(r),s.push(o.name)}}catch(c){n.e(c)}finally{n.f()}e.setOptionData(t.data.xDatas,i,s)}))}}},b=u,v=(a("2402"),a("2877")),h=Object(v["a"])(b,i,s,!1,null,"3f73d982",null);t["default"]=h.exports},8994:function(e,t,a){},b117:function(e,t,a){"use strict";a.d(t,"a",(function(){return s})),a.d(t,"b",(function(){return l}));var i=a("b775");function s(e){return Object(i["a"])({url:"/data/query",method:"post",data:{}})}function l(){return Object(i["a"])({url:"/data/query/table",method:"post",data:{}})}}}]);