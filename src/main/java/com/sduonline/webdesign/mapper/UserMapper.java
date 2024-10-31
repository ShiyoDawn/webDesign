package com.sduonline.webdesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sduonline.webdesign.data.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.SplittableRandom;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user")
    User selectAll();

    //只需确定是否存在
    @Select("")
    List<User> selectByStuId(String stuId);

    @Insert("")
    void insertNewUser(String stuId, String username, String qq);

    @Update("")
    void addAvatar(String url);

    @Update("")
    void addPayCode(String url);
    //从user表加载用户简介
    @Select("")
    List<User> selectProfileById(Integer id);
    //从userDetail加载用户主页信息
    @Select("")
    List<User> selectDetailById(Integer id);


    //以下为detail表sql

    @Insert("")
    void addDetail(Integer userId);

    @Update("")
    void addSelling();
    @Update("")
    void addBought();
    @Update("")
    void addSold();
    @Update("")
    void addToReceive();
    @Update("")
    void addOrder();

    @Update("")
    void reduceSelling();
    @Update("")
    void reduceBought();
    @Update("")
    void reduceSold();
    @Update("")
    void reduceToReceive();
    @Update("")
    void reduceOrder();
}
