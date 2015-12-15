/**
 * AuthenticationInterceptor.java
 * Steven
 * 2011-4-2
 */
package com.steven.framework.core.struts2.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.steven.Constants;
import com.steven.core.model.Audit;
import com.steven.core.model.User;
import com.steven.core.service.AuditManager;
import com.steven.core.service.UserManager;
import com.steven.framework.common.web.RequestUtils;
import com.steven.front.action.LoginAction;
import com.steven.front.action.LogoutAction;

/**
 * @author Steven
 *
 */
public class AuthLogInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7741946300474737063L;


	static Log log = LogFactory.getLog(AuthLogInterceptor.class);

	
	private AuditManager auditManager;
	private Audit audit;
	private UserManager userManager;

	/**
	 * 
	 */
	public AuthLogInterceptor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();  
		Object action=invocation.getAction();
		String result=null;
		
		if(log.isDebugEnabled()){
			log.debug("execute AuthLogInterceptor");
		}
		
		//登录操作
		if(action instanceof LoginAction){
			
			if(log.isDebugEnabled()){
				log.debug("AuthLogInterceptor -- Login Action");
			}
			
			User user=null;
			int saveAffectnum=0;
			//登录记录
			try {
				user = ((LoginAction) action).getUser();
				HttpServletRequest request = ServletActionContext.getRequest();
				
				audit = new Audit();
				audit.setUserid(0); //临时赋值
				audit.setUserName(user.getUserName());
				audit.setActIp(RequestUtils.getIpAddr(request));
				audit.setActTime(new Date());
				audit.setActType(1);
				audit.setActStatus(false);
				
				//系统中存在的用户
				User existUser=userManager.getByUserName(user.getUserName());
				if(existUser!=null && existUser.getUserid()!=null)
					audit.setUserid(existUser.getUserid()); //临时赋值
				
				saveAffectnum=auditManager.insert(audit);
				
			} catch (Exception e) {
				log.warn("login auth  fail step1 ",e);
				return invocation.invoke();
			}
			//执行action
			result = invocation.invoke();
			//成功登录更新
			try{
				HttpSession session = ServletActionContext.getRequest().getSession(false);  
		        if (session!=null && session.getAttribute(Constants.CURRENT_USER)!=null){
		        	if(saveAffectnum > 0){
		        		
		        		audit.setUserid(((User)session.getAttribute(Constants.CURRENT_USER)).getUserid());
		        		//待修改
		        		audit.setUserName(((User)session.getAttribute(Constants.CURRENT_USER)).getUserName());
		        		audit.setActStatus(true);
		        		auditManager.update(audit);
		        	}
		        }
		             
			}catch(Exception e){
				log.warn("login auth  fail step2 ",e);
				return invocation.invoke();
			}
			
			return result;
		}
		//注销操作
		else if(action instanceof LogoutAction){
			if(log.isDebugEnabled()){
				log.debug("AuthLogInterceptor -- Logout Action");
			}
			
			try{
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession(false);
				
		        if (session!=null && session.getAttribute(Constants.CURRENT_USER)!=null){
		        	    User user =(User)session.getAttribute(Constants.CURRENT_USER);	        		
		        		audit = new Audit();
						audit.setUserid(user.getUserid());
						audit.setUserName(user.getUserName());
						audit.setActIp(RequestUtils.getIpAddr(request));
						audit.setActTime(new Date());
						audit.setActType(2);
						audit.setActStatus(true);
						auditManager.save(audit);
						return invocation.invoke();
		        	}
		             
			}catch(Exception e){
				log.warn("logout  auth  fail ",e);
				return invocation.invoke();
			}
		
		}

		return invocation.invoke();  
 

	}

	/**
	 * @return the authenticationManager
	 */
	public AuditManager getAuditManager() {
		return auditManager;
	}

	/**
	 * @param AuditManager the AuditManager to set
	 */
	public void setAuditManager(AuditManager auditManager) {
		this.auditManager = auditManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	
}
