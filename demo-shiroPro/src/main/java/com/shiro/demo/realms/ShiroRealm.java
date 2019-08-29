package com.shiro.demo.realms;

import com.shiro.demo.entity.Permission;
import com.shiro.demo.entity.Role;
import com.shiro.demo.entity.User;
import com.shiro.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //User user = (User) principalCollection.getPrimaryPrincipal();
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findByUserName(primaryPrincipal);
        for (Role role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPresource());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.findByUserName(username);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(),
//                ByteSource.Util.bytes(user.getUsername()),
                this.getName());
        return simpleAuthenticationInfo;
    }
}
