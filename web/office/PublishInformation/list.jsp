<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发布信息 维护</title>
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
			<h1>发布信息</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/office/PublishInformation/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">发布类型</td>		
					<td>
						 <s:select label="发布类型" id="typeId" name="typeId" value="%{typeId}" list="%{baseCode.publishInformationType}" listKey="id" listValue="typeName" headerKey="" headerValue="-全部-" />
	    			</td>
					<td class="tdLabel">标题</td>		
					<td>
						<input value="${query.title}" id="mytitle" name="title" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">作者</td>		
					<td>
						<input value="${query.author}" id="author" name="author" maxlength="100"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">部门</td>		
					<td>
						<input value="${query.department}" id="department" name="department" maxlength="150"  class=""/>
					</td>
					<td class="tdLabel">发布时间</td>		
					<td colspan="3">
						<input value="<fmt:formatDate value='${query.publishTimeBegin}' pattern='<%=PublishInformation.FORMAT_PUBLISH_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=PublishInformation.FORMAT_PUBLISH_TIME%>'})" id="publishTimeBegin" name="publishTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.publishTimeEnd}' pattern='<%=PublishInformation.FORMAT_PUBLISH_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=PublishInformation.FORMAT_PUBLISH_TIME%>'})" id="publishTimeEnd" name="publishTimeEnd"   class="Wdate"/>
					</td>
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/office/PublishInformation/list.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <wylb:hasPermission name="PublishInformation:create">
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/office/PublishInformation/create.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 新增
               </button> 
               </wylb:hasPermission>
               <wylb:hasPermission name="PublishInformation:delete">
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/office/PublishInformation/delete.do','items',document.forms.queryForm)">
                 <img src="<c:url value="/image/form/delete.gif"/>" alt="" /> 删除
               </button> 
               </wylb:hasPermission>
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
				<th sortColumn="TYPE_ID" >发布类型</th>
				<th sortColumn="TITLE" >标题</th>
				<th sortColumn="AUTHOR" >作者</th>
				<th sortColumn="DEPARTMENT" >部门</th>
				<th sortColumn="PUBLISHER_ID" >发布人</th>
				<th sortColumn="PUBLISH_TIME" >发布时间</th>
				<th sortColumn="VIEW_HITS" >点击量</th>
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
				<td><c:out value='${item.publishInformationType.typeName}'/>&nbsp;</td>
				<td style="text-align:left;"><wylb:textCut length="30"><c:out value='${item.title}'/></wylb:textCut>&nbsp;</td>
				<td><c:out value='${item.author}'/>&nbsp;</td>
				<td><c:out value='${item.department}'/>&nbsp;</td>
				<td><c:out value='${item.publisher.realName}'/>&nbsp;</td>
				<td><c:out value='${item.publishTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.viewHits}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/office/PublishInformation/show.do?id=${item.id}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<wylb:hasPermission name="PublishInformation:edit">
					<a href="${ctx}/office/PublishInformation/edit.do?id=${item.id}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
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

