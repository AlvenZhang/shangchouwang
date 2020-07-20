package com.atguigu.crowd.service.Impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {

        //开启分页
        PageHelper.startPage(pageNum,pageSize);

        List<Role> roles = roleMapper.selectByKeyword(keyword);

        PageInfo<Role> pageInfo = new PageInfo<>(roles);

        return pageInfo;
    }

    @Override
    public void addRole(String name) {

        Role role = new Role();
        role.setName(name);

        roleMapper.insertSelective(role);
    }

    @Override
    public void updateRole(Integer id, String name) {

        Role role = new Role();
        role.setId(id);
        role.setName(name);

        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void deleteRole(Integer[] empIdArray) {

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(empIdArray));

        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(arrayList);

        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {

        List<Role> roleList = roleMapper.selectAssignedRole(adminId);
        return roleList;
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {

        List<Role> roleList = roleMapper.selectUnAssignedRole(adminId);
        return roleList;
    }
}
