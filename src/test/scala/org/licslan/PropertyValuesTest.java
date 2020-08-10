package org.licslan;

import org.junit.Test;
import org.licslan.config.AllConfigPropertyValues;
import org.licslan.entity.Dog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class PropertyValuesTest {

    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AllConfigPropertyValues.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        ConfigurableEnvironment environment = ac.getEnvironment();
        String property = environment.getProperty("dog.nickName");
        System.out.println("配置文件中的dog.nickName : "+property);
        Dog dog = (Dog)ac.getBean("dog");
        System.out.println(dog);
        System.out.println("spring 容器创建完成。。。");
        //关闭容器  执行销毁方法
        ac.close();
    }
}
