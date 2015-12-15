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


public class RescueTeamQuery extends BaseQuery implements Serializable {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 8348741682044292441L;
	/** 序号 */
	private Long id;
	/** 救援队名称 */
	private java.lang.String teamName;
	/** 救援队类型 */
	private Long teamTypeId;
	/** 所在省份 */
	private Long provinceId;
	/** 所在城市 */
	private Long cityId;
	/** 所在区县 */
	private Long districtId;
	/** 主管部门 */
	private java.lang.String headDepartment;
	/** 地址 */
	private java.lang.String address;
	/** 应急电话 */
	private java.lang.String telphone;
	/** 传真 */
	private java.lang.String fax;
	/** 负责人 */
	private java.lang.String master;
	/** 负责人电话 */
	private java.lang.String masterTelphone;
	/** 成立时间 */
	private java.util.Date setupTimeBegin;
	private java.util.Date setupTimeEnd;
	/** 成员数 */
	private Long memberAmount;
	/** 设备量 */
	private Long equipmentAmount;
	/** 主要设备描述 */
	private java.lang.String majorEquipment;
	/** 主要专长能力描述 */
	private java.lang.String majorAbility;
	/** 级别 */
	private Long teamLevel;
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
	
	public java.lang.String getTeamName() {
		return this.teamName;
	}
	
	public void setTeamName(java.lang.String value) {
		this.teamName = value;
	}
	
	public Long getTeamTypeId() {
		return this.teamTypeId;
	}
	
	public void setTeamTypeId(Long value) {
		this.teamTypeId = value;
	}
	
	public Long getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(Long value) {
		this.provinceId = value;
	}
	
	public Long getCityId() {
		return this.cityId;
	}
	
	public void setCityId(Long value) {
		this.cityId = value;
	}
	
	public Long getDistrictId() {
		return this.districtId;
	}
	
	public void setDistrictId(Long value) {
		this.districtId = value;
	}
	
	public java.lang.String getHeadDepartment() {
		return this.headDepartment;
	}
	
	public void setHeadDepartment(java.lang.String value) {
		this.headDepartment = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getTelphone() {
		return this.telphone;
	}
	
	public void setTelphone(java.lang.String value) {
		this.telphone = value;
	}
	
	public java.lang.String getFax() {
		return this.fax;
	}
	
	public void setFax(java.lang.String value) {
		this.fax = value;
	}
	
	public java.lang.String getMaster() {
		return this.master;
	}
	
	public void setMaster(java.lang.String value) {
		this.master = value;
	}
	
	public java.lang.String getMasterTelphone() {
		return this.masterTelphone;
	}
	
	public void setMasterTelphone(java.lang.String value) {
		this.masterTelphone = value;
	}
	
	public java.util.Date getSetupTimeBegin() {
		return this.setupTimeBegin;
	}
	
	public void setSetupTimeBegin(java.util.Date value) {
		this.setupTimeBegin = value;
	}	
	
	public java.util.Date getSetupTimeEnd() {
		return this.setupTimeEnd;
	}
	
	public void setSetupTimeEnd(java.util.Date value) {
		this.setupTimeEnd = value;
	}
	
	public Long getMemberAmount() {
		return this.memberAmount;
	}
	
	public void setMemberAmount(Long value) {
		this.memberAmount = value;
	}
	
	public Long getEquipmentAmount() {
		return this.equipmentAmount;
	}
	
	public void setEquipmentAmount(Long value) {
		this.equipmentAmount = value;
	}
	
	public java.lang.String getMajorEquipment() {
		return this.majorEquipment;
	}
	
	public void setMajorEquipment(java.lang.String value) {
		this.majorEquipment = value;
	}
	
	public java.lang.String getMajorAbility() {
		return this.majorAbility;
	}
	
	public void setMajorAbility(java.lang.String value) {
		this.majorAbility = value;
	}
	
	public Long getTeamLevel() {
		return this.teamLevel;
	}
	
	public void setTeamLevel(Long value) {
		this.teamLevel = value;
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

