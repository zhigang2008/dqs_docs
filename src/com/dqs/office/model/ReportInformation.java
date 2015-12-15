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


public class ReportInformation extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "上报信息";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TYPE_ID = "类型";
	public static final String ALIAS_REPORT_TITLE = "信息标题";
	public static final String ALIAS_REPORTER_ID = "上报人";
	public static final String ALIAS_REPORT_TIME = "上报时间";
	public static final String ALIAS_STATUS = "审核状态";
	public static final String ALIAS_BACK_NOTE = "批注";
	public static final String ALIAS_REPORT_FILE = "生成文档";
	public static final String ALIAS_CONTENT = "上报内容";
	public static final String ALIAS_IS_PUBLIC = "是否公开";
	
	//date formats
	public static final String FORMAT_REPORT_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 类型       db_column: TYPE_ID 
        */	
	//@NotNull 
	private Long typeId;
       /**
        * 信息标题       db_column: REPORT_TITLE 
        */	
	//@NotBlank @Length(max=200)
	private java.lang.String reportTitle;
       /**
        * 上报人       db_column: REPORTER_ID 
        */	
	//@NotNull 
	private Long reporterId;
       /**
        * 上报时间       db_column: REPORT_TIME 
        */	
	//
	private java.util.Date reportTime;
       /**
        * 审核状态       db_column: STATUS 
        */	
	//@NotNull 
	private Long status;
       /**
        * 批注       db_column: BACK_NOTE 
        */	
	//@Length(max=1000)
	private java.lang.String backNote;
       /**
        * 生成文档       db_column: REPORT_FILE 
        */	
	//@Length(max=200)
	private java.lang.String reportFile;
       /**
        * 上报内容       db_column: CONTENT 
        */	
	//@NotBlank @Length(max=4000)
	private String content;
       /**
        * 是否公开       db_column: IS_PUBLIC 
        */	
	//@NotNull 
	private java.lang.Boolean isPublic;
	//columns END

	public ReportInformation(){
	}

	public ReportInformation(
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
	public void setReportTitle(java.lang.String value) {
		this.reportTitle = value;
	}
	
	public java.lang.String getReportTitle() {
		return this.reportTitle;
	}
	public void setReporterId(Long value) {
		this.reporterId = value;
	}
	
	public Long getReporterId() {
		return this.reporterId;
	}
	public String getReportTimeString() {
		return DateConvertUtils.format(getReportTime(), FORMAT_REPORT_TIME);
	}
	public void setReportTimeString(String value) {
		setReportTime(DateConvertUtils.parse(value, FORMAT_REPORT_TIME,java.util.Date.class));
	}
	
	public void setReportTime(java.util.Date value) {
		this.reportTime = value;
	}
	
	public java.util.Date getReportTime() {
		return this.reportTime;
	}
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	public void setBackNote(java.lang.String value) {
		this.backNote = value;
	}
	
	public java.lang.String getBackNote() {
		return this.backNote;
	}
	public void setReportFile(java.lang.String value) {
		this.reportFile = value;
	}
	
	public java.lang.String getReportFile() {
		return this.reportFile;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setIsPublic(java.lang.Boolean value) {
		this.isPublic = value;
	}
	
	public java.lang.Boolean getIsPublic() {
		return this.isPublic;
	}
	
	private ReportInformationStatus reportInformationStatus;
	
	public void setReportInformationStatus(ReportInformationStatus reportInformationStatus){
		this.reportInformationStatus = reportInformationStatus;
	}
	
	public ReportInformationStatus getReportInformationStatus() {
		return reportInformationStatus;
	}
	
	private ReportInformationType reportInformationType;
	
	public void setReportInformationType(ReportInformationType reportInformationType){
		this.reportInformationType = reportInformationType;
	}
	
	public ReportInformationType getReportInformationType() {
		return reportInformationType;
	}
	private User reporter;
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TypeId",getTypeId())
			.append("ReportTitle",getReportTitle())
			.append("ReporterId",getReporterId())
			.append("ReportTime",getReportTime())
			.append("Status",getStatus())
			.append("BackNote",getBackNote())
			.append("ReportFile",getReportFile())
			.append("Content",getContent())
			.append("IsPublic",getIsPublic())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ReportInformation == false) return false;
		if(this == obj) return true;
		ReportInformation other = (ReportInformation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	/**
	 * @return the reporter
	 */
	public User getReporter() {
		return reporter;
	}

	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
}

