package com.Lnn.service.impl;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddLabBorrowDto;
import com.Lnn.domain.dto.TimeSlot;
import com.Lnn.domain.dto.UpdateLabBorrowDto;
import com.Lnn.domain.vo.LabBorrowVO;
import com.Lnn.mapper.CourseApplicationMapper;
import com.Lnn.util.BeanCopyUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.LabBorrowMapper;
import com.Lnn.domain.entity.LabBorrow;
import com.Lnn.service.LabBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * (LabBorrow)表服务实现类
 *
 * @author Liang2003
 * @since 2024-04-26 13:59:04
 */
@Service("labBorrowService")
public class LabBorrowServiceImpl extends ServiceImpl<LabBorrowMapper, LabBorrow> implements LabBorrowService {

    @Autowired
    private LabBorrowMapper labBorrowMapper;

    @Autowired
    private CourseApplicationMapper courseApplicationMapper;

    @Override
    public Result getLabBorrowByStudentId(Integer id) {

        LambdaQueryWrapper<LabBorrow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LabBorrow::getStudentId,id);

        List<LabBorrow> labBorrows = labBorrowMapper.selectList(queryWrapper);

        List<LabBorrowVO> result = BeanCopyUtil.copyBeanList(labBorrows,LabBorrowVO.class);

        return Result.ok(result);
    }


    //插入申请
    @Override
    public Result add(AddLabBorrowDto addLabBorrowDto) {
        //先判断 申请是否和其他已审核的申请或排课冲突
        Integer labId = addLabBorrowDto.getLabId();
        boolean[] isFree = new boolean[21]; //假定一个学期二十周
        //借用实验室的占用时间段
        List<TimeSlot> labList =  labBorrowMapper.getTimeSlotByLabId(labId);

        //实验课 占用的时间段
        List<TimeSlot> courseList = courseApplicationMapper.getTimeSlotByLabId(labId);

        Integer startWeek = addLabBorrowDto.getStartWeek();
        Integer endWeek = addLabBorrowDto.getEndWeek();
        String section = addLabBorrowDto.getSection();

        for(TimeSlot ts:labList)
        {
            if(ts.getSection().equals(section))//节次相同再看看选的时间是否相交
            {
                boolean conflict =
                        (startWeek>=ts.getStartWeek()&& startWeek<= ts.getEndWeek())
                      ||(endWeek>= ts.getStartWeek()&&endWeek<= ts.getEndWeek());
                if(conflict)
                {
                    return Result.error(405,"时间冲突，请另选时间");
                }
            }
        }

        for(TimeSlot ts:courseList)
        {
            if(ts.getSection().equals(section))//节次相同再看看选的时间是否相交
            {
                boolean conflict =
                        (startWeek>=ts.getStartWeek()&& startWeek<= ts.getEndWeek())
                                ||(endWeek>= ts.getStartWeek()&&endWeek<= ts.getEndWeek());
                if(conflict)
                {
                    return Result.error(405,"时间冲突，请另选时间");
                }
            }
        }

        LabBorrow labBorrow = BeanCopyUtil.copyBean(addLabBorrowDto, LabBorrow.class);
        labBorrow.setCreateTime(LocalDateTime.now());
        labBorrow.setState(0);
        labBorrowMapper.insert(labBorrow);

        return Result.ok("申请已提交",null);
    }

    @Override
    public Result modifyLabBorrow(UpdateLabBorrowDto updateLabBorrowDto) {

        LabBorrow labBorrow = BeanCopyUtil.copyBean(updateLabBorrowDto, LabBorrow.class);

        labBorrowMapper.updateById(labBorrow);

        return Result.ok("修改成功",null);

    }
}

