package com.steven.core.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.core.GlobalSetting;
import com.steven.core.model.User;
import com.steven.core.model.UserGroup;
import com.steven.core.model.UserRole;
import com.steven.core.service.UserGroupManager;
import com.steven.core.service.UserManager;
import com.steven.core.service.UserRoleManager;
import com.steven.core.vo.query.UserQuery;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.security.encoder.PwdEncoder;
import com.steven.framework.common.web.util.HttpUtils;
import com.steven.framework.core.page.Page;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class UserAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = "userid asc"; 
	
	/**
	 * 用户管理服务
	 */
	private UserManager userManager;
	/**
	 * 用户角色映射管理服务
	 */
	private UserRoleManager userRoleManager;
	
	/**
	 * 用户-用户映射管理服务
	 */
	private UserGroupManager userGroupManager;

	/**
	 * 全局常量
	 */
	private GlobalSetting globalSetting=null;
	/**
	 * 密码加密器
	 */
	private PwdEncoder pwdEncoder;
	
	/**
	 * 用户实体
	 */
	private User user;
	/**
	 * 主键
	 */
	java.lang.Integer id = null;
	/**
	 * 批量处理对象组
	 */
	private String[] items;
	/**
	 * 角色信息
	 */
	private List<Integer> roleIds;
	/**
	 * 用户组信息
	 */
	private List<Integer> groupIds;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			user = new User();
		} else {
			user = (User)userManager.getById(id);
		}
		//取用户类型的基础代码
		fetchBaseCode("userType");
		fetchBaseCode("role");
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserManager(UserManager manager) {
		this.userManager = manager;
	}	
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return user;
	}
	
	/**设置主键
	 * @param val
	 */
	public void setUserid(java.lang.Integer val) {
		this.id = val;
	}

	/**设置批量处理对象组
	 * @param items
	 */
	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		UserQuery query = newQuery(UserQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = userManager.findPage(query);
		savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
	       
		return SHOW_JSP;
	}
	
	/** 进入新增页面	 */
	public String create() {
		String defaultUserType=this.globalSetting.getParam(Constants.DEFAULT_USER_TYPE);
		if(defaultUserType==null || "".equals(defaultUserType.trim())){
			defaultUserType="3";
		}
		user.setUserTypeId(new Integer(defaultUserType));
	        user.setIsDisabled(false);
		
		return CREATE_JSP;
	}
	
    /** 保存新增对象 */
    public String save() {
	if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
	    String defaultPwd = this.globalSetting
		    .getParam(Constants.USER_DEFAULT_PASSWORD);

	    if (defaultPwd == null && "".equals(defaultPwd)) {
		defaultPwd = "123456";
	    }
	    defaultPwd = this.pwdEncoder.encodePassword(defaultPwd);
	    user.setPassword(defaultPwd);
	}
	userManager.save(user);
	
	//添加角色信息
	if(roleIds!=null){
	    for(Integer r:roleIds){
		UserRole userRole=new UserRole();
		userRole.setRoleId(new Integer(r));
		userRole.setUserid(user.getUserid());
		userRoleManager.save(userRole);
		
	    }
	}
	//添加用户组
	if(groupIds!=null){
	    for(Integer g:groupIds){
		UserGroup userGroup=new UserGroup();
		userGroup.setUserid(user.getUserid());
		userGroup.setGroupId(g);
		userGroupManager.save(userGroup);
	    }
	}
	return LIST_ACTION;
    }
	
	/**进入更新页面*/
	public String edit() {
	     //用户角色   
	     if(roleIds!=null){
	            roleIds.clear();
	        }else{
	            roleIds=new ArrayList<Integer>();
	        }
	        for(UserRole userRoles:this.user.getUserRoles()){
	            roleIds.add(userRoles.getRoleId());
	        }
	       //用户组
	        if(groupIds!=null){
	            groupIds.clear();
	        }else{
	            groupIds=new ArrayList<Integer>();
	        }
	        for(UserGroup userGroups:this.user.getUserGroups()){
	            groupIds.add(userGroups.getGroupId());
	        }
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		userManager.update(this.user);

		//删除用户角色信息
		userRoleManager.removeByUserId(user.getUserid());
		//删除用户组信息
		userGroupManager.removeByUserId(user.getUserid());
		
		//添加角色信息
		if(roleIds!=null){
		    for(Integer r:roleIds){
			UserRole userRole=new UserRole();
			userRole.setRoleId(new Integer(r));
			userRole.setUserid(user.getUserid());
			userRoleManager.save(userRole);
			
		    }
		}
		//添加用户组
		if(groupIds!=null){
		    for(Integer g:groupIds){
			UserGroup userGroup=new UserGroup();
			userGroup.setUserid(user.getUserid());
			userGroup.setGroupId(new Integer(g));
			userGroupManager.save(userGroup);
		    }
		}
		
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("userid"));
			userManager.removeById(id);
		}
		//Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/* ------------JSON 方法------------- */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    UserQuery query = newQuery(UserQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = userManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回所有结果,不带分页信息*/
	public String jsonList() {
	    List<User>  list= userManager.findAll();
		saveJsonResult(list);
		return JSONLIST;
	}
	
    /** ajax保存新增对象 */
    public String jsonSave() {
	try {
	    if (user.getPassword() == null
		    || "".equals(user.getPassword().trim())) {
		String defaultPwd = this.globalSetting
			.getParam(Constants.USER_DEFAULT_PASSWORD);

		if (defaultPwd == null && "".equals(defaultPwd)) {
		    defaultPwd = "123456";
		}
		defaultPwd = this.pwdEncoder.encodePassword(defaultPwd);
		user.setPassword(defaultPwd);
	    }

	    userManager.save(user);
	    getJsonResult().setSuccess(true);
	    getJsonResult().setMessage("成功添加");
	} catch (Exception e) {
	    getJsonResult().setSuccess(false);
	    getJsonResult().setMessage(e.getMessage());
	}
	return JSONRESULT;
    }

	/**保存更新对象*/
	public String jsonUpdate() {
	    try{
		    userManager.update(this.user);
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
		userManager.removeById(this.id);
	         getJsonResult().setSuccess(true);
		 getJsonResult().setMessage("成功删除");
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		    getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	/**
	 * @param globalSetting the globalSetting to set
	 */
	public void setGlobalSetting(GlobalSetting globalSetting) {
	    this.globalSetting = globalSetting;
	}

	/**
	 * @param pwdEncoder the pwdEncoder to set
	 */
	public void setPwdEncoder(PwdEncoder pwdEncoder) {
	    this.pwdEncoder = pwdEncoder;
	}

	/**
	 * @return the roleIds
	 */
	public List getRoleIds() {
	    return roleIds;
	}

	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(List roleIds) {
	    this.roleIds = roleIds;
	}

	/**
	 * @param userRoleManager the userRoleManager to set
	 */
	public void setUserRoleManager(UserRoleManager userRoleManager) {
	    this.userRoleManager = userRoleManager;
	}

	/**
	 * @param userGroupManager the userGroupManager to set
	 */
	public void setUserGroupManager(UserGroupManager userGroupManager) {
	    this.userGroupManager = userGroupManager;
	}

	/**
	 * @return the groupIds
	 */
	public List getGroupIds() {
	    return groupIds;
	}

	/**
	 * @param groupIds the groupIds to set
	 */
	public void setGroupIds(List groupIds) {
	    this.groupIds = groupIds;
	}


	/**判断用户名是否已经存在
	 * @return 判断结果
	 */
	public String checkExistUser(){
	    User resultObj=this.userManager.getByUserName(this.user.getUserName());
	    super.checkResult(resultObj==null?true:false);
	    return CHECKRESULT;
	}
	
}
