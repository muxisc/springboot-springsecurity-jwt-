package com.maomiyibian.microservice.customer.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maomiyibian.microservice.api.domain.user.User;
import com.maomiyibian.microservice.api.service.user.UserService;
import com.maomiyibian.microservice.common.page.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(version = "${service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private UserService userService;


    @PostMapping("/login")
    @ResponseBody
    public User login(String username, String password)throws Exception{

        return userService.queryUserByName(username);
    }

    @PostMapping("/login/error")
    @ResponseBody
    public User loginError()throws Exception{

       /* return userService.queryUserByName(username);*/
        return null;
    }

    @GetMapping("/queryUserByPage")
    public Page<User> queryUserByPage(Map<String,Object> parameter) throws Exception{
        Page<User> userPage=new Page<>();
        userPage.setPageCurrent(1);
        userPage.setPageSize(30);
        Page<User> result=userService.queryUserByPage(parameter,userPage);
        return result;
    }
}
