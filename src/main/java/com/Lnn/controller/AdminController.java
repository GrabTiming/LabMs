package com.Lnn.controller;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.ArrangeCourseLabDto;
import com.Lnn.domain.entity.LabCourse;
import com.Lnn.mapper.ScheduleMapper;
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
    private TermLabService termLabService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 查询所有排课申请
     */
    @GetMapping("/get/courseApplication")
    public Result getCourseApplication(Integer pageNum,Integer pageSize)
    {
        return courseApplicationService.getAll(pageNum,pageSize);
    }


    /**
     * 查询与排课申请不冲突的实验室
     */
    @GetMapping("/get/freeLab")
    public Result getFreeLab(Integer applicationId)
    {
        //TODO
        return null;
    }

    /**
     *为排课申请 安排实验室
     */
    @PostMapping("/arrange")
    public Result arrange(@RequestBody LabCourse labCourse)
    {
        return termLabService.arrange(labCourse);
    }

    //---------------------------------学期相关 ---------------------------------//

    @GetMapping("/get/term")
    public Result getTerm(Integer pageNum,Integer pageSize)
    {
        return  scheduleService.getAllTerm(pageNum,pageSize);
    }

    @PostMapping("/setNowTerm")
    public Result setTerm(Integer id)
    {
        return  scheduleService.setNowTerm(id);
    }



}
