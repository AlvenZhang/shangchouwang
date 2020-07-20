package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AssignHandler {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;


    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam(value = "adminId") Integer adminId,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "keyword",defaultValue = "") String keyword,

            @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList
    ){

        //删除旧的权限信息
        adminService.saveRoleRelationShip(adminId,roleIdList);

        //重定向到信息显示页面
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam(value = "adminId") Integer adminId,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "keyword") String keyword,

            ModelMap modelMap
    ){

        //查询已分配的权限
        List<Role> assignedRole = roleService.getAssignedRole(adminId);

        //查询未分配的权限
        List<Role> unAssignRole = roleService.getUnAssignedRole(adminId);

        //存入模型
        modelMap.addAttribute("assignedRole",assignedRole);
        modelMap.addAttribute("unAssignedRole",unAssignRole);

        return "assign-role";
    }
}
