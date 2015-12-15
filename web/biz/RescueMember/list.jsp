<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>救援队员基本信息 维护</title>
<%@ include file="/commons/meta.jsp" %>
<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
			//搜索栏收起
			$("#toggle-bar").click(function(oEvent) {
				$("#param-div").toggle("slow");
				$(this).toggleClass("toggle-up");
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
			<h1>救援队员基本信息</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/biz/RescueMember/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">姓名</td>		
					<td>
						<input value="${query.name}" id="name" name="name" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">性别</td>		
					<td>
						<s:select list='#{"":"----","M":"男","F":"女"}' value="%{query.gender}" id="gender" name="gender"   />
					</td>
					<td class="tdLabel">出生日期</td>		
					<td colspan="3">
						<input value="<fmt:formatDate value='${query.birthdayBegin}' pattern='<%=RescueMember.FORMAT_BIRTHDAY%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueMember.FORMAT_BIRTHDAY%>'})" id="birthdayBegin" name="birthdayBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.birthdayEnd}' pattern='<%=RescueMember.FORMAT_BIRTHDAY%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueMember.FORMAT_BIRTHDAY%>'})" id="birthdayEnd" name="birthdayEnd"   class="Wdate"/>
					</td>
					
				</tr>	
				<tr>
				   <td class="tdLabel">民族</td>		
					<td>
						<input value="${query.nation}" id="nation" name="nation" maxlength="10"  class=""/>
					</td>	
					<td class="tdLabel">证件号码</td>		
					<td>
						<input value="${query.idno}" id="idno" name="idno" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel">办公电话</td>		
					<td>
						<input value="${query.telphone}" id="telphone" name="telphone" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">单位</td>		
					<td>
						<input value="${query.workUnit}" id="workUnit" name="workUnit" maxlength="150"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">手机</td>		
					<td>
						<input value="${query.cellphone}" id="cellphone" name="cellphone" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">家庭电话</td>		
					<td>
						<input value="${query.homePhone}" id="homePhone" name="homePhone" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">紧急联系人1</td>		
					<td>
						<input value="${query.contactsName1}" id="contactsName1" name="contactsName1" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel">所属救援队</td>		
					<td>
						<s:select id="teamId" name="teamId" value="%{query.teamId}" list="%{baseCode.rescueTeam}"  listKey="id"  listValue="teamName" headerKey="" headerValue=" ---- "/>
					</td>
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/biz/RescueMember/list.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/biz/RescueMember/create.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 新增
               </button> 
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/biz/RescueMember/delete.do','items',document.forms.queryForm)">
                 <img src="<c:url value="/image/form/delete.gif"/>" alt="" /> 删除
               </button> 
             </div>
		</div>
	</div>
	<div class="cl"></div>
	<div class="gridTable">
        <simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  <tr>
				<th style="width:1px;">&nbsp;</th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"/></th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="NAME" >姓名</th>
				<th sortColumn="GENDER" >性别</th>
				<th sortColumn="BIRTHDAY" >出生日期</th>
				<th sortColumn="NATION" >民族</th>
				<th sortColumn="BLOOD_TYPE" >血型</th>
				<th sortColumn="HEIGHT" >身高(cm)</th>
				<th sortColumn="WEIGHT" >体重(kg)</th>
				<th sortColumn="POLITICAL_LANDSCAPE" >政治面貌</th>
				<th sortColumn="TELPHONE" >办公电话</th>
				<th sortColumn="CELLPHONE" >手机</th>
				<th sortColumn="TEAM_ID" >所属救援队</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		      <c:choose>
		       <c:when test="${fn:length(page.result)>0}">
		  	   <c:forEach items="${page.result}" var="item" varStatus="status">
			   <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="id=${item.id}&" /></td>
				<td><c:out value='${item.name}'/>&nbsp;</td>
				<td><c:out value='${item.gender}'/>&nbsp;</td>
				<td><c:out value='${item.birthdayString}'/>&nbsp;</td>
				<td><c:out value='${item.nation}'/>&nbsp;</td>
				<td><c:out value='${item.bloodType}'/>&nbsp;</td>
				<td><c:out value='${item.height}'/>&nbsp;</td>
				<td><c:out value='${item.weight}'/>&nbsp;</td>
				<td><c:out value='${item.politicalLandscape}'/>&nbsp;</td>
				<td style="text-align:right;"><c:out value='${item.telphone}'/>&nbsp;</td>
				<td style="text-align:right;"><c:out value='${item.cellphone}'/>&nbsp;</td>
				<td style="text-align:left;"><c:out value='${item.rescueTeam.teamName}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/biz/RescueMember/show.do?id=${item.id}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<a href="${ctx}/biz/RescueMember/edit.do?id=${item.id}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
				</td>
			  </tr>
		  	  </c:forEach>
		  	  </c:when>
		  	  <c:otherwise>
		  	  <!-- 空表格 -->
		  	  <c:forEach var="nullitem" begin="1" end="4" step="1"  varStatus="nullstatus">
		  	   <tr class="${(nullstatus.count+1) % 2 == 0 ? 'even' : 'odd'}">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			  </tr>
		  	  </c:forEach>
		  	  </c:otherwise>
		  	  </c:choose>
		  </tbody>
		</table>
		<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
	</div>
	</form>
	</div>
	<!-- footer -->
	<%@ include file="/include/footer.jsp" %>
	<!-- End of footer -->	
</body>
</html>

