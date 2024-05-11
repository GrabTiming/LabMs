package com.Lnn.domain.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (TermLab)表实体类
 *
 * @author Liang2003
 * @since 2024-05-09 12:20:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("term_lab")
public class TermLab  {
    
    @TableId(type =  IdType.AUTO)
    private Integer id;

    private String term;
    
    private Integer labId;
    
    private String state;
    
    
}
