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


public class GlobalConfig extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "GlobalConfig";
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_PARAM = "参数名称";
	public static final String ALIAS_PARAM_VALUE = "参数值";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_IS_CORE_PARAM = "是否核心参数";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 序号       db_column: ID 
     */	
	//
	private java.lang.Integer id;
    /**
     * 参数名称       db_column: PARAM 
     */	
	//@NotBlank @Length(max=100)
	private java.lang.String param;
    /**
     * 参数值       db_column: PARAM_VALUE 
     */	
	//@NotBlank @Length(max=200)
	private java.lang.String paramValue;
    /**
     * 描述       db_column: DESCRIPTION 
     */	
	//@Length(max=500)
	private java.lang.String description;
    /**
     * 是否核心参数       db_column: IS_CORE_PARAM 
     */	
	//@NotNull 
	private java.lang.Boolean isCoreParam=false;
	//columns END

	public GlobalConfig(){
	}

	public GlobalConfig(
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
	public void setParam(java.lang.String value) {
		this.param = value;
	}
	
	public java.lang.String getParam() {
		return this.param;
	}
	public void setParamValue(java.lang.String value) {
		this.paramValue = value;
	}
	
	public java.lang.String getParamValue() {
		return this.paramValue;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setIsCoreParam(java.lang.Boolean value) {
		this.isCoreParam = value;
	}
	
	public java.lang.Boolean getIsCoreParam() {
		return this.isCoreParam;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Param",getParam())
			.append("ParamValue",getParamValue())
			.append("Description",getDescription())
			.append("IsCoreParam",getIsCoreParam())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GlobalConfig == false) return false;
		if(this == obj) return true;
		GlobalConfig other = (GlobalConfig)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

