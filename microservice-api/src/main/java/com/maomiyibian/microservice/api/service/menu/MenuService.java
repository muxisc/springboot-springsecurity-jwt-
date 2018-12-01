package com.maomiyibian.microservice.api.service.menu;

import com.maomiyibian.microservice.api.domain.menu.Menu;

import java.util.List;

/**
 * TODO: 菜单接口
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:17
 */
public interface MenuService {

    List<Menu> queryMenusByUserId(Long id);
}
