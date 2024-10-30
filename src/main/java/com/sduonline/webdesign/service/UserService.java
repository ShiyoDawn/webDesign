package com.sduonline.webdesign.service;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sduonline.webdesign.data.po.User;
import com.sduonline.webdesign.data.vo.Result;
import com.sduonline.webdesign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Result getAllUser(){
        return new Result(200,userMapper.selectAll(),"fine");
    }
}
