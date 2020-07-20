package com.dfll;

import ch.qos.logback.classic.joran.ReconfigureOnChangeTaskListener;
import com.atguigu.crowd.mapper.AuthMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class AuthMapperTest {

    @Autowired
    private AuthMapper authMapper;

    @Test
    public void countByExample() {
    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectAssignedAuthIdByRoleId() {

        Integer roleId = 1;

        List<Integer> integers = authMapper.selectAssignedAuthIdByRoleId(roleId);

//        System.out.println(integers);

        assert integers.size() == 0;
    }

    @Test
    public void deleteOldRelationShip(){

        Integer roleId = 1;

        authMapper.deleteOldRelationShip(roleId);
    }

    @Test
    public void insertNewRelationShip(){

        Integer roleId = 1;
        List<Integer> authIdList = new ArrayList<>();

        authIdList.add(1);
        authIdList.add(2);

        authMapper.insertNewRelationShip(roleId,authIdList);
    }
}

