<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<title>救援队员详细信息信息</title>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>救援队员详细信息 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
	<s:form action="/biz/RescueMemberDetailinfo/list.do" method="post" theme="simple">
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/biz/RescueMemberDetailinfo/list.do'" >
                 <img src="<c:url value="/image/form/grid.gif"/>" alt="" /> 返回列表
               </button> 
               <button type="button" class="regular" name="backpage" onclick="history.back();">
                 <img src="<c:url value="/image/form/back.gif"/>" alt="" /> 后退
               </button> 
        </div>
        <div class="cl"></div>
        <div id="formdiv" >
        <p>详细内容:</p>
		   <s:hidden name="memberId" id="memberId" value="%{model.memberId}"/>
		<table id="formtable" class="formtable">
			<tr>	
				<td class="tdlabel">身体素质: </td>	
				<td><s:property value="%{model.fitness}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">急救及医疗水平: </td>	
				<td><s:property value="%{model.abilityMedical}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">GPS定位水平: </td>	
				<td><s:property value="%{model.abilityGps}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">交通工具水平: </td>	
				<td><s:property value="%{model.abilityTransport}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">其他专长: </td>	
				<td><s:property value="%{model.abilityOther}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">救援经历: </td>	
				<td><s:property value="%{model.rescueExperience}" /></td>
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
