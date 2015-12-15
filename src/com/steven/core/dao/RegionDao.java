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
public class RegionDao extends BaseIbatis3Dao<Region,Long>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "Region";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(Region entity) {
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
	public Page findPage(RegionQuery query) {
		return pageQuery("Region.findPage",query);
	}
	
	public Region getByRegionCode(java.lang.String v) {
		return (Region)getSqlSessionTemplate().selectOne("Region.getByRegionCode",v);
	}
	/**获取下级地区
	 * @param region 当前地区
	 * @return 下级地区列表
	 */
	public List<Region> getChilds(Region region) {
		 return (List<Region>)getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".getChilds", region, 0,1000);
	}
	/**获取顶级地区列表
	 * @return 地区列表
	 */
	public List<Region> getTopRegions() {
		return (List<Region>)getSqlSessionTemplate().selectList(getIbatisMapperNamesapce()+".getTopRegions", null, 0,1000);
	}	
	

}
