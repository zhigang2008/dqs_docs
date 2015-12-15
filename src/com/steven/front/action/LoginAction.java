/*
 * Powered By Steven
 */

package com.steven.front.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.core.GlobalSetting;
import com.steven.core.model.Module;
import com.steven.core.model.User;
import com.steven.core.service.UserManager;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.menu.Menu;
import com.steven.framework.common.menu.MenuUtil;
import com.steven.framework.common.menu.SimpleMenu;
import com.steven.framework.common.security.encoder.PwdEncoder;
import com.steven.framework.common.web.RequestUtils;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */


public class LoginAction extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware {

	/**
	 * 验证码服务
	 */
	private ImageCaptchaService imageCaptchaService;

	/**
	 * 密码加密器
	 */
	private PwdEncoder pwdEncoder;
	/**
	 * 全局配置变量
	 */
	private GlobalSetting globalSetting=null;
	/**Manager管理器*/
	private UserManager userManager;
	/**
	 * 当前登录用户对象
	 */
	private User user;
	/**
	 * session对象
	 */
	private Map<String,Object> session;
	
	/**
	 * 验证码
	 */
	private String captchaCode;
	
	/**
	 * rememberMe标记
	 */
	private boolean rememberMe=false; 
	
	/**
	 * 前往地址
	 */
	private String forwardToURL;
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		this.clearErrorsAndMessages();
		user = new User();
	}
	
	/**
	 * 获取当前模型
	 * @return 模型对象
	 */
	public Object getModel() {
		return user;
	}

		
	/** 登录 */
	public String execute() {
		// 判断是否匿名登录
		if (this.user.getUserName() == null
				|| "".equals(this.user.getUserName().trim())) {
			this.user = userManager.getById(Integer.parseInt(globalSetting
					.getParam(Constants.ANONYMOUSE_USERID)));
			if (log.isDebugEnabled()) {
				log.debug("使用匿名用户登录");
			}

			if (this.authenticationByShiro(user.getUserName(), user
					.getPassword(), this.rememberMe)) {
				initCurrentUserInfo(this.user);
				return SUCCESS;
			}

			return INPUT;
		}

		// 如果是通过登录页面登录
		else {

			if (validateCaptcha()) {
				if (this.authenticationByShiro(user.getUserName(),this.pwdEncoder.encodePassword(user.getPassword()), this.rememberMe)) {
					
					User loginUser=this.userManager.getByUserName(this.user.getUserName());
					//初始化用户对象
					initCurrentUserInfo(loginUser);
					//更新登录状态
					updateUserLoginInfo(loginUser);
					return SUCCESS;
				}
				return INPUT;
			} else {
				return INPUT;
			}
		}

	}
	
	/**校验验证码
	 * @return 是否正确
	 */
	public boolean validateCaptcha() {
	    	 HttpServletRequest request = ServletActionContext.getRequest();
	    	try {
	    		//log.info(this.captchaCode);
				if (!imageCaptchaService.validateResponseForID(request.getSession().getId(),this.captchaCode.trim())) {
					this.addActionError("验证码不正确");
					if(this.log.isInfoEnabled()){
						this.log.info("验证码不正确");
					}
					return false;
				}
				
			} catch (CaptchaServiceException e) {
				this.addActionError("验证码验证出错");
				log.warn("验证码验证出错", e);
				return false;
			}catch (Exception e){
				this.addActionError("验证码验证出错");
				log.error("验证码验证出错");
				return false;
			}
			return true;
	    }

	
	/**
	 * @return the imageCaptchaService
	 */
	public ImageCaptchaService getImageCaptchaService() {
		return imageCaptchaService;
	}

	/**
	 * @return the captchaCode
	 */
	public String getCaptchaCode() {
		return captchaCode;
	}

	/**
	 * @param imageCaptchaService the imageCaptchaService to set
	 */
	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}

	/**
	 * @param captchaCode the captchaCode to set
	 */
	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	/**
	 * @return the pwdEncoder
	 */
	public PwdEncoder getPwdEncoder() {
		return pwdEncoder;
	}

	/**
	 * @param pwdEncoder the pwdEncoder to set
	 */
	public void setPwdEncoder(PwdEncoder pwdEncoder) {
		this.pwdEncoder = pwdEncoder;
	}


	/**
	 * @return the rememberMe
	 */
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe the rememberMe to set
	 */
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
  
    /**shiro安全框架用户登录
     * @param loginUser
     */
    private boolean authenticationByShiro(String userName,String password,boolean remeberMe){
    	 UsernamePasswordToken token = new UsernamePasswordToken(userName,password,remeberMe);
    	 try {
             SecurityUtils.getSubject().login(token);
          } catch (AuthenticationException e) {
        	 this.addActionError(e.getMessage());
             log.warn("shiro login error"+e.getMessage(),e);
            return false;
         }
         return true;
    }

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @param globalSetting the globalSetting to set
	 */
	public void setGlobalSetting(GlobalSetting globalSetting) {
		this.globalSetting = globalSetting;
	}
	
	/**初始化用户数据
	 * @param logUser
	 */
	private void initCurrentUserInfo(User logUser) {
		   //current user 
		   session.put(Constants.CURRENT_USER, logUser);
		   session.put(Constants.LOGIN_USERNAME, logUser.getRealName());
		   session.put(Constants.LOGIN_USERID, logUser.getUserid());
 	   
		   /* 所有用户模块   */
		   Set<Module> allModules=new HashSet<Module>();
		   List<Module> allModuleList=(List<Module>)userManager.getUserModules(logUser);
		   for(Module m:allModuleList){
			   allModules.add(m);
		   }
		   //session.put(Constants.CURRENT_USER_MODULES,allModules);

		   //初始化菜单
		    this.initUserMenus(allModules);

		   
//		   log.debug(logUser.getUserRoles());
//		   /*
//		    * 当前用户当前所有角色信息
//		    */
//		   List allRoles=userManager.getUserRoles(logUser);
//		   session.put(Constants.CURRENT_USER_ROLES, allRoles);
//		  		   
		   
//		   /* 所有用户权限   */
//		   List allPermissions=userManager.getUserPermissions(logUser);
//		   session.put(Constants.CURRENT_USER_PERMISSIONS,allPermissions);
			
		   
		   
		   //forward page 
		   String goingToURL = (String) session.get(Constants.FORWARD_TO_URL);  
		   
        if (StringUtils.isNotBlank(goingToURL)){  
            setForwardToURL(goingToURL);  
            session.remove(Constants.FORWARD_TO_URL);
            if(this.log.isInfoEnabled()){
					this.log.info("FORWARD URL is :"+goingToURL);
				}
        }else{  
     	   setForwardToURL(globalSetting.getParam(Constants.DEFAULT_HOMEPAGE));
        } 
    
	}
	
	/**更新当前登录用户的最后登录信息.如IP.时间
	 * @param logUser
	 */
	private void updateUserLoginInfo(User logUser) {
		//更新最后登录信息
           logUser.setLastLoginIp(RequestUtils.getIpAddr(getRequest()));
           logUser.setLastLoginTime(new Date());
           if(logUser.getLoginCount()!=null){
        	   logUser.setLoginCount(logUser.getLoginCount()+1);
           }else{
        	   logUser.setLoginCount(1);
           }
           userManager.update(logUser);
	}
	
    /**
     * 初始化用户菜单
     */
    private void  initUserMenus( Set<Module> allModules){
	 //菜单
	   Menu menu=null;
	   
	   if (allModules != null) {
		    //菜单格式化工具
		    MenuUtil util=new MenuUtil();
		    
			Iterator it = allModules.iterator();
			Module tempModule = null;
			int i=0;
			while (it.hasNext()) {
			   
			       SimpleMenu menu1=new SimpleMenu();
				tempModule = (Module) it.next();
				//log.debug("tempModule="+tempModule.toString());
				  menu1.setId(String.valueOf(tempModule.getModuleId()));
				  menu1.setName(tempModule.getModuleName());
				  menu1.setLink(tempModule.getUrl());
				  menu1.setSortOrder(tempModule.getSortOrder().intValue());
				  
				  if(tempModule.getParent()== 0 ){
				      //log.debug("setparent=root");
				    menu1.setParentId(MenuUtil.ROOTMENU);
				  }
				  else{
				     //log.debug("setparent=="+tempModule.getParent());
				      menu1.setParentId(String.valueOf(tempModule.getParent()));
				  }
				  //root.addChild(menu1);
				  util.addMenu(menu1);  
			}
			
			menu=util.getRootMenu();
		   //放入session中
			session.put(Constants.CURRENT_USER_MENUS,menu);
			log.debug("setting session menu:"+menu);
		}
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

	public void setForwardToURL(String forwardToURL) {
		this.forwardToURL = forwardToURL;
	}

	public String getForwardToURL() {
		return forwardToURL;
	}
	
	
    /** 注册后直接登录 */
    public String loginAfterRegister() {

	if (this.authenticationByShiro(user.getUserName(), this.pwdEncoder
		.encodePassword(user.getPassword()), this.rememberMe)) {

	    User loginUser = this.userManager.getByUserName(this.user
		    .getUserName());
	    // 初始化用户对象
	    initCurrentUserInfo(loginUser);
	    // 更新登录状态
	    updateUserLoginInfo(loginUser);
	    return SUCCESS;
	}
	return INPUT;
    }		
	
}
