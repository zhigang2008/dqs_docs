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


public class RescueEquipment extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援装备信息";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_NAME = "装备名称";
	public static final String ALIAS_TYPE_ID = "装备类别";
	public static final String ALIAS_AMOUNT = "数量";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_PRODUCT_LOCATION = "生产地";
	public static final String ALIAS_PRODUCT_TIME = "生产日期";
	public static final String ALIAS_BUY_TIME = "购买日期";
	public static final String ALIAS_PRICE = "造价";
	
	//date formats
	public static final String FORMAT_PRODUCT_TIME = DATE_FORMAT;
	public static final String FORMAT_BUY_TIME = DATE_FORMAT;
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
        * 装备名称       db_column: NAME 
        */	
	//@NotBlank @Length(max=100)
	private java.lang.String name;
       /**
        * 装备类别       db_column: TYPE_ID 
        */	
	//@NotNull 
	private Long typeId;
       /**
        * 数量       db_column: AMOUNT 
        */	
	//@NotNull 
	private Long amount;
       /**
        * 状态       db_column: STATUS 
        */	
	//@NotNull 
	private Long status;
       /**
        * 生产地       db_column: PRODUCT_LOCATION 
        */	
	//@Length(max=100)
	private java.lang.String productLocation;
       /**
        * 生产日期       db_column: PRODUCT_TIME 
        */	
	//
	private java.util.Date productTime;
       /**
        * 购买日期       db_column: BUY_TIME 
        */	
	//
	private java.util.Date buyTime;
       /**
        * 造价       db_column: PRICE 
        */	
	//
	private Long price;
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

	public RescueEquipment(){
	}

	public RescueEquipment(
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
	public void setTypeId(Long value) {
		this.typeId = value;
	}
	
	public Long getTypeId() {
		return this.typeId;
	}
	public void setAmount(Long value) {
		this.amount = value;
	}
	
	public Long getAmount() {
		return this.amount;
	}
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	public void setProductLocation(java.lang.String value) {
		this.productLocation = value;
	}
	
	public java.lang.String getProductLocation() {
		return this.productLocation;
	}
	public String getProductTimeString() {
		return DateConvertUtils.format(getProductTime(), FORMAT_PRODUCT_TIME);
	}
	public void setProductTimeString(String value) {
		setProductTime(DateConvertUtils.parse(value, FORMAT_PRODUCT_TIME,java.util.Date.class));
	}
	
	public void setProductTime(java.util.Date value) {
		this.productTime = value;
	}
	
	public java.util.Date getProductTime() {
		return this.productTime;
	}
	public String getBuyTimeString() {
		return DateConvertUtils.format(getBuyTime(), FORMAT_BUY_TIME);
	}
	public void setBuyTimeString(String value) {
		setBuyTime(DateConvertUtils.parse(value, FORMAT_BUY_TIME,java.util.Date.class));
	}
	
	public void setBuyTime(java.util.Date value) {
		this.buyTime = value;
	}
	
	public java.util.Date getBuyTime() {
		return this.buyTime;
	}
	public void setPrice(Long value) {
		this.price = value;
	}
	
	public Long getPrice() {
		return this.price;
	}
	
	private Set teamEquipments = new HashSet(0);
	public void setTeamEquipments(Set<TeamEquipment> teamEquipment){
		this.teamEquipments = teamEquipment;
	}
	
	public Set<TeamEquipment> getTeamEquipments() {
		return teamEquipments;
	}
	
	private EquipmentStatus equipmentStatus;
	
	public void setEquipmentStatus(EquipmentStatus equipmentStatus){
		this.equipmentStatus = equipmentStatus;
	}
	
	public EquipmentStatus getEquipmentStatus() {
		return equipmentStatus;
	}
	
	private EquipmentType equipmentType;
	
	public void setEquipmentType(EquipmentType equipmentType){
		this.equipmentType = equipmentType;
	}
	
	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("TypeId",getTypeId())
			.append("Amount",getAmount())
			.append("Status",getStatus())
			.append("ProductLocation",getProductLocation())
			.append("ProductTime",getProductTime())
			.append("BuyTime",getBuyTime())
			.append("Price",getPrice())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueEquipment == false) return false;
		if(this == obj) return true;
		RescueEquipment other = (RescueEquipment)obj;
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

