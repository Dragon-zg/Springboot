package com.lnnk.quartz.controller;

import com.lnnk.quartz.model.entity.QuartzEntity;
import com.lnnk.quartz.service.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public List<QuartzEntity> getScheduleList() {
        return quartzService.getScheduleList();
    }

    @ApiOperation(value = "创建定时任务")
    @PostMapping(value = {""})
    public void createScheduleJob(@RequestBody QuartzEntity quartzEntity) {
        quartzService.createScheduleJob(quartzEntity);
    }

    @ApiOperation(value = "重新设置定时任务cron")
    @PutMapping(value = {""})
    void updateScheduleJobCron(@RequestBody QuartzEntity quartzEntity) {
        quartzService.updateScheduleJobCron(quartzEntity);
    }

    @ApiOperation(value = "删除定时任务")
    @DeleteMapping(value = {""})
    void deleteScheduleJob(QuartzEntity quartzEntity) {
        quartzService.deleteScheduleJob(quartzEntity);
    }

    @ApiOperation(value = "运行定时任务")
    @GetMapping(value = {"/run"})
    public void runScheduleJobNow(@RequestParam String jobName, @RequestParam(required = false) String jobGroup) {
        quartzService.runScheduleJobNow(jobName, jobGroup);
    }

    @ApiOperation(value = "暂停定时任务")
    @PostMapping(value = {"/pause"})
    public void pauseScheduleJob(@RequestBody QuartzEntity quartzEntity) {
        quartzService.pauseScheduleJob(quartzEntity.getJobName(), quartzEntity.getJobGroup());
    }

    @ApiOperation(value = "恢复定时任务")
    @PostMapping(value = {"/resume"})
    public void resumeScheduleJob(@RequestBody QuartzEntity quartzEntity) {
        quartzService.resumeScheduleJob(quartzEntity.getJobName(), quartzEntity.getJobGroup());
    }
}
