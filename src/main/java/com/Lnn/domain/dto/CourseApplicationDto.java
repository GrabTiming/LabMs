package com.Lnn.domain.dto;

import com.Lnn.annotation.ListValue;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 新增排课信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseApplicationDto {

    //实验课id
    private Integer courseId;

    //实验课名称
    private String courseName;

    @ListValue(strValues = {"软件","硬件","网络"})
    //需要的实验室类型
    private String labType;

    //学生班级
    private String className;

    //学生人数
    private Integer count;

    //开始周
    private Integer startWeek;

    //结束周
    private Integer endWeek;

    private String day;

    //节次
    private String section;
}
