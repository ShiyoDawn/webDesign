package com.sduonline.webdesign.controller;

import com.sduonline.webdesign.data.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @PostMapping("login")
    public Result login(@RequestBody Map map){
        return new Result();
    }
}
