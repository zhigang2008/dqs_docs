/**
 * GlobalCodeTable.java
 * Steven
 * 2011-5-6
 */
package com.steven.framework.common.codetable;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.steven.framework.base.BaseManager;

/**
 * @author Steven
 *
 */
public class GlobalCodeTable extends WebApplicationObjectSupport {
	private Log log = LogFactory.getLog(getClass());
	
	public List getBaseCode(String codeTable){
		List list=new ArrayList();
		
		BaseManager manager=(BaseManager)(getWebApplicationContext().getBean(codeTable+"Manager"));
		if(manager==null){
			log.error("没有找到基表数据管理类:["+codeTable+"Manager]");
		}
		else{
		  list=manager.getBaseCode();
		}
		
		if(list==null){
			log.info("基表["+codeTable+"]没有数据");
			list=new ArrayList();
		}
		return list;
		
	}
	//TODO 有待实现
	public List getTreeCode(String codeTable){
		List list=new ArrayList();
		return list;
		
		
	}

}
