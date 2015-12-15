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


public class EmergencyPlan extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "应急预案";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_PLAN_TYPE_ID = "类型";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_PLAN_VERSION = "版本";
	public static final String ALIAS_DEPARTMENT = "部门";
	public static final String ALIAS_SUBMITTER_ID = "提交人";
	public static final String ALIAS_SUBMIT_TIME = "提交时间";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_PLAN_DESCRIPTION = "描述";
	public static final String ALIAS_PLAN_FILE = "文件名称";
	
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
        * 类型       db_column: PLAN_TYPE_ID 
        */	
	//
	private Long planTypeId;
       /**
        * 名称       db_column: NAME 
        */	
	//@NotBlank @Length(max=150)
	private java.lang.String name;
       /**
        * 版本       db_column: PLAN_VERSION 
        */	
	//@NotBlank @Length(max=20)
	private java.lang.String planVersion;
       /**
        * 部门       db_column: DEPARTMENT 
        */	
	//@Length(max=100)
	private java.lang.String department;
       /**
        * 提交人       db_column: SUBMITTER_ID 
        */	
	//@NotNull 
	private Long submitterId;
       /**
        * 提交时间       db_column: SUBMIT_TIME 
        */	
	//@NotNull 
	private java.util.Date submitTime;
       /**
        * 状态       db_column: STATUS 
        */	
	//@NotNull 
	private Long status;
       /**
        * 描述       db_column: PLAN_DESCRIPTION 
        */	
	//@Length(max=1000)
	private java.lang.String planDescription;
       /**
        * 文件名称       db_column: PLAN_FILE 
        */	
	//@Length(max=150)
	private java.lang.String planFile;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	//columns END
	

	public EmergencyPlan(){
	}

	public EmergencyPlan(
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
	public void setPlanTypeId(Long value) {
		this.planTypeId = value;
	}
	
	public Long getPlanTypeId() {
		return this.planTypeId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setPlanVersion(java.lang.String value) {
		this.planVersion = value;
	}
	
	public java.lang.String getPlanVersion() {
		return this.planVersion;
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
	public void setPlanDescription(java.lang.String value) {
		this.planDescription = value;
	}
	
	public java.lang.String getPlanDescription() {
		return this.planDescription;
	}
	public void setPlanFile(java.lang.String value) {
		this.planFile = value;
	}
	
	public java.lang.String getPlanFile() {
		return this.planFile;
	}
	
	private EmergencyPlanType emergencyPlanType;
	
	public void setEmergencyPlanType(EmergencyPlanType emergencyPlanType){
		this.emergencyPlanType = emergencyPlanType;
	}
	
	public EmergencyPlanType getEmergencyPlanType() {
		return emergencyPlanType;
	}
	
	private DocumentStatus documentStatus;
	
	public void setDocumentStatus(DocumentStatus documentStatus){
		this.documentStatus = documentStatus;
	}
	
	public DocumentStatus getDocumentStatus() {
		return documentStatus;
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
			.append("PlanTypeId",getPlanTypeId())
			.append("Name",getName())
			.append("PlanVersion",getPlanVersion())
			.append("Department",getDepartment())
			.append("SubmitterId",getSubmitterId())
			.append("SubmitTime",getSubmitTime())
			.append("Status",getStatus())
			.append("PlanDescription",getPlanDescription())
			.append("PlanFile",getPlanFile())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EmergencyPlan == false) return false;
		if(this == obj) return true;
		EmergencyPlan other = (EmergencyPlan)obj;
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

