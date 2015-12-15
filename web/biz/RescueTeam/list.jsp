<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>救援队 维护</title>
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
			<h1>救援队</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/biz/RescueTeam/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">救援队名称</td>		
					<td>
						<input value="${query.teamName}" id="teamName" name="teamName" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">救援队类型</td>		
					<td>
						<s:select label="救援队类型" key="teamTypeId"  value="%{model.teamTypeId}"  cssClass="digits "  required="false" list="%{baseCode.teamType}" headerKey=""  headerValue=""  listKey="id"  listValue="name"/>
					</td>
					<td class="tdLabel">负责人</td>		
					<td>
						<input value="${query.master}" id="master" name="master" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">应急电话</td>		
					<td>
						<input value="${query.telphone}" id="telphone" name="telphone" maxlength="50"  class=""/>
					</td>
				</tr>	
				<tr>
				<td class="tdLabel">位置</td>		
					<td colspan="3">
						<wylb:region firstName="provinceId" firstId="provinceId" firstValue="${query.provinceId}" secondName="cityId"  secondId="cityId" secondValue="${query.cityId}"  headerKey="" headerValue="--All--" />
   				    </td>
					<td class="tdLabel">成立时间</td>		
					<td colspan="3" >
						<input value="<fmt:formatDate value='${query.setupTimeBegin}' pattern='<%=RescueTeam.FORMAT_SETUP_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueTeam.FORMAT_SETUP_TIME%>'})" id="setupTimeBegin" name="setupTimeBegin"  class="Wdate" />
						
						<input value="<fmt:formatDate value='${query.setupTimeEnd}' pattern='<%=RescueTeam.FORMAT_SETUP_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueTeam.FORMAT_SETUP_TIME%>'})" id="setupTimeEnd" name="setupTimeEnd"   class="Wdate"/>
					</td>
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/biz/RescueTeam/list.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/biz/RescueTeam/create.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 新增
               </button> 
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/biz/RescueTeam/delete.do','items',document.forms.queryForm)">
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
				<th sortColumn="TEAM_NAME" >救援队名称</th>
				<th sortColumn="TEAM_TYPE_ID" >救援队类型</th>
				<th sortColumn="PROVINCE_ID" >所在省份</th>
				<th sortColumn="CITY_ID" >所在城市</th>
				<th sortColumn="HEAD_DEPARTMENT" >主管部门</th>
				<th sortColumn="TELPHONE" >应急电话</th>
				<th sortColumn="MASTER" >负责人</th>
				<th sortColumn="SETUP_TIME" >成立时间</th>
				<th sortColumn="MEMBER_AMOUNT" >成员数</th>
				<th sortColumn="EQUIPMENT_AMOUNT" >设备量</th>
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
				<td><c:out value='${item.teamName}'/>&nbsp;</td>
				<td><c:out value='${item.teamType.name}'/>&nbsp;</td>
				<td><c:out value='${item.province.regionName}'/>&nbsp;</td>
				<td><c:out value='${item.city.regionName}'/>&nbsp;</td>
				<td style="text-align:left;"><c:out value='${item.headDepartment}'/>&nbsp;</td>
				<td style="text-align:right;"><c:out value='${item.telphone}'/>&nbsp;</td>
				<td><c:out value='${item.master}'/>&nbsp;</td>
				<td><c:out value='${item.setupTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.memberAmount}'/>&nbsp;</td>
				<td><c:out value='${item.equipmentAmount}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/biz/RescueTeam/show.do?id=${item.id}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<a href="${ctx}/biz/RescueTeam/edit.do?id=${item.id}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
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

