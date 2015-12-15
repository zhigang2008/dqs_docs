package com.steven.core.dao;

import org.springframework.stereotype.Repository;

import com.steven.core.model.UserGroup;
import com.steven.core.vo.query.UserGroupQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class UserGroupDao extends BaseIbatis3Dao<UserGroup,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "UserGroup";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(UserGroup entity) {
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
	public Page findPage(UserGroupQuery query) {
		return pageQuery("UserGroup.findPage",query);
	}
	/**根据用户ID删除记录
	 * @param userid 用户ID
	 */
	public void removeByUserId(Integer userid) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".removeByUserId",
		    userid);
	    
	}
	/**根据组ID删除记录
	 * @param groupid 组ID
	 */
	public void removeByGroupId(Integer groupid) {
	    int affectCount = getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".removeByGroupId",
		    groupid);
	    
	}
	

}
