package com.Lnn.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (LabCourse)表实体类
 *
 * @author Liang2003
 * @since 2024-04-26 16:17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lab_course")
public class LabCourse  {
    
    @TableId
    private Integer id;

    //实验室id
    private Integer labId;
    
    //实验室名称
    private String labName;
    
    //排课申请id
    private Integer applicationId;
    
    
}
