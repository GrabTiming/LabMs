package com.Lnn.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接收前端的登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    private String Account;
    private String password;


}
