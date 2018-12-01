package com.maomiyibian.microservice.provider.impl.permission;

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
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> queryPermissionsByUserId(Long id) {
        return null;
    }
}
