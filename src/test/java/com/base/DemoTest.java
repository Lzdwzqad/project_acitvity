package com.base;

import com.activity.common.JsonData;
import com.activity.gateway.controller.GatewayController;
import com.activity.quartz.bean.ScheduleTask;
import com.activity.quartz.service.TaskService;
import com.activity.quartz.util.SnowflakeIdWorker;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GatewayController gatewayController;

    @Test
    public void addTask() throws InterruptedException {
        ScheduleTask task = new ScheduleTask();
        task.setExpression("0/5 * * * * ?");
        task.setId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        task.setGroup("com.base.MyJob");
        task.setTrigger("findUserName");
        task.setName("task1");
        task.setParam(new Object[]{"刘婷", 19});
        taskService.addTask(task);
        List<ScheduleTask> list = taskService.getAllTask();
        System.out.println(JSON.toJSONString(list));

        Thread.sleep(1000 * 20);
        System.out.println("重启定时任务");
        task.setExpression("0/10 * * * * ?");
        taskService.reStartTask(task);
    }

    @Test
    public void handleTest() {
        JsonData json = gatewayController.geteway("alifenga.xyz.test", null, null);
        JsonData json1 = gatewayController.geteway("alifenga.xyz.test", "test", null);
        JsonData json2 = gatewayController.geteway("alifenga.xyz.test", "test2", "刘婷");
        System.out.println(json);
        System.out.println(json1);
        System.out.println(json2);
    }
}