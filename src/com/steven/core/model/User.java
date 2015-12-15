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


public class User extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "User";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_USER_NAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_USER_TYPE_ID = "用户类型";
	public static final String ALIAS_REAL_NAME = "真实姓名";
	public static final String ALIAS_EMAIL = "邮件";
	public static final String ALIAS_ID_TYPE = "证件类型";
	public static final String ALIAS_ID_CODE = "证件号码";
	public static final String ALIAS_MOBILE = "移动电话";
	public static final String ALIAS_MOBILE2 = "移动电话2";
	public static final String ALIAS_TELEPHONE = "联系电话";
	public static final String ALIAS_TELEPHONE2 = "联系电话2";
	public static final String ALIAS_ADDRESS = "联系地址";
	public static final String ALIAS_POSTCODE = "邮政编码";
	public static final String ALIAS_TITLE = "头衔职称";
	public static final String ALIAS_REGISTER_TIME = "注册时间";
	public static final String ALIAS_REGISTER_IP = "注册ip";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_LAST_LOGIN_IP = "最后登录ip";
	public static final String ALIAS_LOGIN_COUNT = "登录次数";
	public static final String ALIAS_RANK = "级别";
	public static final String ALIAS_IS_DISABLED = "是否禁用";
	
	//date formats
	public static final String FORMAT_REGISTER_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_LOGIN_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 用户id       db_column: USERID 
     */	
	//
	private java.lang.Integer userid;
    /**
     * 用户名       db_column: USER_NAME 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String userName;
    /**
     * 密码       db_column: PASSWORD 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String password;
    /**
     * 用户类型       db_column: USER_TYPE_ID 
     */	
	//
	private java.lang.Integer userTypeId;
    /**
     * 真实姓名       db_column: REAL_NAME 
     */	
	//@Length(max=100)
	private java.lang.String realName;
    /**
     * 邮件       db_column: EMAIL 
     */	
	//@Length(max=100)
	private java.lang.String email;
    /**
     * 证件类型       db_column: ID_TYPE 
     */	
	//@Max(127)
	private Integer idType;
    /**
     * 证件号码       db_column: ID_CODE 
     */	
	//@Length(max=30)
	private java.lang.String idCode;
    /**
     * 移动电话       db_column: MOBILE 
     */	
	//@Length(max=20)
	private java.lang.String mobile;
    /**
     * 移动电话2       db_column: MOBILE2 
     */	
	//@Length(max=30)
	private java.lang.String mobile2;
    /**
     * 联系电话       db_column: TELEPHONE 
     */	
	//@Length(max=30)
	private java.lang.String telephone;
    /**
     * 联系电话2       db_column: TELEPHONE2 
     */	
	//@Length(max=30)
	private java.lang.String telephone2;
    /**
     * 联系地址       db_column: ADDRESS 
     */	
	//@Length(max=150)
	private java.lang.String address;
    /**
     * 邮政编码       db_column: POSTCODE 
     */	
	//@Length(max=10)
	private java.lang.String postcode;
    /**
     * 头衔职称       db_column: TITLE 
     */	
	//@Length(max=30)
	private java.lang.String title;
    /**
     * 注册时间       db_column: REGISTER_TIME 
     */	
	//
	private java.util.Date registerTime;
    /**
     * 注册ip       db_column: REGISTER_IP 
     */	
	//@Length(max=50)
	private java.lang.String registerIp;
    /**
     * 最后登录时间       db_column: LAST_LOGIN_TIME 
     */	
	//
	private java.util.Date lastLoginTime;
    /**
     * 最后登录ip       db_column: LAST_LOGIN_IP 
     */	
	//@Length(max=50)
	private java.lang.String lastLoginIp;
    /**
     * 登录次数       db_column: LOGIN_COUNT 
     */	
	//@Max(127)
	private Integer loginCount;
    /**
     * 级别       db_column: RANK 
     */	
	//@Max(127)
	private Integer rank;
    /**
     * 是否禁用       db_column: IS_DISABLED 
     */	
	//
	private java.lang.Boolean isDisabled;
	//columns END

	public User(){
	}

	public User(
		java.lang.Integer userid
	){
		this.userid = userid;
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
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setUserTypeId(java.lang.Integer value) {
		this.userTypeId = value;
	}
	
	public java.lang.Integer getUserTypeId() {
		return this.userTypeId;
	}
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setIdType(Integer value) {
		this.idType = value;
	}
	
	public Integer getIdType() {
		return this.idType;
	}
	public void setIdCode(java.lang.String value) {
		this.idCode = value;
	}
	
	public java.lang.String getIdCode() {
		return this.idCode;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setMobile2(java.lang.String value) {
		this.mobile2 = value;
	}
	
	public java.lang.String getMobile2() {
		return this.mobile2;
	}
	public void setTelephone(java.lang.String value) {
		this.telephone = value;
	}
	
	public java.lang.String getTelephone() {
		return this.telephone;
	}
	public void setTelephone2(java.lang.String value) {
		this.telephone2 = value;
	}
	
	public java.lang.String getTelephone2() {
		return this.telephone2;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setPostcode(java.lang.String value) {
		this.postcode = value;
	}
	
	public java.lang.String getPostcode() {
		return this.postcode;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public String getRegisterTimeString() {
		return DateConvertUtils.format(getRegisterTime(), FORMAT_REGISTER_TIME);
	}
	public void setRegisterTimeString(String value) {
		setRegisterTime(DateConvertUtils.parse(value, FORMAT_REGISTER_TIME,java.util.Date.class));
	}
	
	public void setRegisterTime(java.util.Date value) {
		this.registerTime = value;
	}
	
	public java.util.Date getRegisterTime() {
		return this.registerTime;
	}
	public void setRegisterIp(java.lang.String value) {
		this.registerIp = value;
	}
	
	public java.lang.String getRegisterIp() {
		return this.registerIp;
	}
	public String getLastLoginTimeString() {
		return DateConvertUtils.format(getLastLoginTime(), FORMAT_LAST_LOGIN_TIME);
	}
	public void setLastLoginTimeString(String value) {
		setLastLoginTime(DateConvertUtils.parse(value, FORMAT_LAST_LOGIN_TIME,java.util.Date.class));
	}
	
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setLastLoginIp(java.lang.String value) {
		this.lastLoginIp = value;
	}
	
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	public void setLoginCount(Integer value) {
		this.loginCount = value;
	}
	
	public Integer getLoginCount() {
		return this.loginCount;
	}
	public void setRank(Integer value) {
		this.rank = value;
	}
	
	public Integer getRank() {
		return this.rank;
	}
	public void setIsDisabled(java.lang.Boolean value) {
		this.isDisabled = value;
	}
	
	public java.lang.Boolean getIsDisabled() {
		return this.isDisabled;
	}
	
	private Set userGroups = new HashSet(0);
	public void setUserGroups(Set<UserGroup> userGroup){
		this.userGroups = userGroup;
	}
	
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	
	private Set userRoles = new HashSet(0);
	public void setUserRoles(Set<UserRole> userRole){
		this.userRoles = userRole;
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	private UserType userType;
	
	public void setUserType(UserType userType){
		this.userType = userType;
	}
	
	public UserType getUserType() {
		return userType;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Userid",getUserid())
			.append("UserName",getUserName())
			.append("UserTypeId",getUserTypeId())
			.append("RealName",getRealName())
			.append("Email",getEmail())
			.append("IdType",getIdType())
			.append("IdCode",getIdCode())
			.append("Mobile",getMobile())
			.append("Mobile2",getMobile2())
			.append("Telephone",getTelephone())
			.append("Telephone2",getTelephone2())
			.append("Address",getAddress())
			.append("Postcode",getPostcode())
			.append("Title",getTitle())
			.append("RegisterTime",getRegisterTime())
			.append("RegisterIp",getRegisterIp())
			.append("LastLoginTime",getLastLoginTime())
			.append("LastLoginIp",getLastLoginIp())
			.append("LoginCount",getLoginCount())
			.append("Rank",getRank())
			.append("IsDisabled",getIsDisabled())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder().append(getUserid(),other.getUserid()).isEquals();
	}
}

