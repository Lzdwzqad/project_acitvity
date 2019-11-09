package com.activity.handle;

import com.activity.common.ArrayUtil;
import com.activity.common.ConvertorUtil;
import com.activity.common.ErrorEnum;
import com.activity.common.JsonData;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GatewayMapping;
import com.activity.model.ScheduleTask;
import com.activity.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-08 22:44
 */
@Component("alifenga.xyz.scheduleTaskHandle")
public class ScheduleTaskHandle extends AbstractHandle {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Override
    public JsonData handle(Map<String, Object> map) {
        return new JsonData(map);
    }

    @GatewayMapping(name = "list")
    public JsonData list() {
        JsonData jsonData;
        List<ScheduleTask> list = this.scheduleTaskService.list();
        if (!ArrayUtil.isEmpty(list)) {
            jsonData = new JsonData(list);
        } else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E501.getDesc(), list);
        }
        return jsonData;
    }

    @GatewayMapping(name = "add")
    public JsonData add(Map<String, Object> map) throws Exception {
        ScheduleTask scheduleTask = ConvertorUtil.mapToObject(map, ScheduleTask.class);
        JsonData jsonData;
        int i = this.scheduleTaskService.insertSelective(scheduleTask);
        if (i > 0) {
            jsonData = new JsonData(ErrorEnum.S200.getCode(), ErrorEnum.S200.getDesc(), i);
        } else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E501.getDesc(), i);
        }
        return jsonData;
    }

    @GatewayMapping(name = "del")
    public JsonData del(Map<String, Object> map) {
        JsonData jsonData;
        int i = this.scheduleTaskService.deleteByPrimaryKey(map.get("id").toString());
        if (i > 0) {
            jsonData = new JsonData(i);
        } else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E501.getDesc(), i);
        }
        return jsonData;
    }


    @GatewayMapping(name = "update")
    public JsonData update(Map<String, Object> map) throws Exception {
        ScheduleTask scheduleTask = ConvertorUtil.mapToObject(map, ScheduleTask.class);
        JsonData jsonData;
        int i = this.scheduleTaskService.updateByPrimaryKey(scheduleTask);
        if (i > 0) {
            jsonData = new JsonData(i);
        } else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E501.getDesc(), i);
        }
        return jsonData;
    }


}
