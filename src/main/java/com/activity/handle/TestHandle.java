package com.activity.handle;

import com.activity.common.JsonData;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GetwayMapping;
import org.springframework.stereotype.Component;

@Component("alifenga.xyz.test")
public class TestHandle extends AbstractHandle {

    @Override
    public JsonData handle(String requestJSON) {
        return new JsonData(requestJSON);
    }

    @GetwayMapping(name = "test")
    public JsonData test() {
        return new JsonData("刘婷");
    }

    @GetwayMapping(name = "test2")
    public JsonData test2(String requestJSON) {
        return new JsonData(requestJSON);
    }
}
