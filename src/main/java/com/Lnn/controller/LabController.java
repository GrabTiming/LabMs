package com.Lnn.controller;

import com.Lnn.domain.Result;
import com.Lnn.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab")
public class LabController {

    @Autowired
    private LabService labService;

    @GetMapping("/all")
    public Result getAll()
    {
        return Result.ok(labService.getAllLab());
    }


    //TODO 查询实验室的在一段时间的占用状态
    @GetMapping("/free")
    public Result getFree(Integer labId)
    {
        //如果用一个表：学期：实验室 实验室占用情况
        //20周，7天 每天6个节次
        return null;
    }

}
