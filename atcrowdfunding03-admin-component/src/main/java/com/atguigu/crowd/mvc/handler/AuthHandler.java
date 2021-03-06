package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.service.api.AuthService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AuthHandler {

    @Autowired
    private AuthService authService;


    @ResponseBody
    @RequestMapping("/assgin/get/all/auth.json")
    public ResultEntity getAllAuth(){

        List<Auth> authList = authService.getAllAuth();

        return ResultEntity.successWithData(authList);
    }

    @ResponseBody
    @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity getAssignedAuthIdByRoleld(
            @RequestParam("roleId") Integer roleId
            ){

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(authIdList);
    }


    @ResponseBody
    @RequestMapping("/assign/do/role/assign/auth.json")
    public ResultEntity saveRoleAuthRelationShip(
            @RequestBody Map<String,List<Integer>> map
            ){

        authService.saveRoleAuthRelationShip(map);

        return ResultEntity.successWithoutData();
    }
}
