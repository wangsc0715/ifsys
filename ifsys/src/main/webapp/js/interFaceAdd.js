$(function() {
	$("#ifId").val($.getUrlParam("ifId"));

	$(".loggxnxi").html(
			"登录用户：<span class='colorBlue'>" + $.getUrlParam("userName")
					+ " </span>");
	// 查询接口（首页）,用户信息
	$(".QueryInterface").on(
			"click",
			function() {
				window.location.href = "main.html?userName="
						+ $.getUrlParam("userName");
			});
	// 添加接口，用户信息
	$(".AddInterface").on(
			"click",
			function() {
				window.location.href = "interFaceBaseInfo.html?userName="
						+ $.getUrlParam("userName");
			});
	var i = 0;

	$(".addResBtn").on("click", function() {
		var resForm = $("#resForm");
		var cloneForm = $(resForm).clone();
		$(cloneForm).find("input").val("");
		if (i == 0) {
			$("#resForm").show();
		} else {
			$(resForm).after(cloneForm);
		}
		i++;

	});

	// 添加返回码
	$(document).on("click", ".addRspBtn", function() {
		var recordForm = $(this).parent().parent();
		console.log(recordForm);
		var cloneForm = $(recordForm).clone();
		// 将form表单元素的值置空
		$(cloneForm).clear();
		// 在当前form后面新增一个form
		$(recordForm).after(cloneForm);
	});

	// 保存返回码
	$(document).on("click", ".saveRspBtn", function() {
		var recordForm = $(this).parent().parent();
		var sendData = {};
		sendData = $(recordForm).serializeJson();
		sendData.ifId = $("#ifId").val();
		sendData.id = $("#id").val();
		console.log(sendData);
		gigold.pay.interFace.ajaxHandler({
			"url" : "addrspcd.do",
			"data" : JSON.stringify(sendData),
			"onSuccess" : function(data) {
				console.log(data.id);
				console.log(data);
				if (data.rspCd == "00000") {
					$(recordForm).find("input[name=id]").val(data.id);
					$(recordForm).find("button[name=delRspBtn]").show();
					alert("保存成功");
				} else {
					alert(data.rspInf);
				}
			}
		});

	});
	// 删除返回码
	$(document).on("click",".delRspBtn",function() {
				var recordForm = $(this).parent().parent();
				var sendData = {};
				sendData = $(recordForm).serializeJson();
				sendData.ifId = $("#ifId").val();
				console.log(sendData);
				gigold.pay.interFace.ajaxHandler({
							"url" : "delrspcdbyid.do",
							"data" : JSON.stringify(sendData),
							"onSuccess" : function(data) {
								if (data.rspCd == "00000") {
									$(recordForm).remove();
								} else {
									alert(data.rspInf);
								}
							}
						});

			});

	// 加载系统信息
	gigold.pay.interFace.ajaxHandler({
		"url" : "getAllSysInfo.do",
		"onSuccess" : function(data) {
			if (data.rspCd == "00000") {
				$.each(data.sysList, function(index, sysData) {
					var optionStr = '<option value="' + sysData.id + '"">'
							+ sysData.sysName + '</option>';
					console.log(optionStr);
					$("#ifSysId").append(optionStr);
				});
			}
		}
	});

	// 加载产品信息
	gigold.pay.interFace.ajaxHandler({
		"url" : "getAllProInfo.do",
		"onSuccess" : function(data) {
			if (data.rspCd == "00000") {
				$.each(data.proList, function(index, proData) {
					var optionStr = '<option value="' + proData.id + '">'
							+ proData.proName + '</option>';
					$("#ifProId").append(optionStr);
				});
			}
		}
	});

	// 保存按钮
	$(document).on(
			"click",
			".saveBtn",
			function() {
				// 获取按钮所在的form对象
				var recordForm = $(this).parent().parent();
				var sendData = {};
				sendData.interFaceField = $(recordForm).serializeJson();
				sendData.interFaceField.ifId = $("#ifId").val();

				$(recordForm).find("button[type=button]").removeClass(
						"am-disabled");
				$(recordForm).find(".saveBtn").addClass("am-disabled");
				$(recordForm).find(".cancleCurBtn").addClass("am-disabled");

				/*
				 * 保存成功后获取字段的ID更新id＝"fieldId"的隐藏域
				 * 
				 */
				console.log(sendData);
				$(recordForm).find("button[type=button]").removeClass(
						"am-disabled");
				$(recordForm).find(".saveBtn").addClass("am-disabled");
				$(recordForm).find(".cancleCurBtn").addClass("am-disabled");
				gigold.pay.interFace.ajaxHandler({
					"url" : "addInterFaceField.do",
					"data" : JSON.stringify(sendData),
					"onSuccess" : function(data) {
						if (data.rspCd == "00000") {
							$(recordForm).find(".fieldId").val(
									data.interFaceField.id);
							$(recordForm).find(".levelCode").val(
									data.interFaceField.level);
							console.log(data);
							alert("保存成功");
							$(recordForm).find("button[type=button]")
									.removeClass("am-disabled");
							$(recordForm).find(".saveBtn").addClass(
									"am-disabled");
							$(recordForm).find(".cancleCurBtn").addClass(
									"am-disabled");
						} else {
							alert("保存失败");
						}
					}
				});
			});

	// 添加同级节点按钮
	$(document).on(
			"click",
			".addCurBtn",
			function() {
				// 获取按钮所在的form对象
				var recordForm = $(this).parent().parent();
				// 复制一份form对象
				var cloneForm = $(recordForm).clone();
				// 应该清空表单所有录入项
				$(cloneForm).find("input[type!=hidden]").find(
						"input[name!=fieldStatusCode]").val("");
				$(cloneForm).find(".amContrller").addClass("am-disabled");
				$(cloneForm).find(".saveBtn").removeClass("am-disabled");
				$(cloneForm).find(".cancleCurBtn").show().removeClass(
						"am-disabled");
				// 在当前form后面新增一个form
				$(recordForm).after(cloneForm);
			});

	// 添加子节点按钮
	$(document).on(
			"click",
			".addChildBtn",
			function() {
				// 获取按钮所在的form对象
				var recordForm = $(this).parent().parent();
				// 复制一份form对象
				var cloneForm = $(recordForm).clone();
				// 获取隐藏域
				$(cloneForm).find("input[name=parentId]").val(
						$(cloneForm).find(".fieldId").val());
				// 应该清空表单所有录入项
				$(cloneForm).find("input[type!=hidden]").find(
						"input[name!=fieldStatusCode]").val("");
				$(cloneForm).find(".amContrller").addClass("am-disabled");
				$(cloneForm).find(".saveBtn").removeClass("am-disabled");
				$(cloneForm).find(".cancleCurBtn").show().removeClass(
						"am-disabled");
				// 在当前form后面新增一个form
				$(recordForm).after(cloneForm);
				$(cloneForm).find(".show").append(
						"-->"
								+ $(recordForm).find("input[name=fieldName]")
										.val());
			});
	// 删除字段按钮
	$(document).on("click", ".deleteCurBtn", function() {
		// 获取按钮所在的form对象
		var recordForm = $(this).parent().parent();
		var recordForm = $(this).parent().parent();
		var sendData = {};
		sendData.interFaceField = $(recordForm).serializeJson();
		sendData.interFaceField.ifId = $("#ifId").val();
		gigold.pay.interFace.ajaxHandler({
			"url" : "deleteFieldByLevel.do",
			"data" : JSON.stringify(sendData),
			"onSuccess" : function(data) {
				if (data.rspCd == "00000") {
					$(recordForm).remove();
				} else {
					alert("删除失败");
				}
			}
		});

	});

	// 取消按钮
	$(document).on("click", ".cancleCurBtn", function() {
		// 获取按钮所在的form对象
		var recordForm = $(this).parent().parent();
		$(recordForm).remove();
	});

});