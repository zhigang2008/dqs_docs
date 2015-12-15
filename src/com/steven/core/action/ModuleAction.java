package com.steven.core.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.web.bind.ServletRequestUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.core.model.Module;
import com.steven.core.service.ModuleManager;
import com.steven.core.service.RoleModuleManager;
import com.steven.core.vo.query.ModuleQuery;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.tree.TreeFactory;
import com.steven.framework.common.tree.TreeNode;
import com.steven.framework.common.web.util.HttpUtils;
import com.steven.framework.core.page.Page;

/**模块管理Action
 * @author steven
 * @version 1.0
 * @since 1.0
 */


public class ModuleAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	/**
	 * 模块管理服务
	 */
	private ModuleManager moduleManager;
	/**
	 * 角色和模块映射服务管理
	 */
	private RoleModuleManager roleModuleManager;
	
	private Module module;
	java.lang.Integer id = null;
	private String[] items;
	
	

	
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			module = new Module();
		} else {
			module = (Module)moduleManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setModuleManager(ModuleManager manager) {
		this.moduleManager = manager;
	}	
	
	public Object getModel() {
		return module;
	}
	
	public void setModuleId(java.lang.Integer val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	
	/** 执行搜索 */
	public String list() {
		ModuleQuery query = newQuery(ModuleQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = moduleManager.findPage(query);
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
		moduleManager.save(module);
		//Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		moduleManager.update(this.module);
		//Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.Integer id = new java.lang.Integer((String)params.get("moduleId"));
			moduleManager.removeById(id);
		}
		//Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/* --------------- JSON 方法 ------------ */
	/** ajax方式  分页返回结果 */
	public String jsonPageList() {
	    ModuleQuery query = newQuery(ModuleQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = moduleManager.findPage(query);
		saveJsonResult(page,query);
		return JSONPAGELIST;
	}
	
	/** ajax方式 返回结果,不带分页信息*/
	public String jsonList() {
		ModuleQuery query = newQuery(ModuleQuery.class,DEFAULT_SORT_COLUMNS);
		Page page = moduleManager.findPage(query);
		saveJsonResult(page,query);
		return JSONLIST;
	}
	
	/** ajax方式  返回树形结构数据 */
	public String treeList() {
		//查询结果
		List<Module> allModules=null;
		
		//
		String queryMode=ServletRequestUtils.getStringParameter(getRequest(), "mode","singleLevel");
		
		if("all".equalsIgnoreCase(queryMode)){
			//查找所有数据
			  allModules=moduleManager.findAll();
		}
		else{
			//根据查询条件查询
			ModuleQuery query = newQuery(ModuleQuery.class,DEFAULT_SORT_COLUMNS);
			Page page = moduleManager.findPage(query);
			allModules=page.getResult();
		}
		
		boolean includeRoot=ServletRequestUtils.getBooleanParameter(getRequest(), "includeRoot", false);
		boolean lazyLoad=ServletRequestUtils.getBooleanParameter(getRequest(), "lazyLoad", false);
		//构造树形结构并存入request
		makeTree(allModules,includeRoot,lazyLoad);
		
		return JSONLIST;
	}

	
	/**将列表数据构造为树形结构
	 * @param allModules
	 * 
	 * 	 */
	private void makeTree(List<Module> allModules,boolean includeRoot,boolean lazyLoad) {
		
		TreeFactory factory=new TreeFactory();
		
		//根节点
		TreeNode rootNode=new TreeNode();
		rootNode.setKey("0");
		rootNode.setTitle("所有模块");
		rootNode.setTooltip("根节点");
		rootNode.setExpand(true);
		rootNode.setFolder(true);
		rootNode.setUnselectable(true);
		rootNode.setHideCheckbox(true);
		rootNode.setLazy(true);
		factory.setRootTreeNode(rootNode);
		
		//所有实际节点
		for(Module m:allModules){
			TreeNode newTreeNode=new TreeNode();
			newTreeNode.setKey(String.valueOf(m.getModuleId()));
			newTreeNode.setTitle(m.getModuleName());
			newTreeNode.setTooltip(m.getDescription());
			newTreeNode.setUrl(m.getUrl());
			
			if(lazyLoad){
			newTreeNode.setLazy(true);
			}

			newTreeNode.setParentId(String.valueOf(m.getParent()));
			newTreeNode.setSortOrder(m.getSortOrder());
			newTreeNode.addExtraData("status", m.getStatus());
			newTreeNode.addExtraData("sortOrder", m.getSortOrder());
			
			factory.addTreeNode(newTreeNode);
		}
		if(includeRoot){
		   List nodeList=new ArrayList();
		   nodeList.add(factory.getRootTreeNode());
		   saveJsonResult(nodeList);
		}else{
			saveJsonResult(factory.getRootTreeNode().getChildren());
		}
	}
	

	/** ajax保存新增对象 */
	public String jsonSave() {
	        try{
		    moduleManager.save(module);
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
		    moduleManager.update(this.module);
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
	    	//先查询该模块有无子节点
	    	ModuleQuery query=new ModuleQuery();
	    	query.setParent(this.module.getModuleId());
	    	Page page=moduleManager.findPage(query);
	    	if(page.getResult().size()>0){
	    		getJsonResult().setSuccess(false);
	 		    getJsonResult().setMessage("存在子模块,不能删除!");
	    	}else{
	    		moduleManager.removeById(this.module.getModuleId());
	    		//删除角色模块对应信息
	    		roleModuleManager.removeByModuleId(this.module.getModuleId());
	    		
			    getJsonResult().setSuccess(true);
			    getJsonResult().setMessage("成功删除");
	    	}
		    
	        }catch(Exception e){
	            getJsonResult().setSuccess(false);
		        getJsonResult().setMessage(e.getMessage());
	        }
		return JSONRESULT;
	}

	/**
	 * @return the roleModuleManager
	 */
	public RoleModuleManager getRoleModuleManager() {
		return roleModuleManager;
	}

	/**
	 * @param roleModuleManager the roleModuleManager to set
	 */
	public void setRoleModuleManager(RoleModuleManager roleModuleManager) {
		this.roleModuleManager = roleModuleManager;
	}

	
}
