package com.Lnn.mapper;

import com.Lnn.domain.entity.RepairApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (RepairApplication)表数据库访问层
 *
 * @since 2024-04-25 21:15:22
 */
@Mapper
public interface RepairApplicationMapper extends BaseMapper<RepairApplication> {


    //查看 实验室
    List<RepairApplication> getByLabAdmin(Integer teacherId,Integer offset,Integer pageSize);
}

