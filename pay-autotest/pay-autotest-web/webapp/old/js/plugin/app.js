var gigold = gigold || {};

var contextPath="/ifSys/"
gigold.pay = gigold.pay || {};
gigold.pay.interFace = gigold.pay.interFace || {};

gigold.pay.interFace.ajaxHandler = function(options) {
	var defaults = {
		"type" : "POST",
		"url" : "",
		"dataType" : "json",
		"contentType" : "application/json",
		//"data" : null,
		"async" : false,
		"onError" : function(request) {
			alert("Connection error");
		},
		"onSuccess" : function(data) {
			//
		}
	};
	var defaults = $.extend({}, defaults, options);
	$.ajax({
		type : defaults.type,
		url : defaults.url,
		dataType : defaults.dataType,
		contentType : defaults.contentType,
		data : defaults.data,
		async : defaults.async,
		error : function(request) {
			defaults.onError(request);
			console.log('%c error ','color: red');
		},
		success : function(data) {
			defaults.onSuccess(data)
		}
	});
}



