package com.dqs.biz.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.steven.core.vo.query.ModuleQuery;
import com.steven.framework.common.beanutils.BeanUtils;
import com.steven.framework.core.page.Page;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

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


public class TeamTypeAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	/**服务管理*/
	private TeamTypeManager teamTypeManager;
	/**Model对象*/
	private TeamType teamType;
	
	/**对象ID*/
	Long id = null;
	/**批量操作对象ID*/
	private String[] items;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			teamType = new TeamType();
		} else {
			teamType = (TeamType)teamTypeManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setTeamTypeManager(TeamTypeManager manager) {
		this.teamTypeManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return teamType;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		TeamTypeQuery query = newQuery(TeamTypeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = teamTypeManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		teamTypeManager.save(teamType);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		teamTypeManager.update(this.teamType);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			teamTypeManager.removeById(id);
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    TeamTypeQuery query = newQuery(TeamTypeQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = teamTypeManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<TeamType>  list= teamTypeManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	            teamTypeManager.save(teamType);
		    getJsonResult().setSuccess(true);
		    getJsonResult().setMessage("成功添加");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}
	
	/**保存更新对象*/
	public String jsonUpdate() {
	    try{
		    teamTypeManager.update(this.teamType);
		    getJsonResult().setSuccess(true);
		    getJsonResult().setMessage("成功更新");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}
	
	/**删除对象*/
	public String jsonDelete() {
	    try{
		teamTypeManager.removeById(this.id);
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}
	

}