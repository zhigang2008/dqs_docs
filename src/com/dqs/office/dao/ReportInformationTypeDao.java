package com.dqs.office.dao;

import java.util.*;

import com.steven.framework.base.*;
import com.steven.framework.util.*;

import com.steven.framework.util.*;
import com.steven.framework.common.web.util.*;
import com.steven.framework.core.page.*;

import com.dqs.office.model.*;
import com.dqs.office.dao.*;
import com.dqs.office.service.*;
import com.dqs.office.vo.query.*;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


import org.springframework.stereotype.Repository;


@Repository
public class ReportInformationTypeDao extends BaseIbatis3Dao<ReportInformationType,Long>{
        /* (non-Javadoc)
	 * @see com.steven.framework.base.BaseIbatis3Dao#getIbatisMapperNamesapce()
	 */
	@Override
	public String getIbatisMapperNamesapce() {
		return "ReportInformationType";
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.EntityDao#saveOrUpdate(java.lang.Object)
	 */
	public void saveOrUpdate(ReportInformationType entity) {
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
	public Page findPage(ReportInformationTypeQuery query) {
		return pageQuery("ReportInformationType.findPage",query);
	}
	
	public ReportInformationType getByTypeCode(java.lang.String v) {
		return (ReportInformationType)getSqlSessionTemplate().selectOne("ReportInformationType.getByTypeCode",v);
	}	
	

}
