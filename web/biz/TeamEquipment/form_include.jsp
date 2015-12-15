<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			救援队ID<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援队ID" key="teamId" value="%{model.teamId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			设备ID<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="设备ID" key="equipmentId" value="%{model.equipmentId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
