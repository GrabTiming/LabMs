package com.Lnn.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVO {


    //姓名
    private String username;

    //职称（老师，实验员专属）
    private String proRank;


}
