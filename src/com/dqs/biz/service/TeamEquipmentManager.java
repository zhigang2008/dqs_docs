package com.dqs.biz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.steven.framework.base.*;
import com.steven.framework.util.*;

import com.steven.framework.util.*;
import com.steven.framework.common.web.util.*;
import com.steven.framework.core.page.*;

import com.dqs.biz.model.*;
import com.dqs.biz.dao.*;
import com.dqs.biz.service.*;
import com.dqs.biz.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class TeamEquipmentManager extends BaseManager<TeamEquipment,Long>{

       /**
	 * DAO对象
	 */
	private TeamEquipmentDao teamEquipmentDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setTeamEquipmentDao(TeamEquipmentDao dao) {
		this.teamEquipmentDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.teamEquipmentDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(TeamEquipmentQuery query) {
		return teamEquipmentDao.findPage(query);
	}
	
}
