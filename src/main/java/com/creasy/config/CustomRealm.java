package com.creasy.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CustomRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(CustomRealm.class);
    private static final Map<String, Set<String>> user_roles = new ConcurrentHashMap<>();
    private static final Map<String, String> users = new ConcurrentHashMap<>();


    public CustomRealm(){
        Set<String> adminroles = new HashSet<>();
        adminroles.add("admin");
        user_roles.put("root", adminroles);
        Set<String> normalroles = new HashSet<>();
        normalroles.add("normal");
        user_roles.put("user", normalroles);
        users.put("root", "e10adc3949ba59abbe56e057f20f883e");
        users.put("user", "e10adc3949ba59abbe56e057f20f883e");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);
        log.debug("doGetAuthorizationInfo username:{}", username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(user_roles.get(username));
        simpleAuthorizationInfo.addStringPermission("*");
        return simpleAuthorizationInfo;
    }

    //模拟从数据库中读取数据
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        log.debug("doGetAuthenticationInfo username:{}", username);
        String password = users.get(username);
        if (password == null) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }
        return new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
    }
}
