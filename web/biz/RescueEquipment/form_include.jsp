<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			装备名称<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="装备名称" key="name" value="%{model.name}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			装备类别<span class="required">*</span>:
		</td>	
		<td>
		  <s:select label="装备类别" key="typeId" value="%{model.typeId}" list="%{baseCode.equipmentType}"  listKey="id"  listValue="typeName" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			数量<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="数量" key="amount" value="%{model.amount}" cssClass="required digits " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			状态<span class="required">*</span>:
		</td>	
		<td>
		  <s:select label="状态" key="status" value="%{model.status}" list="%{baseCode.equipmentStatus}"  listKey="id"  listValue="statusName" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			生产地:
		</td>	
		<td>
		  <s:textfield label="生产地" key="productLocation" value="%{model.productLocation}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			生产日期:
		</td>	
		<td>
		<input value="${model.productTimeString}" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_PRODUCT_TIME%>'})" id="productTimeString" name="productTimeString"  maxlength="0" class="Wdate " />
		</td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			购买日期:
		</td>	
		<td>
		<input value="${model.buyTimeString}" onclick="WdatePicker({dateFmt:'<%=RescueEquipment.FORMAT_BUY_TIME%>'})" id="buyTimeString" name="buyTimeString"  maxlength="0" class="Wdate " />
		</td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			造价(元):
		</td>	
		<td>
		  <s:textfield label="造价" key="price" value="%{model.price}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
