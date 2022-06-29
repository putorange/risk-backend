package com.ietunnel.riskservice.projectbase.controller;

import com.ietunnel.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zxg
 * @Date:2021/12/15/19:59
 */
@RestController
@RequestMapping("/riskservice/user")
@CrossOrigin  //解决跨域
public class LoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
