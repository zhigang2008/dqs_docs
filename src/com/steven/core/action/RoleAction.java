package com.steven.core.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.core.model.Permission;
import com.steven.core.model.Role;
import com.steven.core.model.RoleModule;
import com.steven.core.model.RolePermission;
import com.steven.core.service.PermissionManager;
import com.steven.core.service.RoleManager;
import com.steven.core.service.RoleModuleManager;
import com.steven.core.service.RolePermissionManager;
import com.steven.core.vo.query.RoleQuery;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.web.util.HttpUtils;
import com.steven.framework.core.page.Page;

/**
 * @author steven
 * @version 1.0
 * @since 1.0
 */

public class RoleAction extends BaseStruts2Action implements Preparable,ModelDriven {
    // 默认多列排序,example: username desc,createTime asc
    protected static final String DEFAULT_SORT_COLUMNS = "role_id asc";

    /**
     * 角色管理服务
     */
    private RoleManager roleManager;
    private PermissionManager permissionManager;
    /**
     * 角色模块映射管理服务
     */
    private RoleModuleManager roleModuleManager;
    /**
     * 角色权限映射管理服务
     */
    private RolePermissionManager rolePermissionManager;

    /**
     * 角色对象
     */
    private Role role;
    /**
     * 对象ID
     */
    java.lang.Integer id = null;
    /**
     * 批量对象
     */
    private String[] items;
    /**
     * 角色拥有的模块信息
     */
    private String[] modules;

    /**
     * 角色拥有的所有权限信息
     */
    private String[] permissions;
    
    private List<Permission> existPermissions;
    private List<Permission> notExistPermissions;
    
     /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.Preparable#prepare()
     */
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			role = new Role();

		} else {
			role = (Role) roleManager.getById(id);
		}
		
		fetchBaseCode("permission");
	}

    /** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
    public void setRoleManager(RoleManager manager) {
	this.roleManager = manager;
    }

    /**
     * @param roleModuleManager
     *            the roleModuleManager to set
     */
    public void setRoleModuleManager(RoleModuleManager roleModuleManager) {
	this.roleModuleManager = roleModuleManager;
    }

    /**
     * @param rolePermissionManager
     *            the rolePermissionManager to set
     */
    public void setRolePermissionManager(
	    RolePermissionManager rolePermissionManager) {
	this.rolePermissionManager = rolePermissionManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ModelDriven#getModel()
     */
    public Object getModel() {
	return role;
    }

    /**
     * @param val
     */
    public void setRoleId(java.lang.Integer val) {
	this.id = val;
    }

    /**
     * @param items
     */
    public void setItems(String[] items) {
	this.items = items;
    }

    /**
     * 获取角色拥有的模块
     * 
     * @return the modules
     */
    public String[] getModules() {
	return modules;
    }

    /**
     * @param modules
     *            the modules to set
     */
    public void setModules(String[] modules) {
	this.modules = modules;
    }
    
    /**
     * @param permissions
     *            the permissions to set
     */
    public void setPermissions(String[] permissions) {
	this.permissions = permissions;
    }

    /** 执行搜索 */
    public String list() {
	RoleQuery query = newQuery(RoleQuery.class, DEFAULT_SORT_COLUMNS);
	Page page = roleManager.findPage(query);
	savePage(page, query);
	return LIST_JSP;
    }

    /** 查看对象 */
    public String show() {
	return SHOW_JSP;
	
    }

    /** 进入新增页面 */
    public String create() {
	this.role.setStatus(true);
	this.role.setPriority(1);
	//初始化权限数据
	this.setExistPermissions(new ArrayList());
	this.setNotExistPermissions(permissionManager.findAll());
	return CREATE_JSP;
    }

    /** 保存新增对象 */
    public String save() {
	roleManager.save(role);

	// 插入模块信息
	if (modules != null) {
	    for (String m : modules) {
		RoleModule roleModule = new RoleModule();
		roleModule.setRoleId(this.role.getRoleId());
		roleModule.setModuleCode(new Integer(m));
		this.roleModuleManager.save(roleModule);
	    }
	}
	// 插入权限信息
	if (permissions != null) {
	    for (String p : permissions) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(this.role.getRoleId());
		rolePermission.setPermissionId(new Long(p));
		this.rolePermissionManager.save(rolePermission);
	    }
	}
	return LIST_ACTION;
    }

    /** 进入更新页面 */
    public String edit() {
    	//查询已存在的权限
    	this.setExistPermissions(roleManager.getPermissionByRole(this.role));
    	//查询为拥有的权限
    	this.setNotExistPermissions(roleManager.getNotOwnPermissionByRole(this.role));
    	
	    return EDIT_JSP;
    }

    /** 保存更新对象 */
    public String update() {
	roleManager.update(this.role);
	// 删除以前的数据
	this.roleModuleManager.removeByRoleId(role.getRoleId());
	this.rolePermissionManager.removeByRoleId(role.getRoleId());

	// 插入模块信息
	if(this.modules!=null){
	for (String m : modules) {
	    RoleModule roleModule = new RoleModule();
	    roleModule.setRoleId(this.role.getRoleId());
	    roleModule.setModuleCode(new Integer(m));
	    this.roleModuleManager.save(roleModule);
	}
	}
	// 插入权限信息
	if(this.permissions!=null){
	for (String p : permissions) {
	    RolePermission rolePermission = new RolePermission();
	    rolePermission.setRoleId(this.role.getRoleId());
	    rolePermission.setPermissionId(new Long(p));
	    this.rolePermissionManager.save(rolePermission);
	}
	}

	return LIST_ACTION;
    }

    /** 删除对象 */
    public String delete() {
	for (int i = 0; i < items.length; i++) {
	    Hashtable params = HttpUtils.parseQueryString(items[i]);
	    java.lang.Integer deleteId = new java.lang.Integer((String) params.get("roleId"));
	    // 删除模块和权限
	    this.roleModuleManager.removeByRoleId(deleteId);
	    this.rolePermissionManager.removeByRoleId(deleteId);
	    //删除角色
	    roleManager.removeById(deleteId);
	    
	}

	return LIST_ACTION;
    }

    /* ------------JSON 方法------------- */
    /** ajax方式 分页返回结果 */
    public String jsonPageList() {
	RoleQuery query = newQuery(RoleQuery.class, DEFAULT_SORT_COLUMNS);
	Page page = roleManager.findPage(query);
	saveJsonResult(page, query);
	return JSONPAGELIST;
    }

    /** ajax方式 返回所有结果,不带分页信息 */
    public String jsonList() {
	List<Role> list = roleManager.findAll();
	saveJsonResult(list);
	return JSONLIST;
    }

    /** ajax保存新增对象 */
    public String jsonSave() {
	try {
	    roleManager.save(role);
	    // 插入模块信息

	    for (String m : modules) {
		RoleModule roleModule = new RoleModule();
		roleModule.setRoleId(this.role.getRoleId());
		roleModule.setModuleCode(new Integer(m));
		this.roleModuleManager.save(roleModule);
	    }
	    // 插入权限信息

	    for (String p : permissions) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(this.role.getRoleId());
		rolePermission.setPermissionId(new Long(p));
		this.rolePermissionManager.save(rolePermission);
	    }
	    getJsonResult().setSuccess(true);
	    getJsonResult().setMessage("成功添加");
	} catch (Exception e) {
	    getJsonResult().setSuccess(false);
	    getJsonResult().setMessage(e.getMessage());
	}
	return JSONRESULT;
    }

    /** 保存更新对象 */
    public String jsonUpdate() {
	try {
	    roleManager.update(this.role);
	    // 删除模块和权限
	    this.roleModuleManager.removeByRoleId(this.role.getRoleId());
	    this.rolePermissionManager.removeByRoleId(role.getRoleId());
	    // 插入模块信息

	    for (String m : modules) {
		RoleModule roleModule = new RoleModule();
		roleModule.setRoleId(this.role.getRoleId());
		roleModule.setModuleCode(new Integer(m));
		this.roleModuleManager.save(roleModule);
	    }
	    // 插入权限信息

	    for (String p : permissions) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(this.role.getRoleId());
		rolePermission.setPermissionId(new Long(p));
		this.rolePermissionManager.save(rolePermission);
	    }

	    getJsonResult().setSuccess(true);
	    getJsonResult().setMessage("成功更新");
	} catch (Exception e) {
	    getJsonResult().setSuccess(false);
	    getJsonResult().setMessage(e.getMessage());
	}
	return JSONRESULT;
    }

    /** 删除对象 */
    public String jsonDelete() {
	try {
	   
	    // 删除模块和权限
	    this.roleModuleManager.removeByRoleId(this.id);
	    this.rolePermissionManager.removeByRoleId(this.id);
	    //删除角色
	    roleManager.removeById(this.id);
	    
	    getJsonResult().setSuccess(true);
	    getJsonResult().setMessage("成功删除");
	} catch (Exception e) {
	    getJsonResult().setSuccess(false);
	    getJsonResult().setMessage(e.getMessage());
	}
	return JSONRESULT;
    }

	/**
	 * @return the existPermissions
	 */
	public List<Permission> getExistPermissions() {
		return existPermissions;
	}

	/**
	 * @param existPermissions the existPermissions to set
	 */
	public void setExistPermissions(List<Permission> existPermissions) {
		this.existPermissions = existPermissions;
	}

	/**
	 * @return the notExistPermissions
	 */
	public List<Permission> getNotExistPermissions() {
		return notExistPermissions;
	}

	/**
	 * @param notExistPermissions the notExistPermissions to set
	 */
	public void setNotExistPermissions(List<Permission> notExistPermissions) {
		this.notExistPermissions = notExistPermissions;
	}

	/**
	 * @param permissionManager the permissionManager to set
	 */
	public void setPermissionManager(PermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}

}
