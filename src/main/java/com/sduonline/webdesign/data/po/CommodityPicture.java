package com.sduonline.webdesign.data.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityPicture implements Serializable {
    Integer id;
    Integer commodity_id;
    String url;

    Commodity commodity;
}
