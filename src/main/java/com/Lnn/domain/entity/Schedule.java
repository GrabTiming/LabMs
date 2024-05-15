package com.Lnn.domain.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Schedule)表实体类
 *
 * @author Liang2003
 * @since 2024-05-09 15:23:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("schedule")
public class Schedule  {
    
    @TableId(type = IdType.AUTO)
    private Integer id;
    //学期
    private String term;

    private Date beginTime;
    
}
