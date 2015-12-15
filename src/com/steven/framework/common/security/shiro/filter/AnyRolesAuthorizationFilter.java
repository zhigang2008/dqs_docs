package com.steven.framework.common.security.shiro.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;



public class AnyRolesAuthorizationFilter extends AuthorizationFilter {
	private final Log log = LogFactory.getLog(getClass());

    /* (non-Javadoc)
     * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
     */
    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }
        
        Set<String> roles = new HashSet<String>();;
        for(int i=0;i<rolesArray.length;i++){
        	String[] tempRoles=rolesArray[i].split(";");
        	for(String temp:tempRoles){
        		roles.add(temp);
        	}
        }
        //校验角色
        for(String role:roles){
        	if(log.isDebugEnabled()){
        	   log.debug("role="+role);
        	}
        	
        	if(subject.hasRole(role)){
        		return true;
        	}
        }
        return false;
    }

}
