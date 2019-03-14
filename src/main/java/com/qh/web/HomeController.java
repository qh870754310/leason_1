package com.qh.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/6/15.
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        //加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        //return 模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }
}
