package org.licslan;

import org.junit.Test;
import org.licslan.config.AllConfigLifeCycleBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {

    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AllConfigLifeCycleBean.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("spring 容器创建完成。。。");
        //关闭容器  执行销毁方法
        ac.close();
    }
}
