<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<title>救援队员基本信息信息</title>
</head>

<body>
<!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>救援队员基本信息 -> 查看</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<div class="showdetail"> 
	<s:form action="/biz/RescueMember/list.do" method="post" theme="simple">
		<div id="controlbuttons" class="buttons">
               <button type="button" class="regular" name="backlist" onclick="window.location='${ctx}/biz/RescueMember/list.do'" >
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
				<td class="tdlabel">姓名: </td>	
				<td><s:property value="%{model.name}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">性别: </td>	
				<td><s:property value="%{model.gender}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">出生日期: </td>	
				<td><s:property value="%{model.birthdayString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">民族: </td>	
				<td><s:property value="%{model.nation}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">血型: </td>	
				<td><s:property value="%{model.bloodType}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">身高: </td>	
				<td><s:property value="%{model.height}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">体重: </td>	
				<td><s:property value="%{model.weight}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">政治面貌: </td>	
				<td><s:property value="%{model.politicalLandscape}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">证件号码: </td>	
				<td><s:property value="%{model.idno}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">办公电话: </td>	
				<td><s:property value="%{model.telphone}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">单位: </td>	
				<td><s:property value="%{model.workUnit}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">单位地址: </td>	
				<td><s:property value="%{model.workAddress}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">手机: </td>	
				<td><s:property value="%{model.cellphone}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">家庭电话: </td>	
				<td><s:property value="%{model.homePhone}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">家庭住址: </td>	
				<td><s:property value="%{model.homeAddress}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人1: </td>	
				<td><s:property value="%{model.contactsName1}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人关系1: </td>	
				<td><s:property value="%{model.contactsRelation1}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人电话1: </td>	
				<td><s:property value="%{model.contactsPhone1}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人2: </td>	
				<td><s:property value="%{model.contactsName2}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人关系2: </td>	
				<td><s:property value="%{model.contactsRelation2}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">紧急联系人电话2: </td>	
				<td><s:property value="%{model.contactsPhone2}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">所属救援队: </td>	
				<td><s:property value="%{model.teamId}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">创建人: </td>	
				<td><s:property value="%{model.fcu}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">创建时间: </td>	
				<td><s:property value="%{model.fcdString}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">最后修改人: </td>	
				<td><s:property value="%{model.lcu}" /></td>
			</tr>
			<tr>	
				<td class="tdlabel">最后修改时间: </td>	
				<td><s:property value="%{model.lcdString}" /></td>
			</tr>
		</table>
		<p>可参与的救援组:</p>
		<div style="padding-left:30px; font-weight:bold;font-size:120%">
		 <s:iterator value="%{model.memberGroups}" var="memberGroup" status="groupStatus">
		 
                 <s:iterator value="%{baseCode.rescueGroupType}" var="groupType" >
                    <s:if test="%{#memberGroup.groupId==#groupType.id}"><s:property value="groupName" /></s:if>
                 </s:iterator>
                 <s:if test="!#groupStatus.last">,</s:if>
             </s:iterator>
		 </div>
		<p>详细信息</p>
		<table>
			<tr>	
		<td class="tdlabel">
			身体素质:
		</td>	
		<td>
		  <s:property  value="%{model.rescueMemberDetailinfo.fitness}" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			急救及医疗水平:
		</td>	
		<td>
		  <s:property  value="%{model.rescueMemberDetailinfo.abilityMedical}"  />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			GPS定位水平:
		</td>	
		<td>
		  <s:property  value="%{model.rescueMemberDetailinfo.abilityGps}" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			交通工具水平:
		</td>	
		<td>
		  <s:property  value="%{model.rescueMemberDetailinfo.abilityTransport}"  />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			其他专长:
		</td>	
		<td>
		  <s:property  value="%{model.rescueMemberDetailinfo.abilityOther}"  />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援经历:
		</td>	
		<td>
		  <s:property value="%{model.rescueMemberDetailinfo.rescueExperience}"  />
	    </td>
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
