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


public class RescueGroupType extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援组类型";
	//字段名称
	public static final String ALIAS_ID = "序号";
	public static final String ALIAS_GROUP_NAME = "救援组名称";
	public static final String ALIAS_GROUP_FUNCTION = "救援组职能描述";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序号       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 救援组名称       db_column: GROUP_NAME 
        */	
	//@NotBlank @Length(max=50)
	private java.lang.String groupName;
       /**
        * 救援组职能描述       db_column: GROUP_FUNCTION 
        */	
	//@Length(max=500)
	private java.lang.String groupFunction;
	//columns END

	public RescueGroupType(){
	}

	public RescueGroupType(
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
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	public void setGroupFunction(java.lang.String value) {
		this.groupFunction = value;
	}
	
	public java.lang.String getGroupFunction() {
		return this.groupFunction;
	}
	
	private Set memberGroups = new HashSet(0);
	public void setMemberGroups(Set<MemberGroup> memberGroup){
		this.memberGroups = memberGroup;
	}
	
	public Set<MemberGroup> getMemberGroups() {
		return memberGroups;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("GroupName",getGroupName())
			.append("GroupFunction",getGroupFunction())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RescueGroupType == false) return false;
		if(this == obj) return true;
		RescueGroupType other = (RescueGroupType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

