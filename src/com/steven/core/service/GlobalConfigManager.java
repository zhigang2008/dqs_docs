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
public class GlobalConfigManager extends BaseManager<GlobalConfig,java.lang.Integer>{

       /**
	 * DAO对象
	 */
	private GlobalConfigDao globalConfigDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setGlobalConfigDao(GlobalConfigDao dao) {
		this.globalConfigDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.globalConfigDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(GlobalConfigQuery query) {
		return globalConfigDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public GlobalConfig getByParam(java.lang.String v) {
		return globalConfigDao.getByParam(v);
	}	
	
}
