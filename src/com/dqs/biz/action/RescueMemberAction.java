package com.dqs.biz.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.dqs.biz.model.MemberGroup;
import com.dqs.biz.model.RescueMember;
import com.dqs.biz.model.RescueMemberDetailinfo;
import com.dqs.biz.model.TeamAbility;
import com.dqs.biz.service.MemberGroupManager;
import com.dqs.biz.service.RescueMemberDetailinfoManager;
import com.dqs.biz.service.RescueMemberManager;
import com.dqs.biz.vo.query.RescueMemberQuery;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.web.util.HttpUtils;
import com.steven.framework.core.page.Page;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class RescueMemberAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	/**服务管理*/
	private RescueMemberManager rescueMemberManager;
	private RescueMemberDetailinfoManager rescueMemberDetailinfoManager;
	private MemberGroupManager memberGroupManager;
	/**Model对象*/
	private RescueMember rescueMember;
	
	/**对象ID*/
	Long id = null;
	/**批量操作对象ID*/
	private String[] items;
	
	/**
	 * 救援队员能够参与的救援组
	 */
	private List<Long> memberGroupIds;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			rescueMember = new RescueMember();
			
		} else {
			rescueMember = (RescueMember)rescueMemberManager.getById(id);
		}
		//基础代码
		fetchBaseCode("rescueTeam");
		fetchBaseCode("rescueGroupType");
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRescueMemberManager(RescueMemberManager manager) {
		this.rescueMemberManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return rescueMember;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		RescueMemberQuery query = newQuery(RescueMemberQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = rescueMemberManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		rescueMember.setGender("M");
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		rescueMember.setFcd(new Date());
		rescueMember.setLcd(new Date());
		rescueMemberManager.save(rescueMember);
		//添加详细信息
		RescueMemberDetailinfo detailinfo=this.rescueMember.getRescueMemberDetailinfo();
		if(detailinfo!=null){
			detailinfo.setMemberId(this.rescueMember.getId());
			this.rescueMemberDetailinfoManager.save(detailinfo);
		}
		
		//添加救援组
		if(this.memberGroupIds!=null){
			for(Long groupId:memberGroupIds){
				MemberGroup membergroup=new MemberGroup();
				membergroup.setGroupId(groupId);
				membergroup.setMemberId(this.rescueMember.getId());
				this.memberGroupManager.save(membergroup);
			}
		}
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		
		if(this.memberGroupIds!=null){
			this.memberGroupIds.clear();
		}else{
			this.memberGroupIds=new ArrayList<Long>();
		}
		for(MemberGroup existMg:this.rescueMember.getMemberGroups()){
			log.debug(existMg.getId()+"--"+existMg.getGroupId());
			this.memberGroupIds.add(existMg.getGroupId());
		}
		
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		
		rescueMember.setLcd(new Date());
		rescueMemberManager.update(this.rescueMember);
		//添加详细信息
		RescueMemberDetailinfo existDetail=this.rescueMemberDetailinfoManager.getById(this.rescueMember.getId());
		RescueMemberDetailinfo detailinfo=this.rescueMember.getRescueMemberDetailinfo();
		
		//添加
		if(existDetail==null){
			if(detailinfo!=null){
				detailinfo.setMemberId(this.rescueMember.getId());
				this.rescueMemberDetailinfoManager.save(detailinfo);
			}
		}
		//更新
		else{
			if(detailinfo!=null){
				detailinfo.setMemberId(this.rescueMember.getId());
				this.rescueMemberDetailinfoManager.update(detailinfo);
			}
		}
		
		
		//删除组信息
		this.memberGroupManager.removeByMemberId(this.rescueMember.getId());
		//添加救援组
		if(this.memberGroupIds!=null){
			for(Long groupId:memberGroupIds){
				MemberGroup membergroup=new MemberGroup();
				membergroup.setGroupId(groupId);
				membergroup.setMemberId(this.rescueMember.getId());
				this.memberGroupManager.save(membergroup);
			}
		}
		
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			//先删除详细信息
			this.rescueMemberDetailinfoManager.removeById(id);
			rescueMemberManager.removeById(id);
			
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    RescueMemberQuery query = newQuery(RescueMemberQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = rescueMemberManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<RescueMember>  list= rescueMemberManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	        	rescueMember.setFcd(new Date());
	    		rescueMember.setLcd(new Date());
	            rescueMemberManager.save(rescueMember);
	          //添加详细信息
	    		RescueMemberDetailinfo detailinfo=this.rescueMember.getRescueMemberDetailinfo();
	    		if(detailinfo!=null){
	    			detailinfo.setMemberId(this.rescueMember.getId());
	    			this.rescueMemberDetailinfoManager.save(detailinfo);
	    		}
	    		//添加救援组
	    		if(this.memberGroupIds!=null){
	    			for(Long groupId:memberGroupIds){
	    				MemberGroup membergroup=new MemberGroup();
	    				membergroup.setGroupId(groupId);
	    				membergroup.setMemberId(this.rescueMember.getId());
	    				this.memberGroupManager.save(membergroup);
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

			rescueMember.setLcd(new Date());
		    rescueMemberManager.update(this.rescueMember);
		  //添加详细信息
			RescueMemberDetailinfo existDetail=this.rescueMemberDetailinfoManager.getById(this.rescueMember.getId());
			RescueMemberDetailinfo detailinfo=this.rescueMember.getRescueMemberDetailinfo();
			
			//添加
			if(existDetail==null){
				if(detailinfo!=null){
					detailinfo.setMemberId(this.rescueMember.getId());
					this.rescueMemberDetailinfoManager.save(detailinfo);
				}
			}
			//更新
			else{
				if(detailinfo!=null){
					detailinfo.setMemberId(this.rescueMember.getId());
					this.rescueMemberDetailinfoManager.update(detailinfo);
				}
			}
			
			//删除组信息
			this.memberGroupManager.removeByMemberId(this.rescueMember.getId());
			//更新组信息
			//添加救援组
			if(this.memberGroupIds!=null){
				for(Long groupId:memberGroupIds){
					MemberGroup membergroup=new MemberGroup();
					membergroup.setGroupId(groupId);
					membergroup.setMemberId(this.rescueMember.getId());
					this.memberGroupManager.save(membergroup);
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
	    	//先删除详细信息
	    	this.rescueMemberDetailinfoManager.removeById(this.id);
		    rescueMemberManager.removeById(this.id);
	         
		    getJsonResult().setSuccess(true);
		     getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	/**
	 * @param rescueMemberDetailinfoManager the rescueMemberDetailinfoManager to set
	 */
	public void setRescueMemberDetailinfoManager(
			RescueMemberDetailinfoManager rescueMemberDetailinfoManager) {
		this.rescueMemberDetailinfoManager = rescueMemberDetailinfoManager;
	}

	/**
	 * @return the memberGroupIds
	 */
	public List<Long> getMemberGroupIds() {
		return memberGroupIds;
	}

	/**
	 * @param memberGroupIds the memberGroupIds to set
	 */
	public void setMemberGroupIds(List<Long> memberGroupIds) {
		this.memberGroupIds = memberGroupIds;
	}

	/**
	 * @return the memberGroupManager
	 */
	public MemberGroupManager getMemberGroupManager() {
		return memberGroupManager;
	}

	/**
	 * @param memberGroupManager the memberGroupManager to set
	 */
	public void setMemberGroupManager(MemberGroupManager memberGroupManager) {
		this.memberGroupManager = memberGroupManager;
	}
	

}
