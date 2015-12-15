<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>标准工作程序 维护</title>
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
			<h1>标准工作程序-查阅</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/office/StandardWork/list4submitter.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">类型</td>		
					<td>
						<s:select value="%{model.type}" id="type" name="type" list="%{baseCode.standardWorkType}" listKey="id" listValue="typeName" headerKey="" headerValue="--全部--"  />
					</td>
					<td class="tdLabel">名称</td>		
					<td>
						<input value="${query.workName}" id="workName" name="workName" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">版本</td>		
					<td>
						<input value="${query.version}" id="version" name="version" maxlength="20"  class=""/>
					</td>
					<td class="tdLabel">作者</td>		
					<td>
						<input value="${query.author}" id="author" name="author" maxlength="50"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">状态</td>		
					<td>
						<s:select value="%{model.status}" id="status" name="status" list="%{baseCode.documentStatus}" listKey="id" listValue="name" headerKey="" headerValue="--全部--"/>
					</td>
					<td class="tdLabel">部门</td>		
					<td>
						<input value="${query.department}" id="department" name="department" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">发布时间</td>		
					<td colspan="3">
						<input value="<fmt:formatDate value='${query.submitTimeBegin}' pattern='<%=StandardWork.FORMAT_SUBMIT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=StandardWork.FORMAT_SUBMIT_TIME%>'})" id="submitTimeBegin" name="submitTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.submitTimeEnd}' pattern='<%=StandardWork.FORMAT_SUBMIT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=StandardWork.FORMAT_SUBMIT_TIME%>'})" id="submitTimeEnd" name="submitTimeEnd"   class="Wdate"/>
					</td>
					
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/office/StandardWork/list4submitter.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <wylb:hasPermission name="StandardWork:create">
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/office/StandardWork/create4submitter.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 提交新程序
               </button> 
               </wylb:hasPermission>
               <wylb:hasPermission name="StandardWork:delete">
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/office/StandardWork/delete4submitter.do','items',document.forms.queryForm)">
                 <img src="<c:url value="/image/form/delete.gif"/>" alt="" /> 删除所选
               </button> 
               </wylb:hasPermission>
             </div>
		</div>
	</div>
	<div class="cl"></div>
	<div class="gridTable">
        <simpletable:pageToolbar page="${page}">已提交的标准工作程序</simpletable:pageToolbar>
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  <tr>
				<th style="width:1px;">&nbsp;</th>
				<th style="width:1px;"><input type="checkbox" onclick="setAllCheckboxState('items',this.checked)"/></th>
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="TYPE" >类型</th>
				<th sortColumn="WORK_NAME" >名称</th>
				<th sortColumn="VERSION" >版本</th>
				<th sortColumn="AUTHOR" >作者</th>
				<th sortColumn="DEPARTMENT" >部门</th>
				<th sortColumn="SUBMIT_TIME" >发布时间</th>
				<th sortColumn="STATUS" >状态</th>
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
				<td>
                  <!-- 如果不是审核通过,则允许删除(包括待审核.审核未通过) -->
				  <c:if test="${item.status!=2}">
				  <input type="checkbox" name="items" value="id=${item.id}&" />
				  </c:if>
				  </td>
				<td><c:out value='${item.standardWorkType.typeName}'/>&nbsp;</td>
				<td><c:out value='${item.workName}'/>&nbsp;</td>
				<td><c:out value='${item.version}'/>&nbsp;</td>
				<td><c:out value='${item.author}'/>&nbsp;</td>
				<td><c:out value='${item.department}'/>&nbsp;</td>
				<td><c:out value='${item.submitTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.documentStatus.name}'/>&nbsp;</td>
				<td><wylb:fileSize><c:out value='${item.fileSize}'/></wylb:fileSize>&nbsp;</td>
				<td>
					<wylb:hasPermission name="StandardWork:download">
					<a href="${ctx}/support/download.do?fileName=${item.workName}_${item.version}&filePath=${item.fileName}" title="下载" > <img src="<c:url value='/image/form/download.gif'/>" alt="下载"  /></a>
					</wylb:hasPermission>
					<wylb:hasPermission name="StandardWork:edit">
					<a href="${ctx}/office/StandardWork/edit4submitter.do?id=${item.id}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
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

