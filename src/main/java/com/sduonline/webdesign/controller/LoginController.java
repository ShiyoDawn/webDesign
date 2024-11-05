package com.sduonline.webdesign.controller;

import com.sduonline.webdesign.data.vo.Result;
import com.sduonline.webdesign.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> map){
        return loginService.SDUIdentify(map.get("stuId"),map.get("password"));
    }
    @PostMapping("/refresh")
    public Result refresh(@RequestBody Map<String,String> map){
        return loginService.refresh(map.get("refreshToken"));
    }
}
