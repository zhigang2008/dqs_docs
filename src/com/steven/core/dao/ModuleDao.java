package com.steven.core.dao;

import org.springframework.stereotype.Repository;

import com.steven.core.model.Module;
import com.steven.core.vo.query.ModuleQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class ModuleDao extends BaseIbatis3Dao<Module,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "Module";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(Module entity) {
		if(entity.getModuleId() == null){ 
			save(entity);
		}
		else {
			update(entity);
		}
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 分页查询结果
	 * @see com.steven.framework.core.page.Page
	 */
	public Page findPage(ModuleQuery query) {
		return pageQuery("Module.findPage",query);
	}
	

}
