package org.licslan.entity;

public class Car {

    public Car(){
        System.out.println("car构造方法");
    }

    //初始化一些数据
    public void  init(){
        System.out.println("car .....init ......");
    }

    //销毁 关闭数据连接
    public void destroy(){
        System.out.println("car .... destroy....");
    }
}
