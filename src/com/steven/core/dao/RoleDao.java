package com.steven.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.steven.core.model.Permission;
import com.steven.core.model.Role;
import com.steven.core.vo.query.RoleQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class RoleDao extends BaseIbatis3Dao<Role,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "Role";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(Role entity) {
		if(entity.getRoleId() == null){ 
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
	public Page findPage(RoleQuery query) {
		return pageQuery("Role.findPage",query);
	}
	public List<Permission> getPermissionByRole(Role role) {
		return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".getPermissionByRole", role);
	}
	public List<Permission> getNotOwnPermissionByRole(Role role) {
		return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".getNotOwnPermissionByRole", role);
		
	}

}
