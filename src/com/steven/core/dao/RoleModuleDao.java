package com.steven.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.steven.core.model.Module;
import com.steven.core.model.RoleModule;
import com.steven.core.vo.query.RoleModuleQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class RoleModuleDao extends BaseIbatis3Dao<RoleModule,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "RoleModule";
	}
	
	public void saveOrUpdate(RoleModule entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(RoleModuleQuery query) {
		return pageQuery("RoleModule.findPage",query);
	}

	/**根据模块ID删除
	 * @param moduleId
	 */
	public void removeByModuleId(Integer moduleId) {
		int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".deleteByModuleId",
				moduleId);
		
	}

	/**根据角色ID删除
	 * @param roleID
	 */
	public void removeByRoleId(Integer roleID) {
		int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".deleteByRoleId",roleID);
		
	}

}
