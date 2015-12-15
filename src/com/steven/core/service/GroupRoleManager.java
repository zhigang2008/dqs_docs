package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.GroupRoleDao;
import com.steven.core.model.GroupRole;
import com.steven.core.vo.query.GroupRoleQuery;
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
public class GroupRoleManager extends BaseManager<GroupRole,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private GroupRoleDao groupRoleDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setGroupRoleDao(GroupRoleDao dao) {
		this.groupRoleDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.groupRoleDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(GroupRoleQuery query) {
		return groupRoleDao.findPage(query);
	}
	
}
