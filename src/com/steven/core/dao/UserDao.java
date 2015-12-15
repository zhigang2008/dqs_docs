package com.steven.core.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends BaseIbatis3Dao<User,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "User";
	}
	
	public void saveOrUpdate(User entity) {
		if(entity.getUserid() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("User.findPage",query);
	}
	
	public User getByUserName(java.lang.String v) {
		return (User)getSqlSessionTemplate().selectOne("User.getByUserName",v);
	}	
	public List getUserModules(User entity){
	    return getSqlSessionTemplate().selectList("User.getUserModules", entity);
	    
	}

	public List getUserRoles(User user) {
	    return getSqlSessionTemplate().selectList("User.getUserRoles", user);
	}

	public List getUserGroups(User user) {
	    return getSqlSessionTemplate().selectList("User.getUserGroups", user);
	}

	public List getUserPermissions(User user) {
	    return getSqlSessionTemplate().selectList("User.getUserPermissions", user);
	}

}
