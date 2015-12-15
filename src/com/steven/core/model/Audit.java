package com.steven.core.model;

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

import com.steven.core.model.*;
import com.steven.core.dao.*;
import com.steven.core.service.*;
import com.steven.core.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class Audit extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Audit";
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_USER_NAME = "用户名";
	public static final String ALIAS_ACT_TIME = "登录时间";
	public static final String ALIAS_ACT_IP = "登录ip";
	public static final String ALIAS_ACT_STATUS = "登录状态";
	public static final String ALIAS_ACT_TYPE = "操作类型";
	public static final String ALIAS_NOTES = "备注";
	
	//date formats
	public static final String FORMAT_ACT_TIME = DATE_TIME_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序号       db_column: ID 
     */	
	//
	private java.lang.Long id;
    /**
     * 用户id       db_column: USERID 
     */	
	//@NotNull 
	private java.lang.Integer userid;
    /**
     * 用户名       db_column: USER_NAME 
     */	
	//@Length(max=100)
	private java.lang.String userName;
    /**
     * 登录时间       db_column: ACT_TIME 
     */	
	//@NotNull 
	private java.util.Date actTime;
    /**
     * 登录ip       db_column: ACT_IP 
     */	
	//@Length(max=50)
	private java.lang.String actIp;
    /**
     * 登录状态       db_column: ACT_STATUS 
     */	
	//@NotNull 
	private java.lang.Boolean actStatus;
    /**
     * 操作类型       db_column: ACT_TYPE 
     */	
	//@Max(127)
	private Integer actType;
    /**
     * 备注       db_column: NOTES 
     */	
	//@Length(max=500)
	private java.lang.String notes;
	//columns END

	public Audit(){
	}

	public Audit(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}
	
	public java.lang.Integer getUserid() {
		return this.userid;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public String getActTimeString() {
		return DateConvertUtils.format(getActTime(), FORMAT_ACT_TIME);
	}
	public void setActTimeString(String value) {
		setActTime(DateConvertUtils.parse(value, FORMAT_ACT_TIME,java.util.Date.class));
	}
	
	public void setActTime(java.util.Date value) {
		this.actTime = value;
	}
	
	public java.util.Date getActTime() {
		return this.actTime;
	}
	public void setActIp(java.lang.String value) {
		this.actIp = value;
	}
	
	public java.lang.String getActIp() {
		return this.actIp;
	}
	public void setActStatus(java.lang.Boolean value) {
		this.actStatus = value;
	}
	
	public java.lang.Boolean getActStatus() {
		return this.actStatus;
	}
	public void setActType(Integer value) {
		this.actType = value;
	}
	
	public Integer getActType() {
		return this.actType;
	}
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	
	public java.lang.String getNotes() {
		return this.notes;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("UserName",getUserName())
			.append("ActTime",getActTime())
			.append("ActIp",getActIp())
			.append("ActStatus",getActStatus())
			.append("ActType",getActType())
			.append("Notes",getNotes())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Audit == false) return false;
		if(this == obj) return true;
		Audit other = (Audit)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

