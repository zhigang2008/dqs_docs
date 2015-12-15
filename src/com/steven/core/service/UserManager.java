package com.steven.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.UserDao;
import com.steven.core.model.User;
import com.steven.core.vo.query.UserQuery;
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
public class UserManager extends BaseManager<User,java.lang.Integer>{

	private UserDao userDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setUserDao(UserDao dao) {
		this.userDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.userDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(UserQuery query) {
		return userDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public User getByUserName(java.lang.String v) {
		return userDao.getByUserName(v);
	}
	/**获取用户所有功能模块
	 * @param user
	 * @return
	 */
	public List getUserModules(User user){
	    return userDao.getUserModules(user);
	}
	/**获取用户所有角色
	 * @param user
	 * @return
	 */
	public List getUserRoles(User user){
	    return userDao.getUserRoles(user);
	}
	
	public List getUserGroups(User user){
	    return userDao.getUserGroups(user);
	}
	
	public List getUserPermissions(User user){
	    return userDao.getUserPermissions(user);
	}
	
}
