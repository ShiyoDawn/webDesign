package com.sduonline.webdesign.service;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sduonline.webdesign.cache.IGlobalCache;
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
//    @Autowired
//    private IGlobalCache iGlobalCache;
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

        if(!isExisted(SDUId)){
             userMapper.insertNewUser(SDUId,username);
        }

        try{
            String refreshToken = JWTUtil.getTokenWithPayLoad(SDUId, JWTUtil.REFRESH_EXPIRE_TIME, REFRESH_SECRET_KEY);
            //TODO 实现redis后使用
            //iGlobalCache.set(SDUId, refreshToken, JWTUtil.REFRESH_EXPIRE_TIME);
            String token = JWTUtil.getTokenWithPayLoad( SDUId, JWTUtil.EXPIRE_TIME, JWTUtil.SECRET_KEY);

            Map<String, String> map = new HashMap<>();
            map.put("accessToken", token);
            map.put("refreshToken", refreshToken);
            System.out.println("用户" + SDUId + username + "登录成功");
            return Result.success(map, "登录成功");
        } catch (Exception e){
            return Result.error(400, e.getMessage());
        }
    }

    private boolean isExisted(String stuId){
        List<User> list = userMapper.selectByStuId(stuId);
        if(list.size() == 0)return false;
        else return true;
    }

/*    public Result refresh(String refreshToken) {
        try {
            DecodedJWT info = JWTUtil.getTokenInfo(refreshToken, JWTUtil.REFRESH_SECRET_KEY);
            String userName = info.getClaim("user_name").asString();
            String SDUId = info.getClaim("sdu_id").asString();
            String idType = info.getClaim("id_type").asString();
            String KSH = info.getClaim("ksh").asString();
            String role = info.getClaim("role").asString();

            if (Objects.equals(refreshToken, iGlobalCache.get(SDUId))) {
                String newAccessToken = JWTUtil.getTokenWithPayLoad(SDUId, JWTUtil.EXPIRE_TIME, JWTUtil.SECRET_KEY);
                Map<String, String> map = new HashMap<>();
                map.put("accessToken", newAccessToken);

                String msg;
                long timeLeft = iGlobalCache.getExpire(SDUId);
                if (timeLeft <= JWTUtil.REFRESH_POINT) {
                    String newRefreshToken = JWTUtil.getTokenWithPayLoad(SDUId,JWTUtil.REFRESH_EXPIRE_TIME, JWTUtil.REFRESH_SECRET_KEY);
                    iGlobalCache.set(SDUId, newRefreshToken, JWTUtil.REFRESH_EXPIRE_TIME);
                    map.put("refreshToken", newRefreshToken);
                    msg = "成功！已获取新accessToken和refreshToken";
                } else {
                    msg = "成功！已获取新accessToken";
                }
                return Result.complete(Constant.SUCCESS, map, msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Result.error(Constant.AUTHENTICATED_FAILED, "无有效RefreshToken");
    }
*/

    public Result refresh(String refreshToken) {
        try {
            DecodedJWT info = JWTUtil.getTokenInfo(refreshToken, REFRESH_SECRET_KEY);
            String SDUId = info.getClaim("user_id").asString();
            Date date = info.getExpiresAt();
            if (date.after(new Date())) {
                String newAccessToken = JWTUtil.getTokenWithPayLoad(SDUId, JWTUtil.EXPIRE_TIME, JWTUtil.SECRET_KEY);
                Map<String, String> map = new HashMap<>();
                map.put("accessToken", newAccessToken);
                String msg;
                msg = "成功！已获取新accessToken";
                return new Result(200, map, msg);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Result.error(400, "无有效RefreshToken");
    }



}
