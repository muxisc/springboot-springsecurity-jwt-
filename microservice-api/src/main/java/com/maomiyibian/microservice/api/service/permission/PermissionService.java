package com.maomiyibian.microservice.api.service.permission;

import com.maomiyibian.microservice.api.domain.permission.Permission;
import java.util.List;

/**
 * TODO: 权限接口
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:12
 */
public interface PermissionService {

   List<Permission> queryPermissionsByUserId(Long id);
}
