package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ArrangeCourseLabDto;
import com.Lnn.domain.dto.TermLabDTO;
import com.Lnn.domain.entity.*;
import com.Lnn.domain.vo.FreeLabVO;
import com.Lnn.mapper.CourseApplicationMapper;
import com.Lnn.mapper.LabCourseMapper;
import com.Lnn.mapper.LabMapper;
import com.Lnn.service.LabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.TermLabMapper;
import com.Lnn.service.TermLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private LabService labService;

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
                String newState = modify(termLab.getState(),startWeek,endWeek,sectionNum,dayNum);
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

    //实验室审核 借用
    @Override
    public boolean arrangeByBorrow(BorrowApplication borrowApplication) {

        Integer startWeek = borrowApplication.getStartWeek();
        Integer endWeek = borrowApplication.getEndWeek();
        Integer sectionNum = SystemConstants.SECTION_NUM.get(borrowApplication.getSection());
        Integer dayNum = SystemConstants.DAY_TO_NUM.get(borrowApplication.getDay());
        Integer labId = borrowApplication.getLabId();

        System.out.println(borrowApplication.getTerm());

        TermLab termLab = termLabMapper.get(borrowApplication.getTerm(),labId);
        //如果没有记录，证明实验室从未被借用过
        if(termLab ==null)
        {
            String newState= modify(null,startWeek,endWeek,sectionNum,dayNum);
            termLab = new TermLab();
            termLab.setTerm(borrowApplication.getTerm());
            termLab.setLabId(labId);
            termLab.setState(newState);
            termLabMapper.insert(termLab);
        }
        else
        {
            //不冲突就设置
            if(!conflict(termLab.getState(),startWeek,endWeek,sectionNum,dayNum))
            {
                String newState = modify(termLab.getState(),startWeek,endWeek,sectionNum,dayNum);
                termLab.setState(newState);
                termLabMapper.updateById(termLab);
            }
            //冲突
            else return false;
        }
        return true;
    }

    @Override
    public Result getFreeLab(Integer applicationId) {

        CourseApplication courseApplication = courseApplicationMapper.selectById(applicationId);

        Integer startWeek = courseApplication.getStartWeek();
        Integer endWeek = courseApplication.getEndWeek();
        Integer section = SystemConstants.SECTION_NUM.get(courseApplication.getSection());
        Integer day = SystemConstants.DAY_TO_NUM.get(courseApplication.getDay());

        //筛选出要求的实验室

        List<TermLabDTO> list = baseMapper.getAll(courseApplication.getLabType());

        List<FreeLabVO> freeLabs = new ArrayList<>();

        for(TermLabDTO termLabDTO :list)
        {

            if(!conflict(termLabDTO.getState(),startWeek,endWeek,section,day))
            {
                FreeLabVO freeLabVO = new FreeLabVO(termLabDTO.getLabId(),termLabDTO.getLabName());
                freeLabs.add(freeLabVO);
            }
        }
        //TODO 测试
        return Result.ok(freeLabs);
    }

    //判断实验室的占用情况 是否 与申请的有冲突
    private boolean conflict(String oldState, Integer startWeek, Integer endWeek, Integer sectionNum, Integer dayNum) {
        char[] arr = oldState.toCharArray();
        System.out.println(arr.length);
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
        System.out.println(arr.length);
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

