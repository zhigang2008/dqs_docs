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


public class Group extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Group";
	public static final String ALIAS_GROUP_ID = "组id";
	public static final String ALIAS_GROUP_NAME = "组名";
	public static final String ALIAS_GROUP_DESC = "描述";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 组id       db_column: GROUP_ID 
     */	
	//
	private java.lang.Integer groupId;
    /**
     * 组名       db_column: GROUP_NAME 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String groupName;
    /**
     * 描述       db_column: GROUP_DESC 
     */	
	//@Length(max=500)
	private java.lang.String groupDesc;
    /**
     * 状态       db_column: STATUS 
     */	
	//@NotNull 
	private java.lang.Boolean status;
	//columns END

	public Group(){
	}

	public Group(
		java.lang.Integer groupId
	){
		this.groupId = groupId;
	}

	public void setGroupId(java.lang.Integer value) {
		this.groupId = value;
	}
	
	public java.lang.Integer getGroupId() {
		return this.groupId;
	}
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	public void setGroupDesc(java.lang.String value) {
		this.groupDesc = value;
	}
	
	public java.lang.String getGroupDesc() {
		return this.groupDesc;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	private Set userGroups = new HashSet(0);
	public void setUserGroups(Set<UserGroup> userGroup){
		this.userGroups = userGroup;
	}
	
	public Set<UserGroup> getUserGroups() {
		return userGroups;
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
			.append("GroupId",getGroupId())
			.append("GroupName",getGroupName())
			.append("GroupDesc",getGroupDesc())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGroupId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Group == false) return false;
		if(this == obj) return true;
		Group other = (Group)obj;
		return new EqualsBuilder()
			.append(getGroupId(),other.getGroupId())
			.isEquals();
	}
}

