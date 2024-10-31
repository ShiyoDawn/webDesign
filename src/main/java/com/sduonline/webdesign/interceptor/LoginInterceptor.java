package com.sduonline.webdesign.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.sduonline.webdesign.data.pojo.CurrentUser;
import com.sduonline.webdesign.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.sduonline.webdesign.util.JWTUtil.SECRET_KEY;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object obj, Exception err)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object obj, ModelAndView mav) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //获取请求头(Header)里"accessToken"内数据，即给前端的token
        String accessToken = request.getHeader("accessToken");

        response.setContentType("application/json;charset=UTF-8");

        try {
            //验证token
            JWTUtil.verify(accessToken, SECRET_KEY);
            //获取token的payload中提供的用户信息
            DecodedJWT info = JWTUtil.getTokenInfo(accessToken, SECRET_KEY);
            CurrentUser.setUSERId(info.getClaim("user_id").asString());
        } catch (Exception e) {
            response.sendError(400,"Request Denied");
            System.out.println("failed");
            return false;
        }
        return true;
    }
}
