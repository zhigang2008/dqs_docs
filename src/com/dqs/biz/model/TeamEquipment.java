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


public class TeamEquipment extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援队拥有的设备";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_TEAM_ID = "救援队ID";
	public static final String ALIAS_EQUIPMENT_ID = "设备ID";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 救援队ID       db_column: TEAM_ID 
        */	
	//@NotNull 
	private Long teamId;
       /**
        * 设备ID       db_column: EQUIPMENT_ID 
        */	
	//@NotNull 
	private Long equipmentId;
	//columns END

	public TeamEquipment(){
	}

	public TeamEquipment(
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
	public void setTeamId(Long value) {
		this.teamId = value;
	}
	
	public Long getTeamId() {
		return this.teamId;
	}
	public void setEquipmentId(Long value) {
		this.equipmentId = value;
	}
	
	public Long getEquipmentId() {
		return this.equipmentId;
	}
	
	private RescueEquipment rescueEquipment;
	
	public void setRescueEquipment(RescueEquipment rescueEquipment){
		this.rescueEquipment = rescueEquipment;
	}
	
	public RescueEquipment getRescueEquipment() {
		return rescueEquipment;
	}
	
	private RescueTeam rescueTeam;
	
	public void setRescueTeam(RescueTeam rescueTeam){
		this.rescueTeam = rescueTeam;
	}
	
	public RescueTeam getRescueTeam() {
		return rescueTeam;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TeamId",getTeamId())
			.append("EquipmentId",getEquipmentId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TeamEquipment == false) return false;
		if(this == obj) return true;
		TeamEquipment other = (TeamEquipment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

