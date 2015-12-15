/**
 * Classname:com.steven.member.mailer.UserChangePwdMailer
 * Version info:V1.0
 * Date:2011-10-21 
 * Copyright notice: minshengLife
 */
package com.steven.member.mailer;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
public class PwdChangeApplyMailer extends BaseMailer {

    
	/**确认邮件内容
	 * @param map
	 * @return
	 */
	public SimpleMailMessage createApplyMail(Map map) {
		SimpleMailMessage msg = newSimpleMsgFromTemplate("您修改密码的申请");
		msg.setTo((String)map.get("sendTo"));
		String text = processTemplate("pwdChangeApply.flt", map);
		msg.setText(text);
		return msg;
	}
	
	/**
	 * 发送确认邮件
	 */
	public AsyncToken sendApplyMail(final Map map) {
		final SimpleMailMessage msg = createApplyMail(map);
		
		//转换为html邮件并发送,另有一个参数可以指定发件人名称
		AsyncToken token = getAsyncJavaMailSender().send(SimpleMailMessageUtils.toHtmlMsg(msg)); 
		
		//处理邮件发送结果
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				log.error("[ERROR] PwdChangeApplyMailer send fail,cause:"+fault);
			}
			public void onResult(Object result) {
				log.info("[INFO] PwdChangeApplyMailer send success");
			}
		});
		
		//返回token可以用于外部继续监听
		return token;
	}
	
	/**创建通知邮件内容
	 * @param map
	 * @return
	 */
	public SimpleMailMessage createNoticeMail(Map map) {
		SimpleMailMessage msg = newSimpleMsgFromTemplate("您的密码已经重置");
		msg.setTo((String)map.get("sendTo"));
		String text = processTemplate("pwdChangeNotice.flt", map);
		msg.setText(text);
		return msg;
	}
	
	/**
	 * 发送通知邮件
	 */
	public AsyncToken sendNoticeMail(final Map map) {
		final SimpleMailMessage msg = createNoticeMail(map);
		
		//转换为html邮件并发送,另有一个参数可以指定发件人名称
		AsyncToken token = getAsyncJavaMailSender().send(SimpleMailMessageUtils.toHtmlMsg(msg)); 
		
		//处理邮件发送结果
		token.addResponder(new IResponder() {
			public void onFault(Exception fault) {
				log.error("[ERROR] PwdChangeApplyMailer send fail,cause:"+fault);
			}
			public void onResult(Object result) {
				log.info("[INFO] PwdChangeApplyMailer send success");
			}
		});
		
		//返回token可以用于外部继续监听
		return token;
	}
}
