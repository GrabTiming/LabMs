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

    Result getRepairAppByTeacherId(Integer id);

    Result getLabRepair(Integer id);

    Result updateRepair(UpdateRepairApplicationDto updateRepairApplicationDto);
}

