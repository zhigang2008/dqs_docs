package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.UserRoleDao;
import com.steven.core.model.UserRole;
import com.steven.core.vo.query.UserRoleQuery;
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
public class UserRoleManager extends BaseManager<UserRole,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private UserRoleDao userRoleDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setUserRoleDao(UserRoleDao dao) {
		this.userRoleDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.userRoleDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(UserRoleQuery query) {
		return userRoleDao.findPage(query);
	}
	/**根据用户ID删除记录
	 * @param userid 用户ID
	 */
	public void removeByUserId(Integer userid) {
	    userRoleDao.removeByUserId(userid);
	    
	}
	/**根据角色ID删除记录
	 * @param roleid 角色id
	 */
	public void removeByRoleId(Integer roleid) {
	    userRoleDao.removeByRoleId(roleid);
	    
	}
	
}
