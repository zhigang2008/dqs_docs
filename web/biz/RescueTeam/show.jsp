<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<title>救援队信息</title>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>救援队 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
	<s:form action="/biz/RescueTeam/list.do" method="post" theme="simple">
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/biz/RescueTeam/list.do'" >
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
				<td class="tdlabel">救援队名称: </td>	
				<td><s:property value="%{model.teamName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">救援队类型: </td>	
				<td><s:property value="%{model.teamType.name}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">所在省份: </td>	
				<td><s:property value="%{model.province.regionName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">所在城市: </td>	
				<td><s:property value="%{model.city.regionName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">所在区县: </td>	
				<td><s:property value="%{model.district.regionName}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">主管部门: </td>	
				<td><s:property value="%{model.headDepartment}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">地址: </td>	
				<td><s:property value="%{model.address}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">应急电话: </td>	
				<td><s:property value="%{model.telphone}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">传真: </td>	
				<td><s:property value="%{model.fax}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">负责人: </td>	
				<td><s:property value="%{model.master}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">负责人电话: </td>	
				<td><s:property value="%{model.masterTelphone}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">成立时间: </td>	
				<td><s:property value="%{model.setupTimeString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">成员数: </td>	
				<td><s:property value="%{model.memberAmount}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">设备量: </td>	
				<td><s:property value="%{model.equipmentAmount}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">主要设备描述: </td>	
				<td><s:property value="%{model.majorEquipment}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">主要专长能力描述: </td>	
				<td><s:property value="%{model.majorAbility}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">级别: </td>	
				<td><s:property value="%{model.teamLevel}" /></td>
			</tr>
		</table>
		
		<p>可承担的职能:</p>
		 <div style="padding-left:30px; font-weight:bold;font-size:120%">
		 <s:iterator value="%{model.teamAbilitys}" var="teamAbility" status="teamStatus">
                 <s:iterator value="%{baseCode.rescueAbility}" var="resAbility" >
                    <s:if test="%{#teamAbility.abilityId==#resAbility.id}"><s:property value="abilityName" /></s:if>
                 </s:iterator>
                 <s:if test="!#teamStatus.last">,</s:if>
             </s:iterator>
		 </div>
		 </div>
	</s:form>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>
