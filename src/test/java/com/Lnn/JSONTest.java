package com.Lnn;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.dto.TermLabDTO;
import com.Lnn.domain.entity.Schedule;
import com.Lnn.domain.entity.TermLab;
import com.Lnn.domain.entity.User;
import com.Lnn.mapper.TermLabMapper;
import com.Lnn.util.RedisCache;
import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JSONTest {


    @Autowired
    private TermLabMapper termLabMapper;

    @Autowired
    private RedisCache redisCache;

    @Test
    public void test1()
    {
        String o = redisCache.getCacheObject(SystemConstants.NOW_TERM);
        Schedule schedule = JSON.parseObject(o, Schedule.class);
        System.out.println(schedule.toString());
    }

    @Test
    public void test2()
    {
        List<TermLabDTO> list = termLabMapper.getAll("软件");

        for(TermLabDTO termLab:list)
        {
            System.out.println(termLab.toString());
        }

    }

    public static void main(String[] args) {

//        Schedule schedule =new Schedule(1,"2022-2023-2",);

//        Object json = JSON.toJSON(schedule);
//        System.out.println(json);



    }

}
