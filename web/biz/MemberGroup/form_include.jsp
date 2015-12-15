<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			队员ID<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="队员ID" key="memberId" value="%{model.memberId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援组ID<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援组ID" key="groupId" value="%{model.groupId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
