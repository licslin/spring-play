package org.licslan.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {


    public Cat() {
        System.out.println("cat ....  构造方法。。。。。");
    }

    public void destroy() throws Exception {
        System.out.println("cat .....销毁方法  容器关闭的时候调用");

    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat ....初始化方法  在bean创建完成属性都赋值后调用");

    }
}
