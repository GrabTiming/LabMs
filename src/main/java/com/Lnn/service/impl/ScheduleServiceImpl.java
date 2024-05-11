package com.Lnn.service.impl;

import com.Lnn.domain.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.ScheduleMapper;
import com.Lnn.domain.entity.Schedule;
import com.Lnn.service.ScheduleService;
import org.springframework.stereotype.Service;

/**
 * (Schedule)表服务实现类
 *
 * @author Liang2003
 * @since 2024-05-09 15:28:22
 */
@Service("scheduleService")
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {


    //设置当前学期
    @Override
    public Result setNowTerm(Integer id) {

        //将原来的当前学期置为 0 TODO

        //修改当前学期为 指定的学期
        LambdaUpdateWrapper<Schedule> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Schedule::getId,id);
        updateWrapper.set(Schedule::getState,1);
        this.baseMapper.update(null,updateWrapper);
        return Result.ok("修改成功",null);
    }


    //查看所有的学期
    @Override
    public Result getAllTerm(Integer pageNum, Integer pageSize) {

        Page<Schedule> page = new Page<>(pageNum,pageSize);

        page(page,null);

        return Result.ok(page.getRecords());
    }
}

