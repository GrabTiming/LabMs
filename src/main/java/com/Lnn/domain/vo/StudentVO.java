package com.Lnn.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {


    //学号
    private String account;

    //姓名
    private String username;

    //专业(学生专属)
    private String major;

    //班级（学生专属）
    private String className;

}
