package com.steven.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steven.core.dao.RoleModuleDao;
import com.steven.core.model.Module;
import com.steven.core.model.Role;
import com.steven.core.model.RoleModule;
import com.steven.core.vo.query.RoleModuleQuery;
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
public class RoleModuleManager extends BaseManager<RoleModule,java.lang.Integer>{

	private RoleModuleDao roleModuleDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setRoleModuleDao(RoleModuleDao dao) {
		this.roleModuleDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.roleModuleDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(RoleModuleQuery query) {
		return roleModuleDao.findPage(query);
	}
	
	/**根据模块ID删除映射信息
	 * @param moduleId 模块ID
	 */
	public void removeByModuleId(Integer moduleId) {
		roleModuleDao.removeByModuleId(moduleId);
		
	}
	/**根据角色ID删除映射信息
	 * @param roleID 角色ID
	 */
	public void removeByRoleId(Integer roleID) {
		roleModuleDao.removeByRoleId(roleID);
		
	}

}
