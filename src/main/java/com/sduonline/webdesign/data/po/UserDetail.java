package com.sduonline.webdesign.data.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_detail")
public class UserDetail {
    private int id;
    private int user_id;
    private int selling;
    private int bought;
    private int sold;
    private int to_receive;
    private int order;
}
