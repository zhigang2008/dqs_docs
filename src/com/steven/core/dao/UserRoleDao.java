package com.steven.core.dao;

import org.springframework.stereotype.Repository;

import com.steven.core.model.UserRole;
import com.steven.core.vo.query.UserRoleQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class UserRoleDao extends BaseIbatis3Dao<UserRole,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "UserRole";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(UserRole entity) {
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
	public Page findPage(UserRoleQuery query) {
		return pageQuery("UserRole.findPage",query);
	}
	/**根据用户Id删除记录
	 * @param userid 用户Id
	 */
	public void removeByUserId(Integer userid) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".removeByUserId",
		    userid);
	    
	}
	/**根据角色Id删除记录
	 * @param roleid 角色Id
	 */
	public void removeByRoleId(Integer roleid) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".removeByRoleId",
		    roleid);
	    
	}
	

}
