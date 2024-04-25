package com.Lnn.domain.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2024-04-17 19:49:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User  {
    
    @TableId(type  = IdType.AUTO)
    private Integer id;

    //账号
    private String account;
    
    //姓名
    private String username;
    
    //密码
    private String password;
    
    //职称（老师，实验员专属）
    private String proRank;
    
    //专业(学生专属)
    private String major;
    
    //班级（学生专属）
    private String className;
    
    //0管理员，1学生、2老师、3实验员
    private Integer role;
    
    
}
