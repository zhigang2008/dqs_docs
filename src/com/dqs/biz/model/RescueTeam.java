package com.dqs.biz.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.core.model.Region;
import com.steven.framework.base.BaseEntity;
import com.steven.framework.util.DateConvertUtils;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class RescueTeam extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援队";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TEAM_NAME = "救援队名称";
	public static final String ALIAS_TEAM_TYPE_ID = "救援队类型";
	public static final String ALIAS_PROVINCE_ID = "所在省份";
	public static final String ALIAS_CITY_ID = "所在城市";
	public static final String ALIAS_DISTRICT_ID = "所在区县";
	public static final String ALIAS_HEAD_DEPARTMENT = "主管部门";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_TELPHONE = "应急电话";
	public static final String ALIAS_FAX = "传真";
	public static final String ALIAS_MASTER = "负责人";
	public static final String ALIAS_MASTER_TELPHONE = "负责人电话";
	public static final String ALIAS_SETUP_TIME = "成立时间";
	public static final String ALIAS_MEMBER_AMOUNT = "成员数";
	public static final String ALIAS_EQUIPMENT_AMOUNT = "设备量";
	public static final String ALIAS_MAJOR_EQUIPMENT = "主要设备描述";
	public static final String ALIAS_MAJOR_ABILITY = "主要专长能力描述";
	public static final String ALIAS_TEAM_LEVEL = "级别";
	public static final String ALIAS_FCU = "创建人";
	public static final String ALIAS_FCD = "创建时间";
	public static final String ALIAS_LCU = "最后修改人";
	public static final String ALIAS_LCD = "最后修改时间";
	
	//date formats
	public static final String FORMAT_SETUP_TIME = DATE_FORMAT;
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
        * 救援队名称       db_column: TEAM_NAME 
        */	
	//@NotBlank @Length(max=200)
	private java.lang.String teamName;
       /**
        * 救援队类型       db_column: TEAM_TYPE_ID 
        */	
	//
	private Long teamTypeId;
       /**
        * 所在省份       db_column: PROVINCE_ID 
        */	
	//
	private Long provinceId;
       /**
        * 所在城市       db_column: CITY_ID 
        */	
	//
	private Long cityId;
       /**
        * 所在区县       db_column: DISTRICT_ID 
        */	
	//
	private Long districtId;
       /**
        * 主管部门       db_column: HEAD_DEPARTMENT 
        */	
	//@Length(max=150)
	private java.lang.String headDepartment;
       /**
        * 地址       db_column: ADDRESS 
        */	
	//@Length(max=150)
	private java.lang.String address;
       /**
        * 应急电话       db_column: TELPHONE 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String telphone;
       /**
        * 传真       db_column: FAX 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String fax;
       /**
        * 负责人       db_column: MASTER 
        */	
	//@Length(max=50)
	private java.lang.String master;
       /**
        * 负责人电话       db_column: MASTER_TELPHONE 
        */	
	//@Length(max=50)
	private java.lang.String masterTelphone;
       /**
        * 成立时间       db_column: SETUP_TIME 
        */	
	//
	private java.util.Date setupTime;
       /**
        * 成员数       db_column: MEMBER_AMOUNT 
        */	
	//
	private Long memberAmount;
       /**
        * 设备量       db_column: EQUIPMENT_AMOUNT 
        */	
	//
	private Long equipmentAmount;
       /**
        * 主要设备描述       db_column: MAJOR_EQUIPMENT 
        */	
	//@Length(max=1000)
	private java.lang.String majorEquipment;
       /**
        * 主要专长能力描述       db_column: MAJOR_ABILITY 
        */	
	//@Length(max=1000)
	private java.lang.String majorAbility;
       /**
        * 级别       db_column: TEAM_LEVEL 
        */	
	//
	private Long teamLevel;
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

	public RescueTeam(){
	}

	public RescueTeam(
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
	public void setTeamName(java.lang.String value) {
		this.teamName = value;
	}
	
	public java.lang.String getTeamName() {
		return this.teamName;
	}
	public void setTeamTypeId(Long value) {
		this.teamTypeId = value;
	}
	
	public Long getTeamTypeId() {
		return this.teamTypeId;
	}
	public void setProvinceId(Long value) {
		this.provinceId = value;
	}
	
	public Long getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(Long value) {
		this.cityId = value;
	}
	
	public Long getCityId() {
		return this.cityId;
	}
	public void setDistrictId(Long value) {
		this.districtId = value;
	}
	
	public Long getDistrictId() {
		return this.districtId;
	}
	public void setHeadDepartment(java.lang.String value) {
		this.headDepartment = value;
	}
	
	public java.lang.String getHeadDepartment() {
		return this.headDepartment;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setTelphone(java.lang.String value) {
		this.telphone = value;
	}
	
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	
	public java.lang.String getFax() {
		return this.fax;
	}
	public void setMaster(java.lang.String value) {
		this.master = value;
	}
	
	public java.lang.String getMaster() {
		return this.master;
	}
	public void setMasterTelphone(java.lang.String value) {
		this.masterTelphone = value;
	}
	
	public java.lang.String getMasterTelphone() {
		return this.masterTelphone;
	}
	public String getSetupTimeString() {
		return DateConvertUtils.format(getSetupTime(), FORMAT_SETUP_TIME);
	}
	public void setSetupTimeString(String value) {
		setSetupTime(DateConvertUtils.parse(value, FORMAT_SETUP_TIME,java.util.Date.class));
	}
	
	public void setSetupTime(java.util.Date value) {
		this.setupTime = value;
	}
	
	public java.util.Date getSetupTime() {
		return this.setupTime;
	}
	public void setMemberAmount(Long value) {
		this.memberAmount = value;
	}
	
	public Long getMemberAmount() {
		return this.memberAmount;
	}
	public void setEquipmentAmount(Long value) {
		this.equipmentAmount = value;
	}
	
	public Long getEquipmentAmount() {
		return this.equipmentAmount;
	}
	public void setMajorEquipment(java.lang.String value) {
		this.majorEquipment = value;
	}
	
	public java.lang.String getMajorEquipment() {
		return this.majorEquipment;
	}
	public void setMajorAbility(java.lang.String value) {
		this.majorAbility = value;
	}
	
	public java.lang.String getMajorAbility() {
		return this.majorAbility;
	}
	public void setTeamLevel(Long value) {
		this.teamLevel = value;
	}
	
	public Long getTeamLevel() {
		return this.teamLevel;
	}
	
	private Set teamAbilitys = new HashSet(0);
	public void setTeamAbilitys(Set<TeamAbility> teamAbility){
		this.teamAbilitys = teamAbility;
	}
	
	public Set<TeamAbility> getTeamAbilitys() {
		return teamAbilitys;
	}
	
	private Set teamEquipments = new HashSet(0);
	public void setTeamEquipments(Set<TeamEquipment> teamEquipment){
		this.teamEquipments = teamEquipment;
	}
	
	public Set<TeamEquipment> getTeamEquipments() {
		return teamEquipments;
	}
	
	private Set rescueMembers = new HashSet(0);
	public void setRescueMembers(Set<RescueMember> rescueMember){
		this.rescueMembers = rescueMember;
	}
	
	public Set<RescueMember> getRescueMembers() {
		return rescueMembers;
	}
	
	private TeamType teamType;

	private Region province;

	private Region city;

	private Region district;
	
	public void setTeamType(TeamType teamType){
		this.teamType = teamType;
	}
	
	public TeamType getTeamType() {
		return teamType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TeamName",getTeamName())
			.append("TeamTypeId",getTeamTypeId())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("DistrictId",getDistrictId())
			.append("HeadDepartment",getHeadDepartment())
			.append("Address",getAddress())
			.append("Telphone",getTelphone())
			.append("Fax",getFax())
			.append("Master",getMaster())
			.append("MasterTelphone",getMasterTelphone())
			.append("SetupTime",getSetupTime())
			.append("MemberAmount",getMemberAmount())
			.append("EquipmentAmount",getEquipmentAmount())
			.append("MajorEquipment",getMajorEquipment())
			.append("MajorAbility",getMajorAbility())
			.append("TeamLevel",getTeamLevel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueTeam == false) return false;
		if(this == obj) return true;
		RescueTeam other = (RescueTeam)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	//地区信息
	public Region getProvince(){
		return province;
	}
	public Region getCity(){
		return city;
	}
	public Region getDistrict(){
		return district;
	}
	public void setProvince(Region province){
		this.province=province;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(Region city) {
		this.city = city;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(Region district) {
		this.district = district;
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

