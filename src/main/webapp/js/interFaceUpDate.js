$(function(){
	
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
                		 //保存成功取消遮罩
                		 $(".maskBox").removeClass("maskBox");
                          alert("保存成功");
                           $("#ifId").val(data.interFaceInfo.id);
                      }else{
                          alert("保存失败");
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
	                     $(recordForm).find(".levelCode").val(data.interFaceField.id);
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
})


function propAddHandel(userId){
	
	var propAddReq = propAddRequest("getAllSysInfo.do",getSys,userId);
	propAddReq.request();
	
	var propAddReqa = propAddRequest("getAllProInfo.do",getProduct,userId);
	propAddReqa.request();
	
	var getCurProp = propAddRequest("queryInterFaceById.do",GetCurProp,userId);
	getCurProp.request();
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
				console.log(e);
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
	console.log(result);
	backData.rander = function(){
		$("#id").val(result.interFaceInfo.id);
		$("#ifId").val(result.interFaceInfo.id);
		$("#ifName").val(this.interFaceInfo.ifName);
		$("#ifDesc").val(this.interFaceInfo.ifDesc);
		$("#ifUrl").val(this.interFaceInfo.ifUrl);
		//获取当前接口所属系统
		var ifSysName = this.interFaceInfo.interFaceSysTem.sysName;
		var optionsSys = $("#ifSysId option");
		for(var i=0;i<optionsSys.length;i++){
			if(ifSysName==(optionsSys[i].label)){
				$(optionsSys[i]).attr("selected","selected");
			}
		}
		//获取当前接口所属产品
		var ifProName = this.interFaceInfo.interFacePro.proName;
		var optionsPro = $("#ifProId option");
		for(var i=0;i<optionsPro.length;i++){
			if(ifProName==(optionsPro[i].label)){
				$(optionsPro[i]).attr("selected","selected");
			}
		}
		
		var field = this.interFaceInfo.fieldInfoList;
		
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
					   +'例子：<input name="fieldReferValue" placeholder="请输入字段例子" value='+field[i].fieldReferValue+'><br />'
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
					   +'<input type="hidden" class="levelCode" name="level" value='+field[i].level+'"/>'
					   +'字段名：<input name="fieldName" value='+field[i].fieldName+' placeholder="请输入字段名"/>'
					   +	'字段约束：<select name="fieldCheck" data-type='+field[i].fieldCheck+' data-am-selected="{btnWidth:"10%"}">'
					   +'<option value="0" selected>请选择</option><option value="4">数组</option>'
					   +'<option value="2">邮箱</option></select>&nbsp;&nbsp;&nbsp;&nbsp;'
					   +'字段描述(注解)：<input name="fieldDesc" placeholder="请输入字段描述" value='+field[i].fieldDesc+'>'
					   +'例子：<input name="fieldReferValue" placeholder="请输入字段例子" value='+field[i].fieldReferValue+'><br />'
					   +'<div style="text-align: right;">'
					   +'<button type="button" class="am-btn am-btn-secondary addPropBtn">十</button>'
					   +'<button type="button" class="am-btn am-btn-primary addChildBtn">十Child</button>'
					   +'<button type="button" class="am-btn am-btn-success saveBtn">保存</button>'
					   +'<button type="button" class="am-btn am-btn-danger deleteBtn">删除</button>'
					   +'<button type="button" class="cancleCurBtn am-btn am-btn-warning" style="display: none">取消添加</button></div></form></td></tr>'
					  
			}
		}
		console.log(reqflag);
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