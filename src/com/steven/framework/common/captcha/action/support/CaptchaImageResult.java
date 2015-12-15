package com.steven.framework.common.captcha.action.support;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.steven.framework.common.captcha.action.JCaptchaImageAction;


/**Struts2中实现的验证码结果类,向页面写入图片
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class CaptchaImageResult implements Result {

    private static final Log LOG = LogFactory.getLog(CaptchaImageResult.class);
    private static final long serialVersionUID = -595342817673304030L;


    public void execute(ActionInvocation invocation) throws IOException, IllegalArgumentException {

        // Check if the invoked action was JCaptchaImageAction
        if (!(invocation.getAction() instanceof JCaptchaImageAction)) {
            throw new IllegalArgumentException(
                    "CaptchaImageResult expects JCaptchaImageAction as Action Invocation");
        }

        JCaptchaImageAction action = (JCaptchaImageAction) invocation.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();

        // Read captcha image bytes
        byte[] image = action.getCaptchaImage();

        // Send response
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        response.setContentLength(image.length);
        try {
            response.getOutputStream().write(image);
            response.getOutputStream().flush();
        } catch (IOException e) {
            LOG.error("IOException while writing image response for action : " + e.getMessage(), e);
            throw e;
        }
    }

}
