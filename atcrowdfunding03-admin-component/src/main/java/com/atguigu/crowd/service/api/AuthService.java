package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {

    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    public void saveRoleAuthRelationShip(Map<String, List<Integer>> map);

    public List<Auth> getAllAuth();

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
