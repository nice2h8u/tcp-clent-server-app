package com.nice2h8u.tcpclientserver.helpClasses;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


//this interface helps to get app context
@Service
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}