package com.maomiyibian.microservice.provider.impl.permission;

import com.alibaba.dubbo.config.annotation.Service;
import com.maomiyibian.microservice.api.domain.permission.Permission;
import com.maomiyibian.microservice.api.service.permission.PermissionService;

import java.util.List;

/**
 * TODO: 权限接口实现类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:12
 */
@Service(
        version = "${service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> queryPermissionsByUserId(Long id) {
        //TODO 实现接口
        return null;
    }
}
