<%@page import="com.dqs.biz.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<s:hidden id="id" name="id" />

<!-- ONGL access static field: @package.class@field or @vs@field -->
	<tr>	
		<td class="tdlabel">
			姓名<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="姓名" key="name" value="%{model.name}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			性别<span class="required">*</span>:
		</td>	
		<td>
		  <s:radio list='#{"M":"男","F":"女"}' label="性别" key="gender" value="%{model.gender}" cssClass="required " required="true"></s:radio>
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			出生日期<span class="required">*</span>:
		</td>	
		<td>
		<input value="${model.birthdayString}" onclick="WdatePicker({dateFmt:'<%=RescueMember.FORMAT_BIRTHDAY%>'})" id="birthdayString" name="birthdayString"  maxlength="0" class="Wdate required " />
		</td>
	</tr>
	<tr>	
		<td class="tdlabel">
			证件号码<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="证件号码" key="idno" value="%{model.idno}" cssClass="required " required="true" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			办公电话<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="办公电话" key="telphone" value="%{model.telphone}" cssClass="required " required="true" />
	    </td>
	</tr>
	<tr>	
		<td class="tdlabel">
			手机<span class="required">*</span>:
		</td>	
		<td>
		  <s:textfield label="手机" key="cellphone" value="%{model.cellphone}" cssClass="required " required="true" />
	    </td>
	</tr>
	<tr>	
		<td class="tdlabel">
			民族:
		</td>	
		<td>
		  <s:textfield label="民族" key="nation" value="%{model.nation}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			血型:
		</td>	
		<td>
		  <s:radio list='#{"A":"A","B":"B","O":"O","AB":"AB","Y":"Other"}' label="血型" key="bloodType" value="%{model.bloodType}" cssClass=" " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			身高(cm):
		</td>	
		<td>
		  <s:textfield label="身高" key="height" value="%{model.height}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			体重(kg):
		</td>	
		<td>
		  <s:textfield label="体重" key="weight" value="%{model.weight}" cssClass="digits " required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			政治面貌:
		</td>	
		<td>
		  <s:textfield label="政治面貌" key="politicalLandscape" value="%{model.politicalLandscape}" cssClass="" required="false" />
	    </td>
	</tr>
	
		
	<tr>	
		<td class="tdlabel">
			单位:
		</td>	
		<td>
		  <s:textfield label="单位" key="workUnit" value="%{model.workUnit}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			单位地址:
		</td>	
		<td>
		  <s:textfield label="单位地址" key="workAddress" value="%{model.workAddress}" cssClass="" required="false" />
	    </td>
	</tr>
	
	
	
	<tr>	
		<td class="tdlabel">
			家庭电话:
		</td>	
		<td>
		  <s:textfield label="家庭电话" key="homePhone" value="%{model.homePhone}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			家庭住址:
		</td>	
		<td>
		  <s:textfield label="家庭住址" key="homeAddress" value="%{model.homeAddress}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人1:
		</td>	
		<td>
		  <s:textfield label="紧急联系人1" key="contactsName1" value="%{model.contactsName1}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人关系1:
		</td>	
		<td>
		  <s:textfield label="紧急联系人关系1" key="contactsRelation1" value="%{model.contactsRelation1}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人电话1:
		</td>	
		<td>
		  <s:textfield label="紧急联系人电话1" key="contactsPhone1" value="%{model.contactsPhone1}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人2:
		</td>	
		<td>
		  <s:textfield label="紧急联系人2" key="contactsName2" value="%{model.contactsName2}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人关系2:
		</td>	
		<td>
		  <s:textfield label="紧急联系人关系2" key="contactsRelation2" value="%{model.contactsRelation2}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			紧急联系人电话2:
		</td>	
		<td>
		  <s:textfield label="紧急联系人电话2" key="contactsPhone2" value="%{model.contactsPhone2}" cssClass="" required="false" />
	    </td>
	</tr>
	
	<tr>	
		<td class="tdlabel">
			所属救援队:
		</td>	
		<td>
		 <s:select label="所属救援队" key="teamId" value="%{model.teamId}" cssClass="digits " required="false" list="%{baseCode.rescueTeam}"  listKey="id"  listValue="teamName" headerKey="" headerValue=" --- "/>
	    </td>
	</tr>
	
