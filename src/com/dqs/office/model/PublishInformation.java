package com.dqs.office.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.core.model.User;
import com.steven.framework.base.BaseEntity;
import com.steven.framework.util.DateConvertUtils;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class PublishInformation extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "发布信息";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TYPE_ID = "发布类型";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_AUTHOR = "作者";
	public static final String ALIAS_DEPARTMENT = "部门";
	public static final String ALIAS_PUBLISHER_ID = "发布人";
	public static final String ALIAS_PUBLISH_TIME = "发布时间";
	public static final String ALIAS_VIEW_HITS = "点击量";
	
	//date formats
	public static final String FORMAT_PUBLISH_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 发布类型       db_column: TYPE_ID 
        */	
	//@NotNull 
	private Long typeId;
       /**
        * 标题       db_column: TITLE 
        */	
	//@NotBlank @Length(max=200)
	private java.lang.String title;
       /**
        * 内容       db_column: CONTENT 
        */	
	//@NotBlank @Length(max=4000)
	private java.lang.String content;
       /**
        * 作者       db_column: AUTHOR 
        */	
	//@Length(max=100)
	private java.lang.String author;
       /**
        * 部门       db_column: DEPARTMENT 
        */	
	//@Length(max=150)
	private java.lang.String department;
       /**
        * 发布人       db_column: PUBLISHER_ID 
        */	
	//@NotNull 
	private Long publisherId;
       /**
        * 发布时间       db_column: PUBLISH_TIME 
        */	
	//@NotNull 
	private java.util.Date publishTime;
       /**
        * 点击量       db_column: VIEW_HITS 
        */	
	//
	private Long viewHits;
	//columns END

	public PublishInformation(){
	}

	public PublishInformation(
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
	public void setTypeId(Long value) {
		this.typeId = value;
	}
	
	public Long getTypeId() {
		return this.typeId;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setAuthor(java.lang.String value) {
		this.author = value;
	}
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	public void setDepartment(java.lang.String value) {
		this.department = value;
	}
	
	public java.lang.String getDepartment() {
		return this.department;
	}
	public void setPublisherId(Long value) {
		this.publisherId = value;
	}
	
	public Long getPublisherId() {
		return this.publisherId;
	}
	public String getPublishTimeString() {
		return DateConvertUtils.format(getPublishTime(), FORMAT_PUBLISH_TIME);
	}
	public void setPublishTimeString(String value) {
		setPublishTime(DateConvertUtils.parse(value, FORMAT_PUBLISH_TIME,java.util.Date.class));
	}
	
	public void setPublishTime(java.util.Date value) {
		this.publishTime = value;
	}
	
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	public void setViewHits(Long value) {
		this.viewHits = value;
	}
	
	public Long getViewHits() {
		return this.viewHits;
	}
	
	private PublishInformationType publishInformationType;
	
	public void setPublishInformationType(PublishInformationType publishInformationType){
		this.publishInformationType = publishInformationType;
	}
	
	public PublishInformationType getPublishInformationType() {
		return publishInformationType;
	}

	private User publisher;
	
	public void setPublisher(User publisher) {
	    this.publisher = publisher;
	}

	public User getPublisher() {
	    return publisher;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TypeId",getTypeId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("Author",getAuthor())
			.append("Department",getDepartment())
			.append("PublisherId",getPublisherId())
			.append("PublishTime",getPublishTime())
			.append("ViewHits",getViewHits())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PublishInformation == false) return false;
		if(this == obj) return true;
		PublishInformation other = (PublishInformation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	
}

