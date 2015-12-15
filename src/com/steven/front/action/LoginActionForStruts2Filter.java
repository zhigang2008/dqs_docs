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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


public class LoginActionForStruts2Filter extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5120446243217976896L;

	/**
	 * 全局配置变量
	 */
	private GlobalSetting globalSetting=null;
	
	/**Manager管理器*/
	private UserManager userManager;
	/**
	 * session对象
	 */
	private Map session;
	/**
	 * 验证码服务
	 */
	private ImageCaptchaService imageCaptchaService;

	/**
	 * 密码加密器
	 */
	private PwdEncoder pwdEncoder;

	/**
	 * 当前登录用户对象
	 */
	private User user;
	
	java.lang.Integer id = null;
	
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
		if (isNullOrEmptyString(id)) {
			user = new User();
		} else {
			user = (User)userManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setUserManager(UserManager manager) {
		this.userManager = manager;
	}	
	
	/**
	 * 获取当前模型
	 * @return 模型对象
	 */
	public Object getModel() {
		return user;
	}
	
	public void setUserid(java.lang.Integer val) {
		this.id = val;
	}

		
	/** 登录 */
	public String execute() {
		//判断是否匿名登录
	    if(this.user.getUserName()==null || "".equals(this.user.getUserName().trim())){
		this.user=userManager.getById(Integer.parseInt(globalSetting.getParam(Constants.ANONYMOUSE_USERID)));
		if(log.isDebugEnabled()){
		    log.debug("使用匿名用户登录");
		}
		 initCurrentUserInfo(this.user);
		//安全框架
		this.initShiro(this.user);
		
		return SUCCESS;
	    }
	    
	    //如果是通过登录页面登录
	    else{
		
	    
		if(validateCaptcha()){
		   
		   User logUser=userManager.getByUserName(this.user.getUserName());
		   if(logUser!=null){
			   //如果被禁用.则直接返回
			   if(logUser.getIsDisabled()== true ){
				   this.addActionError("该用户已被禁用或失效,如有疑问请联系系统管理员");
				   return INPUT;
			   }
			   if(pwdEncoder.isPasswordValid(logUser.getPassword(), this.user.getPassword())){
				   this.user=logUser;
				   
				   initCurrentUserInfo(logUser);
				   rememberMeOperation(logUser); 
				   updateUserLoginInfo(logUser);
				   //安全框架
				   this.initShiro(logUser);
				   			  
				   return SUCCESS;
			   }
			   else{
				   this.addActionError("用户名/密码不正确");
			   }
		    }else{
			   this.addActionError("用户不存在或密码不正确");
		   }
		 }
		else{
			return INPUT;
		}
	    }
	   return INPUT;
	    
	}

	/**登录成功,将用户相关信息放入Session
	 * @param logUser 当前登录用户
	 */
	private void initCurrentUserInfo(User logUser) {
		   //current user 
		   session.put(Constants.CURRENT_USER, logUser);
		   session.put(Constants.LOGIN_USERNAME, logUser.getRealName());
		   session.put(Constants.LOGIN_USERID, logUser.getUserid());
		   
		   log.debug(logUser.getUserRoles());
		   /*
		    * 当前用户当前所有角色信息
		    */
		   List allRoles=userManager.getUserRoles(logUser);
		   session.put(Constants.CURRENT_USER_ROLES, allRoles);
		   
		   
		   /* 所有用户模块   */
		   Set<Module> allModules=new HashSet<Module>();
		   List<Module> allModuleList=(List<Module>)userManager.getUserModules(logUser);
		   for(Module m:allModuleList){
			   allModules.add(m);
		   }
		   session.put(Constants.CURRENT_USER_MODULES,allModules);
		   
		   /* 所有用户权限   */
		   List allPermissions=userManager.getUserPermissions(logUser);
		   session.put(Constants.CURRENT_USER_PERMISSIONS,allPermissions);
			
		   //初始化菜单
		   this.initUserMenus(allModules);
		   
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

	/**如果打开rememberMe功能.则将用户信息写入Session
	 * @param logUser
	 */
	private void rememberMeOperation(User logUser) {
		//cookie
		   if (rememberMe){ 
			   
			   if(this.log.isInfoEnabled()){
					this.log.info("rememberMe enabled");
				}
			   
			   HttpServletResponse response = ServletActionContext.getResponse();
               Cookie cookie = new Cookie(Constants.COOKIE_REMEMBERME_KEY, logUser.getUserid() + "==" + logUser.getPassword());  
               cookie.setMaxAge(60 * 60 * 24 * 14);  
               response.addCookie(cookie);  
               
               
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
	 * @return the session
	 */
	public Map getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Map session) {
		this.session = session;
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
	 * @return the forwardToURL
	 */
	public String getForwardToURL() {
		return forwardToURL;
	}

	/**
	 * @param forwardToURL the forwardToURL to set
	 */
	public void setForwardToURL(String forwardToURL) {
		this.forwardToURL = forwardToURL;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param globalSetting the globalSetting to set
	 */
	public void setGlobalSetting(GlobalSetting globalSetting) {
	    this.globalSetting = globalSetting;
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
	
    
    /**shiro安全框架用户登录
     * @param loginUser
     */
    private void initShiro(User loginUser){
    	 UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(),loginUser.getPassword());
    	 try {
    		 token.setRememberMe(this.rememberMe);
             SecurityUtils.getSubject().login(token);

         } catch (AuthenticationException e) {
            log.warn("shiro login error"+e.getMessage(),e);
         }
    }
}
