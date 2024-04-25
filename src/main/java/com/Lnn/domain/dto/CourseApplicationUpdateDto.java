package com.Lnn.domain.dto;

import com.Lnn.annotation.ListValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseApplicationUpdateDto {

    private Integer id;

    //上课学期
    private String term;

    //教师id
    private Integer teacherId;

    //教师姓名
    private String teacherName;

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

    //节次
    private String section;

}
