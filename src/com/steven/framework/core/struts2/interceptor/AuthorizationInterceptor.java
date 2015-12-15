/**
 * AuthenticationInterceptor.java
 * Steven
 * 2011-4-2
 */
package com.steven.framework.core.struts2.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.config.DefaultSettings;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.steven.Constants;
import com.steven.core.model.Permission;

/**
 * @author Steven
 *
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

	static Log log = LogFactory.getLog(AuthorizationInterceptor.class);

	/**
	 * 
	 */
	public AuthorizationInterceptor() {
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
        	
        	
        	String currentUrl=getCurrentURL(invocation);
        	String allMatchUrl=currentUrl.substring(0,currentUrl.lastIndexOf("/"))+"/_all";
        	if(log.isDebugEnabled()){
			log.debug("user exist in Session");
			log.debug("curentUrl="+currentUrl);
			log.debug("allMatchUrl="+allMatchUrl);
		}
        	List<Permission> allPermissions=(List<Permission>)session.get(Constants.CURRENT_USER_PERMISSIONS);
        	for(Permission p:allPermissions){
        	    log.debug("p="+p.getCode());
        	    if(currentUrl.equals(p.getCode().trim()) || allMatchUrl.equals(p.getCode().trim())){
        		return invocation.invoke();
        	    }
        	}
        	
             
        }  
        log.info("无权访问该地址");
        
        return "global_noPermission";  
     }
	
	
    private String getCurrentURL(ActionInvocation invocation) {

	String extension = "do";

	try {
	    if (DefaultSettings.isSet("struts.action.extension")) {
		extension = DefaultSettings.get("struts.action.extension");

	    }
	} catch (Exception e) {
	    extension = "do";
	}

	String url = "";
	String namespace = invocation.getProxy().getNamespace();
	if (StringUtils.isNotBlank(namespace) && !namespace.equals("/")) {
	    url = url + namespace;
	}
	String actionName = invocation.getProxy().getActionName();
	if (StringUtils.isNotBlank(actionName)) {
	    url = url + "/" + actionName;
	}
	/*
	 * String methodName = invocation.getProxy().getMethod();
	 * 
	 * if (StringUtils.isNotBlank(methodName)){ url = url + "!" + methodName
	 * ; }
	 */
	url = url + "." + extension;
	return url;
    }
	
}
