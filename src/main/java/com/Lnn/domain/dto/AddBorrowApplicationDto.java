package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBorrowApplicationDto {

    //实验室id
    private Integer labId;

    //实验室名称
    private String labName;


    //申请节次
    private String section;

    //申请起始周
    private Integer startWeek;

    //申请结束周
    private Integer endWeek;

    private String Day;

    //申请原因
    private String reason;

}
