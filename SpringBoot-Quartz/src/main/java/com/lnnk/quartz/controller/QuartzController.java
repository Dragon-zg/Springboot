package com.lnnk.quartz.controller;

import com.lnnk.quartz.model.entity.QuartzEntity;
import com.lnnk.quartz.service.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 16:48
 **/
@Api(tags = "定时任务操作")
@RestController
@RequestMapping("/api/quartz")
public class QuartzController {

    private final QuartzService quartzService;

    public QuartzController(QuartzService quartzService) {
        this.quartzService = quartzService;
    }

    @ApiOperation(value = "定时任务列表")
    @GetMapping(value = {""})
    public List<QuartzEntity> getJobList() {
        return quartzService.getScheduleList();
    }
}
