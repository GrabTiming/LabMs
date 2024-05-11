package com.Lnn.mapper;

import com.Lnn.domain.dto.TimeSlot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Lnn.domain.entity.CourseApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * (CourseApplication)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-25 21:38:42
 */
@Mapper
public interface CourseApplicationMapper extends BaseMapper<CourseApplication> {

    List<TimeSlot> getTimeSlotByLabId(Integer labId);
}

