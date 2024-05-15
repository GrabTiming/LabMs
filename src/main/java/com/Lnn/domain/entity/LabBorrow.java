package com.Lnn.domain.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (LabBorrow)表实体类
 *
 * @author Liang2003
 * @since 2024-05-14 15:57:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lab_borrow")
public class LabBorrow  {
    
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String term;
    
    //实验室id
    private Integer labId;
    
    private String labName;
    
    //实验室借用申请的id
    private Integer applicationId;
    
    
}
