package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.CourseApplicationDto;
import com.Lnn.domain.dto.CourseApplicationUpdateDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.CourseApplication;

/**
 * (CourseApplication)表服务接口
 *
 * @author Liang2003
 * @since 2024-04-25 22:15:14
 */
public interface CourseApplicationService extends IService<CourseApplication> {

    Result insert(CourseApplicationDto courseApplicationDto);

    Result getCourseByTeacherId(Integer teacherId,Integer pageNum,Integer pageSize);


    //修改未排课 的申请 的信息
    Result modify(CourseApplicationUpdateDto courseApplicationUpdateDto);

    Result getAll(Integer pageNum,Integer pageSize);
}

