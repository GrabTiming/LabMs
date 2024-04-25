package com.Lnn.domain.entity;


import java.io.Serializable;

import com.Lnn.annotation.ListValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.validation.annotation.Validated;

/**
 * (CourseApplication)表实体类
 *
 * @author makejava
 * @since 2024-04-25 21:38:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course_application")
public class CourseApplication  {
    
    @TableId
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

    //申请状态
    private Integer state;
    
    
}
