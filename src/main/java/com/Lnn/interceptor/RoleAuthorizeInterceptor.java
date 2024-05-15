package com.Lnn.interceptor;

import com.Lnn.annotation.RoleAuthorize;
import com.Lnn.domain.entity.User;
import com.Lnn.util.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * 权限校验的拦截器
 */
@Component
public class RoleAuthorizeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        //在方法上寻找注解（这里是反射）
        RoleAuthorize permission = handlerMethod.getMethodAnnotation(RoleAuthorize.class);
        if (permission == null) {
            //方法不存在则在类上寻找注解则在类上寻找注解
            permission = handlerMethod.getBeanType().getAnnotation(RoleAuthorize.class);
        }

        //如果没有添加权限注解则直接跳过允许访问
        if (permission == null) {
            System.out.println("方法无限制访问");
            return true;
        }

        User user = LoginUserUtil.getUser();
        String userRole = String.valueOf(user.getRole());

        for(String role : permission.value())
        {
            //当前用户的角色在权限内
            if(role.equals(userRole)) return true;
        }
        throw new AccessDeniedException("当前用户无访问权限");
    }
}
