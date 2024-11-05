package com.sduonline.webdesign.data.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("commodity")
public class Commodity {
    private Integer id;
    private String commodityName;
    private Date publishTime;
    private Integer category;
    private BigDecimal price;

    //0为正常出售 1为已被预购 2为已卖出
    private Integer state;
    private Integer sellerId;
    private String url;
}
