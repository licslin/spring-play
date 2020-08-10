package org.licslan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/***
 *
 * (1)自动装配 @Autowired [spring注解]
 * 自动注入
 * 1.默认优先按照类型去容器找对应的组件 applicationContext.getBean(xx.class) 如果找到就赋值
 * 2.如果按照类型找到多个，再将属性的名称作为组件的id去容器查找
 * 3.@Qualifier("xxxx"),使用@Qualifier指定需要装配的组件的id而不是使用属性名
 * 4.自动装配默认y一定要将属性值设置好，没有就会报错  @Autowired(required=false)  默认是true
 * 5.@Primary 让spring进行自动装配的时候，默认使用首选的bean 也可以继续使用@Qualifier指定需要装配的bean的名字
 * (2)spring还支持使用@Resource (JSR250) @Inject(JSR330) [JAVA规范注解]
 * @Resuorce 可以和@Autowired一样实现自动装配；默认是按照组件名称进行装配的 不支持@Autowired(required=false) @Primary
 *
 * AutowiredAnnotationBeanPostProcessor 解析完成自动装配功能
 *
 * （3）@Autowired 构造器 参数 方法 属性  都是从容器中获取参数组件的值
 * （4）自定义组件想要使用spring容器底层的一些组件（applicationcontext/beanfactory...）实现xxAware接口就好
 *      自定义组件实现xxAware,在创建对象的时候，会调用接口规定方法注入相关组件AWare，把spring底层一些组件注入到自定义的bean中
 */
@Configuration  //自动装配 DI IOC SPRING 利用依赖注入完成IOC容器中各个组件的依赖关系赋值
@ComponentScan({"org.licslan.dao","org.licslan.service","org.licslan.contorller"})
public class AllConfigAutowired {
    void hello(){
        System.out.println("x");
    }
}

