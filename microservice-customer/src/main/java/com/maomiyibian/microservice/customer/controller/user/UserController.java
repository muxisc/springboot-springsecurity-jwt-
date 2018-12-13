package com.maomiyibian.microservice.customer.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.maomiyibian.microservice.api.domain.user.User;
import com.maomiyibian.microservice.api.service.user.UserService;
import com.maomiyibian.microservice.common.message.TradeMessages;
import com.maomiyibian.microservice.common.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO: 用户服务控制层
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Reference(version = "${service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880"
    )
    private UserService userService;


    /**
     * 尚未认证
     * @return
     * @throws Exception
     */
    @GetMapping("/unAuthorized")
    @ResponseBody
    public TradeMessages<String> oauthLogin()throws Exception{
        TradeMessages<String> messages=new TradeMessages<>();
        messages.setResultCode("401");
        messages.setResultMessage("尚未认证，请进行身份认证后再访问");
        messages.setData(null);
        return messages;
    }

    @RequestMapping("/follow")
    @ResponseBody
    public TradeMessages<String> follow()throws Exception{
        log.info("未被拦截请求...");
        return null;
    }

    /*@RequestMapping("/login")
    @ResponseBody
    public TradeMessages<String> login()throws Exception{

        return null;
    }*/

    @PostMapping("userRegister")
    @ResponseBody
    public TradeMessages<String> userRegister(User user){
         return userService.userRegister(user);
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
