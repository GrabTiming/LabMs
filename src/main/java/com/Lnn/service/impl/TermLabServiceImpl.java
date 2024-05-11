package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ArrangeCourseLabDto;
import com.Lnn.domain.entity.CourseApplication;
import com.Lnn.domain.entity.LabCourse;
import com.Lnn.mapper.CourseApplicationMapper;
import com.Lnn.mapper.LabCourseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.TermLabMapper;
import com.Lnn.domain.entity.TermLab;
import com.Lnn.service.TermLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * (TermLab)表服务实现类
 *
 * @author Liang2003
 * @since 2024-05-09 13:16:30
 */
@Service("termLabService")
public class TermLabServiceImpl extends ServiceImpl<TermLabMapper, TermLab> implements TermLabService {


    @Autowired
    private LabCourseMapper labCourseMapper;

    @Autowired
    private CourseApplicationMapper courseApplicationMapper;

    @Autowired
    private TermLabMapper termLabMapper;

    @Override
    public Result arrange(LabCourse labCourse) {

        //判断 安排的申请是否与 实验室情况有冲突
        Integer labId = labCourse.getLabId();

        //获取该申请
        CourseApplication courseApplication = courseApplicationMapper.selectById(labCourse.getApplicationId());

        //通过下面四个变量获取 state中的占用的位置
        Integer startWeek = courseApplication.getStartWeek();
        Integer endWeek = courseApplication.getEndWeek();
        Integer sectionNum = SystemConstants.SECTION_NUM.get(courseApplication.getSection());
        Integer dayNum = SystemConstants.DAY_TO_NUM.get(courseApplication.getDay());

        System.out.println(courseApplication.getTerm());

        TermLab termLab = termLabMapper.get(courseApplication.getTerm(),labId);
        //如果没有记录，证明实验室从未被借用过
        if(termLab ==null)
        {
           String newState= modify(null,startWeek,endWeek,sectionNum,dayNum);
           termLab = new TermLab();
           termLab.setTerm(courseApplication.getTerm());
           termLab.setLabId(labId);
           termLab.setState(newState);
           termLabMapper.insert(termLab);
        }
        else
        {
            //不冲突就设置
            if(!conflict(termLab.getState(),startWeek,endWeek,sectionNum,dayNum))
            {
                String newState = modify(termLab.getTerm(),startWeek,endWeek,sectionNum,dayNum);
                termLab.setState(newState);
                termLabMapper.updateById(termLab);
            }
            //冲突
            else return Result.error(409,"实验室安排时间有冲突，请选择其他实验室");
        }
        //没冲突 就更新实验室情况，并且将该条记录插入表中
        labCourse.setTerm(courseApplication.getTerm());
        labCourseMapper.insert(labCourse);

        //将原申请的状态设置为 已处理
        courseApplication.setState(SystemConstants.PROCESSED);
        courseApplicationMapper.updateById(courseApplication);
        return Result.ok("安排成功",null);
    }


    //判断实验室的占用情况 是否 与申请的有冲突
    private boolean conflict(String oldState, Integer startWeek, Integer endWeek, Integer sectionNum, Integer dayNum) {
        char[] arr = oldState.toCharArray();
        if(arr==null) return false;
        int idx;
        for(int i=startWeek;i<=endWeek;i++)
        {
            idx = (i-1)*7*6 + dayNum+sectionNum;
            if(arr[idx]=='1') return true; //有冲突
        }
        return false;
    }

    private String modify(String oldState, Integer startWeek, Integer endWeek, Integer sectionNum, Integer dayNum) {

        char[] arr = null;
        if(oldState!=null) arr = oldState.toCharArray();
        if(arr==null) arr = new char[SystemConstants.LAB_STATE_LENGTH];
        Arrays.fill(arr,'0');
        int idx;
        for(int i=startWeek;i<=endWeek;i++)
        {
            idx = (i-1)*7*6 + dayNum+sectionNum;
            arr[idx]='1';
        }

        StringBuilder builder = new StringBuilder();
        builder.append(arr);
        return builder.toString();
    }
}

