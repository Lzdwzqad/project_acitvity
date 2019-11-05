package com.activity.quartz.manager;

import com.activity.quartz.bean.ScheduleTask;
import com.activity.quartz.service.TaskService;
import com.activity.quartz.util.SnowflakeIdWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 调度管理
 */
@Component
public class ManagerJob implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private TaskService taskService;


    /**
     * Schedule注解得任务处理
     *
     * @param arg0
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        ApplicationContext applicationContext = arg0.getApplicationContext();
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            Class<?> class1 = applicationContext.getBean(bean).getClass();
            Method[] methods = class1.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Scheduled.class)) {
                    Scheduled annotation = method.getAnnotation(Scheduled.class);
                    ScheduleTask scheduleTask = new ScheduleTask();
                    scheduleTask.setGroup(class1.getName());
                    scheduleTask.setTrigger(method.getName());
                    scheduleTask.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
                    scheduleTask.setExpression(annotation.cron());
                    scheduleTask.setParam(new Object[]{});
                    scheduleTask.setName(class1.getSimpleName() + method.getName());
                    taskService.addTask(scheduleTask);
                }
            }
        }
    }
}
