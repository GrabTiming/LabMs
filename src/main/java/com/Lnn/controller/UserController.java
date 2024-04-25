package com.Lnn.controller;


import com.Lnn.domain.Result;
import com.Lnn.domain.dto.SelectNameDto;
import com.Lnn.domain.dto.UserLoginDTO;
import com.Lnn.domain.dto.UserRegisterDTO;
import com.Lnn.service.UserService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Result hello()
    {
        return Result.ok("hello");
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO)
    {
        return userService.login(userLoginDTO);
    }

    //只有管理员能注册账号
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO)
    {
        return userService.register(userRegisterDTO);
    }


    //查询一类用户（1学生，2老师，3实验员）
    @GetMapping("/select/{role}")
    public Result selectByCategory(@PathVariable("role") @Range(min=1,max=3) Integer role)
    {
        return userService.selectByCategory(role);
    }


    //根据姓名、身份查询用户
    @PostMapping("/selectByName")
    public Result selectByName(SelectNameDto selectNameDto)
    {
        return userService.selectByName(selectNameDto);
    }



}
