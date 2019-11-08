package com.activity.handle;

import com.activity.common.ErrorEnum;
import com.activity.common.JsonData;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GatewayMapping;
import com.activity.model.ScheduleTask;
import com.activity.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-08 22:44
 */
@Component("lzdwzqad.lt.scheduleTaskHandle")
public class ScheduleTaskHandle extends AbstractHandle {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Override
    public JsonData handle(String requestJSON) {
        return new JsonData(requestJSON+request);
    }

    @GatewayMapping(name = "list")
    public JsonData list() {
        JsonData jsonData = null;
        List<ScheduleTask> list = this.scheduleTaskService.list();
        if (list.size()>0 && list!=null){
            jsonData = new JsonData(ErrorEnum.S200.getCode(), ErrorEnum.S200.getDesc(),list);
        }
        else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(),ErrorEnum.E501.getDesc(),list);
        }
        return jsonData;
    }

    @GatewayMapping(name = "add")
    public JsonData add(ScheduleTask scheduleTask) {
        JsonData jsonData = null;
        int i = this.scheduleTaskService.insertSelective(scheduleTask);
        if (i>0 ){
            jsonData = new JsonData(ErrorEnum.S200.getCode(), ErrorEnum.S200.getDesc(),i);
        }
        else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(),ErrorEnum.E501.getDesc(),i);
        }
        return jsonData;
    }

    @GatewayMapping(name = "del")
    public JsonData del(String id) {
        JsonData jsonData = null;
        int i = this.scheduleTaskService.deleteByPrimaryKey(id);
        if (i>0 ){
            jsonData = new JsonData(ErrorEnum.S200.getCode(), ErrorEnum.S200.getDesc(),i);
        }
        else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(),ErrorEnum.E501.getDesc(),i);
        }
        return jsonData;
    }


    @GatewayMapping(name = "update")
    public JsonData update(ScheduleTask scheduleTask) {
        JsonData jsonData = null;
        int i = this.scheduleTaskService.updateByPrimaryKey(scheduleTask);
        if (i>0 ){
            jsonData = new JsonData(ErrorEnum.S200.getCode(), ErrorEnum.S200.getDesc(),i);
        }
        else {
            jsonData = new JsonData(ErrorEnum.E500.getCode(),ErrorEnum.E501.getDesc(),i);
        }
        return jsonData;
    }


}
