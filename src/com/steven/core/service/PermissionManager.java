package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class PermissionManager extends BaseManager<Permission,Long>{

       /**
	 * DAO对象
	 */
	private PermissionDao permissionDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setPermissionDao(PermissionDao dao) {
		this.permissionDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.permissionDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(PermissionQuery query) {
		return permissionDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public Permission getByCode(java.lang.String v) {
		return permissionDao.getByCode(v);
	}	
	
}
