package com.Lnn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Lnn.domain.entity.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Course)表数据库访问层
 *
 * @author Liang2003
 * @since 2024-05-15 22:01:15
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    
    
}

