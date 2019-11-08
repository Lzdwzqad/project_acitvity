package com.activity.service.impl;

import com.activity.mapper.ScheduleTaskMapper;
import com.activity.model.ScheduleTask;
import com.activity.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-08 20:00
 */

@Repository
public class ScheduleTaskServiceImpl implements ScheduleTaskService {
    @Autowired
    private ScheduleTaskMapper scheduleTaskMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return scheduleTaskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScheduleTask record) {
        return scheduleTaskMapper.insert(record);
    }

    @Override
    public int insertSelective(ScheduleTask record) {
        return scheduleTaskMapper.insertSelective(record);
    }

    @Override
    public ScheduleTask selectByPrimaryKey(String id) {
        return scheduleTaskMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ScheduleTask record) {
        return scheduleTaskMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ScheduleTask record) {
        return scheduleTaskMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(ScheduleTask record) {
        return scheduleTaskMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ScheduleTask> list() {
        return scheduleTaskMapper.list();
    }
}
