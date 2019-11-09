package com.activity.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 数据转换工具类
 */
public class ConvertorUtil {

    /**
     * 工具类私有构造,不建议工具类进行new
     */
    private ConvertorUtil() {
    }

    /**
     * 方法说明：将bean转化为另一种bean实体
     *  
     *
     * @param object
     * @param entityClass
     * @return
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if (null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * Map转对象
     *
     * @param map
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t) throws Exception {
        T instance = t.newInstance();
        BeanUtils.populate(instance, map);
        return instance;
    }

    /**
     * 对象转Map
     *
     * @param object
     * @return
     */
    public static Map<?, ?> objectToMap(Object object) {
        return convertBean(object, Map.class);
    }
}
