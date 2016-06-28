
			$(function(){
				   $("#loginBtn").click(function(){
					   var saveData={
				               "userInfo":{
				                    "loginName":$("#doc-vld-name-2-0").val(),
				                    "loginPassword":$("#doc-vld-pwd-1-0").val()
				               }
				           };
				           
					   $.ajax({
				            type : "POST",
				            url : "login.do",
				            dataType:"json",      
				            contentType:"application/json", 
				            data:JSON.stringify(saveData),
				            async : false,
				            error : function(request) {
				                	alert("System error");
				            },
				            success : function(data) {
				            	console.log(data);
				               if(data.rspCd=="D0000"){
				               	$(".navHint").show();
				               }
				               if(data.rspCd=="00000"){
				            	   //设置参数
				            	   cacheService.setProterties("userInfo",data.userInfo);
			                       window.location.href="main.html?userName="+data.userInfo.userName
			                   }
				            }
				        }); 
					   
				   });
				   
			   });