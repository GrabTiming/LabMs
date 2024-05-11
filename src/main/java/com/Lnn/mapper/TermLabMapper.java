package com.Lnn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Lnn.domain.entity.TermLab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * (TermLab)表数据库访问层
 *
 * @author Liang2003
 * @since 2024-05-09 12:20:55
 */
@Mapper
public interface TermLabMapper extends BaseMapper<TermLab> {


    @Select("select count(*) from term_lab where term = #{term} and lab_id = #{labId}")
    public Integer isExist(String term,Integer labId);

    @Select("select * from term_lab where lab_id = #{labId} and term = #{term}")
    TermLab get(String term, Integer labId);
}

