package com.Lnn.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (LabBorrow)表实体类
 * 实验室借用 申请
 * @author Liang2003
 * @since 2024-04-26 13:59:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("borrow_application")
public class BorrowApplication {
    
    @TableId(type = IdType.AUTO)
    private Integer id;

    //实验室id
    private Integer labId;
    
    //实验室名称
    private String labName;
    
    //学生id
    private Integer studentId;
    
    //学生姓名
    private String studentName;
    
    //申请节次
    private String section;
    
    //申请起始周
    private Integer startWeek;
    
    //申请结束周
    private Integer endWeek;
    
    //申请原因
    private String reason;
    
    //申请状态(0未审核，1通过，2驳回，3使用完毕)
    private Integer state;

    private String term;

    private String day;
    
    //填报日期
    private Date createTime;
    
    
}
