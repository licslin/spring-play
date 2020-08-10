package org.licslan.config;

import org.licslan.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean生命周期  bean创建 ---  初始化 --- 销毁  由容器管理bean的生命周期
 *
 * 我们自定义初始化 和 销毁方法  容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁
 *
 * 1.指定初始化 和 销毁方法   xml  init-method  &  destroy-method
 *
 * 初始化:对象创建完成 并赋值好 调用初始化方法
 * 销毁：容器关闭的时候（单实例bean）  容器不会管理这个bean容器不会调用销毁（多实例）
 *
 *
 * bean的初始化工作可以以下面3种方式实现
 * 1.@bean 指定init-method  &  destroy-method
 * 2.通过让bean实现InitializingBean(定义初始化逻辑)  DisposableBean(定义销毁逻辑)
 * 3.使用JSR250 @PostConstruct  （方法上面） 在bean创建完成并且属性赋值完成，来执行初始化   PreDestroy 在容器销毁bean之前通知我们进行清理工作
 *
 *
 * populateBean(beanName, mbd, instanceWrapper);给bean属性进行赋值,在applyBeanPostProcessorsBeforeInitialization&&invokeInitMethods&&applyBeanPostProcessorsAfterInitialization前执行
 * bean初始化前后的工作由BeanPostProcessor来做
 * 4.BeanPostProcessor[接口] bean 的后置处理器  在bean初始化前后处理一些工作 method: postProcessBeforeInitialization（初始化之前） &&  postProcessAfterInitialization（初始化之后）
 *   --- wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *   --- invokeInitMethods(beanName, wrappedBean, mbd);  执行自定义初始化方法
 *   --- wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *
 *   遍历得到容器中所有的BeanPostProcessor 依次执行BeforeInitialization一旦返回null 跳出for循环 不会执行后面的BeanPostProcessor
 *
 *   spring底层对BeanPostProcessor的使用
 *   bean赋值 注入其他组件  @Autowired  生命周期注解功能
 * */
@Configuration
@ComponentScan("org.licslan")
public class AllConfigLifeCycleBean {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
