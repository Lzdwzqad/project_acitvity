package com.activity.gateway;


import com.activity.common.JsonData;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHandle {

    public HttpServletRequest request = null;

    public abstract JsonData handle(String requestJSON);
}
