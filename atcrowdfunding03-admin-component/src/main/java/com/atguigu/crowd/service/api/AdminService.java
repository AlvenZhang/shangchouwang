package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.exception.LoginFailedException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

    public void saveAdmin(Admin admin);

    public List<Admin> getAll();

    public Admin getByid(Integer id);

    public Admin getAdminByLoginAcc(String loginAcct, String userPswd) throws LoginFailedException;

    public PageInfo getAdminPage(String keyword, Integer pageNum, Integer pageSize);

    public Admin getAdminById(Integer id);

    public void updateAdmin(Admin admin);

    public void deleteAdmin(Integer id);

    public void saveRoleRelationShip(Integer adminId, List<Integer> roleIdList);

    public Admin getAdminByLoginAcct(String username);
}
