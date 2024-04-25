package com.Lnn.domain.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (RepairApplication)实体类
 *
 * @author makejava
 * @since 2024-04-25 21:15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairApplication  {
   
    private Integer id;
    /**
     * 报修教师id
     */
    private Integer teacherId;
    /**
     * 实验室编号
     */
    private Integer labId;
    /**
     * 报修教师姓名
     */
    private String teacherName;
    /**
     * 故障描述
     */
    private String description;
    /**
     * 报修日期
     */
    private Date createTime;


}

