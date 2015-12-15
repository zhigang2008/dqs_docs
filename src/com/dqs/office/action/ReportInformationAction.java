package com.dqs.office.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.dqs.office.model.ReportInformation;
import com.dqs.office.service.ReportInformationManager;
import com.dqs.office.vo.query.ReportInformationQuery;
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


public class ReportInformationAction extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "ID DESC"; 
	
	/**服务管理*/
	private ReportInformationManager reportInformationManager;
	/**Model对象*/
	private ReportInformation reportInformation;
	
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
			reportInformation = new ReportInformation();
		} else {
			reportInformation = (ReportInformation)reportInformationManager.getById(id);
		}
		fetchBaseCode("user");
		fetchBaseCode("reportInformationType");
		fetchBaseCode("reportInformationStatus");
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setReportInformationManager(ReportInformationManager manager) {
		this.reportInformationManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return reportInformation;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		ReportInformationQuery query = newQuery(ReportInformationQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = reportInformationManager.findPage(query);
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
			reportInformation.setReporterId(new Long(userId));
		}else{
		    log.warn("Has no current User.");
		    //返回到登陆页面
		    return "login";
		}
		reportInformation.setReportTime(new Date());

		reportInformationManager.save(reportInformation);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		reportInformationManager.update(this.reportInformation);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			reportInformationManager.removeById(id);
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    ReportInformationQuery query = newQuery(ReportInformationQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = reportInformationManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<ReportInformation>  list= reportInformationManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	        	   //设置当前用户ID
	    		Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
	    		if(userId!=null){
	    			reportInformation.setReporterId(new Long(userId));
	    		}else{
	    		    log.warn("Has no current User.");
	    		    //返回到登陆页面
	    		    return "login";
	    		}
	    		reportInformation.setReportTime(new Date());
	            reportInformationManager.save(reportInformation);
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
		    reportInformationManager.update(this.reportInformation);
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
		reportInformationManager.removeById(this.id);
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
		
	}

	public Map<String, Object> getSession(){
		return this.session;
	}
	
	//额外增加的方法,提供给提交人和查阅人等查看.
	
	//----- Begin 提交者使用 ----------

    public String list4submitter() {
	ReportInformationQuery query = newQuery(ReportInformationQuery.class,DEFAULT_SORT_COLUMNS);
	Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
	if(userId!=null){
 	   query.setReporterId(new Long(userId));
	}else{
	    log.warn("List4submitter has no current User.");
	    //返回到登陆页面
	    return "login";
	}
	
	Page page = reportInformationManager.findPage(query);
	savePage(page, query);
	return "list4submitter";
    }

	public String create4submitter(){
		return "create4submitter";
	}
	
	public String save4submitter() {
		//设置当前用户ID
 		Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
 		if(userId!=null){
 			reportInformation.setReporterId(new Long(userId));
 		}else{
 		    log.warn("Has no current User.");
 		    //返回到登陆页面
 		    return "login";
 		}
		reportInformation.setReportTime(new Date());
		//reportInformation.setIsPublic(false);
		reportInformationManager.save(reportInformation);
		
		return "listAction4submitter";
	}

	public String edit4submitter() {
		return "edit4submitter";
	}
	
	public String update4submitter() {
		reportInformationManager.update(this.reportInformation);
		return "listAction4submitter";
	}
	public String delete4submitter() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			//获取原对象
	    	 ReportInformation oldObj=reportInformationManager.getById(id);
		     reportInformationManager.removeById(id);
		     
		}
		return "listAction4submitter";
	}
	
	//----- End 提交者使用 ----------
	
	 /**普通用户查阅
	 * @return
	 */
	public String list4view() {
		ReportInformationQuery query = newQuery(ReportInformationQuery.class,DEFAULT_SORT_COLUMNS);
		//公开状态的
		query.setIsPublic(true);	
		Page page = reportInformationManager.findPage(query);
		savePage(page, query);
		return "list4view";
	    }
	
	/**审核列表
	 * @return
	 */
	public String list4review() {
		ReportInformationQuery query = newQuery(ReportInformationQuery.class,DEFAULT_SORT_COLUMNS);
		//1 -待审核状态
		query.setStatus(new Long(1));	
		Page page = reportInformationManager.findPage(query);
		savePage(page, query);
		return "list4review";
	    }
	
	public String review(){
	    return "review";
	}
//	/**ajax方式审核*/
//	public String review() {
//	    try{
//		    reportInformationManager.update(this.reportInformation);
//		    getJsonResult().setSuccess(true);
//		    getJsonResult().setMessage("成功");
//	        }catch(Exception e){
//	            getJsonResult().setSuccess(false);
//		    getJsonResult().setMessage(e.getMessage());
//	        }
//		return JSONRESULT;
//	}
	

}
