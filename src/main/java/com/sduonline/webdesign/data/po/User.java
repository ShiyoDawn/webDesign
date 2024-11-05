package com.sduonline.webdesign.data.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.SplittableRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    private Integer id;
    private Integer stuId;
    private String username;
    private String phone;
    private String qq;
    private String avatar;
    private String payCode;
}
