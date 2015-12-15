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


public class ModuleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 模块编码 */
	private java.lang.Integer moduleId;
	/** 模块名称 */
	private java.lang.String moduleName;
	/** 模块地址 */
	private java.lang.String url;
	/** 上级模块 */
	private java.lang.Integer parent;
	/** 状态 */
	private java.lang.Boolean status;
	/** 描述 */
	private java.lang.String description;
	/** 排序字段 */
	private java.lang.Integer sortOrder;

	public java.lang.Integer getModuleId() {
		return this.moduleId;
	}
	
	public void setModuleId(java.lang.Integer value) {
		this.moduleId = value;
	}
	
	public java.lang.String getModuleName() {
		return this.moduleName;
	}
	
	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.Integer getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.Integer value) {
		this.parent = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
	}
	
	public void setSortOrder(java.lang.Integer value) {
		this.sortOrder = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

