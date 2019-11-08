package com.activity.handle;

import com.activity.gateway.AbstractHandle;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component("alifenga.xyz.test")
public class TestHandle extends AbstractHandle {
    @Override
    public Object handle() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","刘婷");
        return jsonObject;
    }
}
