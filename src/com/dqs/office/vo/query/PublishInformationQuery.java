package com.dqs.office.vo.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.steven.framework.base.BaseQuery;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class PublishInformationQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 序号 */
	private Long id;
	/** 发布类型 */
	private Long typeId;
	/** 标题 */
	private java.lang.String title;
	/** 内容 */
	private java.lang.String content;
	/** 作者 */
	private java.lang.String author;
	/** 部门 */
	private java.lang.String department;
	/** 发布人 */
	private Long publisherId;
	/** 发布时间 */
	private java.util.Date publishTimeBegin;
	private java.util.Date publishTimeEnd;
	/** 点击量 */
	private Long viewHits;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getTypeId() {
		return this.typeId;
	}
	
	public void setTypeId(Long value) {
		this.typeId = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(java.lang.String value) {
		this.author = value;
	}
	
	public java.lang.String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(java.lang.String value) {
		this.department = value;
	}
	
	public Long getPublisherId() {
		return this.publisherId;
	}
	
	public void setPublisherId(Long value) {
		this.publisherId = value;
	}
	
	public java.util.Date getPublishTimeBegin() {
		return this.publishTimeBegin;
	}
	
	public void setPublishTimeBegin(java.util.Date value) {
		this.publishTimeBegin = value;
	}	
	
	public java.util.Date getPublishTimeEnd() {
		return this.publishTimeEnd;
	}
	
	public void setPublishTimeEnd(java.util.Date value) {
		this.publishTimeEnd = value;
	}
	
	public Long getViewHits() {
		return this.viewHits;
	}
	
	public void setViewHits(Long value) {
		this.viewHits = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

