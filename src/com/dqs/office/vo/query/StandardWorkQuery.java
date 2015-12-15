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


public class StandardWorkQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 序号 */
	private Long id;
	/** 类型 */
	private Long type;
	/** 名称 */
	private java.lang.String workName;
	/** 版本 */
	private java.lang.String version;
	/** 作者 */
	private java.lang.String author;
	/** 部门 */
	private java.lang.String department;
	/** 发布者 */
	private Long submitterId;
	/** 发布时间 */
	private java.util.Date submitTimeBegin;
	private java.util.Date submitTimeEnd;
	/** 状态 */
	private Long status;
	/** 描述 */
	private java.lang.String description;
	/** 文件名称 */
	private java.lang.String fileName;
	/**
	 * 文件大小
	 */
	private Long fileSize;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getType() {
		return this.type;
	}
	
	public void setType(Long value) {
		this.type = value;
	}
	
	public java.lang.String getWorkName() {
		return this.workName;
	}
	
	public void setWorkName(java.lang.String value) {
		this.workName = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
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
	
	public Long getSubmitterId() {
		return this.submitterId;
	}
	
	public void setSubmitterId(Long value) {
		this.submitterId = value;
	}
	
	public java.util.Date getSubmitTimeBegin() {
		return this.submitTimeBegin;
	}
	
	public void setSubmitTimeBegin(java.util.Date value) {
		this.submitTimeBegin = value;
	}	
	
	public java.util.Date getSubmitTimeEnd() {
		return this.submitTimeEnd;
	}
	
	public void setSubmitTimeEnd(java.util.Date value) {
		this.submitTimeEnd = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
}

