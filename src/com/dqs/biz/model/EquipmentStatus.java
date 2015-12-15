package com.dqs.biz.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import com.steven.framework.base.*;
import com.steven.framework.util.*;

import com.steven.framework.util.*;
import com.steven.framework.common.web.util.*;
import com.steven.framework.core.page.*;

import com.dqs.biz.model.*;
import com.dqs.biz.dao.*;
import com.dqs.biz.service.*;
import com.dqs.biz.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class EquipmentStatus extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "装备状态";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_STATUS_NAME = "状态名称";
	public static final String ALIAS_STATUS_DESC = "状态描述";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 状态名称       db_column: STATUS_NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String statusName;
       /**
        * 状态描述       db_column: STATUS_DESC 
        */	
	//@Length(max=150)
	private java.lang.String statusDesc;
	//columns END

	public EquipmentStatus(){
	}

	public EquipmentStatus(
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
	public void setStatusDesc(java.lang.String value) {
		this.statusDesc = value;
	}
	
	public java.lang.String getStatusDesc() {
		return this.statusDesc;
	}
	
	private Set rescueEquipments = new HashSet(0);
	public void setRescueEquipments(Set<RescueEquipment> rescueEquipment){
		this.rescueEquipments = rescueEquipment;
	}
	
	public Set<RescueEquipment> getRescueEquipments() {
		return rescueEquipments;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("StatusName",getStatusName())
			.append("StatusDesc",getStatusDesc())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EquipmentStatus == false) return false;
		if(this == obj) return true;
		EquipmentStatus other = (EquipmentStatus)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

