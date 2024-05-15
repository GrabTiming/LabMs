package com.Lnn.service;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddBorrowApplicationDto;
import com.Lnn.domain.dto.ArrangeBorrowDTO;
import com.Lnn.domain.dto.UpdateBorrowApplicationDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Lnn.domain.entity.BorrowApplication;

/**
 * (LabBorrow)表服务接口
 *
 * @author Liang2003
 * @since 2024-04-26 13:59:03
 */
public interface BorrowApplicationService extends IService<BorrowApplication> {

    Result getLabBorrowByStudentId(Integer id);

    Result add(AddBorrowApplicationDto addBorrowApplicationDto);

    Result modifyLabBorrow(UpdateBorrowApplicationDto updateBorrowApplicationDto);

    Result arrangeByBorrow(ArrangeBorrowDTO arrangeBorrowDTO);


    //管理员 查看所有借用申请
    Result getAll(Integer pageNum,Integer pageSize);

    Result getByStudent(Integer pageNum, Integer pageSize);

    Result complete(Integer id);
}

