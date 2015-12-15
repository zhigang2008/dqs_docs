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


public class RescueMemberDetailinfo extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援队员详细信息";
	//字段名称
	public static final String ALIAS_MEMBER_ID = "队员ID";
	public static final String ALIAS_FITNESS = "身体素质";
	public static final String ALIAS_ABILITY_MEDICAL = "急救及医疗水平";
	public static final String ALIAS_ABILITY_GPS = "GPS定位水平";
	public static final String ALIAS_ABILITY_TRANSPORT = "交通工具水平";
	public static final String ALIAS_ABILITY_OTHER = "其他专长";
	public static final String ALIAS_RESCUE_EXPERIENCE = "救援经历";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 队员ID       db_column: MEMBER_ID 
        */	
	//
	private Long memberId;
       /**
        * 身体素质       db_column: FITNESS 
        */	
	//@Length(max=200)
	private java.lang.String fitness;
       /**
        * 急救及医疗水平       db_column: ABILITY_MEDICAL 
        */	
	//@Length(max=200)
	private java.lang.String abilityMedical;
       /**
        * GPS定位水平       db_column: ABILITY_GPS 
        */	
	//@Length(max=200)
	private java.lang.String abilityGps;
       /**
        * 交通工具水平       db_column: ABILITY_TRANSPORT 
        */	
	//@Length(max=200)
	private java.lang.String abilityTransport;
       /**
        * 其他专长       db_column: ABILITY_OTHER 
        */	
	//@Length(max=200)
	private java.lang.String abilityOther;
       /**
        * 救援经历       db_column: RESCUE_EXPERIENCE 
        */	
	//@Length(max=200)
	private java.lang.String rescueExperience;
	//columns END

	public RescueMemberDetailinfo(){
	}

	public RescueMemberDetailinfo(
		Long memberId
	){
		this.memberId = memberId;
	}

	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	public void setFitness(java.lang.String value) {
		this.fitness = value;
	}
	
	public java.lang.String getFitness() {
		return this.fitness;
	}
	public void setAbilityMedical(java.lang.String value) {
		this.abilityMedical = value;
	}
	
	public java.lang.String getAbilityMedical() {
		return this.abilityMedical;
	}
	public void setAbilityGps(java.lang.String value) {
		this.abilityGps = value;
	}
	
	public java.lang.String getAbilityGps() {
		return this.abilityGps;
	}
	public void setAbilityTransport(java.lang.String value) {
		this.abilityTransport = value;
	}
	
	public java.lang.String getAbilityTransport() {
		return this.abilityTransport;
	}
	public void setAbilityOther(java.lang.String value) {
		this.abilityOther = value;
	}
	
	public java.lang.String getAbilityOther() {
		return this.abilityOther;
	}
	public void setRescueExperience(java.lang.String value) {
		this.rescueExperience = value;
	}
	
	public java.lang.String getRescueExperience() {
		return this.rescueExperience;
	}
	
	private RescueMember rescueMember;
	
	public void setRescueMember(RescueMember rescueMember){
		this.rescueMember = rescueMember;
	}
	
	public RescueMember getRescueMember() {
		return rescueMember;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("MemberId",getMemberId())
			.append("Fitness",getFitness())
			.append("AbilityMedical",getAbilityMedical())
			.append("AbilityGps",getAbilityGps())
			.append("AbilityTransport",getAbilityTransport())
			.append("AbilityOther",getAbilityOther())
			.append("RescueExperience",getRescueExperience())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getMemberId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueMemberDetailinfo == false) return false;
		if(this == obj) return true;
		RescueMemberDetailinfo other = (RescueMemberDetailinfo)obj;
		return new EqualsBuilder()
			.append(getMemberId(),other.getMemberId())
			.isEquals();
	}
}

