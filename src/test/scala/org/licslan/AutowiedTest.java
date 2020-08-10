package org.licslan;

import org.junit.Test;
import org.licslan.config.AllConfigAutowired;
import org.licslan.dao.Dao;
import org.licslan.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class AutowiedTest {
    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AllConfigAutowired.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        ConfigurableEnvironment environment = ac.getEnvironment();
        HelloService helloService = (HelloService)ac.getBean(HelloService.class);
        System.out.println(helloService.print());
        Dao dao = (Dao)ac.getBean("dao");
        System.out.println("true of flase  ===== "+(dao==helloService.getDao()));
        System.out.println("xxxx "+ helloService.getDao());
        System.out.println("yyyy "+ dao);
        System.out.println("spring 容器创建完成。。。");
        //关闭容器  执行销毁方法
        ac.close();
    }
}
