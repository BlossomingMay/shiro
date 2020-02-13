package com.huang.shiro.config;

import com.huang.shiro.pojo.User;
import com.huang.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangwx
 * @date 2020-02-03 12:13
 */

//自定义用户权限
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作============");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前的User信息
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        //从数据库获取权限，添加权限给相对应的用户
        info.addStringPermission(currentUser.getRole());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证操作============");

        //从数据库获取账号密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.getUserByUsername(usernamePasswordToken.getUsername());
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
