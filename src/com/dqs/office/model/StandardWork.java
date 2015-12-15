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


public class StandardWork extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "标准工作程序";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TYPE = "类型";
	public static final String ALIAS_WORK_NAME = "名称";
	public static final String ALIAS_VERSION = "版本";
	public static final String ALIAS_AUTHOR = "作者";
	public static final String ALIAS_DEPARTMENT = "部门";
	public static final String ALIAS_SUBMITTER_ID = "发布者";
	public static final String ALIAS_SUBMIT_TIME = "发布时间";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_FILE_NAME = "文件名称";
	
	//date formats
	public static final String FORMAT_SUBMIT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 类型       db_column: TYPE 
        */	
	//@NotNull 
	private Long type;
       /**
        * 名称       db_column: WORK_NAME 
        */	
	//@NotBlank @Length(max=100)
	private java.lang.String workName;
       /**
        * 版本       db_column: VERSION 
        */	
	//@Length(max=20)
	private java.lang.String version;
       /**
        * 作者       db_column: AUTHOR 
        */	
	//@Length(max=50)
	private java.lang.String author;
       /**
        * 部门       db_column: DEPARTMENT 
        */	
	//@Length(max=100)
	private java.lang.String department;
       /**
        * 发布者       db_column: SUBMITTER_ID 
        */	
	//@NotNull 
	private Long submitterId;
       /**
        * 发布时间       db_column: SUBMIT_TIME 
        */	
	//@NotNull 
	private java.util.Date submitTime;
       /**
        * 状态       db_column: STATUS 
        */	
	//@NotNull 
	private Long status;
       /**
        * 描述       db_column: DESCRIPTION 
        */	
	//@Length(max=1000)
	private java.lang.String description;
       /**
        * 文件名称       db_column: FILE_NAME 
        */	
	//@Length(max=200)
	private java.lang.String fileName;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	//columns END

	public StandardWork(){
	}

	public StandardWork(
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
	public void setType(Long value) {
		this.type = value;
	}
	
	public Long getType() {
		return this.type;
	}
	public void setWorkName(java.lang.String value) {
		this.workName = value;
	}
	
	public java.lang.String getWorkName() {
		return this.workName;
	}
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
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
	public void setSubmitterId(Long value) {
		this.submitterId = value;
	}
	
	public Long getSubmitterId() {
		return this.submitterId;
	}
	public String getSubmitTimeString() {
		return DateConvertUtils.format(getSubmitTime(), FORMAT_SUBMIT_TIME);
	}
	public void setSubmitTimeString(String value) {
		setSubmitTime(DateConvertUtils.parse(value, FORMAT_SUBMIT_TIME,java.util.Date.class));
	}
	
	public void setSubmitTime(java.util.Date value) {
		this.submitTime = value;
	}
	
	public java.util.Date getSubmitTime() {
		return this.submitTime;
	}
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setFileName(java.lang.String value) {
		this.fileName = value;
	}
	
	public java.lang.String getFileName() {
		return this.fileName;
	}
	
	private DocumentStatus documentStatus;
	
	public void setDocumentStatus(DocumentStatus documentStatus){
		this.documentStatus = documentStatus;
	}
	
	public DocumentStatus getDocumentStatus() {
		return documentStatus;
	}
	
	private StandardWorkType standardWorkType;
	
	public void setStandardWorkType(StandardWorkType standardWorkType){
		this.standardWorkType = standardWorkType;
	}
	
	public StandardWorkType getStandardWorkType() {
		return standardWorkType;
	}
	private User submitter;
	public void setSubmitter(User submitter) {
	    this.submitter = submitter;
	}

	public User getSubmitter() {
	    return submitter;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Type",getType())
			.append("WorkName",getWorkName())
			.append("Version",getVersion())
			.append("Author",getAuthor())
			.append("Department",getDepartment())
			.append("SubmitterId",getSubmitterId())
			.append("SubmitTime",getSubmitTime())
			.append("Status",getStatus())
			.append("Description",getDescription())
			.append("FileName",getFileName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof StandardWork == false) return false;
		if(this == obj) return true;
		StandardWork other = (StandardWork)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
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

