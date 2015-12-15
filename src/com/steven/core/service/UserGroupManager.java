package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.UserGroupDao;
import com.steven.core.model.UserGroup;
import com.steven.core.vo.query.UserGroupQuery;
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
public class UserGroupManager extends BaseManager<UserGroup,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private UserGroupDao userGroupDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setUserGroupDao(UserGroupDao dao) {
		this.userGroupDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.userGroupDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(UserGroupQuery query) {
		return userGroupDao.findPage(query);
	}
	
	/**根据用户ID删除记录
	 * @param userid 用户ID
	 */
	public void removeByUserId(Integer userid) {
	    userGroupDao.removeByUserId(userid);
	    
	}
	/**根据组ID删除记录
	 * @param groupid 组ID
	 */
	public void removeByGroupId(Integer groupid) {
	    userGroupDao.removeByGroupId(groupid);
	    
	}
	
}
