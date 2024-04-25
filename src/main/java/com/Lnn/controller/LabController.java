package com.Lnn.controller;


import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddRepairApplicationDto;
import com.Lnn.domain.dto.CourseApplicationDto;
import com.Lnn.domain.dto.CourseApplicationUpdateDto;
import com.Lnn.domain.entity.CourseApplication;
import com.Lnn.service.CourseApplicationService;
import com.Lnn.service.RepairApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Laboratory")
public class LabController {


    @Autowired
    private CourseApplicationService courseApplicationService;

    @Autowired
    private RepairApplicationService repairApplicationService;



  //-----------------------------------排课相关----------------------------------- //
    //3.2 (1) 教师：新增排课 申请
    @PostMapping("/add/courseApplication")
    public Result applyForLabCourse(@RequestBody CourseApplicationDto courseApplicationDto)
    {
        return courseApplicationService.insert(courseApplicationDto);
    }


    //教师：查看自己的排课申请
    @GetMapping("/getCourse")
    public Result getCourse(@RequestParam("teacherId") Integer id)
    {
        return courseApplicationService.getCourseByTeacherId(id);
    }


    //教师：修改未排课的申请信息
    @PostMapping("/update/CourseApplication")
    public Result updateCourseApplication(@RequestBody CourseApplicationUpdateDto courseApplicationUpdateDto)
    {
        return courseApplicationService.modify(courseApplicationUpdateDto);
    }



    //-----------------------------------维修相关----------------------------------- //


    //教师：实验室报修
    @PostMapping("/add/repairApplication")
    public Result addRepairApplication(@RequestBody AddRepairApplicationDto addRepairApplicationDto)
    {
        return repairApplicationService.add(addRepairApplicationDto);
    }

    //查询教师提交的报修申请
    @GetMapping("/getRepair")
    public Result getRepair(@RequestParam("teacherId") Integer id)
    {
        return repairApplicationService.getRepairAppByTeacherId(id);
    }

    //实验员：列出属于自己管理的实验室

    //实验员：设置报修申请的状态

}
