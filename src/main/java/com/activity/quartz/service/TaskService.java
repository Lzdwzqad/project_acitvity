package com.activity.quartz.service;

import com.activity.quartz.bean.ScheduleTask;
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
    List<ScheduleTask> getAllTask();

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
    ScheduleTask addTask(ScheduleTask task);

    /**
     * 重启定时任务
     *
     * @param task
     * @return
     */
    ScheduleTask reStartTask(ScheduleTask task);

    /**
     * 删除指定定时任务
     *
     * @param task
     * @return
     */
    ScheduleTask deleteTask(ScheduleTask task);

    /**
     * 暂停任务
     *
     * @param task
     * @return
     */
    ScheduleTask pauseTask(ScheduleTask task);

    /**
     * 恢复任务
     *
     * @param task
     * @return
     */
    ScheduleTask resumeTask(ScheduleTask task);

    /**
     * 批量删除定时任务
     */
    void deleteTasks(List<ScheduleTask> scheduleTasks);

    /**
     * 暂停所有的定时任务
     */
    void pauseAllTask();
}
