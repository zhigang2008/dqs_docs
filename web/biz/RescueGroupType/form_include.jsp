<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			救援组名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援组名称" key="groupName" value="%{model.groupName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援组职能描述:
		</td>	
		<td>
		  <s:textfield label="救援组职能描述" key="groupFunction" value="%{model.groupFunction}" cssClass="" required="false" />
	    </td>
	</tr>
	
