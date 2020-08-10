package org.licslan.beanliftcycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
/**
 * @author LICSLAN
 * */
@Component
public class MyBeanLifeCyclePostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("before  postProcessBeforeInitialization....."+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after  postProcessAfterInitialization....."+beanName);
        return bean;
    }

}
