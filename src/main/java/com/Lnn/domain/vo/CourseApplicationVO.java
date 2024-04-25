package com.Lnn.domain.vo;

import com.Lnn.annotation.ListValue;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseApplicationVO {


    private Integer id;

    //上课学期
    private String term;

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

    private Integer state;

}
