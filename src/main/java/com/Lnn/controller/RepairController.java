package com.Lnn.controller;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddRepairApplicationDto;
import com.Lnn.domain.dto.UpdateRepairApplicationDto;
import com.Lnn.service.RepairApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repairApplication")
public class RepairController {

    @Autowired
    private RepairApplicationService repairApplicationService;


    //-----------------------------------维修相关----------------------------------- //

    //教师：实验室报修
    @PostMapping("/add")
    public Result addRepairApplication(@RequestBody AddRepairApplicationDto addRepairApplicationDto)
    {
        return repairApplicationService.add(addRepairApplicationDto);
    }

    //教师：查询教师提交的报修申请bb
    @GetMapping("/get/teacher")
    public Result getRepair(Integer pageNum,Integer pageSize)
    {
        return repairApplicationService.getRepairAppByTeacherId(pageNum,pageSize);
    }


    //实验员：列出属于自己管理的实验室 的报修申请
    @GetMapping("get/LabAdmin")
    public Result getLabRepair(Integer pageNum,Integer pageSize)
    {
        return  repairApplicationService.getLabRepair(pageNum,pageSize);
    }

    //实验员：设置报修申请的状态
    @PostMapping("update")
    public Result updateRepair(@RequestBody UpdateRepairApplicationDto updateRepairApplicationDto)
    {
        return repairApplicationService.updateRepair(updateRepairApplicationDto);
    }

}
