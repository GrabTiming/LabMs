package com.Lnn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.Lab;

import java.util.List;

/**
 * (Lab)表服务接口
 *
 * @author Liang2003
 * @since 2024-05-15 10:21:11
 */
public interface LabService extends IService<Lab> {

    List<Lab> getAllLab();

    List<Lab> getLab(String labType);
}

