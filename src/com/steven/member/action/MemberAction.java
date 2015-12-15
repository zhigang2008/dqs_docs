/*
 * Powered By Steven
 */

package com.steven.member.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.steven.Constants;
import com.steven.core.GlobalSetting;
import com.steven.core.model.PasswordApplyInfo;
import com.steven.core.model.User;
import com.steven.core.service.PasswordApplyInfoManager;
import com.steven.core.service.UserManager;
import com.steven.framework.base.BaseStruts2Action;
import com.steven.framework.common.security.encoder.PwdEncoder;
import com.steven.framework.util.CalendarUtils;
import com.steven.framework.util.UUIDGenerator;
import com.steven.framework.util.concurrent.async.AsyncToken;
import com.steven.framework.util.concurrent.async.IResponder;
import com.steven.member.mailer.PwdChangeApplyMailer;
import com.steven.member.mailer.UserChangePwdMailer;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */


public class MemberAction extends BaseStruts2Action implements Preparable,ModelDriven,SessionAware{
	
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
	 * 旧密码
	 */
	private String oriPwd="";
	/**
	 * 新密码
	 */
	private String newPwd="";
	
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
	
	//邮件发送服务
	private UserChangePwdMailer userChangePwdMailer;
	
	/**
	 * 密码重置申请信息管理
	 */
	private PasswordApplyInfoManager passwordApplyInfoManager;
	
	private PwdChangeApplyMailer pwdChangeApplyMailer;
	
	/**
	 * 密码重置请求序列号
	 */
	private String seqno;

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
		
		return SUCCESS;
	}

	/**修改密码
	 * @return 
	 */
	public String changePassword(){
	    this.clearActionErrors();
	    this.clearErrorsAndMessages();
	    
	    if(this.pwdEncoder.isPasswordValid(this.user.getPassword(), this.oriPwd)){
		this.user.setPassword(this.pwdEncoder.encodePassword(this.newPwd));
		this.userManager.update(user);
		this.newPwd=null;
		this.oriPwd=null;
		this.addActionMessage("密码修改成功!");
		//发送邮件
		this.getUserChangePwdMailer().sendNoticeMail(user);
		
		return INPUT;
	    
	    }else{

		this.addActionError("原始密码不正确!");
		return INPUT;
	    }
	}

	/**进入更新页面
	 * @return
	 */
	public String toUpdateUserInfo(){
	    if(this.user==null || this.user.getUserid()<=0){
		return INPUT;
	    }
	    return SUCCESS;
	}
	/**更新用户信息
	 * @return
	 */
	public String updateUserInfo(){
	    this.clearActionErrors();
	    this.clearErrorsAndMessages();
	    try{
		this.userManager.update(user);
		session.put(Constants.CURRENT_USER, this.user);
		session.put(Constants.LOGIN_USERNAME, user.getRealName());
		this.addActionMessage("用户信息已更新!");
	    }catch(Exception e){
		this.addActionError("更新失败!");
	    }
	    return INPUT;
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
	 * @param globalSetting the globalSetting to set
	 */
	public void setGlobalSetting(GlobalSetting globalSetting) {
	    this.globalSetting = globalSetting;
	}


	/**
	 * @param oriPwd the oriPwd to set
	 */
	public void setOriPwd(String oriPwd) {
	    this.oriPwd = oriPwd;
	}


	/**
	 * @param newPwd the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
	    this.newPwd = newPwd;
	}


	/**
	 * @param session the session to set
	 */
	public void setSession(Map session) {
	    this.session = session;
	}


	public void setUserChangePwdMailer(UserChangePwdMailer userChangePwdMailer) {
	    this.userChangePwdMailer = userChangePwdMailer;
	}


	public UserChangePwdMailer getUserChangePwdMailer() {
	    return userChangePwdMailer;
	}
	
	/**忘记密码,确认用户邮件地址
	 * @return
	 */
	public String forgetPassword(){
		user=this.userManager.getByUserName(user.getUserName());
		if(user==null){
			this.clearErrorsAndMessages();
			this.addActionError("不存在该用户!");
			return INPUT;
		}else{
			return SUCCESS;
		}
		 
	}

	/**发送重置用户密码的邮件
	 * @return
	 */
	public String sendResetPasswordEmail() {
	
		Date now = new Date();
		PasswordApplyInfo applyInfo = new PasswordApplyInfo();
		applyInfo.setSeqno(UUIDGenerator.getID());
		applyInfo.setUserid(user.getUserid());
		applyInfo.setUserName(user.getUserName());
		applyInfo.setApplytime(now);
		applyInfo.setIsValid(true);
		applyInfo.setApplyIp(this.getRequest().getRemoteAddr());
		applyInfo.setDeadline(CalendarUtils.add(Calendar.DAY_OF_YEAR, now, 1));

		try{
		// 写入记录表中
		this.passwordApplyInfoManager.save(applyInfo);
		
		// 发送邮件
		HashMap mailMap = new HashMap();
		mailMap.put("sendTo", user.getEmail());
		mailMap.put("realName", user.getRealName());
		mailMap.put("deadLine", applyInfo.getDeadlineString());
		mailMap.put("userName", user.getUserName());
		
		log.debug(getRequest().getContextPath());
		//请求地址
		String url="http://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath()+"/member/resetPassword.do?seqno="+applyInfo.getSeqno();
		mailMap.put("resetUrl", url);

		AsyncToken toke = this.pwdChangeApplyMailer.sendApplyMail(mailMap);
		toke.addResponder(new IResponder() {

			@Override
			public void onFault(Exception fault) {
				getJsonResult().setSuccess(false);
				getJsonResult().setMessage("请求无法完成,请稍后再试!");
				log.error("发送密码重置邮件失败:" + fault.getMessage(), fault);

			}

			@Override
			public void onResult(Object result) {
				// 提示信息
				getJsonResult().setSuccess(true);
				getJsonResult().setMessage("已向您的信箱中发送了密码修改邮件,请查收!");
				log.info("发送密码重置请求成功");
			}

		}, false);

		}catch(Exception e){
			getJsonResult().setSuccess(false);
			getJsonResult().setMessage("请求无法完成,请稍后再试!");
			log.error("发送密码重置邮件失败:" + e.getMessage(), e);
		}
		return super.JSONRESULT;
	}

	/**重置密码
	 * @return
	 */
	public String resetPassword(){
		this.clearErrorsAndMessages();
		PasswordApplyInfo applyInfo=this.passwordApplyInfoManager.getById(this.seqno);
		
		if(applyInfo.getIsValid()==false){
			this.clearErrorsAndMessages();
			this.addActionMessage("该请求已经失效!");
			return SUCCESS;
		}
		if(applyInfo.getDeadline().before(new Date())){
			this.addActionMessage("该请求已过期!");
			return SUCCESS;
		}
		
		try{
			
		//产生新密码,并更新用户
		this.user=this.userManager.getById(applyInfo.getUserid());
		String newPwd=UUIDGenerator.getID().substring(0,7);
		this.user.setPassword(this.pwdEncoder.encodePassword(newPwd));
		this.userManager.update(user);
		
		
		HashMap mailMap = new HashMap();
		mailMap.put("sendTo", user.getEmail());
		mailMap.put("realName", user.getRealName());
		mailMap.put("newPassword", newPwd);
		mailMap.put("userName", user.getUserName());
		//请求地址
		String url="http://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath();
		mailMap.put("serverUrl", url);
		//发送通知邮件
		AsyncToken toke = this.pwdChangeApplyMailer.sendNoticeMail(mailMap);
		toke.addResponder(new IResponder() {

			@Override
			public void onFault(Exception fault) {
				addActionError("密码重置失败,请联系系统管理员!");
				log.error("发送密码确认邮件失败:" + fault.getMessage(), fault);

			}

			@Override
			public void onResult(Object result) {
				
			    addActionMessage("请进入您的邮箱查收重置密码!");
				log.info("发送密码重置邮件成功");
			}

		}, false);
		
		//更新密码重置信息
		applyInfo.setIsValid(false);
		applyInfo.setUseTime(new Date());
		this.passwordApplyInfoManager.update(applyInfo);
		
		}catch(Exception e){
			log.error("用户重置密码失败:"+e.getMessage(),e);
			this.addActionError("重置密码失败,请联系管理员");
		}
		
		addActionMessage("请进入您的邮箱查收重置密码!");
		return SUCCESS;
	}

	/**
	 * @return the pwdChangeApplyMailer
	 */
	public PwdChangeApplyMailer getPwdChangeApplyMailer() {
		return pwdChangeApplyMailer;
	}


	/**
	 * @param pwdChangeApplyMailer the pwdChangeApplyMailer to set
	 */
	public void setPwdChangeApplyMailer(PwdChangeApplyMailer pwdChangeApplyMailer) {
		this.pwdChangeApplyMailer = pwdChangeApplyMailer;
	}


	/**
	 * @param passwordApplyInfoManager the passwordApplyInfoManager to set
	 */
	public void setPasswordApplyInfoManager(
			PasswordApplyInfoManager passwordApplyInfoManager) {
		this.passwordApplyInfoManager = passwordApplyInfoManager;
	}


	/**
	 * @return the seqno
	 */
	public String getSeqno() {
		return seqno;
	}


	/**
	 * @param seqno the seqno to set
	 */
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	
	
}
