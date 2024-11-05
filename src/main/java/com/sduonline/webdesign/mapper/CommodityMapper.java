package com.sduonline.webdesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sduonline.webdesign.data.po.Commodity;
import com.sduonline.webdesign.data.po.CommodityPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    //除了特殊注明以外，获取商品的同时连表查询商品的图片url
    //需要手写mapper.xml文件手动将字段映射到CommodityPicture对象中
    //首页中展示的所有商品即使他有多个图片,在返回值时只返回第一张图片


    //根据userid获得正在出售商品

    //根据userid获得已出售商品

    //根据userid获得交易中的商品

    //根据商品id获得商品详细信息，包括所有图片

    //获得最新的六个食品商品

    //获得最新的六个商品

    //

}
