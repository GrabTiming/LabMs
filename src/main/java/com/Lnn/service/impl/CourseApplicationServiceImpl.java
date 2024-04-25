package com.Lnn.service.impl;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.CourseApplicationDto;
import com.Lnn.domain.dto.CourseApplicationUpdateDto;
import com.Lnn.domain.vo.CourseApplicationVO;
import com.Lnn.util.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public Result insert(CourseApplicationDto courseApplicationDto) {

        CourseApplication courseApplication = BeanCopyUtil.copyBean(courseApplicationDto,CourseApplication.class);
        courseApplication.setState(0);//设置状态 未排课
        courseApplicationMapper.insert(courseApplication);

        return Result.ok("插入成功",null);

    }

    //教师查看自己的排课申请
    @Override
    public Result getCourseByTeacherId(Integer id) {

        LambdaQueryWrapper<CourseApplication> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(CourseApplication::getTeacherId,id);

        List<CourseApplication> courseApplications = courseApplicationMapper.selectList(queryWrapper);

        List<CourseApplicationVO> result = BeanCopyUtil.copyBeanList(courseApplications, CourseApplicationVO.class);

        return  Result.ok(result);
    }



    @Override
    public Result modify(CourseApplicationUpdateDto courseApplicationUpdateDto) {

        CourseApplication courseApplication = BeanCopyUtil.copyBean(courseApplicationUpdateDto, CourseApplication.class);

        courseApplicationMapper.updateById(courseApplication);

        return Result.ok("修改成功",null);
    }
}

