(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2a430e90"],{"0c7e":function(e,a,i){"use strict";i.r(a);var t=function(){var e=this,a=e.$createElement,i=e._self._c||a;return i("div",{staticClass:"dashboard-editor-container"},[i("div",{staticClass:"panel-group",staticStyle:{"background-color":"white"}},[i("el-upload",{attrs:{action:"http://localhost:8080/file/upload",name:"upfile","on-preview":e.handlePreview,"on-remove":e.handleRemove,"file-list":e.fileList,"list-type":"picture-card"}}),e._v(" "),i("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(a){e.dialogVisible=a}}},[i("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)])},l=[],o={data:function(){return{fileList:[{name:"food.jpeg",url:"https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"},{name:"food2.jpeg",url:"https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"}],dialogImageUrl:"",dialogVisible:!1}},methods:{handleRemove:function(e,a){console.log(e,a)},handlePreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0}}},n=o,d=(i("e39d"),i("2877")),s=Object(d["a"])(n,t,l,!1,null,"127c9ad2",null);a["default"]=s.exports},"6cfd":function(e,a,i){},e39d:function(e,a,i){"use strict";var t=i("6cfd"),l=i.n(t);l.a}}]);