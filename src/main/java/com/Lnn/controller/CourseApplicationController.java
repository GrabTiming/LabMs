package com.Lnn.controller;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.CourseApplicationDto;
import com.Lnn.domain.dto.CourseApplicationUpdateDto;
import com.Lnn.service.CourseApplicationService;
import com.Lnn.service.TermLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseApplication")
public class CourseApplicationController {


    @Autowired
    private CourseApplicationService courseApplicationService;


    @Autowired
    private TermLabService termLabService;

    //-----------------------------------排课相关----------------------------------- //
    //TODO 查询实验室的空闲时间
    @GetMapping("/free")
    public Result getFree(Integer labId)
    {
        //如果用一个表：学期：实验室 实验室占用情况
        //20周，7天 每天6个节次
        return null;
    }


    //3.2 (1) 教师：新增排课 申请
    @PostMapping("/add")
    public Result applyForLabCourse(@RequestBody CourseApplicationDto courseApplicationDto)
    {
        return courseApplicationService.insert(courseApplicationDto);
    }


    //教师：查看自己的排课申请
    @GetMapping("/get")
    public Result getCourse(Integer teacherId,Integer pageNum,Integer pageSize)
    {
        return courseApplicationService.getCourseByTeacherId(teacherId,pageNum,pageSize);
    }


    //教师：修改未排课的申请信息
    @PostMapping("/update")
    public Result updateCourseApplication(@RequestBody CourseApplicationUpdateDto courseApplicationUpdateDto)
    {
        return courseApplicationService.modify(courseApplicationUpdateDto);
    }

}
