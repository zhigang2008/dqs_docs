<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>救援装备信息 维护</title>
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
			<h1>救援装备信息</h1>
		</div>
	</div>
	<!-- End of Page title -->
	<div id="content" class="cl wrapper">
	<form id="queryForm" name="queryForm" action="<c:url value="/biz/RescueEquipment/list.do"/>" method="post" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend><span id="toggle-bar" class="toggle-down">&nbsp;</span><span>搜索</span></legend>
			<div id="param-div" class="param-div" >
			<table>
				<tr>	
					<td class="tdLabel">装备名称</td>		
					<td>
						<input value="${query.name}" id="name" name="name" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">装备类别</td>		
					<td>
						<s:select value="%{model.typeId}" id="typeId" name="typeId" list="%{baseCode.equipmentType}"  listKey="id"  listValue="typeName" headerKey="" headerValue=" ---- "/>
					</td>
					<td class="tdLabel">状态</td>		
					<td>
						<s:select value="%{model.status}" id="status" name="status" list="%{baseCode.equipmentStatus}"  listKey="id"  listValue="statusName" headerKey="" headerValue=" ---- "/>
					</td>
					<td class="tdLabel">生产地</td>		
					<td>
						<input value="${query.productLocation}" id="productLocation" name="productLocation" maxlength="100"  class=""/>
					</td>
				</tr>	
				<tr>	
					
					<td class="tdLabel">生产日期</td>		
					<td colspan="3">
						<input value="<fmt:formatDate value='${query.productTimeBegin}' pattern='<%=RescueEquipment.FORMAT_PRODUCT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_PRODUCT_TIME%>'})" id="productTimeBegin" name="productTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.productTimeEnd}' pattern='<%=RescueEquipment.FORMAT_PRODUCT_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_PRODUCT_TIME%>'})" id="productTimeEnd" name="productTimeEnd"   class="Wdate"/>
					</td>
					<td class="tdLabel">购买日期</td>		
					<td colspan="3">
						<input value="<fmt:formatDate value='${query.buyTimeBegin}' pattern='<%=RescueEquipment.FORMAT_BUY_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_BUY_TIME%>'})" id="buyTimeBegin" name="buyTimeBegin"  class="Wdate" />
						<input value="<fmt:formatDate value='${query.buyTimeEnd}' pattern='<%=RescueEquipment.FORMAT_BUY_TIME%>'/>" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_BUY_TIME%>'})" id="buyTimeEnd" name="buyTimeEnd"   class="Wdate"/>
					</td>
				</tr>	
			</table>
			</div>
		</fieldset>
		<div class="handleControl">
             <div class="buttons">
               <button type="submit" class="regular" name="query" onclick="getReferenceForm(this).action='${ctx}/biz/RescueEquipment/list.do'" >
                 <img src="<c:url value="/image/form/query.gif"/>" alt="" /> 查询
               </button>
               <button type="submit" class="positive" name="additem" onclick="getReferenceForm(this).action='${ctx}/biz/RescueEquipment/create.do'" >
                 <img src="<c:url value="/image/form/add.gif"/>" alt="" /> 新增
               </button> 
               <button type="button" class="negative" name="deleteitem" onclick="batchDelete('${ctx}/biz/RescueEquipment/delete.do','items',document.forms.queryForm)">
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
				<th sortColumn="NAME" >装备名称</th>
				<th sortColumn="TYPE_ID" >装备类别</th>
				<th sortColumn="AMOUNT" >数量</th>
				<th sortColumn="STATUS" >状态</th>
				<th sortColumn="PRODUCT_LOCATION" >生产地</th>
				<th sortColumn="PRODUCT_TIME" >生产日期</th>
				<th sortColumn="BUY_TIME" >购买日期</th>
				<th sortColumn="PRICE" >造价(元)</th>
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
				<td><c:out value='${item.equipmentType.typeName}'/>&nbsp;</td>
				<td><c:out value='${item.amount}'/>&nbsp;</td>
				<td><c:out value='${item.equipmentStatus.statusName}'/>&nbsp;</td>
				<td><c:out value='${item.productLocation}'/>&nbsp;</td>
				<td><c:out value='${item.productTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.buyTimeString}'/>&nbsp;</td>
				<td><c:out value='${item.price}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/biz/RescueEquipment/show.do?id=${item.id}&" title="查看" > <img src="<c:url value='/image/form/view.png'/>" alt="查看"  /></a>&nbsp;
					<a href="${ctx}/biz/RescueEquipment/edit.do?id=${item.id}&" title="修改" > <img src="<c:url value='/image/form/edit.gif'/>" alt="修改"  /></a>
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

