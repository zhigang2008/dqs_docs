<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>救援队员详细信息 维护</title>
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
			<h1>救援队员详细信息</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/biz/RescueMemberDetailinfo/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">身体素质</td>		
					<td>
						<input value="${query.fitness}" id="fitness" name="fitness" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">急救及医疗水平</td>		
					<td>
						<input value="${query.abilityMedical}" id="abilityMedical" name="abilityMedical" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">GPS定位水平</td>		
					<td>
						<input value="${query.abilityGps}" id="abilityGps" name="abilityGps" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">交通工具水平</td>		
					<td>
						<input value="${query.abilityTransport}" id="abilityTransport" name="abilityTransport" maxlength="200"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">其他专长</td>		
					<td>
						<input value="${query.abilityOther}" id="abilityOther" name="abilityOther" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">救援经历</td>		
					<td>
						<input value="${query.rescueExperience}" id="rescueExperience" name="rescueExperience" maxlength="200"  class=""/>
					</td>
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/biz/RescueMemberDetailinfo/list.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/biz/RescueMemberDetailinfo/create.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 新增
               </button> 
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/biz/RescueMemberDetailinfo/delete.do','items',document.forms.queryForm)">
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
				<th sortColumn="FITNESS" >身体素质</th>
				<th sortColumn="ABILITY_MEDICAL" >急救及医疗水平</th>
				<th sortColumn="ABILITY_GPS" >GPS定位水平</th>
				<th sortColumn="ABILITY_TRANSPORT" >交通工具水平</th>
				<th sortColumn="ABILITY_OTHER" >其他专长</th>
				<th sortColumn="RESCUE_EXPERIENCE" >救援经历</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		      <c:choose>
		       <c:when test="${fn:length(page.result)>0}">
		  	   <c:forEach items="${page.result}" var="item" varStatus="status">
			   <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><input type="checkbox" name="items" value="memberId=${item.memberId}&" /></td>
				<td><c:out value='${item.fitness}'/>&nbsp;</td>
				<td><c:out value='${item.abilityMedical}'/>&nbsp;</td>
				<td><c:out value='${item.abilityGps}'/>&nbsp;</td>
				<td><c:out value='${item.abilityTransport}'/>&nbsp;</td>
				<td><c:out value='${item.abilityOther}'/>&nbsp;</td>
				<td><c:out value='${item.rescueExperience}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/biz/RescueMemberDetailinfo/show.do?memberId=${item.memberId}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<a href="${ctx}/biz/RescueMemberDetailinfo/edit.do?memberId=${item.memberId}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
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

