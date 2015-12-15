package com.dqs.biz.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import com.steven.framework.base.*;
import com.steven.framework.util.*;

import com.steven.framework.util.*;
import com.steven.framework.common.web.util.*;
import com.steven.framework.core.page.*;

import com.dqs.biz.model.*;
import com.dqs.biz.dao.*;
import com.dqs.biz.service.*;
import com.dqs.biz.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class RescueMember extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援队员基本信息";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_NAME = "姓名";
	public static final String ALIAS_GENDER = "性别";
	public static final String ALIAS_BIRTHDAY = "出生日期";
	public static final String ALIAS_NATION = "民族";
	public static final String ALIAS_BLOOD_TYPE = "血型";
	public static final String ALIAS_HEIGHT = "身高";
	public static final String ALIAS_WEIGHT = "体重";
	public static final String ALIAS_POLITICAL_LANDSCAPE = "政治面貌";
	public static final String ALIAS_IDNO = "证件号码";
	public static final String ALIAS_TELPHONE = "办公电话";
	public static final String ALIAS_WORK_UNIT = "单位";
	public static final String ALIAS_WORK_ADDRESS = "单位地址";
	public static final String ALIAS_CELLPHONE = "手机";
	public static final String ALIAS_HOME_PHONE = "家庭电话";
	public static final String ALIAS_HOME_ADDRESS = "家庭住址";
	public static final String ALIAS_CONTACTS_NAME1 = "紧急联系人1";
	public static final String ALIAS_CONTACTS_RELATION1 = "紧急联系人关系1";
	public static final String ALIAS_CONTACTS_PHONE1 = "紧急联系人电话1";
	public static final String ALIAS_CONTACTS_NAME2 = "紧急联系人2";
	public static final String ALIAS_CONTACTS_RELATION2 = "紧急联系人关系2";
	public static final String ALIAS_CONTACTS_PHONE2 = "紧急联系人电话2";
	public static final String ALIAS_TEAM_ID = "所属救援队";
	
	//date formats
	public static final String FORMAT_BIRTHDAY = DATE_FORMAT;
	public static final String FORMAT_FCD = DATE_TIME_FORMAT;
	public static final String FORMAT_LCD = DATE_TIME_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 姓名       db_column: NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String name;
       /**
        * 性别       db_column: GENDER 
        */	
	//@NotBlank @Length(max=1)
	private java.lang.String gender;
       /**
        * 出生日期       db_column: BIRTHDAY 
        */	
	//@NotNull 
	private java.util.Date birthday;
       /**
        * 民族       db_column: NATION 
        */	
	//@Length(max=10)
	private java.lang.String nation;
       /**
        * 血型       db_column: BLOOD_TYPE 
        */	
	//@Length(max=5)
	private java.lang.String bloodType;
       /**
        * 身高       db_column: HEIGHT 
        */	
	//
	private Long height;
       /**
        * 体重       db_column: WEIGHT 
        */	
	//
	private Long weight;
       /**
        * 政治面貌       db_column: POLITICAL_LANDSCAPE 
        */	
	//@Length(max=20)
	private java.lang.String politicalLandscape;
       /**
        * 证件号码       db_column: IDNO 
        */	
	//@NotBlank @Length(max=30)
	private java.lang.String idno;
       /**
        * 办公电话       db_column: TELPHONE 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String telphone;
       /**
        * 单位       db_column: WORK_UNIT 
        */	
	//@Length(max=150)
	private java.lang.String workUnit;
       /**
        * 单位地址       db_column: WORK_ADDRESS 
        */	
	//@Length(max=200)
	private java.lang.String workAddress;
       /**
        * 手机       db_column: CELLPHONE 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String cellphone;
       /**
        * 家庭电话       db_column: HOME_PHONE 
        */	
	//@Length(max=50)
	private java.lang.String homePhone;
       /**
        * 家庭住址       db_column: HOME_ADDRESS 
        */	
	//@Length(max=200)
	private java.lang.String homeAddress;
       /**
        * 紧急联系人1       db_column: CONTACTS_NAME1 
        */	
	//@Length(max=20)
	private java.lang.String contactsName1;
       /**
        * 紧急联系人关系1       db_column: CONTACTS_RELATION1 
        */	
	//@Length(max=20)
	private java.lang.String contactsRelation1;
       /**
        * 紧急联系人电话1       db_column: CONTACTS_PHONE1 
        */	
	//@Length(max=50)
	private java.lang.String contactsPhone1;
       /**
        * 紧急联系人2       db_column: CONTACTS_NAME2 
        */	
	//@Length(max=20)
	private java.lang.String contactsName2;
       /**
        * 紧急联系人关系2       db_column: CONTACTS_RELATION2 
        */	
	//@Length(max=20)
	private java.lang.String contactsRelation2;
       /**
        * 紧急联系人电话2       db_column: CONTACTS_PHONE2 
        */	
	//@Length(max=20)
	private java.lang.String contactsPhone2;
       /**
        * 所属救援队       db_column: TEAM_ID 
        */	
	//
	private Long teamId;
    /**
     * 创建人       db_column: FCU 
     */	
	//
	private Long fcu;
    /**
     * 创建时间       db_column: FCD 
     */	
	//
	private java.util.Date fcd;
    /**
     * 最后修改人       db_column: LCU 
     */	
	//
	private Long lcu;
    /**
     * 最后修改时间       db_column: LCD 
     */	
	//
	private java.util.Date lcd;
	//columns END

	public RescueMember(){
	}

	public RescueMember(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setGender(java.lang.String value) {
		this.gender = value;
	}
	
	public java.lang.String getGender() {
		return this.gender;
	}
	public String getBirthdayString() {
		return DateConvertUtils.format(getBirthday(), FORMAT_BIRTHDAY);
	}
	public void setBirthdayString(String value) {
		setBirthday(DateConvertUtils.parse(value, FORMAT_BIRTHDAY,java.util.Date.class));
	}
	
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public void setNation(java.lang.String value) {
		this.nation = value;
	}
	
	public java.lang.String getNation() {
		return this.nation;
	}
	public void setBloodType(java.lang.String value) {
		this.bloodType = value;
	}
	
	public java.lang.String getBloodType() {
		return this.bloodType;
	}
	public void setHeight(Long value) {
		this.height = value;
	}
	
	public Long getHeight() {
		return this.height;
	}
	public void setWeight(Long value) {
		this.weight = value;
	}
	
	public Long getWeight() {
		return this.weight;
	}
	public void setPoliticalLandscape(java.lang.String value) {
		this.politicalLandscape = value;
	}
	
	public java.lang.String getPoliticalLandscape() {
		return this.politicalLandscape;
	}
	public void setIdno(java.lang.String value) {
		this.idno = value;
	}
	
	public java.lang.String getIdno() {
		return this.idno;
	}
	public void setTelphone(java.lang.String value) {
		this.telphone = value;
	}
	
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	public void setWorkUnit(java.lang.String value) {
		this.workUnit = value;
	}
	
	public java.lang.String getWorkUnit() {
		return this.workUnit;
	}
	public void setWorkAddress(java.lang.String value) {
		this.workAddress = value;
	}
	
	public java.lang.String getWorkAddress() {
		return this.workAddress;
	}
	public void setCellphone(java.lang.String value) {
		this.cellphone = value;
	}
	
	public java.lang.String getCellphone() {
		return this.cellphone;
	}
	public void setHomePhone(java.lang.String value) {
		this.homePhone = value;
	}
	
	public java.lang.String getHomePhone() {
		return this.homePhone;
	}
	public void setHomeAddress(java.lang.String value) {
		this.homeAddress = value;
	}
	
	public java.lang.String getHomeAddress() {
		return this.homeAddress;
	}
	public void setContactsName1(java.lang.String value) {
		this.contactsName1 = value;
	}
	
	public java.lang.String getContactsName1() {
		return this.contactsName1;
	}
	public void setContactsRelation1(java.lang.String value) {
		this.contactsRelation1 = value;
	}
	
	public java.lang.String getContactsRelation1() {
		return this.contactsRelation1;
	}
	public void setContactsPhone1(java.lang.String value) {
		this.contactsPhone1 = value;
	}
	
	public java.lang.String getContactsPhone1() {
		return this.contactsPhone1;
	}
	public void setContactsName2(java.lang.String value) {
		this.contactsName2 = value;
	}
	
	public java.lang.String getContactsName2() {
		return this.contactsName2;
	}
	public void setContactsRelation2(java.lang.String value) {
		this.contactsRelation2 = value;
	}
	
	public java.lang.String getContactsRelation2() {
		return this.contactsRelation2;
	}
	public void setContactsPhone2(java.lang.String value) {
		this.contactsPhone2 = value;
	}
	
	public java.lang.String getContactsPhone2() {
		return this.contactsPhone2;
	}
	public void setTeamId(Long value) {
		this.teamId = value;
	}
	
	public Long getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 详细信息
	 */
	private RescueMemberDetailinfo rescueMemberDetailinfo;
	public void setRescueMemberDetailinfo(RescueMemberDetailinfo rescueMemberDetailinfo){
		this.rescueMemberDetailinfo = rescueMemberDetailinfo;
	}
	
	public RescueMemberDetailinfo getRescueMemberDetailinfo() {
		return rescueMemberDetailinfo;
	}
	
	private Set memberGroups = new HashSet(0);
	public void setMemberGroups(Set<MemberGroup> memberGroup){
		this.memberGroups = memberGroup;
	}
	
	public Set<MemberGroup> getMemberGroups() {
		return memberGroups;
	}
	
	private RescueTeam rescueTeam;
	
	public void setRescueTeam(RescueTeam rescueTeam){
		this.rescueTeam = rescueTeam;
	}
	
	public RescueTeam getRescueTeam() {
		return rescueTeam;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Gender",getGender())
			.append("Birthday",getBirthday())
			.append("Nation",getNation())
			.append("BloodType",getBloodType())
			.append("Height",getHeight())
			.append("Weight",getWeight())
			.append("PoliticalLandscape",getPoliticalLandscape())
			.append("Idno",getIdno())
			.append("Telphone",getTelphone())
			.append("WorkUnit",getWorkUnit())
			.append("WorkAddress",getWorkAddress())
			.append("Cellphone",getCellphone())
			.append("HomePhone",getHomePhone())
			.append("HomeAddress",getHomeAddress())
			.append("ContactsName1",getContactsName1())
			.append("ContactsRelation1",getContactsRelation1())
			.append("ContactsPhone1",getContactsPhone1())
			.append("ContactsName2",getContactsName2())
			.append("ContactsRelation2",getContactsRelation2())
			.append("ContactsPhone2",getContactsPhone2())
			.append("TeamId",getTeamId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueMember == false) return false;
		if(this == obj) return true;
		RescueMember other = (RescueMember)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public void setFcu(Long value) {
		this.fcu = value;
	}
	
	public Long getFcu() {
		return this.fcu;
	}
	public String getFcdString() {
		return DateConvertUtils.format(getFcd(), FORMAT_FCD);
	}
	public void setFcdString(String value) {
		setFcd(DateConvertUtils.parse(value, FORMAT_FCD,java.util.Date.class));
	}
	
	public void setFcd(java.util.Date value) {
		this.fcd = value;
	}
	
	public java.util.Date getFcd() {
		return this.fcd;
	}
	public void setLcu(Long value) {
		this.lcu = value;
	}
	
	public Long getLcu() {
		return this.lcu;
	}
	public String getLcdString() {
		return DateConvertUtils.format(getLcd(), FORMAT_LCD);
	}
	public void setLcdString(String value) {
		setLcd(DateConvertUtils.parse(value, FORMAT_LCD,java.util.Date.class));
	}
	
	public void setLcd(java.util.Date value) {
		this.lcd = value;
	}
	
	public java.util.Date getLcd() {
		return this.lcd;
	}
}

