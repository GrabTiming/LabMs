package com.Lnn.config;

import com.Lnn.constants.HttpConstants;
import com.Lnn.constants.SystemConstants;
import com.Lnn.filter.CorsFilter;
import com.Lnn.interceptor.LoginInterceptor;
import com.Lnn.interceptor.RoleAuthorizeInterceptor;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RoleAuthorizeInterceptor roleAuthorizeInterceptor;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*") .
//                allowedMethods("GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE, PATCH") .
//                allowedHeaders("*") .
//                exposedHeaders(
//                        "Access-Control-Allow-Headers",
//                        "Access-Control-Allow-Methods",
//                        "Access-Control-Allow-Origin",
//                        "Access-Control-Max-Age",
//                        "X-Frame-Options","token, Content-Type") .allowCredentials(false)
//                .maxAge(3600);
//
//    }

    //    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 设置允许跨域的路径
//        registry.addMapping("/**")
//                // 设置允许跨域请求的域名
//                .allowedOriginPatterns("*")
//                // 是否允许cookie
//                .allowCredentials(true)
//                // 设置允许的请求方式
////                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedMethods("*")
//                // 设置允许的header属性
//                .allowedHeaders("*")
//                // 跨域允许时间
//                .maxAge(3600);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行哪些网址
        registry.addInterceptor(loginInterceptor)
                        .excludePathPatterns("/user/login","/course/getAll");
        registry.addInterceptor(roleAuthorizeInterceptor).excludePathPatterns("/user/login");
    }


}
