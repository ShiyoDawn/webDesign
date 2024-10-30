package com.sduonline.webdesign.data.po;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
public class Message {
    private int id;
    private int senderId;
    private int receiver_id;
    private String test;
    private Date time;
}
