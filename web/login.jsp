<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="<c:url value="/style/login.css"/>" type="text/css" rel="stylesheet"/>
<script	src="<c:url value="/script/validation/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/additional_methods.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/messages_cn.js"/>"	type="text/javascript"></script>
<script type="text/javascript"> 
        $(document).ready(function() {
            //表单验证
            var v = $("#loginform").validate({
            	debug: false,
    			errorElement: "em",
    			success: function(label) {
    				label.text("OK!").addClass("success");
    			},
    			//errorContainer: $("#warning, #summary"),
    			errorPlacement: function(error, element) {
    			    error.appendTo( element.parent("div").find("div") );
    			},
    			
    			//(Demo)可以通过在此处添加校验规则和校验信息,默认已经通过字段的CSS样式添加了校验信息.
    			rules: {
    				userName: {
    					required: true
    					//,email:true
    				},
    				password: {
    					required: true,
    					minlength:6
    				},
    				captchaCode:"required"
    			},
    			messages: {
    				userName: {
					  required: "请输入用户名"
						  //,  email:"请输入正确的邮件账户形式的用户名"
				     },
				     password: {
					   required: "请输入密码",
					   minlength:"密码长度不低于6位"
				   },
				   captchaCode:{
					   required: "请输入验证码"
				   }
					   
    			}
			
          });
        });
</script>          
<title>登录</title>
<script>
function refreshCheckCode(){
	$("#check_img").removeAttr("src");
	$("#check_img").attr("src","<s:url action='jcaptcha_image' namespace='/' />"+"?rand="+Math.random());	
}
</script>
</head>
<body  style="background-color: #F4F4F4">
<center>
   <div id="container">
            <!-- Header start -->
          <div id="header">
                <div id="logo">
                    
                </div>
                <div class="clear"></div>
          </div>
           <!-- Header end -->
            <div class="clear"></div>
<div id="loginwrapper" class="inc_wrapper">
	<div id="loginnav" class="nav">首页 &gt; 用户登录</div>
	<!-- 布局 -->
	<div class="layout grid-m0s5">
		<!-- 布局main -->
        <div class="col-main">
            <div class="main-wrap">
                <!-- 注册表单 -->
				<div class="reg_reg" id="login_index_left">
					
					 <div class="reg_title"><!-- <img src="image/layout/user_login.jpg" alt="欢迎加入">--></div>
					
					<div  style="width:80%">
	                <s:actionmessage	cssClass="tipbox tipbox-info" />
	                <s:actionerror cssClass="tipbox tipbox-error" />
					</div>
					
                    <s:form id="loginform" name="loginform"  action="login" namespace="/"  method="post">
                    <s:token />
					<div class="reg_form_item login_form_item">
						<dl>
							<dt>用户名: </dt>
							<dd>
								<div class="reg_input">
								    <s:textfield   id="userName" name="userName"  cssClass="basic-input  " />
									<div id="log_tips_account" class=""></div>
								</div>
								<div class="reg_txt"></div>
							</dd>
						</dl>
					</div>
					<div class="reg_form_item login_form_item reg_form_item">
						<dl>
							<dt>密&nbsp;&nbsp;码: </dt>
							<dd>
								<div class="reg_input">
								<s:password id="password" name="password"   cssClass="basic-input  " />
								<div id="log_tips_passwd" class=""></div>
								</div>
								<div class="reg_txt"></div>
							</dd>
						</dl>
					</div>
					<div class="reg_form_item login_form_item reg_form_item">
						<dl>
							<dt>验证码: </dt>
							<dd>
								<div class="reg_input">
								<s:textfield  id="captchaCode" name="captchaCode"  cssClass="basic-input "  cssStyle="width: 78px;"/>
		                        <span>
		                          <img id="check_img" style="display: inline-block;vertical-align:bottom" width="80" height="35" src="<s:url action='jcaptcha_image' namespace='/' />"></img>
		                          <a href="javascript:void(0);" onclick="javascript:refreshCheckCode();">换一换</a></span>
								<div id="log_tips_checkcode" class=""></div>
								</div>
								<div class="reg_txt"></div>
							</dd>
						</dl>
					</div>
					<!--  -->
					<div class="reg_form_item login_form_item reg_form_item2">
						<dl>
							<dt>&nbsp;</dt>
							<dd>
								<s:checkbox name="rememberMe" id="rememberMe"  value="false"  ></s:checkbox>记住我
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:url id="forgetpasswdUrl" value="/member/forgetpwd.jsp"></s:url>
				                <s:a href="%{forgetpasswdUrl}">忘记密码</s:a>
							</dd>
						</dl>
					</div>
					
					<input type="hidden" name="_last_url" value="" id="last_url"/>
					<div class="reg_form_item login_form_item reg_form_item2">
						<dl>
							<dt>&nbsp;</dt>
							<dd>
								<div>
								  <input type="submit" id="reg_submit_btn" value="" class="btn_login_submit" style="border:0px;" name="reg_submit_btn" />
								</div>
							</dd>
						</dl>
					</div>
					
					</s:form>
				</div>
				<!-- 注册表单结束 -->
			</div>
        </div>
		<!-- 布局main结束 -->
		<!-- 布局sub开始 -->
        <div class="col-sub">
			<div id="login_index_right">
				<div>还未注册账号？</div>
				<s:url id="registerUrl" value="/reg/register.jsp">
				   <s:param name="from" value="loginpage" />
				</s:url>
				<s:a href="%{registerUrl}">	<img src="<s:url value="/image/layout/bg_btn_01.jpg"/>" ></s:a>
			</div>
        </div>
		<!-- 布局sub结束 -->
	</div>
</div>
</div>

</center>
</body>
</html>