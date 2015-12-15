package com.steven.framework.common.security.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;



public class UrlAuthorizationFilter extends AuthorizationFilter {
	private final Log log = LogFactory.getLog(getClass());

    /* (non-Javadoc)
     * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURI();
        int lastPoint=url.lastIndexOf("/");
        String tempStr=url.substring(0,lastPoint);
        lastPoint=tempStr.lastIndexOf("/");
        tempStr=tempStr.substring(lastPoint+1);
        
        String permissionTag="view";
        if(mappedValue!=null){
            permissionTag=(String)mappedValue;
        }
        //判断是否有访问权限
        if(subject.isPermitted(tempStr+":"+permissionTag)){
        	return true;
        }
        
        return false;
    }

}
