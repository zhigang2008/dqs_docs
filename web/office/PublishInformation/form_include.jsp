<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			发布类型<span class="required">*</span>:
		</td>	
		<td colspan="3">
		  <s:select label="发布类型" key="typeId" value="%{model.typeId}" list="%{baseCode.publishInformationType}" listKey="id" listValue="typeName" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			标题<span class="required">*</span>:
		</td>	
		<td colspan="3">
		  <s:textfield label="标题" key="title" value="%{model.title}" cssClass="required " cssStyle="width:600px;" required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			作者:
		</td>	
		<td>
		  <s:textfield label="作者" key="author" value="%{model.author}" cssClass="" required="false" />
	    </td>
		<td class="tdlabel">
			部门:
		</td>	
		<td>
		  <s:textfield label="部门" key="department" value="%{model.department}" cssClass="" required="false" />
	    </td>
	</tr>
		
	<tr>	
		<td colspan="4" align="center">
		<s:textarea  id="content" name="content" value="%{model.content}" ></s:textarea>
	    </td>
	</tr>

	
	 
	<!-- 
	<tr>	
		<td class="tdlabel">
			发布人<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="发布人" key="publisherId" value="%{model.publisherId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			发布时间<span class="required">*</span>:
		</td>	
		<td>
		<input value="${model.publishTimeString}" onclick="WdatePicker({dateFmt:'<%=PublishInformation.FORMAT_PUBLISH_TIME%>'})" id="publishTimeString" name="publishTimeString"  maxlength="0" class="Wdate required " />
		</td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			点击量:
		</td>	
		<td>
		  <s:textfield label="点击量" key="viewHits" value="%{model.viewHits}" cssClass="digits " required="false" />
	    </td>
	</tr>
	 -->
