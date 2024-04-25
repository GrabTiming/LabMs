package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 根据姓名、身份查询用户的dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectNameDto {

    private String username;
    private Integer role;

}
