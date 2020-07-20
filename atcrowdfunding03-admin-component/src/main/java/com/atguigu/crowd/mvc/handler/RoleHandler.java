package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/remove/by/role/id/array.json")
    public ResultEntity deleteRole(@RequestBody Integer[] empIdArray){

        roleService.deleteRole(empIdArray);

        ResultEntity<Object> resultEntity = ResultEntity.successWithoutData();
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity updateRole(
            @RequestParam(value = "id") Integer id,

            @RequestParam(value = "name") String name
    ){

        roleService.updateRole(id,name);

        ResultEntity<Object> resultEntity = ResultEntity.successWithoutData();
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity addRole(@RequestParam(value = "roleName") String name){

        roleService.addRole(name);

        ResultEntity<Object> resultEntity = ResultEntity.successWithoutData();
        return resultEntity;
    }

    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(

            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,

            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,

            @RequestParam(value = "keyword",defaultValue = "") String keyword
    ){

        //查询分页信息
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum,pageSize,keyword);

        ResultEntity<PageInfo<Role>> resultEntity = ResultEntity.successWithData(pageInfo);
        return resultEntity;
    }
}
