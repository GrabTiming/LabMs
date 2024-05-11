package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddRepairApplicationDto;
import com.Lnn.domain.dto.UpdateRepairApplicationDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.RepairApplication;

/**
 * (RepairApplication)表服务接口
 *
 * @author Liang2003
 * @since 2024-04-25 22:41:31
 */
public interface RepairApplicationService extends IService<RepairApplication> {

    Result add(AddRepairApplicationDto addRepairApplicationDto);

    //教师查询 报修申请
    Result getRepairAppByTeacherId(Integer teacherId,Integer pageNum,Integer pageSize);

    //实验员查询 自己管的实验室的报修申请
    Result getLabRepair(Integer teacherId,Integer pageNum,Integer pageSize);

    Result updateRepair(UpdateRepairApplicationDto updateRepairApplicationDto);
}

