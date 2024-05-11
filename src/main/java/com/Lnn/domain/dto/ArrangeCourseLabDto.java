package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrangeCourseLabDto {

    //实验室id
    private Integer labId;

    private String term;

    private String labName;

    //排课申请id
    private Integer applicationId;

}
