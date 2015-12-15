<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			身体素质:
		</td>	
		<td>
		  <s:textarea cols="100" rows="4" label="身体素质" key="rescueMemberDetailinfo.fitness" value="%{model.rescueMemberDetailinfo.fitness}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			急救及医疗水平:
		</td>	
		<td>
		  <s:textarea  cols="100" rows="4" label="急救及医疗水平" key="rescueMemberDetailinfo.abilityMedical" value="%{model.rescueMemberDetailinfo.abilityMedical}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			GPS定位水平:
		</td>	
		<td>
		  <s:textarea  cols="100" rows="4" label="GPS定位水平" key="rescueMemberDetailinfo.abilityGps" value="%{model.rescueMemberDetailinfo.abilityGps}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			交通工具水平:
		</td>	
		<td>
		  <s:textarea  cols="100" rows="4" label="交通工具水平" key="rescueMemberDetailinfo.abilityTransport" value="%{model.rescueMemberDetailinfo.abilityTransport}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			其他专长:
		</td>	
		<td>
		  <s:textarea  cols="100" rows="4" label="其他专长" key="rescueMemberDetailinfo.abilityOther" value="%{model.rescueMemberDetailinfo.abilityOther}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援经历:
		</td>	
		<td>
		  <s:textarea  cols="100" rows="4" label="救援经历" key="rescueMemberDetailinfo.rescueExperience" value="%{model.rescueMemberDetailinfo.rescueExperience}" cssClass="" required="false" />
	    </td>
	</tr>
	
