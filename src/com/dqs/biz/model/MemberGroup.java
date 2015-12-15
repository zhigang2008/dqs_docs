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


public class MemberGroup extends BaseEntity implements java.io.Serializable{
        //序列化,请重新生成
	private static final long serialVersionUID = 0L;
	
	//表别名
	public static final String TABLE_ALIAS = "救援队员可参与的救援组";
	//字段名称
	public static final String ALIAS_ID = "序列";
	public static final String ALIAS_MEMBER_ID = "队员ID";
	public static final String ALIAS_GROUP_ID = "救援组ID";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
       /**
        * 序列       db_column: ID 
        */	
	//
	private Long id;
       /**
        * 队员ID       db_column: MEMBER_ID 
        */	
	//@NotNull 
	private Long memberId;
       /**
        * 救援组ID       db_column: GROUP_ID 
        */	
	//@NotNull 
	private Long groupId;
	//columns END

	public MemberGroup(){
	}

	public MemberGroup(
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
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public Long getMemberId() {
		return this.memberId;
	}
	public void setGroupId(Long value) {
		this.groupId = value;
	}
	
	public Long getGroupId() {
		return this.groupId;
	}
	
	private RescueGroupType rescueGroupType;
	
	public void setRescueGroupType(RescueGroupType rescueGroupType){
		this.rescueGroupType = rescueGroupType;
	}
	
	public RescueGroupType getRescueGroupType() {
		return rescueGroupType;
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
			.append("Id",getId())
			.append("MemberId",getMemberId())
			.append("GroupId",getGroupId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MemberGroup == false) return false;
		if(this == obj) return true;
		MemberGroup other = (MemberGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

