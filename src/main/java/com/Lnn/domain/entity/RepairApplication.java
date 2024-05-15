package com.Lnn.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 报修申请
 * @author makejava
 * @since 2024-04-25 21:15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairApplication  {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 报修教师id
     */
    private Integer teacherId;

    /**
     * 报修教师姓名
     */
    private String teacherName;

    /**
     * 实验室编号
     */
    private Integer labId;

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
    private Date createTime;

    private String term;

    /**
     * 报修状态
     */
    private Integer state;


}

