package com.sduonline.webdesign.data.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("report")
public class Report {
    private Integer id;
    private Integer reporterId;
    private Integer reportedId;
    private Integer commodity_id;
    private String reason;
    //0为未审核 1为审核通过 2为审核不通过
    private Integer state;
    //1为商品举报 2为商家/买家举报
    private Integer type;
}
