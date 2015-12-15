<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />
    <s:hidden id="fileSize" name="fileSize"  />
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			类型:
		</td>	
		<td>
		  <s:select label="类型" key="planTypeId" list="%{baseCode.emergencyPlanType}" listKey="id" listValue="typeName" value="%{model.planTypeId}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="名称" id="documentName" name="name" value="%{model.name}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			版本<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="版本" key="planVersion" value="%{model.planVersion}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			部门:
		</td>	
		<td>
		  <s:textfield label="部门" key="department" value="%{model.department}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<!--  
	<tr>	
		<td class="tdlabel">
			提交人<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="提交人" key="submitterId" value="%{model.submitterId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	<tr>	
		<td class="tdlabel">
			提交时间<span class="required">*</span>:
		</td>	
		<td>
		<input value="${model.submitTimeString}" onclick="WdatePicker({dateFmt:'<%=EmergencyPlan.FORMAT_SUBMIT_TIME%>'})" id="submitTimeString" name="submitTimeString"  maxlength="0" class="Wdate required " />
		</td>
	</tr>
	<tr>	
		<td class="tdlabel">
			状态<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="状态" key="status" value="%{model.status}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	-->
	
	<tr>	
		<td class="tdlabel">
			描述:
		</td>	
		<td>
		  <s:textarea rows="4" cols="80" label="描述" key="planDescription" value="%{model.planDescription}" cssClass="" required="false" />
	    </td>
	</tr>
	<!-- 
	<tr>	
		<td class="tdlabel">
			文件名称:
		</td>	
		<td>
		  <s:textfield label="文件名称" id="documentFile" name="planFile" value="%{model.planFile}" cssClass="" required="false" />
	    </td>
	</tr>
	 -->
