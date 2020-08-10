package org.licslan.ext;


import org.licslan.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * BeanPostProcessor  bean 后置处理器 bean创建对象初始化前后进行拦截工作的
 * BeanFactoryPostProcessor  beanFactory 后置处理器  在beanfactory标准初始化之后调用
 *      -- 所有的bean定义已经保加载到beanFactory，bean实例还未创建
 * */
@Configuration
@ComponentScan("org.licslan.ext")
public class ExtConfig {

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     * @param beanFactory the bean factory used by the application context
     * @throws org.springframework.beans.BeansException in case of errors
     *
     */
    @Bean
    public Car car(){
        return new Car();
    }
}
