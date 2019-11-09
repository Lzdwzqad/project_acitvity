package com.activity.quartz.service;

import com.activity.quartz.bean.QuartzScheduleTask;
import org.quartz.Trigger;

import java.util.List;

/**
 * 任务调度接口
 */
public interface TaskService {

    /**
     * 获取所有的定时任务
     *
     * @return
     */
    List<QuartzScheduleTask> getAllTask();

    /**
     * 获取触发器
     *
     * @param trigger 触发器名称(这里相当于方法名)
     * @param group   任务组名称(这里相当于job所在的类名称)
     * @return
     */
    Trigger getTrigger(String trigger, String group);

    /**
     * 添加一个新的定时任务
     *
     * @param task
     * @return
     */
    QuartzScheduleTask addTask(QuartzScheduleTask task);

    /**
     * 重启定时任务
     *
     * @param task
     * @return
     */
    QuartzScheduleTask reStartTask(QuartzScheduleTask task);

    /**
     * 删除指定定时任务
     *
     * @param task
     * @return
     */
    QuartzScheduleTask deleteTask(QuartzScheduleTask task);

    /**
     * 暂停任务
     *
     * @param task
     * @return
     */
    QuartzScheduleTask pauseTask(QuartzScheduleTask task);

    /**
     * 恢复任务
     *
     * @param task
     * @return
     */
    QuartzScheduleTask resumeTask(QuartzScheduleTask task);

    /**
     * 批量删除定时任务
     */
    void deleteTasks(List<QuartzScheduleTask> scheduleTasks);

    /**
     * 暂停所有的定时任务
     */
    void pauseAllTask();
}
