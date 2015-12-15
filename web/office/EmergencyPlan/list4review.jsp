<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应急预案审核</title>
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

		
		function review(id,status){
			$.getJSON("<s:url value="/office/EmergencyPlan/review.do"/>?id="+id+"&status="+status, function(data){
				  if(data.success==true){
					  $("#queryForm").submit();
				  }
				  else{
					  alert("审批失败:"+data.message);
				  }
				});
		}
	</script>
</head>

<body>
    <!-- Header -->
	<%@ include file="/include/header.jsp" %>
	<!-- End of Header -->
	<!-- Page title -->
	<div id="pagetitle">
		<div class="wrapper">
			<h1>应急预案审核</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/office/EmergencyPlan/list4review.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">类型</td>		
					<td>
						<s:select   value="%{model.planTypeId}" id="planTypeId" name="planTypeId" list="%{baseCode.emergencyPlanType}" headerKey="" headerValue="--全部--" listKey="id" listValue="typeName"/>
					</td>
					<td class="tdLabel">名称</td>		
					<td>
						<input value="${query.name}" id="name" name="name" maxlength="150"  class=""/>
					</td>
					<td class="tdLabel">部门</td>		
					<td>
						<input value="${query.department}" id="department" name="department" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">提交时间</td>		
					<td>
						<input value="<fmt:formatDate value='${query.submitTimeBegin}' pattern='<%=EmergencyPlan.FORMAT_SUBMIT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=EmergencyPlan.FORMAT_SUBMIT_TIME%>'})" id="submitTimeBegin" name="submitTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.submitTimeEnd}' pattern='<%=EmergencyPlan.FORMAT_SUBMIT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=EmergencyPlan.FORMAT_SUBMIT_TIME%>'})" id="submitTimeEnd" name="submitTimeEnd"   class="Wdate"/>
					</td>
					
				</tr>	
				
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/office/EmergencyPlan/list4review.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
             </div>
		</div>
	</div>
	<div class="cl"></div>
	<div class="gridTable">
        <simpletable:pageToolbar page="${page}">待审核应急方案</simpletable:pageToolbar>
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  <tr>
				<th style="width:1px;">&nbsp;</th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="PLAN_TYPE_ID" >类型</th>
				<th sortColumn="NAME" >名称</th>
				<th sortColumn="PLAN_VERSION" >版本</th>
				<th sortColumn="DEPARTMENT" >部门</th>
				<th sortColumn="SUBMITTER_ID" >提交人</th>
				<th sortColumn="SUBMIT_TIME" >提交时间</th>
				<th sortColumn="FILE_SIZE" >文件大小</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		      <c:choose>
		       <c:when test="${fn:length(page.result)>0}">
		  	   <c:forEach items="${page.result}" var="item" varStatus="status">
			   <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><c:out value='${item.emergencyPlanType.typeName}'/>&nbsp;</td>
				<td><c:out value='${item.name}'/>&nbsp;</td>
				<td><c:out value='${item.planVersion}'/>&nbsp;</td>
				<td><c:out value='${item.department}'/>&nbsp;</td>
				<td><c:out value='${item.submitter.realName}'/>&nbsp;</td>
				<td><c:out value='${item.submitTimeString}'/>&nbsp;</td>
				<td><wylb:fileSize><c:out value='${item.fileSize}'/></wylb:fileSize>&nbsp;</td>
				<td>
					<wylb:hasPermission name="EmergencyPlan:download">
					 <a href="${ctx}/support/download.do?fileName=${item.name}_${item.planVersion}&filePath=${item.planFile}" title="下载" > <img src="<c:url value='/image/form/download.gif'/>" alt="下载"  /></a>
					&nbsp;
					</wylb:hasPermission>
					<wylb:hasPermission name="EmergencyPlan:review">
					 <a href="javascript:review(${item.id},2);" title="审核通过" > <img src="<c:url value='/image/form/ok.gif'/>" alt="通过"  /></a>
					 &nbsp;
					 <a href="javascript:review(${item.id},3);" title="审核未通过" > <img src="<c:url value='/image/form/cancle.gif'/>" alt="未通过"  /></a>
					</wylb:hasPermission>
					&nbsp;
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

