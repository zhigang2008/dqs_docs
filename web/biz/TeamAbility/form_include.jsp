<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			救援队序号<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援队序号" key="teamId" value="%{model.teamId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			能力编号<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="能力编号" key="abilityId" value="%{model.abilityId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
