package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.CourseApplicationDto;
import com.Lnn.domain.dto.CourseApplicationUpdateDto;
import com.Lnn.domain.dto.PageResult;
import com.Lnn.domain.entity.User;
import com.Lnn.domain.vo.CourseApplicationVO;
import com.Lnn.service.TermLabService;
import com.Lnn.service.TermService;
import com.Lnn.util.BeanCopyUtil;
import com.Lnn.util.LoginUserUtil;
import com.Lnn.util.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.CourseApplicationMapper;
import com.Lnn.domain.entity.CourseApplication;
import com.Lnn.service.CourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (CourseApplication)表服务实现类
 *
 * @author Liang2003
 * @since 2024-04-25 22:15:14
 */
@Service("courseApplicationService")
public class CourseApplicationServiceImpl extends ServiceImpl<CourseApplicationMapper, CourseApplication> implements CourseApplicationService {

    @Autowired
    private CourseApplicationMapper courseApplicationMapper;

    @Autowired
    private TermService termService;


    @Override
    public Result insert(CourseApplicationDto courseApplicationDto) {

        CourseApplication courseApplication = BeanCopyUtil.copyBean(courseApplicationDto,CourseApplication.class);
        courseApplication.setState(0);//设置状态 未排课
        User user = LoginUserUtil.getUser();

        courseApplication.setTeacherId(user.getId());
        courseApplication.setTeacherName(user.getUsername());

        courseApplication.setTerm(termService.getNowTerm());

        courseApplicationMapper.insert(courseApplication);

        return Result.ok("插入成功",null);

    }

    //教师查看自己的排课申请
    @Override
    public Result getCourseByTeacherId(Integer pageNum,Integer pageSize) {

        Integer teacherId = LoginUserUtil.getUser().getId();

        LambdaQueryWrapper<CourseApplication> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseApplication::getTeacherId,teacherId);

        Page<CourseApplication> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        List<CourseApplicationVO> result = BeanCopyUtil.copyBeanList(page.getRecords(), CourseApplicationVO.class);

        return  Result.ok(result);
    }



    //修改未排课的申请
    @Override
    public Result modify(CourseApplicationUpdateDto courseApplicationUpdateDto) {

        CourseApplication courseApplication = BeanCopyUtil.copyBean(courseApplicationUpdateDto, CourseApplication.class);

        courseApplicationMapper.updateById(courseApplication);

        return Result.ok("修改成功",null);
    }


    //管理员查看所有排课申请

    @Override
    public Result getAll(Integer pageNum,Integer pageSize) {

        Page<CourseApplication> page = new Page<>(pageNum,pageSize);
        page(page,null);

        List<CourseApplicationVO> list = BeanCopyUtil.copyBeanList(page.getRecords(), CourseApplicationVO.class);
        PageResult pageResult = new PageResult(list,pageNum,pageSize,page.getTotal());
        return Result.ok(pageResult);
    }
}

