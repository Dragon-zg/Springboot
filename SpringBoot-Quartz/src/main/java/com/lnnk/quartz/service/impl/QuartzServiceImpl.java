package com.lnnk.quartz.service.impl;

import com.lnnk.quartz.mapper.QuartzEntityMapper;
import com.lnnk.quartz.model.entity.QuartzEntity;
import com.lnnk.quartz.service.QuartzService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 16:44
 **/
@Service
public class QuartzServiceImpl implements QuartzService {

    private final QuartzEntityMapper quartzEntityMapper;

    public QuartzServiceImpl(QuartzEntityMapper quartzEntityMapper) {
        this.quartzEntityMapper = quartzEntityMapper;
    }

    @Override
    public List<QuartzEntity> getJobList() {
        return quartzEntityMapper.getJobList();
    }
}
