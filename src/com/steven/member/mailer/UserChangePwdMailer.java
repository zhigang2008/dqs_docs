/**
 * Classname:com.steven.member.mailer.UserChangePwdMailer
 * Version info:V1.0
 * Date:2011-10-21 
 * Copyright notice: minshengLife
 */
package com.steven.member.mailer;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.steven.core.model.User;
import com.steven.framework.base.BaseMailer;
import com.steven.framework.common.mail.SimpleMailMessageUtils;
import com.steven.framework.util.concurrent.async.AsyncToken;
import com.steven.framework.util.concurrent.async.IResponder;

/**
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserChangePwdMailer extends BaseMailer {

    
	public SimpleMailMessage createNoticeMail(User user) {
		SimpleMailMessage msg = newSimpleMsgFromTemplate("您的用户密码已修改");
		msg.setTo(user.getEmail());
		String text = processTemplate("userChangePwd.flt", user);
		msg.setText(text);
		return msg;
	}
	
	/**
	 * 发送邮件
	 */
	public AsyncToken sendNoticeMail(final User user) {
		final SimpleMailMessage msg = createNoticeMail(user);
		
		//转换为html邮件并发送,另有一个参数可以指定发件人名称
		AsyncToken token = getAsyncJavaMailSender().send(SimpleMailMessageUtils.toHtmlMsg(msg)); 
		
		//处理邮件发送结果
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				log.error("[ERROR] UserChangePwdMailer send fail,cause:"+fault);
			}
			public void onResult(Object result) {
				log.info("[INFO] UserChangePwdMailer send success");
			}
		});
		
		//返回token可以用于外部继续监听
		return token;
	}
}
