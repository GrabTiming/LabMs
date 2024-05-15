package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddRepairApplicationDto;
import com.Lnn.domain.dto.PageResult;
import com.Lnn.domain.dto.UpdateRepairApplicationDto;
import com.Lnn.domain.entity.User;
import com.Lnn.domain.vo.RepairApplicationVO;
import com.Lnn.service.TermLabService;
import com.Lnn.service.TermService;
import com.Lnn.util.BeanCopyUtil;
import com.Lnn.util.LoginUserUtil;
import com.Lnn.util.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.RepairApplicationMapper;
import com.Lnn.domain.entity.RepairApplication;
import com.Lnn.service.RepairApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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


    @Autowired
    private TermService termService;

    @Override
    public Result add(AddRepairApplicationDto addRepairApplicationDto) {

        RepairApplication repairApplication = BeanCopyUtil.copyBean(addRepairApplicationDto,RepairApplication.class);
        repairApplication.setState(0); //状态设为未维修

        //设置当前用户的账号id和姓名
        User user =LoginUserUtil.getUser();
        repairApplication.setTeacherId(user.getId());
        repairApplication.setTeacherName(user.getUsername());

        //设置当前学期
        repairApplication.setTerm(termService.getNowTerm());
        repairApplication.setCreateTime(new Date());

        repairApplicationMapper.insert(repairApplication);

        return Result.ok("报修申请已提交",null);
    }


    //查询 教师 提交的 报修申请
    @Override
    public Result getRepairAppByTeacherId(Integer pageNum,Integer pageSize) {

        LambdaQueryWrapper<RepairApplication> queryWrapper = new LambdaQueryWrapper<>();

        //当前用户id
        Integer teacherId = LoginUserUtil.getUser().getId();

        //查询条件：teacher_id为当前用户id
        queryWrapper.eq(RepairApplication::getTeacherId,teacherId);
        Page<RepairApplication> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        PageResult pageResult = new PageResult(page.getRecords(),pageNum,pageSize,page.getTotal());

        return Result.ok(pageResult);

    }

    //实验员查看自己管理的实验室的 报修申请
    @Override
    public Result getLabRepair(Integer pageNum,Integer pageSize) {
         //实验室+ 报修申请
        Integer teacherId = LoginUserUtil.getUser().getId();

        //TODO 如何进行连表分页
        List<RepairApplication> list = repairApplicationMapper.getByLabAdmin(teacherId,pageNum,pageSize);

         PageResult pageResult = new PageResult(list,pageNum-1,pageSize, (long) list.size());
//
         return Result.ok(pageResult);
    }

    //实验员 修改 报修申请的 状态 ，并且修改维修 说明
    @Override
    public Result updateRepair(UpdateRepairApplicationDto updateRepairApplicationDto) {

        RepairApplication repairApplication = repairApplicationMapper.selectById(updateRepairApplicationDto.getId());
        repairApplication.setState(updateRepairApplicationDto.getState());
        repairApplication.setDescription(updateRepairApplicationDto.getDescription());

        repairApplicationMapper.updateById(repairApplication);
        return Result.ok("修改成功",null);
    }



}

