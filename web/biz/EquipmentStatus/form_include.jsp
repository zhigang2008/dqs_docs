<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			状态名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="状态名称" key="statusName" value="%{model.statusName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			状态描述:
		</td>	
		<td>
		  <s:textfield label="状态描述" key="statusDesc" value="%{model.statusDesc}" cssClass="" required="false" />
	    </td>
	</tr>
	
