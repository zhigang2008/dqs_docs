package com.steven.core.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.framework.base.BaseEntity;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class Role extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Role";
	public static final String ALIAS_ROLE_ID = "角色id";
	public static final String ALIAS_ROLE_NAME = "角色名称";
	public static final String ALIAS_ROLE_DESC = "角色描述";
	public static final String ALIAS_PRIORITY = "排列顺序";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 角色id       db_column: ROLE_ID 
     */	
	//
	private java.lang.Integer roleId;
    /**
     * 角色名称       db_column: ROLE_NAME 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String roleName;
    /**
     * 角色描述       db_column: ROLE_DESC 
     */	
	//@Length(max=500)
	private java.lang.String roleDesc;
    /**
     * 排列顺序       db_column: PRIORITY 
     */	
	//
	private java.lang.Integer priority = 1;
    /**
     * 状态       db_column: STATUS 
     */	
	//@NotNull 
	private java.lang.Boolean status=true;
	//columns END

	public Role(){
	}

	public Role(
		java.lang.Integer roleId
	){
		this.roleId = roleId;
	}

	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	public void setRoleDesc(java.lang.String value) {
		this.roleDesc = value;
	}
	
	public java.lang.String getRoleDesc() {
		return this.roleDesc;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	private Set userRoles = new HashSet(0);
	public void setUserRoles(Set<UserRole> userRole){
		this.userRoles = userRole;
	}
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	private Set rolePermissions = new HashSet(0);
	public void setRolePermissions(Set<RolePermission> rolePermission){
		this.rolePermissions = rolePermission;
	}
	
	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}
	
	private Set roleModules = new HashSet(0);
	public void setRoleModules(Set<RoleModule> roleModule){
		this.roleModules = roleModule;
	}
	
	public Set<RoleModule> getRoleModules() {
		return roleModules;
	}
	
	private Set groupRoles = new HashSet(0);
	public void setGroupRoles(Set<GroupRole> groupRole){
		this.groupRoles = groupRole;
	}
	
	public Set<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RoleId",getRoleId())
			.append("RoleName",getRoleName())
			.append("RoleDesc",getRoleDesc())
			.append("Priority",getPriority())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRoleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getRoleId(),other.getRoleId())
			.isEquals();
	}
}

