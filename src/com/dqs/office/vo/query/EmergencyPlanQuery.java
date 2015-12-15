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


public class EmergencyPlanQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 序号 */
	private Long id;
	/** 类型 */
	private Long planTypeId;
	/** 名称 */
	private java.lang.String name;
	/** 版本 */
	private java.lang.String planVersion;
	/** 部门 */
	private java.lang.String department;
	/** 提交人 */
	private Long submitterId;
	/** 提交时间 */
	private java.util.Date submitTimeBegin;
	private java.util.Date submitTimeEnd;
	/** 状态 */
	private Long status;
	/** 描述 */
	private java.lang.String planDescription;
	/** 文件名称 */
	private java.lang.String planFile;
	/**文件大小**/
	private Long fileSize;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getPlanTypeId() {
		return this.planTypeId;
	}
	
	public void setPlanTypeId(Long value) {
		this.planTypeId = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getPlanVersion() {
		return this.planVersion;
	}
	
	public void setPlanVersion(java.lang.String value) {
		this.planVersion = value;
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
	
	public java.lang.String getPlanDescription() {
		return this.planDescription;
	}
	
	public void setPlanDescription(java.lang.String value) {
		this.planDescription = value;
	}
	
	public java.lang.String getPlanFile() {
		return this.planFile;
	}
	
	public void setPlanFile(java.lang.String value) {
		this.planFile = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public void setFileSize(Long fileSize) {
	    this.fileSize = fileSize;
	}

	public Long getFileSize() {
	    return fileSize;
	}
	
}

