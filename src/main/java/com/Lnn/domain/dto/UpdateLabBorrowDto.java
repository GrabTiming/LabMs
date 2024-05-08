package com.Lnn.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLabBorrowDto {

    private Integer id;

    //实验室id
    private Integer labId;

    //实验室名称
    private String labName;

    //学生id
    private Integer studentId;

    //学生姓名
    private String studentName;

    //申请节次
    private String section;

    //申请起始周
    private Integer startWeek;

    //申请结束周
    private Integer endWeek;

    //申请原因
    private String reason;

}
