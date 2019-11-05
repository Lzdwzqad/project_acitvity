package com.activity.quartz.service;

import com.activity.quartz.bean.ScheduleTask;
import com.activity.quartz.manager.JobProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 任务调度实现
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LogManager.getLogger(TaskServiceImpl.class);

    private ConcurrentHashMap<String, ScheduleTask> allTask = new ConcurrentHashMap<String, ScheduleTask>();

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 获取所有的定时任务
     *
     * @return
     */
    @Override
    public List<ScheduleTask> getAllTask() {
        List<ScheduleTask> list = new ArrayList();
        list.addAll(allTask.values());
        return list;
    }

    /**
     * 获取触发器
     *
     * @param trigger 触发器名称(这里相当于方法名)
     * @param group   任务组名称(这里相当于job所在的类名称)
     * @return
     */
    @Override
    public Trigger getTrigger(String trigger, String group) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        CronTrigger cronTrigger = null;
        try {
            cronTrigger = (CronTrigger) scheduler.getTrigger(new TriggerKey(trigger, group));
        } catch (Exception e) {
            logger.error("触发器获取出现异常：[{}]", e.getMessage());
            e.printStackTrace();
        }
        return cronTrigger;
    }

    /**
     * 添加一个新的定时任务
     *
     * @param task
     * @return
     */
    @Override
    public ScheduleTask addTask(ScheduleTask task) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(JobProxy.class)
                    .withIdentity(task.getName(), task.getGroup()).build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(task.getTrigger(), task.getGroup())
                    .startNow()
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(task
                                    .getExpression())).build();
            System.out.println(trigger.getKey());
            Class<?> classzz = Class.forName(task.getGroup());
            Class<?>[] c = new Class<?>[task.getParam().length];
            for (int i = 0; i < task.getParam().length; i++) {
                c[i] = task.getParam()[i].getClass();
            }
            Method method = classzz.getMethod(task.getTrigger(), c);
            JobDataMap jobDataMap = trigger.getJobDataMap();
            jobDataMap.put(JobProxy.JOB_NAME, task.getName());
            jobDataMap.put(JobProxy.JOB_GROUP, classzz);
            jobDataMap.put(JobProxy.JOB_TRIGGER, method);
            jobDataMap.put(JobProxy.JOB_TRIGGER_PARAM, task.getParam());
            scheduler.scheduleJob(jobDetail, trigger);
            if (scheduler.isStarted()) {
                scheduler.start();
            }
            if (!allTask.containsKey(task.getId())) {
                allTask.put(task.getId(), task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }


    /**
     * 重启定时任务
     *
     * @param task
     * @return
     */
    @Override
    public ScheduleTask reStartTask(ScheduleTask task) {
        try {
            CronTrigger cronTrigger = (CronTrigger) getTrigger(task.getTrigger(), task.getGroup());
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(new TriggerKey(task.getTrigger(), task.getGroup()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getExpression())).build();

            // 按新的trigger重新设置job执行

            scheduler.rescheduleJob(new TriggerKey(task.getTrigger(), task.getGroup()), cronTrigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 下方可以做一些更新数据库中任务的操作

        return task;
    }

    /**
     * 删除指定定时任务
     *
     * @param task
     * @return
     */
    @Override
    public ScheduleTask deleteTask(ScheduleTask task) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(task.getName(), task.getGroup());
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * 暂停任务
     *
     * @param task
     * @return
     */
    @Override
    public ScheduleTask pauseTask(ScheduleTask task) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(task.getName(), task.getGroup());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * 恢复任务
     *
     * @param task
     * @return
     */
    @Override
    public ScheduleTask resumeTask(ScheduleTask task) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(task.getName(), task.getGroup());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * 批量删除定时任务
     */
    @Override
    public void deleteTasks(List<ScheduleTask> scheduleTasks) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            List<JobKey> jobKeys = new ArrayList<JobKey>();
            JobKey jobKey;
            for (ScheduleTask scheduleTask : scheduleTasks) {
                jobKey = new JobKey(scheduleTask.getName(), scheduleTask.getGroup());
                jobKeys.add(jobKey);
            }
            scheduler.deleteJobs(jobKeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停所有的定时任务
     */
    @Override
    public void pauseAllTask() {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
