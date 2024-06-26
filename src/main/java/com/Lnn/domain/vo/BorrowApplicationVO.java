package com.Lnn.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Format;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowApplicationVO {

    private Integer id;

    //实验室名称
    private String labName;

    //申请节次
    private String section;

    //申请起始周
    private Integer startWeek;

    //申请结束周
    private Integer endWeek;

    private String day;

    //申请原因
    private String reason;

    //申请状态(0未审核，1通过，2驳回，3使用完毕)
    private Integer state;

    //填报日期
    private Date createTime;

}
