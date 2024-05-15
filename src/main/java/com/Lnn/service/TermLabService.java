package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ArrangeCourseLabDto;
import com.Lnn.domain.entity.BorrowApplication;
import com.Lnn.domain.entity.LabBorrow;
import com.Lnn.domain.entity.LabCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.TermLab;

/**
 * (TermLab)表服务接口
 *
 * @author Liang2003
 * @since 2024-05-09 13:16:30
 */
public interface TermLabService extends IService<TermLab> {


    Result arrange(LabCourse labCourse);

    boolean arrangeByBorrow(BorrowApplication borrowApplication);

    Result getFreeLab(Integer applicationId);
}

