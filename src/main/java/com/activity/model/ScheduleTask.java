package com.activity.model;

public class ScheduleTask {
    private String id;

    private String name;

    private String group;

    private String trigger;

    private String expression;

    private Integer state;

    private String param;

    public ScheduleTask(String id, String name, String group, String trigger, String expression, Integer state, String param) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.trigger = trigger;
        this.expression = expression;
        this.state = state;
        this.param = param;
    }

    public ScheduleTask() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}