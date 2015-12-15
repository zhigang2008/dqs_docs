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


public class PasswordApplyInfo extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "忘记密码重置信息";
	//字段名称
	public static final String ALIAS_SEQNO = "申请识别码";
	public static final String ALIAS_USERID = "申请账户";
	public static final String ALIAS_USER_NAME = "申请账户名";
	public static final String ALIAS_APPLYTIME = "申请时间";
	public static final String ALIAS_APPLY_IP = "申请地址";
	public static final String ALIAS_DEADLINE = "有效截止时间";
	public static final String ALIAS_IS_VALID = "是否有效";
	public static final String ALIAS_USE_TIME = "使用时间";
	
	//date formats
	public static final String FORMAT_APPLYTIME = DATE_FORMAT;
	public static final String FORMAT_DEADLINE = DATE_FORMAT;
	public static final String FORMAT_USE_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 申请识别码       db_column: SEQNO 
        */	
	//@Length(max=50)
	private java.lang.String seqno;
       /**
        * 申请账户       db_column: USERID 
        */	
	//@NotNull 
	private Integer userid;
       /**
        * 申请账户名       db_column: USER_NAME 
        */	
	//@NotBlank @Length(max=80)
	private java.lang.String userName;
       /**
        * 申请时间       db_column: APPLYTIME 
        */	
	//@NotNull 
	private java.util.Date applytime;
       /**
        * 申请地址       db_column: APPLY_IP 
        */	
	//@Length(max=50)
	private java.lang.String applyIp;
       /**
        * 有效截止时间       db_column: DEADLINE 
        */	
	//
	private java.util.Date deadline;
       /**
        * 是否有效       db_column: IS_VALID 
        */	
	//@NotNull 
	private java.lang.Boolean isValid;
       /**
        * 使用时间       db_column: USE_TIME 
        */	
	//
	private java.util.Date useTime;
	//columns END

	public PasswordApplyInfo(){
	}

	public PasswordApplyInfo(
		java.lang.String seqno
	){
		this.seqno = seqno;
	}

	public void setSeqno(java.lang.String value) {
		this.seqno = value;
	}
	
	public java.lang.String getSeqno() {
		return this.seqno;
	}
	public void setUserid(Integer value) {
		this.userid = value;
	}
	
	public Integer getUserid() {
		return this.userid;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public String getApplytimeString() {
		return DateConvertUtils.format(getApplytime(), FORMAT_APPLYTIME);
	}
	public void setApplytimeString(String value) {
		setApplytime(DateConvertUtils.parse(value, FORMAT_APPLYTIME,java.util.Date.class));
	}
	
	public void setApplytime(java.util.Date value) {
		this.applytime = value;
	}
	
	public java.util.Date getApplytime() {
		return this.applytime;
	}
	public void setApplyIp(java.lang.String value) {
		this.applyIp = value;
	}
	
	public java.lang.String getApplyIp() {
		return this.applyIp;
	}
	public String getDeadlineString() {
		return DateConvertUtils.format(getDeadline(), FORMAT_DEADLINE);
	}
	public void setDeadlineString(String value) {
		setDeadline(DateConvertUtils.parse(value, FORMAT_DEADLINE,java.util.Date.class));
	}
	
	public void setDeadline(java.util.Date value) {
		this.deadline = value;
	}
	
	public java.util.Date getDeadline() {
		return this.deadline;
	}
	public void setIsValid(java.lang.Boolean value) {
		this.isValid = value;
	}
	
	public java.lang.Boolean getIsValid() {
		return this.isValid;
	}
	public String getUseTimeString() {
		return DateConvertUtils.format(getUseTime(), FORMAT_USE_TIME);
	}
	public void setUseTimeString(String value) {
		setUseTime(DateConvertUtils.parse(value, FORMAT_USE_TIME,java.util.Date.class));
	}
	
	public void setUseTime(java.util.Date value) {
		this.useTime = value;
	}
	
	public java.util.Date getUseTime() {
		return this.useTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Seqno",getSeqno())
			.append("Userid",getUserid())
			.append("UserName",getUserName())
			.append("Applytime",getApplytime())
			.append("ApplyIp",getApplyIp())
			.append("Deadline",getDeadline())
			.append("IsValid",getIsValid())
			.append("UseTime",getUseTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSeqno())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PasswordApplyInfo == false) return false;
		if(this == obj) return true;
		PasswordApplyInfo other = (PasswordApplyInfo)obj;
		return new EqualsBuilder()
			.append(getSeqno(),other.getSeqno())
			.isEquals();
	}
}

