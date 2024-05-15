package com.Lnn.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRepairApplicationDto {

    /**
     * 实验室编号
     */
    private Integer labId;

    /**
     * 实验室名称
     */
    private String LabName;

    /**
     * 故障描述
     */
    private String description;

}
