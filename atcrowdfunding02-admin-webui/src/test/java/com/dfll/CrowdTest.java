package com.dfll;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testTx(){
        adminService.saveAdmin(new Admin(null,"jerry","jerry","jerry","jerry@163com","12345"));
    }

    @Test
    public void testInsert(){

        adminService.saveAdmin(new Admin(null, "root", "root", "root", "root@email", "123456"));

    }

    @Test
    public void testSelect(){
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andIdIsNotNull();

        List<Admin> admins = adminMapper.selectByExample(adminExample);

        for (Admin admin : admins) {
            System.out.println(admin.toString());
        }
    }

    @Test
    public void testConnection() throws SQLException {

        System.out.println(dataSource.getConnection());

    }

}
