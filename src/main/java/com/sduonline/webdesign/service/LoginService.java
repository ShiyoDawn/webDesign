package com.sduonline.webdesign.service;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sduonline.webdesign.data.po.LoginDTO;
import com.sduonline.webdesign.data.po.User;
import com.sduonline.webdesign.data.vo.Result;
import com.sduonline.webdesign.mapper.UserMapper;
import com.sduonline.webdesign.util.JWTUtil;
import com.sduonline.webdesign.util.Regex;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sduonline.webdesign.util.JWTUtil.REFRESH_SECRET_KEY;

@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    public Result SDUIdentify(String SDUId, String password) {
        String baseURL = "https://pass.sdu.edu.cn/";
        String ticket, sTicket, validationResult;

        //第一次请求(POST)，获取ticket，认证失败返回403
        try {
            ticket = Unirest.post(baseURL + "cas/restlet/tickets").
                    body("username=" + SDUId + "&password=" + password).asString().getBody();
            if (!ticket.startsWith("TGT")) {
                throw new RuntimeException("Ticket wrong");
            }
        } catch (RuntimeException e) {
            return Result.error(401, "Authentication failed");
        }

        //第二次请求(POST)，提供服务类型，获取sTicket
        sTicket = Unirest.post(baseURL + "cas/restlet/tickets/" + ticket).
                body("service=https://service.sdu.edu.cn/tp_up/view?m=up").asString().getBody();

        //第三次请求(GET)，提供服务类型和对应的票据，获取用户信息
        validationResult = Unirest.get(baseURL + "cas/serviceValidate").
                queryString("ticket", sTicket).
                queryString("service", "https://service.sdu.edu.cn/tp_up/view?m=up").asString().getBody();

        //提取用户信息
        String username = Regex.getContext(validationResult, "cas:USER_NAME");
        String idType = Regex.getContext(validationResult, "cas:ID_TYPE");
        String KSH = Regex.getContext(validationResult, "cas:KSH");

//        if(!isExisted(SDUId)){
 //           userMapper.addUser(username,password,SDUId);
   //     }

        try{
            String refreshToken = JWTUtil.getTokenWithPayLoad(SDUId, JWTUtil.REFRESH_EXPIRE_TIME, REFRESH_SECRET_KEY);

            String token = JWTUtil.getTokenWithPayLoad( SDUId, JWTUtil.EXPIRE_TIME, JWTUtil.SECRET_KEY);

            Map<String, String> map = new HashMap<>();
            map.put("accessToken", token);
            map.put("refreshToken", refreshToken);
            System.out.println("app login succeed");
            return Result.success(map, "登陆成功");
        } catch (Exception e){
            return Result.error(400, e.getMessage());
        }
    }

    private boolean isExisted(String stuId){
        List<User> list = new ArrayList<>();
        if(list.size() == 0)return false;
        else return true;
    }




}
