/** * Created by marszed on 16/3/8. */define(function(require, exports, module){	//引入jq依赖	var $ = require('jquery'),		layer = require('layer');	//创建方法库对象	function GiGoldPay(){};	GiGoldPay.prototype.ajaxHandler = function(options){		var defaults = {			"type" : "POST",			"url" : "",			"dataType" : "json",			"contentType" : "application/json",			"data" : JSON.stringify({}),			"async" : false,			"onError" : function(data) {				layer.msg(data);			},			"onSuccess" : function(data) {				//TODO 成功弹层				console.log("接口请求成功-->>");				console.log(data);			}		};		var defaults = $.extend({}, defaults, options);		$.ajax({			type : defaults.type,			url : defaults.url,			dataType : defaults.dataType,			contentType : defaults.contentType,			data : JSON.stringify(defaults.data),			async : defaults.async,			error : function(res) {				defaults.onError(res);			},			success : function(res) {				defaults.onSuccess(res)			}		});	};	GiGoldPay.prototype.cancleLock = function(data){		setTimeout(function(){			data = false;			$('#slider').removeClass('loading');		},2000);	};	GiGoldPay.prototype.ipBullShit = function(){		return 	'';		//return 	'http://123.57.152.75:8051';		//return 	'http://192.168.2.20:8080';	};	// 对外提供 msg 属性	//exports.msg = "hello index, i'm common";	// 通过 exports 对外提供接口	//exports.doSomething =  function(){	//	console.log("hello index, i'm common");	//};	// 或者通过 module.exports 提供整个接口	module.exports = {		GiGoldPay: GiGoldPay	};});