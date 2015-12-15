/**
 * LogoutAction.java
 * Steven
 * 2011-4-3
 */
package com.steven.front.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.steven.Constants;
import com.steven.framework.base.BaseStruts2Action;

/**
 * @author Steven
 *
 */
public class LogoutAction extends BaseStruts2Action implements ServletRequestAware , ServletResponseAware {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3223159841620424242L;
	private HttpServletRequest request;  
        private HttpServletResponse response;  
  
    public String execute() throws Exception{
    	 //清理安全信息
        clearShiroInfo();
        
    	//清理当前的session信息
        HttpSession session = request.getSession(false);  
        if (session!=null) { 
        	if(this.log.isInfoEnabled()){
    			this.log.info("user logout"+session.getAttribute(Constants.CURRENT_USER));
    		}
            session.removeAttribute(Constants.CURRENT_USER); 
            session.invalidate();
        }
       
        
        //清理cookie中的信息
        Cookie[] cookies = request.getCookies();  
        if (cookies!=null) {  
            for (Cookie cookie : cookies) {  
                if (Constants.COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {  
                    cookie.setValue("");  
                    cookie.setMaxAge(0);  
                    response.addCookie(cookie);  
                    
                }  
            }  
        }  
        return SUCCESS;  
    }  
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
	/**
	 * 清理shiro信息
	 */
	private void clearShiroInfo(){
		SecurityUtils.getSubject().logout();
	}

}
