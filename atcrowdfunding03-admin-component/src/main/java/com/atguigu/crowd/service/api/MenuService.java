package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> getAll();

    public void insertMenu(Menu menu);

    public void updateMenu(Menu menu);

    public void deleteMenu(Integer id);
}
