package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.entity.Schedule;
import com.Lnn.service.TermLabService;
import com.Lnn.service.TermService;
import com.Lnn.util.RedisCache;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public String getNowTerm() {

        Schedule schedule = JSON.parseObject(redisCache.getCacheObject(SystemConstants.NOW_TERM),Schedule.class);
        return schedule.getTerm();
    }

    @Override
    public Date getBeginTime() {
        Schedule schedule = JSON.parseObject(redisCache.getCacheObject(SystemConstants.NOW_TERM),Schedule.class);
        return schedule.getBeginTime();
    }
}
