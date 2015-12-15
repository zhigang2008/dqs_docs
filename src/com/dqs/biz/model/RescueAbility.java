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


public class RescueAbility extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援能力";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_ABILITY_NAME = "救援能力";
	public static final String ALIAS_ABILITY_DESC = "能力描述";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 救援能力       db_column: ABILITY_NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String abilityName;
       /**
        * 能力描述       db_column: ABILITY_DESC 
        */	
	//@Length(max=200)
	private java.lang.String abilityDesc;
	//columns END

	public RescueAbility(){
	}

	public RescueAbility(
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
	public void setAbilityName(java.lang.String value) {
		this.abilityName = value;
	}
	
	public java.lang.String getAbilityName() {
		return this.abilityName;
	}
	public void setAbilityDesc(java.lang.String value) {
		this.abilityDesc = value;
	}
	
	public java.lang.String getAbilityDesc() {
		return this.abilityDesc;
	}
	
	private Set teamAbilitys = new HashSet(0);
	public void setTeamAbilitys(Set<TeamAbility> teamAbility){
		this.teamAbilitys = teamAbility;
	}
	
	public Set<TeamAbility> getTeamAbilitys() {
		return teamAbilitys;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AbilityName",getAbilityName())
			.append("AbilityDesc",getAbilityDesc())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueAbility == false) return false;
		if(this == obj) return true;
		RescueAbility other = (RescueAbility)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

