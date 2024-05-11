package com.Lnn.service;

import com.Lnn.domain.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.Schedule;

/**
 * (Schedule)表服务接口
 *
 * @author Liang2003
 * @since 2024-05-09 15:28:22
 */
public interface ScheduleService extends IService<Schedule> {

    Result setNowTerm(Integer id);

    Result getAllTerm(Integer pageNum, Integer pageSize);
}

