package com.activity.gateway;


import com.activity.common.JsonData;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 抽象业务处理类,默认一个处理方法,继承来实现
 */
public abstract class AbstractHandle {

    public HttpServletRequest request = null;

    public abstract JsonData handle(Map<String, Object> map);
}
