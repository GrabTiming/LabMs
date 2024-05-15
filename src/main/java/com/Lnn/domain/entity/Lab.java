package com.Lnn.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Lab)表实体类
 *
 * @author Liang2003
 * @since 2024-05-15 10:22:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lab")
public class Lab  {
//实验室id    
    @TableId
    private Integer id;

    //实验室名称
    private String labName;
    
    //实验室类别
    private String labType;
    
    //设备数量
    private Integer equipCount;
    
    //实验员id
    private Integer adminId;
    
    //实验员名字
    private String adminName;
    
    
}
