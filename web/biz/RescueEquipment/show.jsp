<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<title>救援装备信息信息</title>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>救援装备信息 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
	<s:form action="/biz/RescueEquipment/list.do" method="post" theme="simple">
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/biz/RescueEquipment/list.do'" >
                 <img src="<c:url value="/image/form/grid.gif"/>" alt="" /> 返回列表
               </button> 
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 后退
               </button> 
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
        <p>详细内容:</p>
		   <s:hidden name="id" id="id" value="%{model.id}"/>
		<table id="formtable" class="formtable">
			<tr>	
				<td class="tdlabel">装备名称: </td>	
				<td><s:property value="%{model.name}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">装备类别: </td>	
				<td><s:property value="%{model.equipmentType.typeName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">数量: </td>	
				<td><s:property value="%{model.amount}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">状态: </td>	
				<td><s:property value="%{model.equipmentStatus.statusName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">生产地: </td>	
				<td><s:property value="%{model.productLocation}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">生产日期: </td>	
				<td><s:property value="%{model.productTimeString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">购买日期: </td>	
				<td><s:property value="%{model.buyTimeString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">造价(元): </td>	
				<td><s:property value="%{model.price}" /></td>
			</tr>
		</table>
		</div>
	</s:form>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>
