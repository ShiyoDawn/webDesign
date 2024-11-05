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
    private Integer id;
    private Integer user_id;
    private Integer selling;
    private Integer bought;
    private Integer sold;
    private Integer to_receive;
    private Integer order;
}
