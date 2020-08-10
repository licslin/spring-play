package org.licslan.config;

import org.licslan.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/Dog.properties") //加载外部配置文件 @value 注入
@Configuration
@ComponentScan("org.licslan.entity") //指定范围要扫描的bean
public class AllConfigPropertyValues {
    @Bean
    public Dog dog(){
        return new Dog();
    }
}
