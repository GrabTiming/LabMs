package com.Lnn.domain.entity;


import java.io.Serializable;
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
    
    @TableId
    private Integer id;

    //学期
    private String term;
    
    //标记是否是当前学期
    private Integer state;
    
    
}
