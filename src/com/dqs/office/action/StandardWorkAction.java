package com.dqs.office.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.dqs.office.model.StandardWork;
import com.dqs.office.service.StandardWorkManager;
import com.dqs.office.vo.query.StandardWorkQuery;
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


public class StandardWorkAction extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	/**服务管理*/
	private StandardWorkManager standardWorkManager;
	/**Model对象*/
	private StandardWork standardWork;
	
	/**对象ID*/
	Long id = null;
	/**批量操作对象ID*/
	private String[] items;
	/**
	 * session信息
	 */
	private Map<String, Object> session;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			standardWork = new StandardWork();
		} else {
			standardWork = (StandardWork)standardWorkManager.getById(id);
		}
		fetchBaseCode("standardWorkType");
		fetchBaseCode("documentStatus");
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setStandardWorkManager(StandardWorkManager manager) {
		this.standardWorkManager = manager;
	}	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return standardWork;
	}
	
	public void setId(Long val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		StandardWorkQuery query = newQuery(StandardWorkQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = standardWorkManager.findPage(query);
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
		standardWork.setSubmitTime(new Date());
		standardWorkManager.save(standardWork);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		//先获取以前的对象
		StandardWork oldObj=standardWorkManager.getById(this.standardWork.getId());
		//更新
		standardWorkManager.update(this.standardWork);
		//如果重新上传文件,则删除原来的文件
		if(!oldObj.getFileName().equalsIgnoreCase(standardWork.getFileName())){
		   this.deleteFile(oldObj.getFileName());
	     }
		
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long did = new Long((String)params.get("id"));
						
			StandardWork oldObj=standardWorkManager.getById(did);
			standardWorkManager.removeById(did);
			//删除关联文件
			this.deleteFile(oldObj.getFileName());
		}

		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    StandardWorkQuery query = newQuery(StandardWorkQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = standardWorkManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<StandardWork>  list= standardWorkManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
	        	standardWork.setSubmitTime(new Date());
	            standardWorkManager.save(standardWork);
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
	    	//先获取以前的对象
			StandardWork oldObj=standardWorkManager.getById(this.standardWork.getId());
			//更新
			standardWorkManager.update(this.standardWork);
			//如果重新上传文件,则删除原来的文件
			if(!oldObj.getFileName().equalsIgnoreCase(standardWork.getFileName())){
			   this.deleteFile(oldObj.getFileName());
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
	    	 //先获取以前的对象
			 StandardWork oldObj=standardWorkManager.getById(this.standardWork.getId());
		     standardWorkManager.removeById(this.id);
		     //删除文件
			 this.deleteFile(oldObj.getFileName());
				
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	//额外增加的方法,提供给提交人和查阅人等查看.
	
	//----- Begin 提交者使用 ----------

    public String list4submitter() {
	StandardWorkQuery query = newQuery(StandardWorkQuery.class,DEFAULT_SORT_COLUMNS);
	Integer userId=(Integer)this.getSession().get(Constants.LOGIN_USERID);
	if(userId!=null){
 	   query.setSubmitterId(new Long(userId));
	}else{
	    log.warn("List4submitter has no current User.");
	    //返回到登陆页面
	    return "login";
	}
	
	Page page = standardWorkManager.findPage(query);
	savePage(page, query);
	return "list4submitter";
    }

	public String create4submitter(){
		return "create4submitter";
	}
	
	public String save4submitter() {
		standardWork.setSubmitTime(new Date());
		standardWorkManager.save(standardWork);
		return "listAction4submitter";
	}

	public String edit4submitter() {
		return "edit4submitter";
	}
	
	public String update4submitter() {
		//先获取以前的对象
		StandardWork oldObj=standardWorkManager.getById(this.standardWork.getId());
		//更新
		standardWorkManager.update(this.standardWork);
		//如果重新上传文件,则删除原来的文件
		if(!oldObj.getFileName().equalsIgnoreCase(standardWork.getFileName())){
		   this.deleteFile(oldObj.getFileName());
	     }
		
		return "listAction4submitter";
	}
	
	public String delete4submitter() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			Long id = new Long((String)params.get("id"));
			StandardWork oldObj=standardWorkManager.getById(id);
			standardWorkManager.removeById(id);
			//删除关联文件
			this.deleteFile(oldObj.getFileName());
			
		}
		return "listAction4submitter";
	}
	
	//----- End 提交者使用 ----------
	
	 /**普通用户查阅
	 * @return
	 */
	public String list4view() {
		StandardWorkQuery query = newQuery(StandardWorkQuery.class,DEFAULT_SORT_COLUMNS);
		//2 已审核状态
		query.setStatus(new Long(2));	
		Page page = standardWorkManager.findPage(query);
		savePage(page, query);
		return "list4view";
	    }
	
	/**审核列表
	 * @return
	 */
	public String list4review() {
		StandardWorkQuery query = newQuery(StandardWorkQuery.class,DEFAULT_SORT_COLUMNS);
		//1 -待审核状态
		query.setStatus(new Long(1));	
		Page page = standardWorkManager.findPage(query);
		savePage(page, query);
		return "list4review";
	    }
	
	/**ajax方式审核*/
	public String review() {
	    try{
		    standardWorkManager.update(this.standardWork);
		    getJsonResult().setSuccess(true);
		    getJsonResult().setMessage("成功");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	
	/**删除废弃的文件
	 * @param filePath
	 */
	private void deleteFile(String filePath){
		String destPath = ServletActionContext.getServletContext().getRealPath(filePath);
		File deleteFile=new File(destPath);
		deleteFile.delete();
		deleteFile=null;
	}

}
