package com.steven.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class RegionManager extends BaseManager<Region,Long>{

       /**
	 * DAO对象
	 */
	private RegionDao regionDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.regionDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(RegionQuery query) {
		return regionDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public Region getByRegionCode(java.lang.String v) {
		return regionDao.getByRegionCode(v);
	}	
	/**获取下级地区列表
	 * @param region 当前地区
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Region> getChilds(Region region){
		return regionDao.getChilds(region);
	}
	
	/**获取顶级地区列表
	 * @return 地区列表
	 */
	@Transactional(readOnly=true)
	public List<Region> getTopRegions(){
		return regionDao.getTopRegions();
	}
}
