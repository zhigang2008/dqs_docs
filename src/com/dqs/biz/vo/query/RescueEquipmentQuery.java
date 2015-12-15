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


public class RescueEquipmentQuery extends BaseQuery implements Serializable {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = -4750401043294324914L;
	/** 序号 */
	private Long id;
	/** 装备名称 */
	private java.lang.String name;
	/** 装备类别 */
	private Long typeId;
	/** 数量 */
	private Long amount;
	/** 状态 */
	private Long status;
	/** 生产地 */
	private java.lang.String productLocation;
	/** 生产日期 */
	private java.util.Date productTimeBegin;
	private java.util.Date productTimeEnd;
	/** 购买日期 */
	private java.util.Date buyTimeBegin;
	private java.util.Date buyTimeEnd;
	/** 造价 */
	private Long price;
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
	
	public Long getTypeId() {
		return this.typeId;
	}
	
	public void setTypeId(Long value) {
		this.typeId = value;
	}
	
	public Long getAmount() {
		return this.amount;
	}
	
	public void setAmount(Long value) {
		this.amount = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public java.lang.String getProductLocation() {
		return this.productLocation;
	}
	
	public void setProductLocation(java.lang.String value) {
		this.productLocation = value;
	}
	
	public java.util.Date getProductTimeBegin() {
		return this.productTimeBegin;
	}
	
	public void setProductTimeBegin(java.util.Date value) {
		this.productTimeBegin = value;
	}	
	
	public java.util.Date getProductTimeEnd() {
		return this.productTimeEnd;
	}
	
	public void setProductTimeEnd(java.util.Date value) {
		this.productTimeEnd = value;
	}
	
	public java.util.Date getBuyTimeBegin() {
		return this.buyTimeBegin;
	}
	
	public void setBuyTimeBegin(java.util.Date value) {
		this.buyTimeBegin = value;
	}	
	
	public java.util.Date getBuyTimeEnd() {
		return this.buyTimeEnd;
	}
	
	public void setBuyTimeEnd(java.util.Date value) {
		this.buyTimeEnd = value;
	}
	
	public Long getPrice() {
		return this.price;
	}
	
	public void setPrice(Long value) {
		this.price = value;
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

