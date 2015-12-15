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


public class RescueTeamAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	/**服务管理*/
	private RescueTeamManager rescueTeamManager;
	/**
	 * 救援队能力管理服务
	 */
	private TeamAbilityManager teamAbilityManager;
	/**Model对象*/
	private RescueTeam rescueTeam;
	
	/**对象ID*/
	Long id = null;
	/**批量操作对象ID*/
	private String[] items;
	
	/**
	 * 救援能力
	 */
	private List<Long> abilityIds;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			rescueTeam = new RescueTeam();
		} else {
			rescueTeam = (RescueTeam)rescueTeamManager.getById(id);
		}
		
		fetchBaseCode("teamType");
		fetchBaseCode("rescueAbility");
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRescueTeamManager(RescueTeamManager manager) {
		this.rescueTeamManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return rescueTeam;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		RescueTeamQuery query = newQuery(RescueTeamQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = rescueTeamManager.findPage(query);
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
		
		rescueTeam.setFcd(new Date());
		rescueTeam.setLcd(new Date());
		rescueTeamManager.save(rescueTeam);
		
		//添加救援队能力
		if(this.abilityIds!=null){
			for(Long ab:abilityIds){
				TeamAbility teamAbility=new TeamAbility();
				teamAbility.setAbilityId(ab);
				teamAbility.setTeamId(this.rescueTeam.getId());
				this.teamAbilityManager.save(teamAbility);
			}
		}
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		//更新救援能力显示
		
		if(this.abilityIds!=null){
			this.abilityIds.clear();
		}else{
			this.abilityIds=new ArrayList<Long>();
		}
		for(TeamAbility existTb:this.rescueTeam.getTeamAbilitys()){
			this.abilityIds.add(existTb.getAbilityId());
		}
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {

		rescueTeam.setLcd(new Date());
		rescueTeamManager.update(this.rescueTeam);
		
		//先删除救援能力
		for(TeamAbility existTb:this.rescueTeam.getTeamAbilitys()){
			this.teamAbilityManager.removeById(existTb.getId());
		}
		//添加救援队能力
		if(this.abilityIds!=null){
			for(Long ab:abilityIds){
				TeamAbility teamAbility=new TeamAbility();
				teamAbility.setAbilityId(ab);
				teamAbility.setTeamId(this.rescueTeam.getId());
				this.teamAbilityManager.save(teamAbility);
			}
		}
		
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			rescueTeamManager.removeById(id);
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    RescueTeamQuery query = newQuery(RescueTeamQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = rescueTeamManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<RescueTeam>  list= rescueTeamManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	        	rescueTeam.setFcd(new Date());
	    		rescueTeam.setLcd(new Date());
	            rescueTeamManager.save(rescueTeam);
	    		//添加救援队能力
	    		if(this.abilityIds!=null){
	    			for(Long ab:abilityIds){
	    				TeamAbility teamAbility=new TeamAbility();
	    				teamAbility.setAbilityId(ab);
	    				teamAbility.setTeamId(this.rescueTeam.getId());
	    				this.teamAbilityManager.save(teamAbility);
	    			}
	    		}
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
	    	
			rescueTeam.setLcd(new Date());
		    rescueTeamManager.update(this.rescueTeam);
		  //先删除救援能力
			for(TeamAbility existTb:this.rescueTeam.getTeamAbilitys()){
				this.teamAbilityManager.removeById(existTb.getId());
			}
			//添加救援队能力
			if(this.abilityIds!=null){
				for(Long ab:abilityIds){
					TeamAbility teamAbility=new TeamAbility();
					teamAbility.setAbilityId(ab);
					teamAbility.setTeamId(this.rescueTeam.getId());
					this.teamAbilityManager.save(teamAbility);
				}
			}
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
		rescueTeamManager.removeById(this.id);
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	/**
	 * @return the abilityIds
	 */
	public List getAbilityIds() {
		return abilityIds;
	}

	/**
	 * @param abilityIds the abilityIds to set
	 */
	public void setAbilityIds(List abilityIds) {
		this.abilityIds = abilityIds;
	}

	/**
	 * @param teamAbilityManager the teamAbilityManager to set
	 */
	public void setTeamAbilityManager(TeamAbilityManager teamAbilityManager) {
		this.teamAbilityManager = teamAbilityManager;
	}
	

}
