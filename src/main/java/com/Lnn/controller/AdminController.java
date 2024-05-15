package com.Lnn.controller;

import com.Lnn.annotation.RoleAuthorize;
import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ArrangeBorrowDTO;
import com.Lnn.domain.dto.ArrangeCourseLabDto;
import com.Lnn.domain.entity.BorrowApplication;
import com.Lnn.domain.entity.LabBorrow;
import com.Lnn.domain.entity.LabCourse;
import com.Lnn.mapper.ScheduleMapper;
import com.Lnn.service.BorrowApplicationService;
import com.Lnn.service.CourseApplicationService;
import com.Lnn.service.ScheduleService;
import com.Lnn.service.TermLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员权限
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    // ------------------------------排课相关---------------------------------//
    @Autowired
    private CourseApplicationService courseApplicationService;

    @Autowired
    private BorrowApplicationService borrowApplicationService;

    @Autowired
    private TermLabService termLabService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 查询所有排课申请
     */
    @RoleAuthorize(value = {"0"})
    @GetMapping("/get/courseApplication")
    public Result getCourseApplication(Integer pageNum,Integer pageSize)
    {
        return courseApplicationService.getAll(pageNum,pageSize);
    }


    /**
     * 查询与排课申请不冲突的实验室
     */
    @RoleAuthorize(value = {"0"})
    @GetMapping("/get/freeLab")
    public Result getFreeLab(Integer applicationId)
    {
        //TODO
        return termLabService.getFreeLab(applicationId);
    }

    /**
     *为排课申请 安排实验室
     */
    @RoleAuthorize(value = {"0"})
    @PostMapping("/arrange/course")
    public Result arrangeByCourse(@RequestBody LabCourse labCourse)
    {
        return termLabService.arrange(labCourse);
    }




    //---------------------------------学期相关 ---------------------------------//
    @GetMapping("/get/term")
    public Result getTerm(Integer pageNum,Integer pageSize)
    {
        return  scheduleService.getAllTerm(pageNum,pageSize);
    }


    //设置新学期
    @RoleAuthorize(value = {"0"})
    @PostMapping("/setNowTerm")
    public Result setTerm(Integer id)
    {
        return  scheduleService.setNowTerm(id);
    }


    //添加新学期
    @RoleAuthorize(value = {"0"})
    @PostMapping("/addTerm")
    public Result addTerm(String term,String beginTime)
    {
        return scheduleService.addTerm(term,beginTime);
    }


    // ------------------------------实验室借用相关---------------------------------//

    //审批借用申请
    @PostMapping("/arrange/borrow")
    public Result arrangeByBorrow(@RequestBody ArrangeBorrowDTO arrangeBorrowDTO)
    {

//        return termLabService.arrangeByBorrow(labBorrow);
        return borrowApplicationService.arrangeByBorrow(arrangeBorrowDTO);
    }

    //查看所有借用申请
    @GetMapping("/get/allBorrow")
    public Result getAllBorrow(Integer pageNum,Integer pageSize)
    {
        return borrowApplicationService.getAll(pageNum,pageSize);
    }

}
