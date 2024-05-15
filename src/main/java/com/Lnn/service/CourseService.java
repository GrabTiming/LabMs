package com.Lnn.service;

import com.Lnn.domain.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.Course;

/**
 * (Course)表服务接口
 *
 * @author Liang2003
 * @since 2024-05-15 22:01:16
 */
public interface CourseService extends IService<Course> {

     Result getAll();
}

