package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.AuditDao;
import com.steven.core.model.Audit;
import com.steven.core.vo.query.AuditQuery;
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
public class AuditManager extends BaseManager<Audit,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private AuditDao auditDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setAuditDao(AuditDao dao) {
		this.auditDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.auditDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(AuditQuery query) {
		return auditDao.findPage(query);
	}
	
}
