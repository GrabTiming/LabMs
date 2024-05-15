package com.Lnn.controller;


import com.Lnn.annotation.RoleAuthorize;
import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ResetPwdDTO;
import com.Lnn.domain.dto.SelectNameDto;
import com.Lnn.domain.dto.UserLoginDTO;
import com.Lnn.domain.dto.UserRegisterDTO;
import com.Lnn.service.UserService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @RoleAuthorize(value = {"1"})
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

    //TODO 批量导入 注册


    //查询一类用户（1学生，2老师，3实验员）
    @RoleAuthorize(value = {"0"})
    @GetMapping("/select")
    public Result selectByCategory(@Range(min=1,max=3) Integer role,
                                   Integer pageNum,Integer pageSize)
    {
        return userService.selectByCategory(role,pageNum,pageSize);
    }


    //根据姓名、身份查询用户
    @PostMapping("/selectByName")
    public Result selectByName(@RequestBody SelectNameDto selectNameDto)
    {
        return userService.selectByName(selectNameDto);
    }

    //重置密码
    @PostMapping("/resetPwd")
    public Result resetPwd(@RequestBody ResetPwdDTO resetPwdDTO)
    {
        return userService.resetPwd(resetPwdDTO);
    }


}
