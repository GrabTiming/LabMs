package com.Lnn.interceptor;

import com.Lnn.constants.HttpConstants;
import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.entity.User;
import com.Lnn.mapper.UserMapper;
import com.Lnn.util.JwtUtil;
import com.Lnn.util.LoginUserUtil;
import com.Lnn.util.RedisCache;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token =  request.getHeader("token");
        try {
            System.out.println(token);
            if(token==null)
            {
                log.info("token为空");
                throw new RuntimeException("请先登录");
            }

            String userId = JwtUtil.parseJWT(token).getSubject();
            String key = SystemConstants.LOGIN_KEY_PREFIX+ userId;
            String RedisToken = redisCache.getCacheObject(key);

            if(RedisToken==null) {
                throw new RuntimeException("登录过期,请重新登录");
            }
            String s = SystemConstants.LOGIN_VALUE_PREFIX+token;
            if(!s.equals(RedisToken)) {
                throw new RuntimeException("非法操作");
            }

            //登录成功，刷新时间
            //redisCache.expire(key,6, TimeUnit.HOURS);
            User user = userMapper.selectById(Integer.valueOf(userId));
            System.out.println("登录拦截器 : "+user);
            LoginUserUtil.setUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录失败");
            response.setStatus(HttpConstants.NEED_LOGIN.getCode());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        LoginUserUtil.removeUser();

    }
}
