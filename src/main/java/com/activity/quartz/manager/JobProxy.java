package com.activity.quartz.manager;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.lang.reflect.Method;

/**
 * 代理Job
 */
public class JobProxy implements Job {
    public static final String JOB_NAME = "job_name";
    public static final String JOB_GROUP = "job_group";
    public static final String JOB_TRIGGER = "job_trigger";
    public static final String JOB_TRIGGER_PARAM = "job_trigger_param";

    @Override
    public void execute(JobExecutionContext context) {
        try {
            JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
            Class<?> classzz = (Class<?>) jobDataMap.get(JobProxy.JOB_GROUP);
            Method method = (Method) jobDataMap.get(JobProxy.JOB_TRIGGER);
            Object[] objects = (Object[]) jobDataMap.get(JobProxy.JOB_TRIGGER_PARAM);
            method.invoke(classzz.newInstance(), objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
