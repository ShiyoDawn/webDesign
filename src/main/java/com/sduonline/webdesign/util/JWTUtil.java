package com.sduonline.webdesign.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    public static String SECRET_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK0f97eWwqSDRj1sAMYTvHsGpeTMypot";
    public static final String REFRESH_SECRET_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJdOK+jsvg+fXUsqEtyszPs6Dwhzelvm";//refreshToken密钥

    public static final int EXPIRE_TIME = 30 * 24 * 3600;//Token过期时间
    public static final int REFRESH_EXPIRE_TIME = 30 * 24 * 3600;//RefreshToken过期时间

    //生成token
    public static String getToken(Map<String, String> map, int expireTime, String key) throws UnsupportedEncodingException {
        //获取时间，设置token过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, expireTime);

        //JWT添加payload
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);

        //JWT过期时间 + signature
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(key));
    }

    //验证token
    public static void verify(String token, String key) throws UnsupportedEncodingException {
        JWT.require(Algorithm.HMAC256(key)).build().verify(token);
    }

    //返回token内容
    public static DecodedJWT getTokenInfo(String token, String key) throws UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(key)).build().verify(token);
    }

    public static String getTokenWithPayLoad(String SDUId, int expireTime, String key) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SDUId);
        return JWTUtil.getToken(map, expireTime, key);
    }
}


