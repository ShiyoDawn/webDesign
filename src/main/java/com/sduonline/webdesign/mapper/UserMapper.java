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
    @Select("select * from user where stu_id=#{stuId}")
    List<User> selectByStuId(String stuId);

    @Insert("insert into user (stu_id, username, qq) values ( #{stuId}), #{username}, #{username}")
    void insertNewUser(String stuId, String username, String qq);

    @Update("update user set avatar=#{url} where id=#{id}")
    void addAvatar(Integer id, String url);

    @Update("update user set pay_code=#{url} where id=#{id}")
    void addPayCode(Integer id, String url);
    //从user表加载用户简介
    @Select("select * from user where id=#{id}")
    List<User> selectProfileById(Integer id);
    //从userDetail加载用户主页信息
    @Select("select * from user_detail where id=#{id}")
    List<User> selectDetailById(Integer id);


    //以下为detail表sql

    @Insert("insert into user_detail (user_id) values ( #{userId})")
    void addDetail(Integer userId);

    @Update("update user_detail set selling=#{selling} where id=#{id}")
    void addSelling(Integer id,Integer selling);
    @Update("update user_detail set bought=#{bought} where id=#{id}")
    void addBought(Integer id,Integer bought);
    @Update("update user_detail set sold=#{sold} where id=#{id}")
    void addSold(Integer id,Integer sold);
    @Update("update user_detail set to_receive=#{toReceive} where id=#{id}")
    void addToReceive(Integer id,Integer toReceive);
    @Update("update user_detail set order=#{order} where id=#{id}")
    void addOrder(Integer id,Integer order);

    @Update("update user_detail set selling=#{selling} where id=#{id}")
    void reduceSelling(Integer id,Integer selling);
    @Update("update user_detail set bought=#{bought} where id=#{id}")
    void reduceBought(Integer id,Integer bought);
    @Update("update user_detail set sold=#{sold} where id=#{id}")
    void reduceSold(Integer id,Integer sold);
    @Update("update user_detail set to_receive=#{toReceive} where id=#{id}")
    void reduceToReceive(Integer id,Integer toReceive);
    @Update("update user_detail set order=#{order} where id=#{id}")
    void reduceOrder(Integer id,Integer order);
}
