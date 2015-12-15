package com.steven.core.vo.query;

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

import com.steven.core.model.*;
import com.steven.core.dao.*;
import com.steven.core.service.*;
import com.steven.core.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class PasswordApplyInfoQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 申请识别码 */
	private java.lang.String seqno;
	/** 申请账户 */
	private Integer userid;
	/** 申请账户名 */
	private java.lang.String userName;
	/** 申请时间 */
	private java.util.Date applytimeBegin;
	private java.util.Date applytimeEnd;
	/** 申请地址 */
	private java.lang.String applyIp;
	/** 有效截止时间 */
	private java.util.Date deadlineBegin;
	private java.util.Date deadlineEnd;
	/** 是否有效 */
	private java.lang.Boolean isValid;
	/** 使用时间 */
	private java.util.Date useTimeBegin;
	private java.util.Date useTimeEnd;

	public java.lang.String getSeqno() {
		return this.seqno;
	}
	
	public void setSeqno(java.lang.String value) {
		this.seqno = value;
	}
	
	public Integer getUserid() {
		return this.userid;
	}
	
	public void setUserid(Integer value) {
		this.userid = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.util.Date getApplytimeBegin() {
		return this.applytimeBegin;
	}
	
	public void setApplytimeBegin(java.util.Date value) {
		this.applytimeBegin = value;
	}	
	
	public java.util.Date getApplytimeEnd() {
		return this.applytimeEnd;
	}
	
	public void setApplytimeEnd(java.util.Date value) {
		this.applytimeEnd = value;
	}
	
	public java.lang.String getApplyIp() {
		return this.applyIp;
	}
	
	public void setApplyIp(java.lang.String value) {
		this.applyIp = value;
	}
	
	public java.util.Date getDeadlineBegin() {
		return this.deadlineBegin;
	}
	
	public void setDeadlineBegin(java.util.Date value) {
		this.deadlineBegin = value;
	}	
	
	public java.util.Date getDeadlineEnd() {
		return this.deadlineEnd;
	}
	
	public void setDeadlineEnd(java.util.Date value) {
		this.deadlineEnd = value;
	}
	
	public java.lang.Boolean getIsValid() {
		return this.isValid;
	}
	
	public void setIsValid(java.lang.Boolean value) {
		this.isValid = value;
	}
	
	public java.util.Date getUseTimeBegin() {
		return this.useTimeBegin;
	}
	
	public void setUseTimeBegin(java.util.Date value) {
		this.useTimeBegin = value;
	}	
	
	public java.util.Date getUseTimeEnd() {
		return this.useTimeEnd;
	}
	
	public void setUseTimeEnd(java.util.Date value) {
		this.useTimeEnd = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

