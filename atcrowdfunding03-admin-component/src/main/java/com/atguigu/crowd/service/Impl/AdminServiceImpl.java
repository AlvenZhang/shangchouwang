package com.atguigu.crowd.service.Impl;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {

        //生成当前时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);

        //为密码加密
        String source = admin.getUserPswd();
//        String encoded = CrowdUtil.md5(source);
        String encoded = passwordEncoder.encode(source);

        admin.setUserPswd(encoded);

        //执行保存方法，可能会抛出异常（用户名相同）
        try {
            adminMapper.insert(admin);
        }catch (Exception e){

            if (e instanceof DuplicateKeyException){
                //用户名已存在，抛出异常
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }else {
                //为了不掩盖问题，当抛出的异常不是DuplicateKeyException时，继续向上抛出
                throw e;
            }

        }
    }

    public List<Admin> getAll() {
        List<Admin> admins = adminMapper.selectByExample(new AdminExample());

        return admins;
    }

    @Override
    public Admin getByid(Integer id) {

        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public Admin getAdminByLoginAcc(String loginAcct, String userPswd) throws LoginFailedException {

        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();

        criteria.andLoginAcctEqualTo(loginAcct);

        List<Admin> admins = adminMapper.selectByExample(adminExample);

        if (admins == null || admins.size() == 0){
            //登录失败查不到信息
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (admins.size() > 1){
            //用户信息不唯一，系统异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNQUE);
        }

        Admin admin = admins.get(0);

        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //将提交的用户密码加密
        String userPswdForm = CrowdUtil.md5(userPswd);

        //得到数据库中的admin信息，比对密码
        if (!Objects.equals(admin.getUserPswd(),userPswdForm)){
            //比较结果不一致，抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //比较结果一致，返回用户信息
        return admin;
    }

    public PageInfo getAdminPage(String keyword, Integer pageNum, Integer pageSize) {

        //1、开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        //2、查询admin数据
        List<Admin> admins = adminMapper.selectAdminListByKeyword(keyword);

        //打印admins全类名
        Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
        logger.debug("admins的全类名是" + admins.getClass().getName());


        //3、将admins封装到PageInfo中
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);

        return pageInfo;
    }

    @Override
    public Admin getAdminById(Integer id) {

        Admin admin = adminMapper.selectByPrimaryKey(id);

        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {

        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andIdEqualTo(admin.getId());

        adminMapper.updateByExampleSelective(admin,adminExample);
    }

    @Override
    public void deleteAdmin(Integer id) {

        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveRoleRelationShip(Integer adminId, List<Integer> roleIdList) {

        //删除旧的权限信息
        adminMapper.deleteOLdRelationship(adminId);

        //添加新的全新信息
        adminMapper.insertNewRelationship(adminId,roleIdList);
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {

        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(username);

        List<Admin> admins = adminMapper.selectByExample(adminExample);

        return admins.get(0);
    }
}














