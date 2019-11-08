package com.activity.handle;

import com.activity.common.JsonData;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GetewayMapping;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("alifenga.xyz.test")
public class TestHandle extends AbstractHandle {

    @Override
    public JsonData handle(String requestJSON) {
        return new JsonData(requestJSON+request);
    }

    @GetewayMapping(name = "test")
    public JsonData test() {
        return new JsonData("刘婷");
    }

    @GetewayMapping(name = "test2")
    public JsonData test2(String requestJSON) {
        HttpServletRequest request = super.request;
        return new JsonData("请求IP:"+request.getRemoteAddr()+requestJSON);
    }
}
