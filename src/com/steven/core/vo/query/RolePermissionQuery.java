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


public class RolePermissionQuery extends BaseQuery implements Serializable {
    //序列化,请自动生成
    private static final long serialVersionUID = 0L;
    

	/** 序号 */
	private Long id;
	/** 角色id */
	private Long roleId;
	/** 权限编号 */
	private Long permissionId;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long value) {
		this.roleId = value;
	}
	
	public Long getPermissionId() {
		return this.permissionId;
	}
	
	public void setPermissionId(Long value) {
		this.permissionId = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

