<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="memberId" name="memberId" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			身体素质:
		</td>	
		<td>
		  <s:textfield label="身体素质" key="fitness" value="%{model.fitness}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			急救及医疗水平:
		</td>	
		<td>
		  <s:textfield label="急救及医疗水平" key="abilityMedical" value="%{model.abilityMedical}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			GPS定位水平:
		</td>	
		<td>
		  <s:textfield label="GPS定位水平" key="abilityGps" value="%{model.abilityGps}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			交通工具水平:
		</td>	
		<td>
		  <s:textfield label="交通工具水平" key="abilityTransport" value="%{model.abilityTransport}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			其他专长:
		</td>	
		<td>
		  <s:textfield label="其他专长" key="abilityOther" value="%{model.abilityOther}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援经历:
		</td>	
		<td>
		  <s:textfield label="救援经历" key="rescueExperience" value="%{model.rescueExperience}" cssClass="" required="false" />
	    </td>
	</tr>
	
