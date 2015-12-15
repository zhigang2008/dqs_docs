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


public class Permission extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "权限信息";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_CODE = "编码";
	public static final String ALIAS_DESCRIPTION = "权限描述";
	public static final String ALIAS_STATUS = "状态";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 编码       db_column: CODE 
        */	
	//@NotBlank @Length(max=100)
	private java.lang.String code;
       /**
        * 权限描述       db_column: DESCRIPTION 
        */	
	//@Length(max=500)
	private java.lang.String description;
       /**
        * 状态       db_column: STATUS 
        */	
	//@NotNull 
	private java.lang.Boolean status;
	//columns END

	public Permission(){
	}

	public Permission(
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
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	private Set rolePermissions = new HashSet(0);
	public void setRolePermissions(Set<RolePermission> rolePermission){
		this.rolePermissions = rolePermission;
	}
	
	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Description",getDescription())
			.append("Status",getStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Permission == false) return false;
		if(this == obj) return true;
		Permission other = (Permission)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

