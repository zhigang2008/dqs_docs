<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			救援能力<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援能力" key="abilityName" value="%{model.abilityName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			能力描述:
		</td>	
		<td>
		  <s:textfield label="能力描述" key="abilityDesc" value="%{model.abilityDesc}" cssClass="" required="false" />
	    </td>
	</tr>
	
