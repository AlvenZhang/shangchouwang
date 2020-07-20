package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdConstant;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {

    @Autowired
    AdminService adminService;


    @RequestMapping("/admin/delete.html")
    public String deleteAdmin(

            @RequestParam(value = "id") Integer id,

            @RequestParam(value = "pageNum") Integer pageNum,

            @RequestParam(value = "keyword") String keyword
            ){

        adminService.deleteAdmin(id);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/update.html")
    public String updateAdmin(

            Admin admin,

            @RequestParam(value = "pageNum") Integer pageNum,

            @RequestParam(value = "keyword") String keyword
    ) {

        adminService.updateAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @RequestMapping("/admin/to/edit/page.html")
    public String editAdmin(

            @RequestParam(value = "adminId") Integer id,

            ModelMap modelMap
    ) {

        //通过id查询到响应的admin信息
        Admin admin = adminService.getAdminById(id);

        //将admin信息放入modelMap
        modelMap.put("admin",admin);

        return "admin-edit";
    }

    @RequestMapping("/save/admin.html")
    public String saveAdmin(Admin admin) {

        adminService.saveAdmin(admin);

        //重定向到分页页面，使用重定向，为了防止浏览器刷新重复提交表单
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/get/page.html")
    public String getAdminPage(
            //如果没有关键字，则关键字为空
            @RequestParam(value = "keyword", defaultValue = "") String keyword,

            //页码默认为1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,

            //页面大小默认为5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            ModelMap modelMap
    ) {

        PageInfo<Admin> adminPage = adminService.getAdminPage(keyword, pageNum, pageSize);

        modelMap.put("pageInfo", adminPage);

        return "admin-page";
    }


    @RequestMapping(value = "/admin/do/login.html")
    public String login(
            @RequestParam(value = "loginAcct") String loginAcct,
            @RequestParam(value = "userPswd") String userPswd,
            HttpSession session
    ) throws LoginFailedException {

        //调用Service验证账号密码是否正确
        Admin admin = adminService.getAdminByLoginAcc(loginAcct, userPswd);

        //如果正确将用户信息，放入session中
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

//        return "admin-main";

        //为了避免跳转到后台主页面再刷新浏览器导致重复提交登录表单，重定向到目的页面
        return "redirect:/admin/to/main/page.html";
    }


    @RequestMapping(value = "/admin/do/logout.html")
    public String logout(HttpSession session) {

        //强制session失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }

//    @RequestMapping(value = "/to/page/main")
//    public String logout1(HttpSession session){
//
//        //强制session失效
////        session.invalidate();
//        System.out.println("afdsafdsf++++++++++++++++++++++++++++++++++++++++++++++");
//
//        return "redirect:/admin/to/page/main";
//    }
}
