<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="<c:url value="/script/jquery-ui.js"/>"></script>
<script	src="<c:url value="/script/validation/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/additional_methods.js"/>"	type="text/javascript"></script>
<script	src="<c:url value="/script/validation/messages_cn.js"/>"	type="text/javascript"></script>
<script type="text/javascript"> 
        $(document).ready(function() {
            //表单验证
            var v = $("#myForm").validate({

            	/*默认条件即可,如果需要请在下面启用相关选项
            	debug: false,
    			errorElement: "em",
    			success: function(label) {
    				label.text("ok!").addClass("success");
    			},
    			//errorContainer: $("#warning, #summary"),
    			//errorPlacement: function(error, element) {
    			//error.appendTo( element.parent("td").next("td") );
    			//},
    			
    			//(Demo)可以通过在此处添加校验规则和校验信息,默认已经通过字段的CSS样式添加了校验信息.
    			rules: {
    				firstname: "required",
    				lastname: "required",
    				username: {
    					required: true,
    					minlength: 2
    				}
    			},
    			messages: {
    				firstname: "Please enter your firstname",
    				lastname: "Please enter your lastname",
    				username: {
    					required: "Please enter a username",
    					minlength: "Your username must consist of at least 2 characters"
    				}
    			}
			*/
          });
            //产生tabs
        	$("#formdiv").tabs();
        });
</script>          
<title>救援队员基本信息新增</title>
</head>

<body>
    <!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>救援队员基本信息 -> 新增</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<s:form id="myForm" action="/biz/RescueMember/save.do" method="post">
		<div id="controlbuttons" class="buttons">
               <button id="submitButton" name="submitButton" type="submit"  class="positive">
                 <img src="<c:url value="/image/form/ok.gif"/>" alt="" /> 提交
               </button>
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/biz/RescueMember/list.do'" >
                 <img src="<c:url value="/image/form/grid.gif"/>" alt="" /> 返回列表
               </button> 
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 取消
               </button> 
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
         <ul>
		   <li><a class="corner-tl" href="#tabs-basic">基础信息</a></li>
		    <li><a class="" href="#tabs-group">可参与的救援组</a></li>
		   <li><a class="corner-tr" href="#tabs-detail">详细信息</a></li>
	     </ul>
	    <div id="tabs-basic">
	    <s:hidden key="fcu" value="%{#session.CURRENT_USER.userid}"></s:hidden>
		<s:hidden key="lcu" value="%{#session.CURRENT_USER.userid}"></s:hidden>
		<table id="formtable" class="formtable">
		<%@ include file="form_include.jsp" %>
		</table>
		</div>
		<div id="tabs-group" style="padding:20px;">
		  <s:checkboxlist name="memberGroupIds" list="%{baseCode.rescueGroupType}" listKey="id" listValue="groupName"></s:checkboxlist>
		</div>
		<div id="tabs-detail">
		<table id="formtable_detail" class="formtable">
		<%@ include file="form_include_detail.jsp" %>
		</table>
		</div>
		</div>
	</s:form>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>