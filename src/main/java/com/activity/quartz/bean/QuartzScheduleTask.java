package com.activity.quartz.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 任务信息
 */
@Data
public class QuartzScheduleTask implements Serializable {

    /**
     * 任务Id
     */
    private String id;

    /**
     * 任务名
     */
    private String name;

    /**
     * 任务所在类全名称
     */
    private String group;

    /**
     * 任务执行的方法名
     */
    private String trigger;

    /**
     * 任务频率 和cron语法保持一致
     */
    private String expression;

    /**
     * 执行任务方法的参数
     */
    private Object[] param;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 任务状态
     */
    private int state;
}
