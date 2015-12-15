package com.dqs.biz.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

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


public class RescueMemberDetailinfoQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 队员ID */
	private Long memberId;
	/** 身体素质 */
	private java.lang.String fitness;
	/** 急救及医疗水平 */
	private java.lang.String abilityMedical;
	/** GPS定位水平 */
	private java.lang.String abilityGps;
	/** 交通工具水平 */
	private java.lang.String abilityTransport;
	/** 其他专长 */
	private java.lang.String abilityOther;
	/** 救援经历 */
	private java.lang.String rescueExperience;

	public Long getMemberId() {
		return this.memberId;
	}
	
	public void setMemberId(Long value) {
		this.memberId = value;
	}
	
	public java.lang.String getFitness() {
		return this.fitness;
	}
	
	public void setFitness(java.lang.String value) {
		this.fitness = value;
	}
	
	public java.lang.String getAbilityMedical() {
		return this.abilityMedical;
	}
	
	public void setAbilityMedical(java.lang.String value) {
		this.abilityMedical = value;
	}
	
	public java.lang.String getAbilityGps() {
		return this.abilityGps;
	}
	
	public void setAbilityGps(java.lang.String value) {
		this.abilityGps = value;
	}
	
	public java.lang.String getAbilityTransport() {
		return this.abilityTransport;
	}
	
	public void setAbilityTransport(java.lang.String value) {
		this.abilityTransport = value;
	}
	
	public java.lang.String getAbilityOther() {
		return this.abilityOther;
	}
	
	public void setAbilityOther(java.lang.String value) {
		this.abilityOther = value;
	}
	
	public java.lang.String getRescueExperience() {
		return this.rescueExperience;
	}
	
	public void setRescueExperience(java.lang.String value) {
		this.rescueExperience = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

