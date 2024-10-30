package com.sduonline.webdesign.controller;

import com.sduonline.webdesign.data.vo.Result;
import com.sduonline.webdesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    UserService userService;

    @GetMapping("/1")
    Result test(){
        return userService.getAllUser();
    }
}
