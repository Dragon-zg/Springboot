package com.lnnk.quartz.controller;

import com.lnnk.quartz.model.vo.QuartzVO;
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
    public List<QuartzVO> getScheduleList() {
        return quartzService.getScheduleList();
    }

    @ApiOperation(value = "创建定时任务")
    @PostMapping(value = {""})
    public void createScheduleJob(@RequestBody QuartzVO quartzVO) {
        quartzService.createScheduleJob(quartzVO);
    }

    @ApiOperation(value = "重新设置定时任务cron")
    @PutMapping(value = {""})
    public void updateScheduleJobCron(@RequestBody QuartzVO quartzVO) {
        quartzService.updateScheduleJobCron(quartzVO);
    }

    @ApiOperation(value = "删除定时任务")
    @DeleteMapping(value = {""})
    public void deleteScheduleJob(QuartzVO quartzVO) {
        quartzService.deleteScheduleJob(quartzVO);
    }

    @ApiOperation(value = "运行定时任务")
    @GetMapping(value = {"/run"})
    public void runScheduleJobNow(@RequestParam String jobName, @RequestParam(required = false) String jobGroup) {
        quartzService.runScheduleJobNow(jobName, jobGroup);
    }

    @ApiOperation(value = "暂停定时任务")
    @PostMapping(value = {"/pause"})
    public void pauseScheduleJob(@RequestBody QuartzVO quartzVO) {
        quartzService.pauseScheduleJob(quartzVO.getJobName(), quartzVO.getJobGroup());
    }

    @ApiOperation(value = "恢复定时任务")
    @PostMapping(value = {"/resume"})
    public void resumeScheduleJob(@RequestBody QuartzVO quartzVO) {
        quartzService.resumeScheduleJob(quartzVO.getJobName(), quartzVO.getJobGroup());
    }

    @ApiOperation(value = "恢复所有定时任务")
    @GetMapping(value = {"/resumeAll"})
    public void resumeAllJobs() {
        quartzService.resumeAllJobs();
    }

    @ApiOperation(value = "暂停所有定时任务")
    @GetMapping(value = {"/pauseAll"})
    public void pauseAllJobs() {
        quartzService.pauseAllJobs();
    }
}
