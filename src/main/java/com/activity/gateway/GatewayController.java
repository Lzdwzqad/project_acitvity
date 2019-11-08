package com.activity.gateway;

import com.activity.util.SpringContextUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @PostMapping("/geteway")
    public JSONObject geteway(String path, String requestJson) {
        JSONObject json = new JSONObject();
        try {
            AbstractHandle handle = SpringContextUtils.getBean(path);
            json = (JSONObject) handle.handle();
        } catch (Exception e) {
            json.put("code", 404);
            json.put("msg", "请求不存在");
        } finally {
            return json;
        }
    }
}
