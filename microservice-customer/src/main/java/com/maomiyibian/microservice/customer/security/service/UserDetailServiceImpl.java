package com.maomiyibian.microservice.customer.security.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maomiyibian.microservice.api.domain.menu.Menu;
import com.maomiyibian.microservice.api.domain.permission.Permission;
import com.maomiyibian.microservice.api.domain.user.User;
import com.maomiyibian.microservice.api.service.menu.MenuService;
import com.maomiyibian.microservice.api.service.permission.PermissionService;
import com.maomiyibian.microservice.api.service.user.UserService;
import com.maomiyibian.microservice.customer.security.authority.CustomGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/27 10:24
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private Logger logger= LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Reference(version = "${service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private UserService userService;


    @Reference(version = "${service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private PermissionService permissionService;


    @Reference(version = "${service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private MenuService menuService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("用户认证开始...........");
        //获取用户信息及角色
        User user =userService.queryUserByName(userName);
        if (StringUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户："+userName+"不存在，请检查");
        }
        //获取用户权限
        List<Permission> permissions =permissionService.queryPermissionsByUserId(user.getId());
        List<Menu> menus=menuService.queryMenusByUserId(user.getId());
        List<GrantedAuthority> authorities=new ArrayList<>();
        if (!StringUtils.isEmpty(user.getRoles())){
            authorities.add(new CustomGrantedAuthority(user.getRoles(),menus,permissions));
        }
        return  new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPwd() ,true,true,true,true,authorities);
    }
}
