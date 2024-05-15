package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO
{

    private String Account;
    private String username;
    //职称（老师，实验员专属）
    private String proRank;

    //专业(学生专属)
    private String major;

    //班级（学生专属）
    private String className;

    private Integer role;
    private String token;

}
