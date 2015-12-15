<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script	src="<s:url value="/script/validation/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script	src="<s:url value="/script/validation/additional_methods.js"/>"	type="text/javascript"></script>
<script	src="<s:url value="/script/validation/messages_cn.js"/>"	type="text/javascript"></script>
<script type="text/javascript"> 
        $(document).ready(function() {
            //表单验证
            var v = $("#regform").validate({
            	debug: false,
            	errorPlacement: function(error, element) {
            		error.insertAfter(element.parent().find('label:first'));
            	}, 
            	success: function(label) {
            		// set &nbsp; as text for IE
            		label.html("&nbsp;").addClass("ok");
            	} ,
    			rules: {
    				userName: {
    					required: true
    					//,	email:true
    				}
    			},
    			messages: {
    				userName: {
					  required: "请输入用户名"
						 //,  email:"请以邮件地址为用户名"
				     }
  
    			}
			
          });
        });

</script>
<title>忘记密码</title>
</head>
<body>
<div class="wrapper" style="margin-top:50px;">
		<div id="top_div"  >
		 <div style="width:50%">
	     <s:actionmessage	cssClass="tipbox tipbox-info" />
	      <s:actionerror cssClass="tipbox tipbox-error" />
        </div>
   <div class="cl register">
   <s:form id="regform" name="regform"  action="forgetPassword" namespace="/member"  method="post">
        <s:token />
    <fieldset>
	<legend >账户信息</legend>

	<p>
	<label class="required" for="firstname">用户名:</label>
    <br>
    <input id="userName" class="half valid " style="height:20px;line-height:20px;font-size:14px;padding-left:15px;" type="text" name="userName" value="">
</p>
	<div class="buttons">
	<button id="submitButton" name="submitButton" type="submit"  class="positive">
                 确&nbsp;&nbsp;认
     </button>
    </div>
						
	</fieldset>
</s:form>

	</div>
</div>
</div>
</body>
</html>