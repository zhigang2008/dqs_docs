/**
 * CaptchaBaseAction.java
 * Steven
 * 2011-4-1
 */
package com.steven.framework.common.captcha.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


/**通过Struts2实现的验证码验证类
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class CaptchaBaseAction extends ActionSupport implements Preparable {
	private static final Log log = LogFactory.getLog(CaptchaBaseAction.class);

    private static final long serialVersionUID = -2L;
    
    @Autowired
    private ImageCaptchaService imageCaptchaService=null;
    
    private String captchaCode;
    
	public CaptchaBaseAction() {
		super();
	}

    /**
     * Flag whether validation should be done.
     */
    private boolean validationEnabled = true;

   
    public CaptchaBaseAction(boolean validationEnabled) {
        super();
        this.validationEnabled = validationEnabled;
    }

    public boolean validateCaptcha() {
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	try {
    		//log.info(this.captchaCode);
			if (!imageCaptchaService.validateResponseForID(request.getSession().getId(),this.captchaCode.trim())) {
				this.addActionError("验证码不正确");
				return false;
			}
			
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
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
	 * @param imageCaptchaService the imageCaptchaService to set
	 */
	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}

	
	@Override
	public void prepare() throws Exception {
		this.clearErrorsAndMessages();
		
	}

	/**
	 * @return the captchaCode
	 */
	public String getCaptchaCode() {
		return captchaCode;
	}

	/**
	 * @param captchaCode the captchaCode to set
	 */
	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

}
