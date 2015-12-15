/**
 * Classname:com.steven.framework.base.BaseMailer
 * Version info:V1.0
 * Date:2011-10-21 
 * Copyright notice: minshengLife
 */
package com.steven.framework.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.Assert;

import com.steven.framework.common.mail.AsyncJavaMailSender;
import com.steven.framework.core.freemarker.FreemarkerTemplateProcessor;

/**
 * mailer基类,用于其它mailer继承.
 * 现本mailer与freemarker模板结合,用于生成邮件内容
 * 本类你也可以自由修改
 * 
 * <pre>
 * Mailer使用规范:
 * 1. 包名: 放在mailer包内,如com.company.project.mailer
 * 2. 类名: 以Mailer结尾,如UserMailer
 * 3. 方法名: 
 * 			使用UserMailer.createXXXX()来创建邮件消息,如UserMailer.createConfirmMail()
 * 			使用UserMailer.sendXXXX()来发送邮件,如UserMailer.sendConfirmMail()
 * 4.必须继承之BaseMailer
 * 5.单元测试一般情况下测试testCreateXXXX()即可
 * </pre>
 * @author Steven
 * @version 1.0
 * @since 1.0
 */
public class BaseMailer implements InitializingBean{
        /**
         * 共用的log
         */
        protected Log log=LogFactory.getLog(getClass());
	/**
	 * 异步的邮件发送
	 */
	private AsyncJavaMailSender asyncJavaMailSender;
	/**
	 * 邮件内容模板
	 */
	protected SimpleMailMessage simpleMailMessageTemplate;
	/**
	 * freemarker 模板处理器
	 */
	protected FreemarkerTemplateProcessor freemarkerTemplateProcessor;
	/**
	 * 邮件前缀,子类可以使用
	 */
	protected String mailSubjectPrefix ;  
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(asyncJavaMailSender,"asyncJavaMailSender must be not null");
		Assert.notNull(freemarkerTemplateProcessor,"freemarkerTemplateProcessor must be not null");
	}
	
	public void setAsyncJavaMailSender(AsyncJavaMailSender asyncJavaMailSender) {
		this.asyncJavaMailSender = asyncJavaMailSender;
	}
	
	public AsyncJavaMailSender getAsyncJavaMailSender() {
		return asyncJavaMailSender;
	}
	
	public JavaMailSender getJavaMailSender() {
		return getAsyncJavaMailSender().getJavaMailSender();
	}

	public void setSimpleMailMessageTemplate(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessageTemplate = simpleMailMessage;
	}
	
	public FreemarkerTemplateProcessor getFreemarkerTemplateProcessor() {
		return freemarkerTemplateProcessor;
	}

	public void setFreemarkerTemplateProcessor(FreemarkerTemplateProcessor freemarkerTemplateProcessor) {
		this.freemarkerTemplateProcessor = freemarkerTemplateProcessor;
	}

	public void setMailSubjectPrefix(String subjectPrefix) {
		this.mailSubjectPrefix = subjectPrefix;
	}

	public String getMailSubjectPrefix() {
		return mailSubjectPrefix;
	}
	
	/**处理模板,通过数据填充模板
	 * @param templateName 模板名称
	 * @param model 数据对象
	 * @return 处理后的数据信息
	 */
	public String processTemplate(String templateName,Object model) {
		return getFreemarkerTemplateProcessor().processTemplate(templateName, model);
	}
	
	/**
	 * @param subject
	 * @return
	 */
	protected SimpleMailMessage newSimpleMsgFromTemplate(String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		if(simpleMailMessageTemplate != null) {
			simpleMailMessageTemplate.copyTo(msg);
		}
		
		String prefix = getMailSubjectPrefix();
		msg.setSubject(((prefix == null ? "" : prefix) + subject));
		
		return msg;
	}

}
