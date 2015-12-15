package com.dqs.office.model;

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


public class PublishInformationType extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "发布信息类型";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TYPE_NAME = "类型";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 类型       db_column: TYPE_NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String typeName;
	//columns END

	public PublishInformationType(){
	}

	public PublishInformationType(
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
	public void setTypeName(java.lang.String value) {
		this.typeName = value;
	}
	
	public java.lang.String getTypeName() {
		return this.typeName;
	}
	
	private Set publishInformations = new HashSet(0);
	public void setPublishInformations(Set<PublishInformation> publishInformation){
		this.publishInformations = publishInformation;
	}
	
	public Set<PublishInformation> getPublishInformations() {
		return publishInformations;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TypeName",getTypeName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PublishInformationType == false) return false;
		if(this == obj) return true;
		PublishInformationType other = (PublishInformationType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

