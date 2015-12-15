package com.steven.framework.common.captcha.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.opensymphony.xwork2.ActionSupport;


/**通过Struts2实现的验证码实现类
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class JCaptchaImageAction extends ActionSupport {

    private static final String IMAGE_FORMAT = "jpg";
    private static final long serialVersionUID = 563498642053643243L;
    private static final Log LOG = LogFactory.getLog(JCaptchaImageAction.class);

    @Autowired
    private ImageCaptchaService imageCaptchaService=null;

    private byte[] captchaImage;


    public String execute() {

        ByteArrayOutputStream imageOut = new ByteArrayOutputStream();
        HttpServletRequest request = ServletActionContext.getRequest();

        // Captcha Id is the session ID
        String captchaId = request.getSession().getId();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Generating Captcha Image for SessionID : " + captchaId);
        }

        // Generate Captcha Image
        BufferedImage image =
                getImageCaptchaService().getImageChallengeForID(captchaId, request.getLocale());

        // Encode to JPEG Stream
        try {
            ImageIO.write(image, IMAGE_FORMAT, imageOut);
        } catch (IOException e) {
            LOG.error("Unable to JPEG encode the Captcha Image due to IOException", e);
            throw new IllegalArgumentException("Unable to JPEG encode the Captcha Image", e);
        }

        // Get byte[] for image
        captchaImage = imageOut.toByteArray();

        return SUCCESS;
    }

   
    public byte[] getCaptchaImage() {
        return captchaImage;
    }

    protected ImageCaptchaService getImageCaptchaService() {
    	
      return this.imageCaptchaService;
    }

	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}
}
