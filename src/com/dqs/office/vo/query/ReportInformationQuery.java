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


public class ReportInformationQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 序号 */
	private Long id;
	/** 类型 */
	private Long typeId;
	/** 信息标题 */
	private java.lang.String reportTitle;
	/** 上报人 */
	private Long reporterId;
	/** 上报时间 */
	private java.util.Date reportTimeBegin;
	private java.util.Date reportTimeEnd;
	/** 审核状态 */
	private Long status;
	/** 批注 */
	private java.lang.String backNote;
	/** 生成文档 */
	private java.lang.String reportFile;
	/** 上报内容 */
	private String content;
	/** 是否公开 */
	private java.lang.Boolean isPublic;

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
	
	public java.lang.String getReportTitle() {
		return this.reportTitle;
	}
	
	public void setReportTitle(java.lang.String value) {
		this.reportTitle = value;
	}
	
	public Long getReporterId() {
		return this.reporterId;
	}
	
	public void setReporterId(Long value) {
		this.reporterId = value;
	}
	
	public java.util.Date getReportTimeBegin() {
		return this.reportTimeBegin;
	}
	
	public void setReportTimeBegin(java.util.Date value) {
		this.reportTimeBegin = value;
	}	
	
	public java.util.Date getReportTimeEnd() {
		return this.reportTimeEnd;
	}
	
	public void setReportTimeEnd(java.util.Date value) {
		this.reportTimeEnd = value;
	}
	
	public Long getStatus() {
		return this.status;
	}
	
	public void setStatus(Long value) {
		this.status = value;
	}
	
	public java.lang.String getBackNote() {
		return this.backNote;
	}
	
	public void setBackNote(java.lang.String value) {
		this.backNote = value;
	}
	
	public java.lang.String getReportFile() {
		return this.reportFile;
	}
	
	public void setReportFile(java.lang.String value) {
		this.reportFile = value;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String value) {
		this.content = value;
	}
	
	public java.lang.Boolean getIsPublic() {
		return this.isPublic;
	}
	
	public void setIsPublic(java.lang.Boolean value) {
		this.isPublic = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

