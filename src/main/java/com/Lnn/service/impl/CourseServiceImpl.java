package com.Lnn.service.impl;

import com.Lnn.domain.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.CourseMapper;
import com.Lnn.domain.entity.Course;
import com.Lnn.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * (Course)表服务实现类
 *
 * @author Liang2003
 * @since 2024-05-15 22:01:16
 */
@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public Result getAll() {

        return Result.ok(this.baseMapper.selectList(null));

    }
}

