package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员 审批 实验室借用申请
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArrangeBorrowDTO {

    private Integer borrowId;

    private Integer state;

}
