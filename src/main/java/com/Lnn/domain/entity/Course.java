package com.Lnn.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Course)表实体类
 *
 * @author Liang2003
 * @since 2024-05-15 22:01:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course  {
    
    @TableId
    private Integer id;

    //实验课名称
    private String courseName;
    
    //课程介绍
    private String description;
    
    //课程人数
    private Integer count;
    
    
}
