package com.steven.core.dao;

import org.springframework.stereotype.Repository;

import com.steven.core.model.UserType;
import com.steven.core.vo.query.UserTypeQuery;
import com.steven.framework.base.BaseIbatis3Dao;
import com.steven.framework.core.page.Page;


@Repository
public class UserTypeDao extends BaseIbatis3Dao<UserType,java.lang.Integer>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "UserType";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(UserType entity) {
		if(entity.getCode() == null){ 
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
	public Page findPage(UserTypeQuery query) {
		return pageQuery("UserType.findPage",query);
	}
	

}
