package com.sduonline.webdesign.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sduonline.webdesign.data.po.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    //除了特殊注明以外，获取商品的同时连表查询商品的图片url
    @Select("")
    List<Commodity> selectCommodityById();

    //根据id获得正在出售商品

    //根据id获得已出售商品

    //获得最新的六个食品商品

    //获得最新的六个商品

    //

}
