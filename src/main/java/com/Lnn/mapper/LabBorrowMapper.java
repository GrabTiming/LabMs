package com.Lnn.mapper;

import com.Lnn.domain.dto.TimeSlot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Lnn.domain.entity.LabBorrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * (LabBorrow)表数据库访问层
 *
 * @author Liang2003
 * @since 2024-04-26 13:59:02
 */
@Mapper
public interface LabBorrowMapper extends BaseMapper<LabBorrow> {

    //已审核的申请的全部占用时间段
    @Select("select start_week,end_week,section " +
            "from lab_borrow " +
            "where lab_id = #{labId} and state=1")
    List<TimeSlot> getTimeSlotByLabId(Integer labId);
}

