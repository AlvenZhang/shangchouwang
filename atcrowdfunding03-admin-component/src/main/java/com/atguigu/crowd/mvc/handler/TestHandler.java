package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.ResultEntity;
import com.atguigu.crowd.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/test/testAjax.json")
    public ResultEntity testAjax(@RequestBody Admin admin){
        Admin byid = adminService.getByid(admin.getId());

        ResultEntity<Admin> adminResultEntity = ResultEntity.successWithData(byid);

        return adminResultEntity;
    }

    @ResponseBody
    @RequestMapping(value = "/testAjax")
    public Admin testAjax1(){


        return new Admin();
    }

    @RequestMapping(value = "/test/ssm/html")
    public String testGetAll(ModelMap modelMap) throws LoginFailedException {

        List<Admin> admins = adminService.getAll();

        try {
            int a = 10/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        modelMap.put("admins",admins);

        return "success";

    }


    @RequestMapping(value = "/test/aaa/bb.html")
    public String testPage(){


        return "target";
    }
}
