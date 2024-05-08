package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请的时间段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {

    private Integer startWeek;

    private Integer endWeek;

    private String section;

}
