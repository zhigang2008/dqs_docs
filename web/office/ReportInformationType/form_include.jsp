<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			编码<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="编码" key="typeCode" value="%{model.typeCode}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			类型<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="类型" key="typeName" value="%{model.typeName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td colspan="2">
		  <wylb:editor instanceName="typeTemplate" value="${model.typeTemplate}" toolbarSet="MyToolbar" height="500" ></wylb:editor>
		  
	    </td>
	</tr>
