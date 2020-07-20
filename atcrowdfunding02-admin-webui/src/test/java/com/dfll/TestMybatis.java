package com.dfll;


import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestMybatis {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;


    //测试角色分页service
    @Test
    public void testPageService(){
        PageInfo<Role> pageInfo = roleService.getPageInfo(1, 6, "");

        System.out.println("分页信息如下" + pageInfo);
    }

    //根据关键字进行查询
    @Test
    public void testSelectByKeyword(){

        String keyword = "高级";

        List<Role> roles = roleMapper.selectByKeyword(keyword);

        for (Role role : roles) {
            System.out.println(role.toString());
        }
    }

    @Test
    public void testRoleInsert(){

        Role role = new Role();
        role.setName("高级工程师");

        int insert = roleMapper.insert(role);

        System.out.println("影响的行数：" + insert);
    }

    //向数据库中批量加入admin数据，用来测试
    @Test
    public void addRoles(){
        for (int i = 0; i < 100; i++){

            String val = "Roles" + i;

            Role role = new Role();
            role.setName(val);

            roleMapper.insert(role);
        }
    }

    //向数据库中批量加入admin数据，用来测试
    @Test
    public void addAdmins(){
        for (int i = 0; i < 100; i++){

            String val = "admins" + i;

            Admin admin = new Admin();
            admin.setLoginAcct(val);
            admin.setUserPswd(val);
            admin.setUserName(val);
            admin.setEmail(val);

            adminService.saveAdmin(admin);
        }
    }

    //根据关键字进行查询
    @Test
    public void selectByKeyword(){

        String keyword = "e";

        List<Admin> admins = adminMapper.selectAdminListByKeyword(keyword);

        System.out.println(admins);

        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    //测试分页查询信息
    @Test
    public void testPageInfo(){

        PageInfo adminPage = adminService.getAdminPage("r", 5, 5);

        System.out.println(adminPage.toString());
    }
}
