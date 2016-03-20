$(function(){
	
	$(".loggxnxi").html("登录用户：<span class='colorBlue'>" + $.getUrlParam("userName") + " </span>");
	// 查询接口（首页）,用户信息
	$(".QueryInterface").on("click",function(){
		window.location.href="main.html?userName="+$.getUrlParam("userName");
	});
	//添加接口，用户信息
	$(".AddInterface").on("click",function(){
		window.location.href="interFaceBaseInfo.html?userName="+$.getUrlParam("userName");
	});
	
	
    var userId = $.getUrlParam("id");
	propAddHandel(userId);
	//基本信息保存
	$("#saveInterFaceBtn").click(function() {
		var sendData = {};
		sendData.interFaceInfo = $('#interFaceForm').serializeJson();
		console.log(sendData);
		gigold.pay.interFace.ajaxHandler({
                "url":"updateInterFace.do",
                "data":JSON.stringify(sendData),
                "onSuccess":function(data){
                	 if (data.rspCd == "00000") {
                		 $(".maskBox").removeClass("maskBox");
                          alert("保存成功");
                      }else{
                          alert("保存失败");
                      }
                }
            });

	});
	
	//加载接口返回码信息
	var postData={"ifId":userId};
	gigold.pay.interFace.ajaxHandler({
		"url" : "getrspcdbyifid.do",
		"data" : JSON.stringify(postData),
		"onSuccess" : function(data) {
			if (data.rspCd == "00000") {
				var formObj=$("form[name=resForm]");
				var size=data.list.length;
				
				$.each(data.list,function(index,row){
					$(formObj).find("input[name=id]").val(row.id);
					$(formObj).find("input[name=rspCode]").val(row.rspCode);
					$(formObj).find("input[name=rspCodeDesc]").val(row.rspCodeDesc);
					if(index<size-1){
						var colneFrom=formObj.clone();
						$(formObj).after(colneFrom);
						formObj=colneFrom;
					}
					
				});
			} else {
				alert(data.rspInf);
			}
		}
	});
	
	
	$('#ifSysId').change(function(){ 
		var sysId=	$(this).children('option:selected').val();
		var sendData={
				"interFacePro":{
					"sysId":sysId
				}
			};
		//加载产品信息
		  gigold.pay.interFace.ajaxHandler({
				"url":"getProInfoBySysId.do",
				"data":JSON.stringify(sendData),
				"onSuccess":function(data){
					if (data.rspCd == "00000") {
						$("#ifProId").removeAttr("disabled");  
						 var optionStr='';
						$.each(data.proList, function(index, proData) {
	                        optionStr += '<option value="'+proData.id+'">'
	                                + proData.proName + '</option>';
	                       
	                    });
						 $("#ifProId").html(optionStr);
	              }
				}
			});
		});
	
	
	
	//添加返回码
	$(document).on("click",".addRspBtn",function(){
		  var recordForm = $(this).parent().parent();
		  console.log(recordForm);
		  var cloneForm = $(recordForm).clone();
		   //清空表单元素的值
		  $(cloneForm).clear();
		//在当前form后面新增一个form
	        $(recordForm).after(cloneForm);
	});
	
	
	//保存返回码
	$(document).on("click",".saveRspBtn",function(){
		 var recordForm = $(this).parent().parent();
		var sendData = {};
		sendData = $(recordForm).serializeJson();
		sendData.ifId=$("#ifId").val();
		console.log(sendData);
		gigold.pay.interFace.ajaxHandler({
                "url":"addrspcd.do",
                "data":JSON.stringify(sendData),
                "onSuccess":function(data){
                	console.log(data);
                	 if (data.rspCd == "00000") {
                          alert("保存成功");
                      }else{
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
	
	
	
	//添加同级字段
	$(document).on("click",".addPropBtn",function(){
        //获取按钮所在的form对象
        var recordForm = $(this).parent().parent();
		console.log(recordForm)
        //复制一份form对象
        var cloneForm = $(recordForm).clone();
        //应该清空表单所有录入项
       $(cloneForm).find("input[type!=hidden]").val("");
       $(cloneForm).find("input[name=operFlag]").val("insert");
       $(cloneForm).find(".am-btn-default").addClass("am-disabled");
       $(cloneForm).find(".saveBtn").removeClass("am-disabled");
       $(cloneForm).find(".cancleCurBtn").show().removeClass("am-disabled");
        //在当前form后面新增一个form
        $(recordForm).after(cloneForm);
		
	});
	//添加子字段
	$(document).on("click",".addChildBtn",function(){
		var canCle = '<button type="button" class="am-btn am-btn-warning cancleCurBtn">取消添加</button>';
        //获取按钮所在的form对象
        var recordForm = $(this).parent().parent();
        //复制一份form对象
        var cloneForm = $(recordForm).clone(true);
        //获取隐藏域
        $(cloneForm).find("input[name=parentId]").val($(cloneForm).find(".fieldId").val());
        $(cloneForm).find("input[name=operFlag]").val("insert");
        //应该清空表单所有录入项
        $(cloneForm).find("input[type!=hidden]").val("");
        $(cloneForm).find(".am-btn-default").addClass("am-disabled");
        $(cloneForm).find(".saveBtn").removeClass("am-disabled");
        $(cloneForm).find(".cancleCurBtn").show().removeClass("am-disabled");
        //在当前form后面新增一个form
        $(recordForm).after(cloneForm);
        $(cloneForm).find(".show").append("-->"+ $(recordForm).find("input[name=fieldName]").val());
		
	});
	//保存字段
	$(document).on("click",".saveBtn",function(){
		//获取按钮所在的form对象
		var recordForm = $(this).parent().parent();
		var sendData={};
		sendData.interFaceField=$(recordForm).serializeJson();
		sendData.interFaceField.ifId=$("#ifId").val();
		
		$(recordForm).find("button[type=button]").removeClass("am-disabled");
        //$(recordForm).readOnlyForm();
        $(recordForm).find(".saveBtn").addClass("am-disabled");
        $(recordForm).find(".cancleCurBtn").addClass("am-disabled");
		
		/*
		   保存成功后获取字段的ID更新id＝"fieldId"的隐藏域
		
		*/
		console.log(sendData);
		var ajaxResData={
	             //"url":"updateInterFaceField.do",
	             "data":JSON.stringify(sendData),
	             "onSuccess":function(data){
	            	 if (data.rspCd == "00000") {
	            		 alert("保存成功");
	            		 if($(recordForm).find("input[name=operFlag]").val()=='insert'){
	            		 $(recordForm).find(".fieldId").val(data.interFaceField.id);
	                     $(recordForm).find(".levelCode").val(data.interFaceField.level);
	            		 }
	                     console.log(data);
	                     
	                     $(recordForm).find("button[type=button]").removeClass("am-disabled");
	                     $(recordForm).find(".saveBtn").addClass("am-disabled");
	                     $(recordForm).find(".cancleCurBtn").addClass("am-disabled");
	                     $(recordForm).find("input[name=operFlag]").val("update");
	                 }else{
	                     alert("保存失败");
	                 }
	             }
	         };;
		if($(recordForm).find("input[name=operFlag]").val()=='update'){
		    ajaxResData.url="updateInterFaceField.do"
		}
		if($(recordForm).find("input[name=operFlag]").val()=='insert'){
		    ajaxResData.url="addInterFaceField.do"
		}
		 gigold.pay.interFace.ajaxHandler(ajaxResData);
	});
	//删除字段
	$(document).on("click",".deleteBtn",function(){
		//获取按钮所在的form对象
        var recordForm = $(this).parent().parent();
        var recordForm = $(this).parent().parent();
        var sendData={};
        sendData.interFaceField=$(recordForm).serializeJson();
        sendData.interFaceField.ifId=$("#ifId").val();
        gigold.pay.interFace.ajaxHandler({
            "url":"deleteFieldByLevel.do",
            "data":JSON.stringify(sendData),
            "onSuccess":function(data){
                if (data.rspCd == "00000") {
                	 $(recordForm).remove();
                }else{
                    alert("删除失败");
                }
            }
        });
	});

	//取消字段添加
    $(document).on("click",".cancleCurBtn",function() {
        //获取按钮所在的form对象
        var recordForm = $(this).parent().parent();
        $(recordForm).remove();
    });
    //修改协议
    $(document).on("change", "#ifProtocolId", function() {
		if($(this).val()=="HTTP"){
			$("#asd,#wer").css({"display":"none"});
			$("#zxc").css({"display":"table-row"})
			$("#asd,#wer").find("input").attr("disabled","disabled");
			$("#zxc").find("select").removeAttr("disabled");
		}else if($(this).val()=="FTP"){
			
		}else if($(this).val()=="DUBBO"){
			$("#zxc").hide();
			$("#asd,#wer").show();
			$("#zxc").find("select").attr("disabled","disabled");
			$("#asd,#wer").find("input").removeAttr("disabled");
		}
	});
	
})


function propAddHandel(userId){
	
	var getSysProp = propAddRequest("getAllSysInfo.do",getSys,userId);
	getSysProp.request();
	
	
	
	var getCurProp = propAddRequest("queryInterFaceById.do",GetCurProp,userId);
	getCurProp.request();
	

}


function getProductBySysId(sysId){
	var sendData={
			"interFacePro":{
		       "sysId":sysId
	         }
	}
	console.log("hhhhhh");
	console.log(sendData);
	gigold.pay.interFace.ajaxHandler({
		"url" : "getProInfoBySysId.do",
		"data" : JSON.stringify(sendData),
		"onSuccess" : function(data) {
			console.log(data);
			if (data.rspCd == "00000") {
				getProduct(data)
			}
		}
	});
}

//ajax获取接口信息
function propAddRequest(urlStr,fn,userId){
	
	var data = {
		"interFaceInfo":{
			"id":userId,
		}
	};
	data.request=function (perData){
		$.ajax({
			type:"post",
			url:urlStr,
			dataType:'json',
			async:"false",
			contentType : "application/json",
			data:addParam(data),
			success:fn,
			error:function(e){
				alert("Error")
			}
		});
		
	}
	return data;
}
//获取所有系统信息
function getSys(result){
	var propAddData = propAddRander(result);
	propAddData.rander();
}
//渲染接口所属系统
function propAddRander(result){
	var backData = {};
	backData = result;
	backData.rander = function(){
		var sysList = this.sysList;
		var str = "";
		for(var i=0;i<sysList.length;i++){
			str += '<option value='+sysList[i].id+'>'+sysList[i].sysName+'</option>';
		}
		$("#ifSysId").html(str);
	}
	return backData;
}


//渲染接口接口信息
function getProduct(result){
	var propAddData = propAddRandera(result);
	propAddData.rander();
}
//渲染接口所属产品
function propAddRandera(result){
	var backData = {};
	backData = result;
	backData.rander = function(){
		var proList = this.proList;
		var str = "";
		for(var i=0;i<proList.length;i++){
			str += '<option value='+proList[i].id+'>'+proList[i].proName+'</option>';
		}
		$("#ifProId").html(str);
	}
	return backData;
}



//渲染接口接口信息
function GetCurProp(result){
	var propAddData = getCurProp(result);
	propAddData.rander();
}
function getCurProp(result){
	var backData = {};
	backData = result;
	backData.rander = function(){
		$("#id").val(this.interFaceInfo.id);
		$("#ifId").val(this.interFaceInfo.id);
		$("#ifName").val(this.interFaceInfo.ifName);
		$("#ifDesc").val(this.interFaceInfo.ifDesc);
		$("#ifUrl").val(this.interFaceInfo.ifUrl);
		var ifProtocolId = this.interFaceInfo.ifProtocol;
		if(ifProtocolId=="HTTP"){
			//获取当前接口请求方式
			var ifTypeName = this.interFaceInfo.ifType;
			var ifTypes = $("#ifTypeId option");
				for(var i=0;i<ifTypes.length;i++){
					if(ifTypeName==(ifTypes[i].label)){
						$(ifTypes[i]).attr("selected","selected");
						
					}
				}
				$("#wer").hide();
				$("#method").attr("disabled");
				$("#asd").hide();;
				$("#methodVersion").attr("disabled");
		}else if(ifProtocolId=="DUBBO"){
			$("#method").val(this.interFaceInfo.method);
			$("#methodVersion").val(this.interFaceInfo.methodVersion);
			$("#zxc").hide();
			$("#ifTypeId").attr("disabled");
		}
		
		
		//获取当前接口所属系统
		var ifSysName = this.system.sysName;
		var optionsSys = $("#ifSysId option");
		for(var i=0;i<optionsSys.length;i++){
			if(ifSysName==(optionsSys[i].label)){
				$(optionsSys[i]).attr("selected","selected");
				getProductBySysId(this.system.id);
			}
		}
		//获取当前接口所属产品
		var ifProName = this.pro.proName;
		var optionsPro = $("#ifProId option");
		for(var i=0;i<optionsPro.length;i++){
			if(ifProName==(optionsPro[i].label)){
				$(optionsPro[i]).attr("selected","selected");
			}
		}
		//获取当前接口所属协议
		var ifProtocolName = this.interFaceInfo.ifProtocol;
		var ifProtocols = $("#ifProtocolId option");
		for(var i=0;i<ifProtocols.length;i++){
			if(ifProtocolName==(ifProtocols[i].label)){
				$(ifProtocols[i]).attr("selected","selected");
			}
		}
		
		
		//获取当前接口协议
		var ifTypes = $("#ifProtocolId option");
			for(var i=0;i<ifTypes.length;i++){
				if(ifProtocolId==(ifTypes[i].label)){
					$(ifTypes[i]).attr("selected","selected");
				}
			}
		
		var field = this.fieldList;
		console.log(field);
		
		//获取接口所有字段
		var resStr = "";
		var reqStr = "";
		var resflag=false;
		var reqflag=false;
		
		for(var i=0;i<field.length;i++){
			if(field[i].fieldFlag==1){
				reqflag=true;
				reqStr += '<tr><td><form><div class="show"></div>'
					   +'<input type="hidden" class="fieldId" name="id" value="'+field[i].id+'">'
					   +'<input type="hidden" name="parentId" value='+field[i].parentId+'>'
					   +'<input type="hidden" name="fieldFlag" value="1"/>'
					   +'<input type="hidden" name="operFlag" value="update"/>'
					   +'<input type="hidden" class="levelCode" name="level" value='+field[i].level+'>'
					   +'字段名：<input name="fieldName" value='+field[i].fieldName+' placeholder="请输入字段名"/>'
					   +	'字段约束：<select name="fieldCheck" data-type='+field[i].fieldCheck+' data-am-selected="{btnWidth: "10%"}">'
					   +'<option value="0" selected>请选择</option><option value="4">数组</option>'
					   +'<option value="2">邮箱</option></select>&nbsp;&nbsp;&nbsp;&nbsp;'
					   +'字段描述(注解)：<input name="fieldDesc" placeholder="请输入字段描述" value='+field[i].fieldDesc+'>'
					   +'<br>例子：<textarea name="fieldReferValue" placeholder="请输入字段例子" class="lzsty">'+field[i].fieldReferValue+'</textarea><br />'
					   +'<div style="text-align: right;">'
					   +'<button type="button" class="am-btn am-btn-secondary addPropBtn">十</button>'
					   +'<button type="button" class="am-btn am-btn-primary addChildBtn">十Child</button>'
					   +'<button type="button" class="am-btn am-btn-success saveBtn">保存</button>'
					   +'<button type="button" class="am-btn am-btn-danger deleteBtn">删除</button>'
					   +'<button type="button" class="cancleCurBtn am-btn am-btn-warning" style="display: none">取消添加</button></form></td></tr>'
					  
			}else{
				resflag=true;
				resStr += '<tr><td><form><div class="show"></div>'
					+'<input type="hidden" class="fieldId" name="id" value='+field[i].id+'>'
					 +'<input type="hidden" name="parentId" value='+field[i].parentId+'>'
					   +'<input type="hidden" name="fieldFlag" value="2"/>'
					   +'<input type="hidden" name="operFlag" value="update"/>'
					   +'<input type="hidden" class="levelCode" name="level" value='+field[i].level+'>'
					   +'字段名：<input name="fieldName" value='+field[i].fieldName+' placeholder="请输入字段名"/>'
					   +	'字段约束：<select name="fieldCheck" data-type='+field[i].fieldCheck+' data-am-selected="{btnWidth:"10%"}">'
					   +'<option value="0" selected>请选择</option><option value="4">数组</option>'
					   +'<option value="2">邮箱</option></select>&nbsp;&nbsp;&nbsp;&nbsp;'
					   +'字段描述(注解)：<input name="fieldDesc" placeholder="请输入字段描述" value='+field[i].fieldDesc+'>'
					   +'<br>例子：<textarea name="fieldReferValue" placeholder="请输入字段例子" class="lzsty">'+field[i].fieldReferValue+'</textarea><br />'
					   +'<div style="text-align: right;">'
					   +'<button type="button" class="am-btn am-btn-secondary addPropBtn">十</button>'
					   +'<button type="button" class="am-btn am-btn-primary addChildBtn">十Child</button>'
					   +'<button type="button" class="am-btn am-btn-success saveBtn">保存</button>'
					   +'<button type="button" class="am-btn am-btn-danger deleteBtn">删除</button>'
					   +'<button type="button" class="cancleCurBtn am-btn am-btn-warning" style="display: none">取消添加</button></div></form></td></tr>'
					  
			}
		}
		//渲染字段信息
		if(resflag){
		   $(".responseDefine").html(resStr);
		}
        if(reqflag){
	      $(".requestDefine").html(reqStr);
		}
		
		//判断类型
		$(".requestDefine select").each(function(a,b){
			var typeNum = $(b).attr("data-type");
			$(b).val(typeNum);
		})
		//判断类型
		$(".responseDefine select").each(function(a,b){
			var typeNum = $(b).attr("data-type");
			$(b).val(typeNum);
		})
	}
	return backData;
}