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


public class GlobalConfigQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 序号 */
	private java.lang.Integer id;
	/** 参数名称 */
	private java.lang.String param;
	/** 参数值 */
	private java.lang.String paramValue;
	/** 描述 */
	private java.lang.String description;
	/** 是否核心参数 */
	private java.lang.Integer isCoreParam;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.String getParam() {
		return this.param;
	}
	
	public void setParam(java.lang.String value) {
		this.param = value;
	}
	
	public java.lang.String getParamValue() {
		return this.paramValue;
	}
	
	public void setParamValue(java.lang.String value) {
		this.paramValue = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.Integer getIsCoreParam() {
		return this.isCoreParam;
	}
	
	public void setIsCoreParam(java.lang.Integer value) {
		this.isCoreParam = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

