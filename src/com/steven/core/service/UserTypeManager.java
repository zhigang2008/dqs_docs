package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.UserTypeDao;
import com.steven.core.model.UserType;
import com.steven.core.vo.query.UserTypeQuery;
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
public class UserTypeManager extends BaseManager<UserType,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private UserTypeDao userTypeDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setUserTypeDao(UserTypeDao dao) {
		this.userTypeDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.userTypeDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(UserTypeQuery query) {
		return userTypeDao.findPage(query);
	}
	
}
