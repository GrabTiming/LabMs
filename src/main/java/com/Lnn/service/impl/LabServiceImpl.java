package com.Lnn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Lnn.mapper.LabMapper;
import com.Lnn.domain.entity.Lab;
import com.Lnn.service.LabService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Lab)表服务实现类
 *
 * @author Liang2003
 * @since 2024-05-15 10:22:41
 */
@Service("labService")
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab> implements LabService {

    @Override
    public List<Lab> getAllLab() {

        return this.baseMapper.selectList(null);
    }

    @Override
    public List<Lab> getLab(String labType) {

        LambdaQueryWrapper<Lab> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lab::getLabType,labType);
        List<Lab> labList = this.baseMapper.selectList(queryWrapper);
        return labList;

    }
}

