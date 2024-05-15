package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.util.RedisCache;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.ScheduleMapper;
import com.Lnn.domain.entity.Schedule;
import com.Lnn.service.ScheduleService;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (Schedule)表服务实现类
 *
 * @author Liang2003
 * @since 2024-05-09 15:28:22
 */
@Service("scheduleService")
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {


    @Autowired
    private RedisCache redisCache;

    //设置当前学期
    @Override
    public Result setNowTerm(Integer id) {

        System.out.println(id);
        Schedule schedule = getById(id);
        Object json = JSON.toJSONString(schedule);
        System.out.println(schedule.toString());
        redisCache.setCacheObject(SystemConstants.NOW_TERM,json);
        String message = "修改成功,当前学期为 "+schedule.getTerm();

        return Result.ok(message,null);
    }


    //查看所有的学期
    @Override
    public Result getAllTerm(Integer pageNum, Integer pageSize) {

        Page<Schedule> page = new Page<>(pageNum,pageSize);

        page(page,null);

        return Result.ok(page.getRecords());
    }

    @Override
    public Result addTerm(String term, String beginTime) {

        if(isExist(term))
        {
            return Result.error(410,"该学期已存在");
        }

        Schedule schedule = new Schedule();
        schedule.setTerm(term);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date begin;
        try {
            begin =simpleDateFormat.parse(beginTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(begin);
        schedule.setBeginTime(begin);
        save(schedule);
        return Result.ok("添加成功",null);
    }

    private boolean isExist(String term) {
        LambdaQueryWrapper<Schedule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Schedule::getTerm,term);
        return this.baseMapper.selectCount(queryWrapper) > 0;
    }
}

