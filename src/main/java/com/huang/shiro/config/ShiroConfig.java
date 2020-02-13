package com.huang.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangwx
 * @date 2020-02-03 12:12
 */

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //关联安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器

        /*
         * anno:无需认证
         * authc: 必须认证了才让问
         * user: 必须拥有记住我功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role： 拥有某个角色权限才能访问
         *
         * */
        //设置权限过滤链

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/shoppingcar/*", "authc");
        filterMap.put("/addGoodsToSc", "authc");
        filterMap.put("/pay", "authc");
        filterMap.put("/order/*", "authc");

        filterMap.put("/goods/add", "perms[seller]");
        filterMap.put("/addGoods", "perms[seller]");
//       filterMap.put("/user/add","perms[user:add]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登陆url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置无权限跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联自定义Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //自定义创建Realm类
    @Bean
    public UserRealm getUserRealm() {
        return new UserRealm();
    }
}
