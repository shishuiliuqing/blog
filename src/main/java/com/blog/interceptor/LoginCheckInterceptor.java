package com.blog.interceptor;

import ch.qos.logback.core.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.blog.pojo.BaseUserInfo;
import com.blog.pojo.Result;
import com.blog.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求url：{}", request.getRequestURL());
        //测试关闭拦截器
        if (1 == 1)
            return true;
        //获取token
        String token = request.getHeader("token");
        //判断token是否为空
        if (StringUtil.isNullOrEmpty(token)) {
            log.info("令牌为空");
            Result error = Result.fail("NOT_LOGIN");
            String json = JSONObject.toJSONString(error);
            response.getWriter().write(json);
            return false;
        }

        //判断token是否正确
        try {
            Jws<Claims> jws = JWTUtil.parse(token);
            String id = jws.getPayload().get("id").toString();
            String username = (String) jws.getPayload().get("username").toString();
            BaseUserInfo.put("id", id);
            BaseUserInfo.put("username", username);
            log.info("放行");
            return true;
        } catch (Exception e) {
            log.info("令牌解析失败！");
            Result error = Result.fail("NOT_LOGIN");
            String json = JSONObject.toJSONString(error);
            response.getWriter().write(json);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
