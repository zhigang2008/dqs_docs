package com.dqs.biz.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.framework.base.BaseQuery;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class RescueMemberQuery extends BaseQuery implements Serializable {
    
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 4762232929114216721L;
	/** 序号 */
	private Long id;
	/** 姓名 */
	private java.lang.String name;
	/** 性别 */
	private java.lang.String gender;
	/** 出生日期 */
	private java.util.Date birthdayBegin;
	private java.util.Date birthdayEnd;
	/** 民族 */
	private java.lang.String nation;
	/** 血型 */
	private java.lang.String bloodType;
	/** 身高 */
	private Long height;
	/** 体重 */
	private Long weight;
	/** 政治面貌 */
	private java.lang.String politicalLandscape;
	/** 证件号码 */
	private java.lang.String idno;
	/** 办公电话 */
	private java.lang.String telphone;
	/** 单位 */
	private java.lang.String workUnit;
	/** 单位地址 */
	private java.lang.String workAddress;
	/** 手机 */
	private java.lang.String cellphone;
	/** 家庭电话 */
	private java.lang.String homePhone;
	/** 家庭住址 */
	private java.lang.String homeAddress;
	/** 紧急联系人1 */
	private java.lang.String contactsName1;
	/** 紧急联系人关系1 */
	private java.lang.String contactsRelation1;
	/** 紧急联系人电话1 */
	private java.lang.String contactsPhone1;
	/** 紧急联系人2 */
	private java.lang.String contactsName2;
	/** 紧急联系人关系2 */
	private java.lang.String contactsRelation2;
	/** 紧急联系人电话2 */
	private java.lang.String contactsPhone2;
	/** 所属救援队 */
	private Long teamId;
	/** 创建人 */
	private Long fcu;
	/** 创建时间 */
	private java.util.Date fcdBegin;
	private java.util.Date fcdEnd;
	/** 最后修改人 */
	private Long lcu;
	/** 最后修改时间 */
	private java.util.Date lcdBegin;
	private java.util.Date lcdEnd;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getGender() {
		return this.gender;
	}
	
	public void setGender(java.lang.String value) {
		this.gender = value;
	}
	
	public java.util.Date getBirthdayBegin() {
		return this.birthdayBegin;
	}
	
	public void setBirthdayBegin(java.util.Date value) {
		this.birthdayBegin = value;
	}	
	
	public java.util.Date getBirthdayEnd() {
		return this.birthdayEnd;
	}
	
	public void setBirthdayEnd(java.util.Date value) {
		this.birthdayEnd = value;
	}
	
	public java.lang.String getNation() {
		return this.nation;
	}
	
	public void setNation(java.lang.String value) {
		this.nation = value;
	}
	
	public java.lang.String getBloodType() {
		return this.bloodType;
	}
	
	public void setBloodType(java.lang.String value) {
		this.bloodType = value;
	}
	
	public Long getHeight() {
		return this.height;
	}
	
	public void setHeight(Long value) {
		this.height = value;
	}
	
	public Long getWeight() {
		return this.weight;
	}
	
	public void setWeight(Long value) {
		this.weight = value;
	}
	
	public java.lang.String getPoliticalLandscape() {
		return this.politicalLandscape;
	}
	
	public void setPoliticalLandscape(java.lang.String value) {
		this.politicalLandscape = value;
	}
	
	public java.lang.String getIdno() {
		return this.idno;
	}
	
	public void setIdno(java.lang.String value) {
		this.idno = value;
	}
	
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	
	public void setTelphone(java.lang.String value) {
		this.telphone = value;
	}
	
	public java.lang.String getWorkUnit() {
		return this.workUnit;
	}
	
	public void setWorkUnit(java.lang.String value) {
		this.workUnit = value;
	}
	
	public java.lang.String getWorkAddress() {
		return this.workAddress;
	}
	
	public void setWorkAddress(java.lang.String value) {
		this.workAddress = value;
	}
	
	public java.lang.String getCellphone() {
		return this.cellphone;
	}
	
	public void setCellphone(java.lang.String value) {
		this.cellphone = value;
	}
	
	public java.lang.String getHomePhone() {
		return this.homePhone;
	}
	
	public void setHomePhone(java.lang.String value) {
		this.homePhone = value;
	}
	
	public java.lang.String getHomeAddress() {
		return this.homeAddress;
	}
	
	public void setHomeAddress(java.lang.String value) {
		this.homeAddress = value;
	}
	
	public java.lang.String getContactsName1() {
		return this.contactsName1;
	}
	
	public void setContactsName1(java.lang.String value) {
		this.contactsName1 = value;
	}
	
	public java.lang.String getContactsRelation1() {
		return this.contactsRelation1;
	}
	
	public void setContactsRelation1(java.lang.String value) {
		this.contactsRelation1 = value;
	}
	
	public java.lang.String getContactsPhone1() {
		return this.contactsPhone1;
	}
	
	public void setContactsPhone1(java.lang.String value) {
		this.contactsPhone1 = value;
	}
	
	public java.lang.String getContactsName2() {
		return this.contactsName2;
	}
	
	public void setContactsName2(java.lang.String value) {
		this.contactsName2 = value;
	}
	
	public java.lang.String getContactsRelation2() {
		return this.contactsRelation2;
	}
	
	public void setContactsRelation2(java.lang.String value) {
		this.contactsRelation2 = value;
	}
	
	public java.lang.String getContactsPhone2() {
		return this.contactsPhone2;
	}
	
	public void setContactsPhone2(java.lang.String value) {
		this.contactsPhone2 = value;
	}
	
	public Long getTeamId() {
		return this.teamId;
	}
	
	public void setTeamId(Long value) {
		this.teamId = value;
	}
	
	public Long getFcu() {
		return this.fcu;
	}
	
	public void setFcu(Long value) {
		this.fcu = value;
	}
	
	public java.util.Date getFcdBegin() {
		return this.fcdBegin;
	}
	
	public void setFcdBegin(java.util.Date value) {
		this.fcdBegin = value;
	}	
	
	public java.util.Date getFcdEnd() {
		return this.fcdEnd;
	}
	
	public void setFcdEnd(java.util.Date value) {
		this.fcdEnd = value;
	}
	
	public Long getLcu() {
		return this.lcu;
	}
	
	public void setLcu(Long value) {
		this.lcu = value;
	}
	
	public java.util.Date getLcdBegin() {
		return this.lcdBegin;
	}
	
	public void setLcdBegin(java.util.Date value) {
		this.lcdBegin = value;
	}	
	
	public java.util.Date getLcdEnd() {
		return this.lcdEnd;
	}
	
	public void setLcdEnd(java.util.Date value) {
		this.lcdEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

