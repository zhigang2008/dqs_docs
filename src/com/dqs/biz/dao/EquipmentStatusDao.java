package com.dqs.biz.dao;

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


import org.springframework.stereotype.Repository;


@Repository
public class EquipmentStatusDao extends BaseIbatis3Dao<EquipmentStatus,Long>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "EquipmentStatus";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(EquipmentStatus entity) {
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
	public Page findPage(EquipmentStatusQuery query) {
		return pageQuery("EquipmentStatus.findPage",query);
	}
	

}