package com.moyan.com.moyan.example.springboot.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermission("query");
        simpleAuthorizationInfo.addStringPermission("add");
        simpleAuthorizationInfo.addStringPermission("update");
        simpleAuthorizationInfo.addStringPermission("delete");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(name, "123456", getName());
        return simpleAuthenticationInfo;
    }
}
