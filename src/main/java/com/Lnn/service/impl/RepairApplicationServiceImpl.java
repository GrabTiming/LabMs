package com.Lnn.service.impl;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddRepairApplicationDto;
import com.Lnn.domain.vo.RepairApplicationVO;
import com.Lnn.util.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.RepairApplicationMapper;
import com.Lnn.domain.entity.RepairApplication;
import com.Lnn.service.RepairApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (RepairApplication)表服务实现类
 *
 * @author Liang2003
 * @since 2024-04-25 22:41:31
 */
@Service("repairApplicationService")
public class RepairApplicationServiceImpl extends ServiceImpl<RepairApplicationMapper, RepairApplication> implements RepairApplicationService {

    @Autowired
    private RepairApplicationMapper repairApplicationMapper;

    @Override
    public Result add(AddRepairApplicationDto addRepairApplicationDto) {

        RepairApplication repairApplication = BeanCopyUtil.copyBean(addRepairApplicationDto,RepairApplication.class);
        repairApplication.setState(0); //状态设为未维修
        repairApplicationMapper.insert(repairApplication);

        return Result.ok("报修申请已提交",null);
    }


    //查询 教师 提交的 报修申请
    @Override
    public Result getRepairAppByTeacherId(Integer id) {

        LambdaQueryWrapper<RepairApplication> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(RepairApplication::getTeacherId,id);

        List<RepairApplication> repairApplications = repairApplicationMapper.selectList(queryWrapper);

        List<RepairApplicationVO> result = BeanCopyUtil.copyBeanList(repairApplications, RepairApplicationVO.class);

        return Result.ok(result);

    }
}

