package com.Lnn.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.SelectNameDto;
import com.Lnn.domain.dto.UserLoginDTO;
import com.Lnn.domain.dto.UserRegisterDTO;
import com.Lnn.domain.vo.StudentVO;
import com.Lnn.domain.vo.TeacherVO;
import com.Lnn.util.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.UserMapper;
import com.Lnn.domain.entity.User;
import com.Lnn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-17 19:49:40
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result register(UserRegisterDTO userRegisterDTO) {
        //TODO 注册 需管理员权限
        return null;
    }

    @Override
    public Result login(UserLoginDTO userLoginDTO) {


        if(userLoginDTO.getAccount()==null)
        {
            return Result.error(400,"用户名为空");
        }


        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,userLoginDTO.getAccount())
                    .eq(User::getRole,userLoginDTO.getRole());
        User user = getOne(queryWrapper);
        if(user==null)
        {
            return Result.error(402,"用户不存在");
        }

        if(!user.getPassword().equals(userLoginDTO.getPassword()))
        {
            return Result.error(401,"用户名错误或密码错误");
        }
        return Result.ok("登录成功",null);

    }

    @Override
    public Result modifyPwd(UserLoginDTO userLoginDTO) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,userLoginDTO.getAccount());

        User user = getOne(queryWrapper);

        user.setPassword(userLoginDTO.getPassword());

        this.updateById(user);
        return Result.ok("修改成功",null);
    }

    //根据类别查询
    @Override
    public Result selectByCategory(Integer role,Integer pageNum,Integer pageSize) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getRole,role);

        //分页
        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<User> users = page.getRecords();

        //学生
        if(role==1)
        {
            List<StudentVO> students = BeanCopyUtil.copyBeanList(users,StudentVO.class);
            return Result.ok(students);
        }
        else //老师
        {
            List<TeacherVO> teachers = BeanCopyUtil.copyBeanList(users,TeacherVO.class);
            return Result.ok(teachers);
        }

    }


    //根据姓名查询
    @Override
    public Result selectByName(SelectNameDto selectNameDto) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getUsername,selectNameDto.getUsername()+"%")
                .eq(User::getRole,selectNameDto.getRole());

        Page<User> page = new Page<>(selectNameDto.getPageNum(),selectNameDto.getPageSize());
        page(page,queryWrapper);
        //学生
        if(selectNameDto.getRole()==0)
        {
            List<User> list = page.getRecords();
            return Result.ok(BeanCopyUtil.copyBeanList(list,StudentVO.class));
        }
        //老师
        else
        {
            List<User> list = page.getRecords();
            return Result.ok(BeanCopyUtil.copyBeanList(list, TeacherVO.class));
        }
    }
}

