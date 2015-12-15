package com.steven.framework.base;

import java.io.Serializable;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**框架基本管理类
 * @author Steven
 * @version 1.0
 * @since 1.0
 * @param <E>
 * @param <PK>
 */
@Transactional
public abstract class BaseManager <E,PK extends Serializable>{
	
	/**
	 * 日志,子类可以使用
	 */
	protected Log log = LogFactory.getLog(getClass());

	protected abstract EntityDao getEntityDao();

	@Transactional(readOnly=true)
	public E getById(PK id) throws DataAccessException{
		return (E)getEntityDao().getById(id);
	}
	                          
	@Transactional(readOnly=true)
	public List<E> findAll() throws DataAccessException{
		return getEntityDao().findAll();
	}
	
	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(E entity) throws DataAccessException{
		getEntityDao().saveOrUpdate(entity);
	}
	
	/** 插入数据 */
	public void save(E entity) throws DataAccessException{
		 getEntityDao().save(entity);
	}
	
	public void removeById(PK id) throws DataAccessException{
		getEntityDao().deleteById(id);
	}
	
	public void update(E entity) throws DataAccessException{
		getEntityDao().update(entity);
	}
	
	/**插入记录,提供返回值判断
	 * @param entity
	 * @return 影响的记录数
	 * @throws DataAccessException
	 */
	public int insert(E entity) throws DataAccessException{
	       return getEntityDao().insert(entity);
	}
	
	@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}

	/**查找基础代码
	 * @return 基础代码列表
	 */
	@Transactional(readOnly=true)
	public List<E> getBaseCode() {
	    return getEntityDao().getBaseCode();
	}
	
}
