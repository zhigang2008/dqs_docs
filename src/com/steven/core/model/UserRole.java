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


public class UserRole extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserRole";
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_ROLE_ID = "角色id";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序号       db_column: ID 
     */	
	//
	private java.lang.Integer id;
    /**
     * 用户id       db_column: USERID 
     */	
	//
	private java.lang.Integer userid;
    /**
     * 角色id       db_column: ROLE_ID 
     */	
	//@NotNull 
	private java.lang.Integer roleId;
	//columns END

	public UserRole(){
	}

	public UserRole(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserid(java.lang.Integer value) {
		this.userid = value;
	}
	
	public java.lang.Integer getUserid() {
		return this.userid;
	}
	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	
	private User user;
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	private Role role;
	
	public void setRole(Role role){
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Userid",getUserid())
			.append("RoleId",getRoleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserRole == false) return false;
		if(this == obj) return true;
		UserRole other = (UserRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

