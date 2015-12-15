package com.steven.core.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.framework.base.BaseQuery;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class AuditQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序号 */
	private java.lang.Long id;
	/** 用户id */
	private java.lang.Integer userid;
	/** 用户名 */
	private java.lang.String userName;
	/** 登录时间 */
	private java.util.Date actTimeBegin;
	private java.util.Date actTimeEnd;
	/** 登录ip */
	private java.lang.String actIp;
	/** 登录状态 */
	private java.lang.Boolean actStatus;
	/** 操作类型 */
	private Integer actType;
	/** 备注 */
	private java.lang.String notes;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Integer getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.util.Date getActTimeBegin() {
		return this.actTimeBegin;
	}
	
	public void setActTimeBegin(java.util.Date value) {
		this.actTimeBegin = value;
	}	
	
	public java.util.Date getActTimeEnd() {
		return this.actTimeEnd;
	}
	
	public void setActTimeEnd(java.util.Date value) {
		this.actTimeEnd = value;
	}
	
	public java.lang.String getActIp() {
		return this.actIp;
	}
	
	public void setActIp(java.lang.String value) {
		this.actIp = value;
	}
	
	public java.lang.Boolean getActStatus() {
		return this.actStatus;
	}
	
	public void setActStatus(java.lang.Boolean value) {
		this.actStatus = value;
	}
	
	public Integer getActType() {
		return this.actType;
	}
	
	public void setActType(Integer value) {
		this.actType = value;
	}
	
	public java.lang.String getNotes() {
		return this.notes;
	}
	
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

