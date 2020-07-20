package com.atguigu.crowd.service.Impl;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {

        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andIdIsNotNull();

        List<Menu> menus = menuMapper.selectByExample(menuExample);

        return menus;
    }

    @Override
    public void insertMenu(Menu menu) {

        menuMapper.insertSelective(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteMenu(Integer id) {

        menuMapper.deleteByPrimaryKey(id);
    }
}
