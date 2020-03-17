package com.netease.cloud.skiff.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 * Created by peimingming
 * Date: 2018-08-08
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /** ctx */
    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.ctx = context;
    }

    /**
     * 
     * 创建一个新的实例 SpringContextUtil.
     *
     */
    public SpringContextUtil() {
    }

    /**
     * 获取 bean 实例
     * 
     * @param beanName
     * @return
     * @throws BeansException
     *             T
     * @since 1.0.0
     */
    public static <T> T getBean(String beanName) throws BeansException {
        return (T) ctx.getBean(beanName);
    }

    /**
     * 是包含 bean 消息
     * 
     * @param beanName
     * @return boolean
     * @since 1.0.0
     */
    public static boolean containsBean(String beanName) {
        return ctx.containsBean(beanName);
    }

    /**
     * 获取 ctx
     * 
     * @return ApplicationContext
     * @since 1.0.0
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * 查找 bean
     * 
     * 
     * @param clazz
     * @return T
     * @since 1.0.0
     */
    public static <T> T getBean(Class<T> clazz) {
        Map map = ctx.getBeansOfType(clazz);
        if (map.isEmpty()) {
            throw new IllegalStateException("未找到bean:" + clazz);
        } else {
            return ctx.getBeansOfType(clazz).values().iterator().next();
        }
    }

}
