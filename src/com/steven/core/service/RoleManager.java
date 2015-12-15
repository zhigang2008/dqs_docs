package com.steven.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.RoleDao;
import com.steven.core.model.Permission;
import com.steven.core.model.Role;
import com.steven.core.vo.query.RoleQuery;
import com.steven.framework.base.BaseManager;
import com.steven.framework.base.EntityDao;
import com.steven.framework.core.page.Page;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class RoleManager extends BaseManager<Role,java.lang.Integer>{

	/**
	 * DAO对象
	 */
	private RoleDao roleDao;
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setRoleDao(RoleDao dao) {
		this.roleDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.roleDao;
	}
	
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(RoleQuery query) {
		return roleDao.findPage(query);
	}
	public List<Permission> getPermissionByRole(Role role) {
		return this.roleDao.getPermissionByRole(role);
		
	}
	public List<Permission> getNotOwnPermissionByRole(Role role) {
		return this.roleDao.getNotOwnPermissionByRole(role);
	}
}
