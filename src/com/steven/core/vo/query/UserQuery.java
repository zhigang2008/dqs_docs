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


public class UserQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 用户id */
	private java.lang.Integer userid;
	/** 用户名 */
	private java.lang.String userName;
	/** 密码 */
	private java.lang.String password;
	/** 用户类型 */
	private java.lang.Integer userTypeId;
	/** 真实姓名 */
	private java.lang.String realName;
	/** 邮件 */
	private java.lang.String email;
	/** 证件类型 */
	private Integer idType;
	/** 证件号码 */
	private java.lang.String idCode;
	/** 移动电话 */
	private java.lang.String mobile;
	/** 移动电话2 */
	private java.lang.String mobile2;
	/** 联系电话 */
	private java.lang.String telephone;
	/** 联系电话2 */
	private java.lang.String telephone2;
	/** 联系地址 */
	private java.lang.String address;
	/** 邮政编码 */
	private java.lang.String postcode;
	/** 头衔职称 */
	private java.lang.String title;
	/** 注册时间 */
	private java.util.Date registerTimeBegin;
	private java.util.Date registerTimeEnd;
	/** 注册ip */
	private java.lang.String registerIp;
	/** 最后登录时间 */
	private java.util.Date lastLoginTimeBegin;
	private java.util.Date lastLoginTimeEnd;
	/** 最后登录ip */
	private java.lang.String lastLoginIp;
	/** 登录次数 */
	private Integer loginCount;
	/** 级别 */
	private Integer rank;
	/** 是否禁用 */
	private java.lang.Boolean isDisabled;

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
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.Integer getUserTypeId() {
		return this.userTypeId;
	}
	
	public void setUserTypeId(java.lang.Integer value) {
		this.userTypeId = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public Integer getIdType() {
		return this.idType;
	}
	
	public void setIdType(Integer value) {
		this.idType = value;
	}
	
	public java.lang.String getIdCode() {
		return this.idCode;
	}
	
	public void setIdCode(java.lang.String value) {
		this.idCode = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile2() {
		return this.mobile2;
	}
	
	public void setMobile2(java.lang.String value) {
		this.mobile2 = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone2() {
		return this.telephone2;
	}
	
	public void setTelephone2(java.lang.String value) {
		this.telephone2 = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getPostcode() {
		return this.postcode;
	}
	
	public void setPostcode(java.lang.String value) {
		this.postcode = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.util.Date getRegisterTimeBegin() {
		return this.registerTimeBegin;
	}
	
	public void setRegisterTimeBegin(java.util.Date value) {
		this.registerTimeBegin = value;
	}	
	
	public java.util.Date getRegisterTimeEnd() {
		return this.registerTimeEnd;
	}
	
	public void setRegisterTimeEnd(java.util.Date value) {
		this.registerTimeEnd = value;
	}
	
	public java.lang.String getRegisterIp() {
		return this.registerIp;
	}
	
	public void setRegisterIp(java.lang.String value) {
		this.registerIp = value;
	}
	
	public java.util.Date getLastLoginTimeBegin() {
		return this.lastLoginTimeBegin;
	}
	
	public void setLastLoginTimeBegin(java.util.Date value) {
		this.lastLoginTimeBegin = value;
	}	
	
	public java.util.Date getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}
	
	public void setLastLoginTimeEnd(java.util.Date value) {
		this.lastLoginTimeEnd = value;
	}
	
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(java.lang.String value) {
		this.lastLoginIp = value;
	}
	
	public Integer getLoginCount() {
		return this.loginCount;
	}
	
	public void setLoginCount(Integer value) {
		this.loginCount = value;
	}
	
	public Integer getRank() {
		return this.rank;
	}
	
	public void setRank(Integer value) {
		this.rank = value;
	}
	
	public java.lang.Boolean getIsDisabled() {
		return this.isDisabled;
	}
	
	public void setIsDisabled(java.lang.Boolean value) {
		this.isDisabled = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

