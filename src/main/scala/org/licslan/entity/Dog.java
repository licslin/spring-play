package org.licslan.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
@ToString
public class Dog  implements ApplicationContextAware {

    //其他方法可以获取ioc容器   ApplicationContextAwareProcessor实现设置ioc容器环境
    private ApplicationContext applicationContext;

    //@Value 1.基本类型的值  2.SpEL  #{} 3.可以写${} 获取配置文件中的值（运作环境变量中值）
    @Value("licslan")
    private String name;

    @Value("#{20-5}")
    private Integer age;

    @Value("${dog.nickName}")
    private String nickName;

    public Dog(){
        System.out.println("------>构造函数调用");
    }

    public void init(){
        System.out.println("------>init函数调用");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}