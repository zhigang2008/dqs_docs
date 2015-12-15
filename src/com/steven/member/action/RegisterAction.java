/*
 * Powered By Steven
 */

package com.steven.member.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.core.GlobalSetting;
import com.steven.core.model.User;
import com.steven.core.model.UserRole;
import com.steven.core.service.UserManager;
import com.steven.core.service.UserRoleManager;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.security.encoder.PwdEncoder;
import com.steven.framework.common.web.RequestUtils;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */


public class RegisterAction extends BaseStruts2Action implements Preparable,ModelDriven{
	
       /**
	 * 全局配置变量
	 */
	private GlobalSetting globalSetting=null;
	/**Manager管理器*/
	private UserManager userManager;
	
	/**
	 * 用户对象
	 */
	private User user;
	
	/**
	 * ID
	 */
	java.lang.Integer id = null;

	/**
	 * 验证码服务
	 */
	private ImageCaptchaService imageCaptchaService;
	
	/**
	 * 加密器
	 */
	private PwdEncoder pwdEncoder;

	private String captchaCode;
	
	
	/*
	 * 用户-角色映射管理器
	 */
	private UserRoleManager userRoleManager;
	

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
	

	/** set usermanager
	 * @param manager
	 */
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
	
	/**
	 * @param val
	 */
	public void setUserid(java.lang.Integer val) {
		this.id = val;
	}

		
	/** 保存新增对象 */

	public String execute() {
		
		if(validateCaptcha()){
		    
		    //判断用户名是否存在
		    User existUser=this.userManager.getByUserName(user.getUserName());
		    if(existUser!=null){
			this.addActionMessage("用户已存在!");
			return INPUT;
		    }
		    //继续处理
		   initUserInfo();
		   int saveNum=userManager.insert(user);
		   
		   //设置默认角色
		   if(saveNum > 0 ){
			   initUserRoleInfo(user);
		   }
		   
		   log.info("create new member success");
		 }
		else{
			
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 初始化用户角色信息
	 */
	private void initUserRoleInfo(User user) {
		UserRole userRole=new UserRole();
		userRole.setRoleId(Integer.parseInt(globalSetting.getParam(Constants.MEMBER_DEFAULT_ROLE)));
		userRole.setUserid(user.getUserid());
		this.userRoleManager.save(userRole);
		if(log.isDebugEnabled()){
			log.debug("Set default Role["+globalSetting.getParam(Constants.MEMBER_DEFAULT_ROLE) +"] for user["+user.getUserid()+"]");
		}
		
	}
	
	/**
	 * 初始化用户信息
	 */
	private void initUserInfo() {
		this.user.setEmail(this.user.getUserName());
		this.user.setPassword(pwdEncoder.encodePassword(this.user.getPassword()));
		this.user.setRegisterTime(new Date());
		this.user.setRegisterIp(RequestUtils.getIpAddr(getRequest()));
		this.user.setIsDisabled(Boolean.parseBoolean(globalSetting.getParam(Constants.USER_DEFAULT_DISABLED)));
		this.user.setRank(Integer.parseInt(globalSetting.getParam(Constants.MEMBER_DEFAULT_RANK)));
		this.user.setUserTypeId(Integer.parseInt(globalSetting.getParam(Constants.DEFAULT_USER_TYPE)));
		this.user.setLoginCount(0);
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
					return false;
				}
				
			} catch (CaptchaServiceException e) {
				this.addActionError("验证码验证出错");
				log.warn("验证码验证出错", e);
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
	 * @return the userRoleManager
	 */
	public UserRoleManager getUserRoleManager() {
		return userRoleManager;
	}

	/**
	 * @param userRoleManager the userRoleManager to set
	 */
	public void setUserRoleManager(UserRoleManager userRoleManager) {
		this.userRoleManager = userRoleManager;
	}

	/**
	 * @param globalSetting the globalSetting to set
	 */
	public void setGlobalSetting(GlobalSetting globalSetting) {
	    this.globalSetting = globalSetting;
	}


	
}
