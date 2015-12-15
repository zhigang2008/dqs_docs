<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			救援队名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="救援队名称" key="teamName" value="%{model.teamName}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援队类型:
		</td>	
		<td>
		  <s:select label="救援队类型" key="teamTypeId" value="%{model.teamTypeId}" cssClass="digits " required="false" list="%{baseCode.teamType}"  listKey="id"  listValue="name"/>
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			救援队位置:
		</td>	
		<td>
		 <wylb:region firstName="provinceId" firstValue="${model.provinceId}" secondName="cityId" secondValue="${model.cityId}" thirdValue="${model.districtId}" thirdName="districtId"/>
	    </td>
	</tr>
	<tr>	
		<td class="tdlabel">
			主管部门:
		</td>	
		<td>
		  <s:textfield label="主管部门" key="headDepartment" value="%{model.headDepartment}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			地址:
		</td>	
		<td>
		  <s:textfield label="地址" key="address" value="%{model.address}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			应急电话<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="应急电话" key="telphone" value="%{model.telphone}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			传真<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="传真" key="fax" value="%{model.fax}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			负责人:
		</td>	
		<td>
		  <s:textfield label="负责人" key="master" value="%{model.master}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			负责人电话:
		</td>	
		<td>
		  <s:textfield label="负责人电话" key="masterTelphone" value="%{model.masterTelphone}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			成立时间:
		</td>	
		<td>
		<input value="${model.setupTimeString}" onclick="WdatePicker({dateFmt:'<%=RescueTeam.FORMAT_SETUP_TIME%>'})" id="setupTimeString" name="setupTimeString"  maxlength="0" class="Wdate " />
		</td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			成员数:
		</td>	
		<td>
		  <s:textfield label="成员数" key="memberAmount" value="%{model.memberAmount}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			设备量:
		</td>	
		<td>
		  <s:textfield label="设备量" key="equipmentAmount" value="%{model.equipmentAmount}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			主要设备描述:
		</td>	
		<td>
		  <s:textarea label="主要设备描述" key="majorEquipment" value="%{model.majorEquipment}" cssClass="" required="false" cols="60" rows="3"/>
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			主要专长能力描述:
		</td>	
		<td>
		  <s:textarea label="主要专长能力描述" key="majorAbility" value="%{model.majorAbility}" cssClass="" required="false"  cols="60" rows="3"/>
	    </td>
	</tr>
	<!-- 
	<tr>	
		<td class="tdlabel">
			级别:
		</td>	
		<td>
		  <s:textfield label="级别" key="teamLevel" value="%{model.teamLevel}" cssClass="digits " required="false" />
	    </td>
	</tr>
	 -->
