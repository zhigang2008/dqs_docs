package com.dqs.office.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
@Transactional
public class ReportInformationTypeManager extends BaseManager<ReportInformationType,Long>{

       /**
	 * DAO对象
	 */
	private ReportInformationTypeDao reportInformationTypeDao;
	/**设置DAO对象,spring就可以通过autowire自动设置对象属性*/
	public void setReportInformationTypeDao(ReportInformationTypeDao dao) {
		this.reportInformationTypeDao = dao;
	}
	/* (non-Javadoc)
	 * @see com.steven.framework.base.BaseManager#getEntityDao()
	 */
	public EntityDao getEntityDao() {
		return this.reportInformationTypeDao;
	}
	/**分页查询
	 * @param query 查询条件
	 * @return 封装的Page对象
	 */
	@Transactional(readOnly=true)
	public Page findPage(ReportInformationTypeQuery query) {
		return reportInformationTypeDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public ReportInformationType getByTypeCode(java.lang.String v) {
		return reportInformationTypeDao.getByTypeCode(v);
	}	
	
}
