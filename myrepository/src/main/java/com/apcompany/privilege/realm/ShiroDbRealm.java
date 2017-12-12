package com.apcompany.privilege.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.apcompany.privilege.pojo.User;
import com.apcompany.privilege.service.AccountService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private AccountService accountService;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		  String name = (String) getAvailablePrincipal(principals);  
	        List<String> roles = new ArrayList<String>();  
	        // 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);  
	        User user=accountService.getUserByname(name, ""); 
	        if (user.getName().equals(name)) {  
	        	user.getRoleList();
	        } else {  
	            throw new AuthorizationException();  
	        }  
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
	        // 增加角色  
	        info.addRoles(roles);  
	        return info;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
	    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
	    User user = accountService.getUserByname(token.getUsername(),String.valueOf(token.getPassword()));  
        if (user == null) {  
            throw new AuthorizationException();  
        }  
        SimpleAuthenticationInfo info = null;  
        if (user.getName().equals(token.getUsername())) {  
            info = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());  
        }  
        return info;  
	}


}
