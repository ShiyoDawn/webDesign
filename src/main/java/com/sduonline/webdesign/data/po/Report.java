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
    private int id;
    private int reporterId;
    private int reportedId;
    private int commodity_id;
    private String reason;
    //0为未审核 1为审核通过 2为审核不通过
    private int state;
    //1为商品举报 2为商家/买家举报
    private int type;
}
