package com.Lnn.controller;


import com.Lnn.domain.Result;
import com.Lnn.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getAll")
    public Result getAll()
    {
        return courseService.getAll();
    }

}
