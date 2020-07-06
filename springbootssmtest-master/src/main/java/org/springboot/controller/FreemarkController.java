package org.springboot.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ClassName FreemarkController
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/19 15:18
 * @Version 1.0
 */
@Controller
@RequestMapping("api/freemark")
public class FreemarkController {

    @RequestMapping("/index")
    public String index(Model model) {
        Map map = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put("key" + i, "value" + i);
        }
        model.addAttribute("list", Arrays.asList("string1", "string2", "string3", "string4", "string5", "string6"));
        model.addAttribute("map", map);
        model.addAttribute("name", "   htTps://wWw.zHyD.mE   ");
        model.addAttribute("htmlText", "<span style=\"color: red;font-size: 16px;\">html内容</span>");
        model.addAttribute("num", 123.012);
        model.addAttribute("null", "2");
        model.addAttribute("dateObj", new Date());
        model.addAttribute("bol", true);
        return "index";
    }
}
