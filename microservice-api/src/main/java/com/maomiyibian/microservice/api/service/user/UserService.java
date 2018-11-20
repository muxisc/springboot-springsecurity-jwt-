package com.maomiyibian.microservice.api.service.user;

import com.maomiyibian.microservice.api.domain.user.User;
import com.maomiyibian.microservice.common.page.Page;

import java.util.Map;

/**
 * TODO: 用户Rpc接口
 *
 * @author junyunxiao
 * @date 2018-9-10 9:33
 */
public interface UserService {


    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    User queryUserByName(String userName);


    /**
     * 根据参数查询用户信息
     * @param parmeter
     * @return
     */
    User queryUser(Map<String,Object> parmeter);

    /**
     *
     * 根据参数查询用户列表并分页
     * @param parameter
     * @param parmeter
     * @return
     * @throws Exception
     */
    Page<User> queryUserByPage(Object parameter,Page parmeter) throws Exception;
}
