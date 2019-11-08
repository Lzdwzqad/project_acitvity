package com.activity.gateway;


import com.activity.common.JsonData;

public abstract class AbstractHandle {

    public abstract JsonData handle(String requestJSON);
}
