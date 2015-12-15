package com.dqs.office.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.dqs.office.model.PublishInformation;
import com.dqs.office.service.PublishInformationManager;
import com.dqs.office.vo.query.PublishInformationQuery;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.web.util.HttpUtils;
import com.steven.framework.core.page.Page;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class PublishInformationAction extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "ID DESC"; 
	
	/**服务管理*/
	private PublishInformationManager publishInformationManager;
	/**Model对象*/
	private PublishInformation publishInformation;
	
	/**
	 * session信息
	 */
	private Map<String, Object> session;
	
	/**对象ID*/
	Long id = null;
	/**批量操作对象ID*/
	private String[] items;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			publishInformation = new PublishInformation();
		} else {
			publishInformation = (PublishInformation)publishInformationManager.getById(id);
		}
		fetchBaseCode("publishInformationType");

	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setPublishInformationManager(PublishInformationManager manager) {
		this.publishInformationManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return publishInformation;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		PublishInformationQuery query = newQuery(PublishInformationQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = publishInformationManager.findPage(query);
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
	        //设置当前用户ID
		Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
		if(userId!=null){
		    publishInformation.setPublisherId(new Long(userId));
		}else{
		    log.warn("List4submitter has no current User.");
		    //返回到登陆页面
		    return "login";
		}
		
	        publishInformation.setPublishTime(new Date());
	        publishInformation.setViewHits(new Long(0));
		publishInformationManager.save(publishInformation);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		publishInformationManager.update(this.publishInformation);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			publishInformationManager.removeById(id);
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    PublishInformationQuery query = newQuery(PublishInformationQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = publishInformationManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<PublishInformation>  list= publishInformationManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	            //设置当前用户ID
			Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
			if(userId!=null){
			    publishInformation.setPublisherId(new Long(userId));
			}else{
			    log.warn("List4submitter has no current User.");
			    //返回到登陆页面
			    return "login";
			}
	            publishInformation.setPublishTime(new Date());
	            publishInformation.setViewHits(new Long(0));
	            publishInformationManager.save(publishInformation);
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
		    publishInformationManager.update(this.publishInformation);
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
		publishInformationManager.removeById(this.id);
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	@Override
	public void setSession(Map<String, Object> session) {
	    this.session=session;
	    
	}
	/**获取Session
	 * @return
	 */
	public Map<String, Object> getSession(){
	    return this.session;
	}

	
	/**给前台展示*/
	public String showNews() {
		this.publishInformation.setViewHits(this.publishInformation.getViewHits()+1);
		this.publishInformationManager.update(publishInformation);
		return "showNews";
	}
	

}
