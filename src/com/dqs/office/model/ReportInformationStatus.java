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


public class ReportInformationStatus extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "上报信息审批状态";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_STATUS_NAME = "状态";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 状态       db_column: STATUS_NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String statusName;
	//columns END

	public ReportInformationStatus(){
	}

	public ReportInformationStatus(
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
	public void setStatusName(java.lang.String value) {
		this.statusName = value;
	}
	
	public java.lang.String getStatusName() {
		return this.statusName;
	}
	
	private Set reportInformations = new HashSet(0);
	public void setReportInformations(Set<ReportInformation> reportInformation){
		this.reportInformations = reportInformation;
	}
	
	public Set<ReportInformation> getReportInformations() {
		return reportInformations;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("StatusName",getStatusName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ReportInformationStatus == false) return false;
		if(this == obj) return true;
		ReportInformationStatus other = (ReportInformationStatus)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

