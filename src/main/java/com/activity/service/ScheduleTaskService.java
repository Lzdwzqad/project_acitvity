package com.activity.service;

import com.activity.model.ScheduleTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-08 19:59
 */
@Service
public interface ScheduleTaskService {
    int deleteByPrimaryKey(String id);

    int insert(ScheduleTask record);

    int insertSelective(ScheduleTask record);

    ScheduleTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleTask record);

    int updateByPrimaryKeyWithBLOBs(ScheduleTask record);

    int updateByPrimaryKey(ScheduleTask record);

    List<ScheduleTask> list();
}
