<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>上报信息 审核</title>
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
			
			showFrame("添加模块","<s:url value="/office/ReportInformation/review.do"/>?id="+id+"&status="+status,500,360);
			
			$.getJSON("<s:url value="/office/ReportInformation/review.do"/>?id="+id+"&status="+status, function(data){
				  if(data.success==true){
					  $("#queryForm").submit();
				  }
				  else{
					  alert("审批失败:"+data.message);
				  }
				});
		}

		 //关闭弹出窗口
	    function closePopup(){
	        $("#dialogBoxClose").click();
	        //刷新列表
	        $("#queryForm").submit();
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
			<h1>上报信息-审核</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/office/ReportInformation/list4review.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">类型</td>		
					<td>
						<s:select value="%{typeId}" id="typeId" name="typeId" list="%{baseCode.reportInformationType}" listKey="id" listValue="typeName" headerKey="" headerValue="--全部--" />
					</td>
					<td class="tdLabel">信息标题</td>		
					<td>
						<input value="${query.reportTitle}" id="reportTitle" name="reportTitle" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">上报人</td>		
					<td>
						<s:select value="%{reporterId}" id="reporterId" name="reporterId" list="%{baseCode.user}" listKey="userid" listValue="realName" headerKey="" headerValue="--全部--" />
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">上报时间</td>		
					<td colspan="5">
						<input value="<fmt:formatDate value='${query.reportTimeBegin}' pattern='<%=ReportInformation.FORMAT_REPORT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=ReportInformation.FORMAT_REPORT_TIME%>'})" id="reportTimeBegin" name="reportTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.reportTimeEnd}' pattern='<%=ReportInformation.FORMAT_REPORT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=ReportInformation.FORMAT_REPORT_TIME%>'})" id="reportTimeEnd" name="reportTimeEnd"   class="Wdate"/>
					</td>
  			  </tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/office/ReportInformation/list4review.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
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
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="TYPE_ID" >类型</th>
				<th sortColumn="REPORT_TITLE" >信息标题</th>
				<th sortColumn="REPORTER_ID" >上报人</th>
				<th sortColumn="REPORT_TIME" >上报时间</th>
				<th sortColumn="STATUS" >审核状态</th>
				<th sortColumn="BACK_NOTE" >批注</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		      <c:choose>
		       <c:when test="${fn:length(page.result)>0}">
		  	   <c:forEach items="${page.result}" var="item" varStatus="status">
			   <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.thisPageFirstElementNumber + status.index}</td>
				<td><c:out value='${item.reportInformationType.typeName}'/>&nbsp;</td>
				<td style="text-align:left;"><a href="#" title="<wylb:textCut length="200"><c:out value='${item.reportTitle}'/></wylb:textCut>"><wylb:textCut length="25"><c:out value='${item.reportTitle}'/></wylb:textCut></a>&nbsp;</td>
				<td><c:out value='${item.reporter.realName}'/>&nbsp;</td>
				<td><c:out value='${item.reportTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.reportInformationStatus.statusName}'/>&nbsp;</td>
				<td><a href="#" title="${item.backNote}"><wylb:textCut length="15"><c:out value='${item.backNote}'/></wylb:textCut></a>&nbsp;</td>
				<td>
					<a href="${ctx}/office/ReportInformation/show.do?id=${item.id}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<wylb:hasPermission name="EmergencyPlan:review">
					 <a href="javascript:review(${item.id},2);" title="审核通过" > <img src="<c:url value='/image/form/ok.gif'/>" alt="通过"  /></a>
					 &nbsp;
					 <a href="javascript:review(${item.id},3);" title="退回" > <img src="<c:url value='/image/form/cancle.gif'/>" alt="未通过"  /></a>
					</wylb:hasPermission>
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

