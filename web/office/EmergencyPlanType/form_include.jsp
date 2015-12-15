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
	
	<tr>	
		<td class="tdlabel">
			描述:
		</td>	
		<td>
		  <s:textfield label="描述" key="typeDesc" value="%{model.typeDesc}" cssClass="" required="false" />
	    </td>
	</tr>
	
