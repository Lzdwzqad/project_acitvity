package com.activity.mapper;

import com.activity.model.ScheduleTask;

public interface ScheduleTaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScheduleTask record);

    int insertSelective(ScheduleTask record);

    ScheduleTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleTask record);

    int updateByPrimaryKeyWithBLOBs(ScheduleTask record);

    int updateByPrimaryKey(ScheduleTask record);
}