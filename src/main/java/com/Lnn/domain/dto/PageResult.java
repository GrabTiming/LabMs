package com.Lnn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PageResult {

    private List list;
    private Integer pageNum;
    private Integer pageSize;
    private Long itemCount;

}
