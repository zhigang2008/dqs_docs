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


public class RegionQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** id */
	private Long id;
	/** regionCode */
	private java.lang.String regionCode;
	/** regionName */
	private java.lang.String regionName;
	/** rome */
	private java.lang.String rome;
	/** zmCode */
	private java.lang.String zmCode;
	/** parentId */
	private Long parentId;
	/** memo */
	private java.lang.String memo;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public java.lang.String getRegionCode() {
		return this.regionCode;
	}
	
	public void setRegionCode(java.lang.String value) {
		this.regionCode = value;
	}
	
	public java.lang.String getRegionName() {
		return this.regionName;
	}
	
	public void setRegionName(java.lang.String value) {
		this.regionName = value;
	}
	
	public java.lang.String getRome() {
		return this.rome;
	}
	
	public void setRome(java.lang.String value) {
		this.rome = value;
	}
	
	public java.lang.String getZmCode() {
		return this.zmCode;
	}
	
	public void setZmCode(java.lang.String value) {
		this.zmCode = value;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(Long value) {
		this.parentId = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

