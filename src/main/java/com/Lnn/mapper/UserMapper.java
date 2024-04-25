package com.Lnn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Lnn.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-17 19:49:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    
}

