package com.Lnn.service.impl;

import com.Lnn.constants.SystemConstants;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.*;
import com.Lnn.domain.entity.Schedule;
import com.Lnn.domain.entity.User;
import com.Lnn.domain.vo.BorrowApplicationVO;
import com.Lnn.service.TermLabService;
import com.Lnn.service.TermService;
import com.Lnn.util.BeanCopyUtil;
import com.Lnn.util.LoginUserUtil;
import com.Lnn.util.RedisCache;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.BorrowApplicationMapper;
import com.Lnn.domain.entity.BorrowApplication;
import com.Lnn.service.BorrowApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * (LabBorrow)表服务实现类
 *
 * @author Liang2003
 * @since 2024-04-26 13:59:04
 */
@Service("labBorrowService")
public class BorrowApplicationServiceImpl extends ServiceImpl<BorrowApplicationMapper, BorrowApplication> implements BorrowApplicationService {

    @Autowired
    private BorrowApplicationMapper borrowApplicationMapper;

    @Autowired
    private TermService termService;

    @Autowired
    private TermLabService termLabService;

    @Override
    public Result getLabBorrowByStudentId(Integer id) {

        LambdaQueryWrapper<BorrowApplication> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowApplication::getStudentId,id);

        List<BorrowApplication> borrowApplications = borrowApplicationMapper.selectList(queryWrapper);

        List<BorrowApplicationVO> result = BeanCopyUtil.copyBeanList(borrowApplications, BorrowApplicationVO.class);

        return Result.ok(result);
    }


    //插入申请
    @Override
    public Result add(AddBorrowApplicationDto addBorrowApplicationDto) {

//        borrowApplicationMapper.insert(borrowApplication);
//
//        //先判断 申请是否和其他已审核的申请或排课冲突
//        Integer labId = addBorrowApplicationDto.getLabId();
//        boolean[] isFree = new boolean[21]; //假定一个学期二十周
//        //借用实验室的占用时间段
//        List<TimeSlot> labList =  borrowApplicationMapper.getTimeSlotByLabId(labId);
//
//        //实验课 占用的时间段
//        List<TimeSlot> courseList = courseApplicationMapper.getTimeSlotByLabId(labId);
//
//        Integer startWeek = addBorrowApplicationDto.getStartWeek();
//        Integer endWeek = addBorrowApplicationDto.getEndWeek();
//        String section = addBorrowApplicationDto.getSection();
//
//        for(TimeSlot ts:labList)
//        {
//            if(ts.getSection().equals(section))//节次相同再看看选的时间是否相交
//            {
//                boolean conflict =
//                        (startWeek>=ts.getStartWeek()&& startWeek<= ts.getEndWeek())
//                      ||(endWeek>= ts.getStartWeek()&&endWeek<= ts.getEndWeek());
//                if(conflict)
//                {
//                    return Result.error(405,"时间冲突，请另选时间");
//                }
//            }
//        }
//
//        for(TimeSlot ts:courseList)
//        {
//            if(ts.getSection().equals(section))//节次相同再看看选的时间是否相交
//            {
//                boolean conflict =
//                        (startWeek>=ts.getStartWeek()&& startWeek<= ts.getEndWeek())
//                                ||(endWeek>= ts.getStartWeek()&&endWeek<= ts.getEndWeek());
//                if(conflict)
//                {
//                    return Result.error(405,"时间冲突，请另选时间");
//                }
//            }
//        }

        BorrowApplication borrowApplication = BeanCopyUtil.copyBean(addBorrowApplicationDto, BorrowApplication.class);

        //当前用户为学生 借用实验室，填写相关信息
        User user = LoginUserUtil.getUser();
        borrowApplication.setStudentId(user.getId());
        borrowApplication.setStudentName(user.getUsername());

        //设置为当前学期
        borrowApplication.setTerm(termService.getNowTerm());
        borrowApplication.setCreateTime(new Date());
        borrowApplication.setState(SystemConstants.NOT_PROCESSED);
        borrowApplicationMapper.insert(borrowApplication);

        return Result.ok("申请成功提交",null);
    }


    //（学生） 修改申请内容
    @Override
    public Result modifyLabBorrow(UpdateBorrowApplicationDto updateBorrowApplicationDto) {

        BorrowApplication borrowApplication = BeanCopyUtil.copyBean(updateBorrowApplicationDto, BorrowApplication.class);
        borrowApplicationMapper.updateById(borrowApplication);

        return Result.ok("修改成功",null);
    }

    //（管理员）审批 借用申请 状态
    @Override
    public Result arrangeByBorrow(ArrangeBorrowDTO arrangeBorrowDTO) {

        Integer borrowId = arrangeBorrowDTO.getBorrowId();
        BorrowApplication borrowApplication = getById(borrowId);
        Integer newState = arrangeBorrowDTO.getState();
        Integer oldState = borrowApplication.getState();

        if(newState.equals(oldState)) return Result.ok("无修改变动",null);
        else if(newState == SystemConstants.BORROW_ACCESS_STATE)
        {
            if(termLabService.arrangeByBorrow(borrowApplication))
            {
                borrowApplication.setState(newState);
                updateById(borrowApplication);
                return Result.ok("审批通过",null);
            }
            else
            {
                borrowApplication.setState(SystemConstants.BORROW_REJECT_STATE);
                updateById(borrowApplication);
                return Result.ok("申请时间冲突,状态强制设为驳回",null);
            }
        }
        else
        {
            borrowApplication.setState(newState);
            updateById(borrowApplication);
            return Result.ok("修改成功",null);
        }
    }


    //返回所有的借用申请
    @Override
    public Result getAll(Integer pageNum,Integer pageSize) {

        Page<BorrowApplication> page = new Page<>();

        page(page,null);

        List<BorrowApplicationVO> list = BeanCopyUtil.copyBeanList(page.getRecords(),BorrowApplicationVO.class);

        PageResult pageResult = new PageResult(list,pageNum,pageSize,page.getTotal());

        return Result.ok(pageResult);
    }

    @Override
    public Result getByStudent(Integer pageNum, Integer pageSize) {

        User user = LoginUserUtil.getUser();

        Integer studentId = user.getId();

        LambdaQueryWrapper<BorrowApplication> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BorrowApplication::getStudentId,studentId);

        Page<BorrowApplication> page = new Page<>();
        page(page,queryWrapper);

        List<BorrowApplicationVO> list = BeanCopyUtil.copyBeanList(page.getRecords(),BorrowApplicationVO.class);

        PageResult pageResult = new PageResult(list,pageNum,pageSize,page.getTotal());

        return Result.ok(pageResult);



    }

    //使用完毕
    @Override
    public Result complete(Integer id) {

        removeById(id);

        return Result.ok("使用完毕，清除该数据",null);
    }


}

