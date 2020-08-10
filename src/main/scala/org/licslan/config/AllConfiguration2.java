package org.licslan.config;

import org.licslan.entity.Dog;
import org.springframework.context.annotation.*;

@ComponentScan(value = "org.licslan")
@Configuration
public class AllConfiguration2 {

    //默认是单例的
    //prototype  多实例  如果是多实例   ioc容器启动不会调用方法创建对象到ioc容器中 当ioc容器创建完成后 调用获取bean实例调用bean中的方法才会创建对象 获取几次调用几次
    //singleton  单实例  ioc容器启动会调用方法创建对象到ioc容器中 以后每次获取直接从容器中拿。
    //request    同一个请求创建一个实例
    //session    同一个session创建一个实例
    //懒加载 ：
    //  单实例bean 默认在容器启动的时候创建对象，懒加载:容器启动不创建对象，第一次使用（获取）bean创建对象，并初始化


    //给容器注册组件
    //1.包扫描+组件标注注解  自己的写类
    //2.@Bean[导入的第三方包里的组件]
    //3.@Import[快速给容器中导入一个组件]
    //  ---  1.id默认是全类名
    //  ---  2.importSelector 返回需要导入的组件的全类名数组
    //  ---  3.ImportBeanDefinitionRegistrar 手动注册bean到spring容器中
    //4.使用spring提供的FactoryBean (bean工厂) getObject获取工厂bean 获取工厂bean本身  加一个&即可
    @Scope
    @Lazy
    @Bean("licslan_yy")
    public Dog dogxx(){
        System.out.println("fffffffffff");
        return new Dog();
    }
}
