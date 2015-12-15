/**
 * AuthenticationInterceptor.java
 * Steven
 * 2011-4-2
 */
package com.steven.framework.core.struts2.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.config.DefaultSettings;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.steven.Constants;
import com.steven.core.model.Module;
import com.steven.core.model.Permission;
import com.steven.core.model.Role;
import com.steven.core.model.User;
import com.steven.core.service.UserManager;

/**
 * @author Steven
 *
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

	/**
     * 
     */
    private static final long serialVersionUID = 2063893168857665282L;

	static Log log = LogFactory.getLog(AuthenticationInterceptor.class);
	
	private UserManager userManager;
	/**
	 * 
	 */
	public AuthenticationInterceptor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();  
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);  
          
        Map session = actionContext.getSession();  
        if (session != null && session.get(Constants.CURRENT_USER) != null){ 
        	
        	if(log.isDebugEnabled()){
    			log.debug("user exist in Session");
    		}
        	
            return invocation.invoke();  
        }  
          
        Cookie[] cookies = request.getCookies();  
        if (cookies!=null) {  
            for (Cookie cookie : cookies) {  
                if (Constants.COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {  
                    String value = cookie.getValue();  
                    if (StringUtils.isNotBlank(value)) {  
                        String[] split = value.split("==");  
                        String userId = split[0];  
                        String password = split[1];  
                        try {  
                            User user = userManager.getById(new Integer(userId));
                            if(user!=null && user.getPassword().equals(password)){
                            	//session.put(Constants.CURRENT_USER, user);
                            	initCurrentUserInfo(session,user);
                            	
                            	if(log.isDebugEnabled()){
                        			log.debug("user exist in Cookie");
                        		}
                            	 
                            }
                            else{
                            	setGoingToURL(session, invocation);
                            	return Action.LOGIN;
                            }
                              
                        } catch (Exception e) {  
                        	setGoingToURL(session, invocation);  
                        	log.warn("验证 cookie信息出错");
                            return Action.LOGIN;  
                        }  
                    } else {  
                        setGoingToURL(session, invocation); 
                        log.warn("验证 cookie信息为空");
                        return Action.LOGIN;  
                    }  
                    
                    return invocation.invoke();  
                }  
            }  
        }  
        setGoingToURL(session, invocation);
        log.info("没有存在的用户信息");
        return Action.LOGIN;  
	}

	
	@SuppressWarnings("deprecation")
	private void setGoingToURL(Map session, ActionInvocation invocation){ 
		
		String extension="do";
		
		try{
		if(DefaultSettings.isSet("struts.action.extension")){
			extension=DefaultSettings.get("struts.action.extension");
						
		}
		}catch(Exception e){
			extension="do";
		}
       
        String url = "";  
        String namespace = invocation.getProxy().getNamespace();  
        if (StringUtils.isNotBlank(namespace) && !namespace.equals("/")){  
            url = url + namespace;  
        }  
        String actionName = invocation.getProxy().getActionName();  
        if (StringUtils.isNotBlank(actionName)){  
            url = url + "/" + actionName;  
        } 
        /*
          String methodName = invocation.getProxy().getMethod();  

        if (StringUtils.isNotBlank(methodName)){  
            url = url + "!" + methodName ;  
        }
        */
        url=url+"."+extension;
        
        session.put(Constants.FORWARD_TO_URL, url);  
    }  
	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**登录成功,将用户相关信息放入Session
	 * @param logUser 当前登录用户
	 */
	private void initCurrentUserInfo(Map session,User logUser) {
	    //current user 
		   session.put(Constants.CURRENT_USER, logUser);
		   session.put(Constants.LOGIN_USERNAME, logUser.getRealName());
		   session.put(Constants.LOGIN_USERID, logUser.getUserid());
		   
		   log.debug(logUser.getUserRoles());
		   /*
		    * 当前用户当前所有角色信息
		    */
		   List allRoles=userManager.getUserRoles(logUser);
		   session.put(Constants.CURRENT_USER_ROLES, allRoles);
		   
		   
		   /* 所有用户模块   */
		   Set<Module> allModules=new HashSet<Module>();
		   List<Module> allModuleList=(List<Module>)userManager.getUserModules(logUser);
		   for(Module m:allModuleList){
			   allModules.add(m);
		   }
		   session.put(Constants.CURRENT_USER_MODULES,allModules);
		   
		   /* 所有用户权限   */
		   List allPermissions=userManager.getUserPermissions(logUser);
		   session.put(Constants.CURRENT_USER_PERMISSIONS,allPermissions);
				   
       
	}
	
}
