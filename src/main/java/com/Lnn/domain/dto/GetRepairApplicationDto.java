package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRepairApplicationDto {

    private Integer teacherId;

    private Integer pageNum;
    private Integer pageSize;

}
