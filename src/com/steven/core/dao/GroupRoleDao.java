package com.steven.core.dao;

import org.springframework.stereotype.Repository;

import com.steven.core.model.GroupRole;
import com.steven.core.vo.query.GroupRoleQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class GroupRoleDao extends BaseIbatis3Dao<GroupRole,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "GroupRole";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(GroupRole entity) {
		if(entity.getId() == null){ 
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
	public Page findPage(GroupRoleQuery query) {
		return pageQuery("GroupRole.findPage",query);
	}
	

}
