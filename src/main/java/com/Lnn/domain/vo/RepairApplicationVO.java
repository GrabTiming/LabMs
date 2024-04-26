package com.Lnn.domain.vo;

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
public class RepairApplicationVO {


    private Integer id;
    /**
     * 实验室名称
     */
    private String labName;

    /**
     * 故障描述
     */
    private String description;
    /**
     * 报修日期
     */
    private LocalDateTime createTime;

    /**
     * 报修状态
     */
    private Integer state;

}
