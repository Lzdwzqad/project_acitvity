package com.activity.gateway.controller;

import com.activity.common.AesUtil;
import com.activity.common.BusinessException;
import com.activity.common.ErrorEnum;
import com.activity.common.JsonData;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GatewayMapping;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 自定义请求网关路由
 */
@RestController
public class GatewayController {

    @Autowired
    Map<String, AbstractHandle> abstractHandleMap;

    /**
     * 入口请求
     *
     * @param request
     * @param beanName
     * @param methodName
     * @param requestJson
     * @return
     */
    @PostMapping("/gateway")
    public JsonData gateway(HttpServletRequest request, String beanName, String methodName, String requestJson) {
        JsonData json = new JsonData(ErrorEnum.E404.getCode(), ErrorEnum.E404.getDesc());
        try {
            //根据参数获取容器中handle实现Bean
            AbstractHandle handle = abstractHandleMap.get(beanName);
            if (handle == null) {
                return json;
            }
            handle.request = request;
            //参数解密
            requestJson = AesUtil.aesDecrypt(requestJson, AesUtil.AES_KEY);
            //方法为空,默认为handle方法
            if (StringUtils.isEmpty(methodName)) {
                return handle.handle(JSON.parseObject(requestJson, Map.class));
            }
            //获取bean自定义方法
            Method[] methods = handle.getClass().getDeclaredMethods();
            for (Method method : methods) {
                //判断方法是存在映射注解标注
                if (method.isAnnotationPresent(GatewayMapping.class)) {
                    //获取注解对象
                    GatewayMapping getwayMapping = method.getAnnotation(GatewayMapping.class);
                    //校验映射名是否与请求方法名一致
                    if (getwayMapping.value().equals(methodName) || method.getName().equals(methodName)) {
                        //获得方法参数列表,目前只采用无参数和一个参数得方法,进行反射传参返回响应结果
                        Type[] paramTypeList = method.getGenericParameterTypes();
                        if (paramTypeList.length == 0) {
                            return (JsonData) method.invoke(handle);
                        } else if (paramTypeList.length == 1) {
                            return (JsonData) method.invoke(handle, JSON.parseObject(requestJson, Map.class));
                        } else {
                            //参数大于1,暂定为请求非法 TODO
                            return new JsonData(ErrorEnum.E410.getCode(), ErrorEnum.E410.getDesc());
                        }
                    }
                }
            }
        } catch (BusinessException e) {
            json = new JsonData(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            json = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E500.getDesc());
        }
        return json;
    }
}
