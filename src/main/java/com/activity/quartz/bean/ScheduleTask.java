package com.activity.quartz.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 任务信息
 */
@Setter
@Getter
public class ScheduleTask implements Serializable {


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
}
