package com.atguigu.crowd.service.Impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {

        List<Integer> roleIdList = authMapper.selectAssignedAuthIdByRoleId(roleId);

        return roleIdList;
    }

    @Override
    public void saveRoleAuthRelationShip(Map<String, List<Integer>> map) {

        //1、获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        //2、删除旧关联关系数据
        authMapper.deleteOldRelationShip(roleId);

        //3、获取authIdList
        List<Integer> authIdList = map.get("authIdArray");

        //4、判断authIdList是否有效
        if(authIdList != null && authIdList.size() > 0){
            authMapper.insertNewRelationShip(roleId,authIdList);
        }

    }

    @Override
    public List<Auth> getAllAuth() {

        AuthExample authExample = new AuthExample();
        AuthExample.Criteria criteria = authExample.createCriteria();
        criteria.andIdIsNotNull();

        List<Auth> authList = authMapper.selectByExample(authExample);

        return authList;
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {

        List<String> authNameList = authMapper.selectAssignedAuthNameByAdminId();
        return authNameList;
    }
}
