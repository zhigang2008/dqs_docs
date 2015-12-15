<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />
    <s:hidden id="fileSize" name="fileSize"  />
<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			类型<span class="required">*</span>:
		</td>	
		<td>
		  <s:select label="类型" key="type" value="%{model.type}" list="%{baseCode.standardWorkType}" listKey="id" listValue="typeName" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="名称" id="documentName" name="workName" value="%{model.workName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			版本:
		</td>	
		<td>
		  <s:textfield label="版本" key="version" value="%{model.version}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			作者:
		</td>	
		<td>
		  <s:textfield label="作者" key="author" value="%{model.author}" cssClass="" required="false" />
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
			发布者<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="发布者" key="submitterId" value="%{model.submitterId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			发布时间<span class="required">*</span>:
		</td>	
		<td>
		<input value="${model.submitTimeString}" onclick="WdatePicker({dateFmt:'<%=StandardWork.FORMAT_SUBMIT_TIME%>'})" id="submitTimeString" name="submitTimeString"  maxlength="0" class="Wdate required " />
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
		  <s:textarea rows="4" cols="80" label="描述" key="description" value="%{model.description}" cssClass="" required="false" />
	    </td>
	</tr>
	<!--
	<tr>	
		<td class="tdlabel">
			文件名称:
		</td>	
		<td>
		  <s:textfield label="文件名称" key="fileName" value="%{model.fileName}" cssClass="" required="false" />
	    </td>
	</tr>
	-->
