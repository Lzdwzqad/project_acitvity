package com.activity.mapper;

import com.activity.model.ScheduleTask;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScheduleTaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScheduleTask record);

    int insertSelective(ScheduleTask record);

    ScheduleTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleTask record);

    int updateByPrimaryKeyWithBLOBs(ScheduleTask record);

    int updateByPrimaryKey(ScheduleTask record);

    List<ScheduleTask> list();
}