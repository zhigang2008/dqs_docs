<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="<c:url value="/script/jquery-plugin/jquery.printElement.min.js"/>"></script>
<title>上报信息信息</title>
<script type="text/javascript">
$(document).ready(function() {
	$("#printContent").click(function(){
		$("#infoContent").printElement({pageTitle:"${model.reportTitle}"});
	});
});
</script>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>上报信息 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 返回
               </button> 
               <wylb:hasPermission name="ReportInformation:print"></wylb:hasPermission>
               <button type="button" class="regular" id="printContent" name="printContent" >
                 <img src="<c:url value="/image/form/print.gif"/>" alt="" /> 打印
               </button> 
               
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
        <p>基本内容:</p>
		   <s:hidden name="id" id="id" value="%{model.id}"/>
		<table id="formtable" class="formtable">
			<tr>	
				<td class="tdlabel">类型: </td>	
				<td><s:property value="%{model.reportInformationType.typeName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">信息标题: </td>	
				<td><s:property value="%{model.reportTitle}" /></td>
			</tr>
			
			<tr>	
				<td class="tdlabel">上报人: </td>	
				<td><s:property value="%{model.reporter.realName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">上报时间: </td>	
				<td><s:property value="%{model.reportTimeString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">审核状态: </td>	
				<td><s:property value="%{model.reportInformationStatus.statusName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">批注: </td>	
				<td><s:property value="%{model.backNote}" /></td>
			</tr>
			<!-- 
			 <tr>	
				<td class="tdlabel">生成文档: </td>	
				<td><s:property value="%{model.reportFile}" /></td>
			</tr>
			-->
		</table>
		<p>信息内容:</p>
		<div id="infoContent" class="cl">
		<s:property value="%{model.content}" escape="false"/>
		</div>
		</div>
	</div>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>
