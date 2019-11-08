package com.activity.gateway.controller;

import com.activity.common.ErrorEnum;
import com.activity.common.JsonData;
import com.activity.common.SpringContextUtils;
import com.activity.gateway.AbstractHandle;
import com.activity.gateway.annotation.GetwayMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 自定义请求网路由
 */
@RestController
public class GatewayController {

    /**
     * 入口请求
     *
     * @param request
     * @param beanName
     * @param methodName
     * @param requestJson
     * @return
     */
    @PostMapping("/geteway")
    public JsonData geteway(HttpServletRequest request, String beanName, String methodName, String requestJson) {
        JsonData json = new JsonData();
        try {
            //根据参数获取容器中handle实现Bean
            AbstractHandle handle = SpringContextUtils.getBean(beanName);
            handle.request = request;
            //方法为空,默认为handle方法
            if (StringUtils.isEmpty(methodName)) {
                json = handle.handle(requestJson);
            } else {
                //获取bean自定义方法
                Method[] methods = handle.getClass().getDeclaredMethods();
                boolean isAnn = false;
                for (Method method : methods) {
                    //判断方法是存在映射注解标注
                    if (method.isAnnotationPresent(GetwayMapping.class)) {
                        isAnn = true;
                        //获取注解对象
                        GetwayMapping getwayMapping = method.getAnnotation(GetwayMapping.class);
                        //校验映射名是否与请求方法名一致
                        if (getwayMapping.name().equals(methodName)) {
                            //获得方法参数列表,目前只采用无参数和一个参数得方法,进行反射传参返回响应结果
                            Type[] paramTypeList = method.getGenericParameterTypes();
                            if (paramTypeList.length == 0) {
                                json = (JsonData) method.invoke(handle);
                                break;
                            } else if (paramTypeList.length == 1) {
                                json = (JsonData) method.invoke(handle, requestJson);
                                break;
                            } else {
                                //参数大于1,暂定为请求非法 TODO
                                json = new JsonData(ErrorEnum.E410.getCode(), ErrorEnum.E410.getDesc(), null);
                                break;
                            }
                        }
                    }
                }

                //不存在映射注解,请求非法不存在,返回结束
                if (!isAnn) {
                    json = new JsonData(ErrorEnum.E404.getCode(), ErrorEnum.E404.getDesc(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json = new JsonData(ErrorEnum.E500.getCode(), ErrorEnum.E500.getDesc(), null);
        } finally {
            return json;
        }
    }
}
