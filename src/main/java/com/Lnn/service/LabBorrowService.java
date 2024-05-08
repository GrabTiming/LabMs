package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddLabBorrowDto;
import com.Lnn.domain.dto.UpdateLabBorrowDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.LabBorrow;

/**
 * (LabBorrow)表服务接口
 *
 * @author Liang2003
 * @since 2024-04-26 13:59:03
 */
public interface LabBorrowService extends IService<LabBorrow> {

    Result getLabBorrowByStudentId(Integer id);

    Result add(AddLabBorrowDto addLabBorrowDto);

    Result modifyLabBorrow(UpdateLabBorrowDto updateLabBorrowDto);
}

