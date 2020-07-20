package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.service.api.MenuService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuHandler {

    @Autowired
    private MenuService menuService;



    @ResponseBody
    @RequestMapping("/menu/remove.json")
    public ResultEntity removeMenu(@RequestParam(value = "id") Integer id){

        menuService.deleteMenu(id);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/update.json")
    public ResultEntity updateMenu(
            @RequestParam(value = "id") Integer id,

            @RequestParam(value = "name") String name,

            @RequestParam(value = "url") String url,

            @RequestParam(value = "icon") String icon
    ){

        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setUrl(url);
        menu.setIcon(icon);

        menuService.updateMenu(menu);


        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity saveMenu(
            @RequestParam(value = "pid") Integer pid,

            @RequestParam(value = "name") String name,

            @RequestParam(value = "url") String url,

            @RequestParam(value = "icon") String icon
    ){

        Menu menu = new Menu();
        menu.setPid(pid);
        menu.setName(name);
        menu.setUrl(url);
        menu.setIcon(icon);

        menuService.insertMenu(menu);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<Menu> getWholeMenuTree(){

        //查询全部信息
        List<Menu> allMenu = menuService.getAll();

        //创建根节点
        Menu root = null;

        //将菜单的id与Menu对象的对应关系，存储到map中
        Map<Integer, Menu> menuMap = new HashMap<>();

        //遍历allMenu，将所有的menu放入到map中
        for (Menu menu : allMenu) {

            Integer id = menu.getId();

            menuMap.put(id,menu);
        }

        //从allMenu中找到menu的从属关系
        for (Menu menu : allMenu) {

            //首先判断当前menu是不是根节点
            if (menu.getPid() == null){
                root = menu;
                root.setOpen(true);
                continue;
            }

            //如果不是根节点，则将找到它是哪个目录的子节点，并将其添加到其中
            Integer id = menu.getPid();
            Menu menuFather = menuMap.get(id);
            menuFather.getChildren().add(menu);
        }


        return ResultEntity.successWithData(root);
    }
}
