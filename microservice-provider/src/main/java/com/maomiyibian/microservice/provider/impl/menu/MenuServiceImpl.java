package com.maomiyibian.microservice.provider.impl.menu;

import com.alibaba.dubbo.config.annotation.Service;
import com.maomiyibian.microservice.api.domain.menu.Menu;
import com.maomiyibian.microservice.api.service.menu.MenuService;

import java.util.List;

/**
 * TODO: 菜单接口实现类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:22
 */
@Service(
        version = "${service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class MenuServiceImpl implements MenuService {
    @Override
    public List<Menu> queryMenusByUserId(Long id) {
        return null;
    }
}
