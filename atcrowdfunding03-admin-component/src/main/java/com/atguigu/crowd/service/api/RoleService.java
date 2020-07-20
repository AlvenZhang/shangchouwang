package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    public void addRole(String name);

    public void updateRole(Integer id, String name);

    public void deleteRole(Integer[] empIdArray);

    public List<Role> getAssignedRole(Integer adminId);

    public List<Role> getUnAssignedRole(Integer adminId);



}
