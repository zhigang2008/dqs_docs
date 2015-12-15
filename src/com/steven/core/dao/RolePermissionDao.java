package com.steven.core.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class RolePermissionDao extends BaseIbatis3Dao<RolePermission,Long>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "RolePermission";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(RolePermission entity) {
		if(entity.getId() == null){ 
			save(entity);
		}
		else {
			update(entity);
		}
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 分页查询结果
	 * @see com.steven.framework.core.page.Page
	 */
	public Page findPage(RolePermissionQuery query) {
		return pageQuery("RolePermission.findPage",query);
	}
	/**根据用户角色ID删除
	 * @param roleId 角色ID
	 */
	public void removeByRoleId(Integer roleId) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".deleteByRoleId",
		    roleId);
	    
	}
	/**根据权限编码删除
	 * @param permissionCode
	 */
	public void removeByPermissionId(String permissionId) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".deleteByPermissionId",
		    permissionId);
	    
	}
	

}
