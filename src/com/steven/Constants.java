/**
 * Constants.java
 * Steven
 * 2011-2-21
 */
package com.steven;

/**
 * 系统常量
 * 
 * @author Steven
 * 
 */
public abstract class Constants {
    /* web 常量 */

    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 提示信息
     */
    public static final String MESSAGE = "message";
    /**
     * cookie中的JSESSIONID名称
     */
    public static final String JSESSION_COOKIE = "JSESSIONID";
    /**
     * url中的jsessionid名称
     */
    public static final String JSESSION_URL = "jsessionid";
    /**
     * HTTP POST请求
     */
    public static final String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static final String GET = "GET";

    /**
     * session中存放当前用户的key
     */
    public static final String CURRENT_USER = "CURRENT_USER";
    
    public static final String LOGIN_USERNAME = "loginUserName";
    
    public static final String LOGIN_USERID = "_LOGINUSERID";
    /**
     * rememberMe的key值
     */
    public static final String COOKIE_REMEMBERME_KEY = "STEVEN_COOKIE_REMEMBERME";
    /**
     * 下一个页面
     */
    public static final String FORWARD_TO_URL = "FORWARD_TO_URL";

    /**
     * 当前用户拥有的角色信息
     */
    public static final String CURRENT_USER_ROLES = "CURRENT_USER_ROLES";
    /**
     * 当前用户拥有的角色信息名称
     */
    public static final String CURRENT_USER_ROLES_NAME = "CURRENT_USER_ROLES_NAME";
    /**
     * 当前用户拥有的模块信息
     */
    public static final String CURRENT_USER_MODULES = "CURRENT_USER_MODULES";
    /**
     * 当前用户拥有的权限信息
     */
    public static final String CURRENT_USER_PERMISSIONS = "CURRENT_USER_PERMISSIONS";
    /**
     * 当前用户的菜单
     */
    public static final String CURRENT_USER_MENUS = "userMenus";
    
    //全局常量默认参数名
	/**
	 * 默认用户密码
	 */
	public static final String USER_DEFAULT_PASSWORD = "USER_DEFAULT_PASSWORD";
	/**
	 * 默认用户类型
	 */
	public static final String DEFAULT_USER_TYPE = "DEFAULT_USER_TYPE";
	public static final String USER_DEFAULT_DISABLED = "USER_DEFAULT_DISABLED";
	public static final String MEMBER_DEFAULT_RANK = "MEMBER_DEFAULT_RANK";
	public static final String MEMBER_DEFAULT_ROLE = "MEMBER_DEFAULT_ROLE";
	public static final String DEFAULT_HOMEPAGE = "DEFAULT_HOMEPAGE";
	public static final String ANONYMOUSE_USERID = "ANONYMOUSE_USERID";
	
	
   
}
