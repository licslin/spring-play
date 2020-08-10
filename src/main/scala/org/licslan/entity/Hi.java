package org.licslan.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Hi {

    public Hi(){
        System.out.println("Hi 构造方法。。。。");
    }
    //对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("Hi .....");
    }

    //容器移除对象之前
    @PreDestroy
    public void PreDestroy(){
        System.out.println("Hi PreDestroy......");
    }
}
