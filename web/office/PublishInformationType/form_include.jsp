<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			类型<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="类型" key="typeName" value="%{model.typeName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
