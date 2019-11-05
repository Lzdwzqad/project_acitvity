package com.activity.model;

import lombok.Data;

@Data
public class ScheduleTask {
    private String id;

    private String name;

    private String group;

    private String trigger;

    private String expression;

    private Integer state;

    private Integer type;

    private String param;
}