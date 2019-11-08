package com.activity.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器获取Bean
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    /**
     * 工具类私有构造,不建议工具类进行new
     */
    private SpringContextUtils() {

    }

    /**
     * 容器赋值
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据beanId获取Bean
     *
     * @param beanId
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanId) {
        T bean = (T) applicationContext.getBean(beanId);
        return bean;
    }

    /**
     * 根据类获取Bean
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return (T) applicationContext.getBean(requiredType);
    }
}
