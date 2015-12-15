<%@page import="com.dqs.office.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			类型<span class="required">*</span>:
		</td>	
		<td>
		   <s:select label="类型" value="%{model.typeId}" id="typeId" name="typeId" list="%{baseCode.reportInformationType}" listKey="id" listValue="typeName" headerKey="" headerValue="---请选择上报信息类型---" cssClass="required " required="true"/>
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			信息标题<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="信息标题" key="reportTitle" value="%{model.reportTitle}" cssClass="required " required="true" cssStyle="width:600px;"/>
	    </td>
	</tr>
<!-- 
	<tr>	
		<td class="tdlabel">
			上报人<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="上报人" key="reporterId" value="%{model.reporterId}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			上报时间:
		</td>	
		<td>
		<input value="${model.reportTimeString}" onclick="WdatePicker({dateFmt:'<%=ReportInformation.FORMAT_REPORT_TIME%>'})" id="reportTimeString" name="reportTimeString"  maxlength="0" class="Wdate " />
		</td>
	</tr>
	
<tr>	
		<td class="tdlabel">
			批注:
		</td>	
		<td>
		  <s:textfield label="批注" key="backNote" value="%{model.backNote}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			生成文档:
		</td>	
		<td>
		  <s:textfield label="生成文档" key="reportFile" value="%{model.reportFile}" cssClass="" required="false" />
	    </td>
	</tr>
	<tr>	
		<td class="tdlabel">
			审核状态<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="审核状态" key="status" value="%{model.status}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	 -->	
	<tr>	
		<td colspan="2">
		  <wylb:editor instanceName="InformationContent" inputName="content" toolbarSet="Smart" height="400" value="${model.content}"></wylb:editor>
	    </td>
	</tr>
	
	
