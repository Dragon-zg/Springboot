package com.lnnk.quartz.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lnnk
 * @date 2019/7/18 15:43
 */
@Data
@NoArgsConstructor
public class QuartzVO {

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 执行类
     */
    private String jobClassName;

    /**
     * 执行时间
     */
    private String cronExpression;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器分组
     */
    private String triggerGroup;

    /**
     * 任务状态
     */
    private String triggerState;
}
