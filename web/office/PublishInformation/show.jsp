<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<title>发布信息信息</title>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>发布信息 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
	<s:form action="/office/PublishInformation/list.do" method="post" theme="simple">
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/office/PublishInformation/list.do'" >
                 <img src="<c:url value="/image/form/grid.gif"/>" alt="" /> 返回列表
               </button> 
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 后退
               </button> 
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
        <p>基本信息:</p>
		   <s:hidden name="id" id="id" value="%{model.id}"/>
		<table id="formtable" class="formtable">
			<tr>	
				<td class="tdlabel">发布类型: </td>	
				<td><s:property value="%{model.publishInformationType.typeName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">标题: </td>	
				<td><s:property value="%{model.title}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">作者: </td>	
				<td><s:property value="%{model.author}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">部门: </td>	
				<td><s:property value="%{model.department}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">发布人: </td>	
				<td><s:property value="%{model.publisher.realName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">发布时间: </td>	
				<td><s:property value="%{model.publishTimeString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">点击量: </td>	
				<td><s:property value="%{model.viewHits}" /></td>
			</tr>
		</table>
		<p>内容: </p>
		<div>
				<s:property value="%{model.content}"  escape="false"/>
	    </div>
		</div>
	</s:form>
	</div>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>
