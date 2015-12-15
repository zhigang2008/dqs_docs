package com.steven.framework.common.security.shiro.realm;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.steven.core.model.Permission;
import com.steven.core.model.Role;
import com.steven.core.model.User;
import com.steven.core.service.UserManager;

/**实现shiro安全认证与框架结合的realm实现
 * @author Steven
 *
 */
@Component
public class SimpleRealm extends AuthorizingRealm {
	
    private final Log log = LogFactory.getLog(getClass());
	
    protected UserManager userManager = null;

    public SimpleRealm() {
        setName("SimpleRealm"); //This name must match the name in the User class's getPrincipals() method
        //setCredentialsMatcher(new Sha256CredentialsMatcher());
       
    }
    
    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        log.debug("remeberme="+token.isRememberMe());

        //查找用户
        User user = userManager.getByUserName(token.getUsername());
        if( user != null ) {
        	//判断用户是否禁用
        	if(user.getIsDisabled()==true){
        		throw new LockedAccountException("该用户已被禁用.请联系系统管理员.");
        	}
        	String sendPassword=new String(token.getPassword());
        	if(!sendPassword.equals(user.getPassword())){
        		if(log.isDebugEnabled()){
        		   log.debug(user.getPassword());
        		   log.debug(sendPassword);
        	    }
        		throw new  CredentialsException("用户名/密码不匹配!");
        	}
        	//初始化用户数据.设置session对象
        	
        	//返回认证对象
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else {
        	  log.warn("用户"+token.getUsername()+"不存在");
        	throw new UnknownAccountException("用户不存在!");
        }
    }


    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	User currentUser = (User) principals.fromRealm(getName()).iterator().next();
        	
        User user = userManager.getById(currentUser.getUserid());
        if( user != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            /*
 		    * 当前用户当前所有角色信息
 		    */
 		   List allRoles=userManager.getUserRoles(user);
 		   Role tempRole=null;
            for(int i=0;i<allRoles.size();i++ ) {
            	tempRole=(Role)allRoles.get(i);
            	info.addRole(tempRole.getRoleName());
            }
            /* 所有用户权限   */
 		   List allPermissions=userManager.getUserPermissions(user);
 		   Permission tempPermission=null;
 		   for(int p=0;p<allPermissions.size();p++){
 			  tempPermission=(Permission)allPermissions.get(p);
 			  info.addStringPermission(tempPermission.getCode());
 		   }
            return info;
        } else {
            return null;
        }
    }

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}

