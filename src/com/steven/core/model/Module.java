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


public class Module extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Module";
	public static final String ALIAS_MODULE_ID = "模块编码";
	public static final String ALIAS_MODULE_NAME = "模块名称";
	public static final String ALIAS_URL = "模块地址";
	public static final String ALIAS_PARENT = "上级模块";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_SORT_ORDER = "排序字段";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 模块编码       db_column: MODULE_ID 
     */	
	//
	private java.lang.Integer moduleId;
    /**
     * 模块名称       db_column: MODULE_NAME 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String moduleName;
    /**
     * 模块地址       db_column: URL 
     */	
	//@NotBlank @Length(max=200)
	private java.lang.String url;
    /**
     * 上级模块       db_column: PARENT 
     */	
	//@NotNull 
	private java.lang.Integer parent;
    /**
     * 状态       db_column: STATUS 
     */	
	//@NotNull 
	private java.lang.Boolean status=true;
    /**
     * 描述       db_column: DESCRIPTION 
     */	
	//@Length(max=500)
	private java.lang.String description;
    /**
     * 排序字段       db_column: SORT_ORDER 
     */	
	//@NotNull 
	private java.lang.Integer sortOrder=1;
	//columns END

	public Module(){
	}

	public Module(
		java.lang.Integer moduleId
	){
		this.moduleId = moduleId;
	}

	public void setModuleId(java.lang.Integer value) {
		this.moduleId = value;
	}
	
	public java.lang.Integer getModuleId() {
		return this.moduleId;
	}
	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}
	
	public java.lang.String getModuleName() {
		return this.moduleName;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setParent(java.lang.Integer value) {
		this.parent = value;
	}
	
	public java.lang.Integer getParent() {
		return this.parent;
	}
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setSortOrder(java.lang.Integer value) {
		this.sortOrder = value;
	}
	
	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
	}
	
	private Set roleModules = new HashSet(0);
	public void setRoleModules(Set<RoleModule> roleModule){
		this.roleModules = roleModule;
	}
	
	public Set<RoleModule> getRoleModules() {
		return roleModules;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ModuleId",getModuleId())
			.append("ModuleName",getModuleName())
			.append("Url",getUrl())
			.append("Parent",getParent())
			.append("Status",getStatus())
			.append("Description",getDescription())
			.append("SortOrder",getSortOrder())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getModuleId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Module == false) return false;
		if(this == obj) return true;
		Module other = (Module)obj;
		return new EqualsBuilder()
			.append(getModuleId(),other.getModuleId())
			.isEquals();
	}
}

