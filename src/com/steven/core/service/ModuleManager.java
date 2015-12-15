package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.ModuleDao;
import com.steven.core.model.Module;
import com.steven.core.vo.query.ModuleQuery;
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
public class ModuleManager extends BaseManager<Module,java.lang.Integer>{

	private ModuleDao moduleDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setModuleDao(ModuleDao dao) {
		this.moduleDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.moduleDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(ModuleQuery query) {
		return moduleDao.findPage(query);
	}
	
}
