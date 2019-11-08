package com.activity.gateway.controller;

import com.activity.common.ErrorEnum;
import com.activity.common.JsonData;
import com.activity.common.SpringContextUtils;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GetwayMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@RestController
public class GatewayController {

    @PostMapping("/geteway")
    public JsonData geteway(String beanName, String methodName, String requestJson) {
        JsonData json = new JsonData();

        try {
            AbstractHandle handle = SpringContextUtils.getBean(beanName);
            if (StringUtils.isEmpty(methodName)) {
                json = handle.handle(requestJson);
            } else {
                Method[] methods = handle.getClass().getDeclaredMethods();
                boolean isAnn = false;
                for (Method method : methods) {
                    if (method.isAnnotationPresent(GetwayMapping.class)) {
                        isAnn = true;
                        GetwayMapping getwayMapping = method.getAnnotation(GetwayMapping.class);
                        if (getwayMapping.name().equals(methodName)) {
                            Type[] paramTypeList = method.getGenericParameterTypes();
                            if (paramTypeList.length == 0) {
                                json = (JsonData) method.invoke(handle);
                                break;
                            } else if (paramTypeList.length == 1) {
                                json = (JsonData) method.invoke(handle, requestJson);
                                break;
                            } else {
                                json = new JsonData(ErrorEnum.E410.getCode(), ErrorEnum.E410.getDesc(), null);
                                break;
                            }
                        }
                    }
                }
                if (!isAnn) {
                    json = new JsonData(ErrorEnum.E404.getCode(), ErrorEnum.E404.getDesc(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json = new JsonData(ErrorEnum.E404.getCode(), ErrorEnum.E404.getDesc(), null);
        } finally {
            return json;
        }
    }
}
