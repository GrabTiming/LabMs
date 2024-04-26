package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * 实验员 修改 报修申请的状态 以及 故障维修描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRepairApplicationDto {

    private Integer id;
    /**
     * 故障描述
     */
    private String description;

    /**
     * 报修状态
     */
    private Integer state;

}
