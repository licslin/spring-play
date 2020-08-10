package org.licslan.config;

import org.licslan.dao.Dao;
import org.licslan.entity.Dog;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration //告诉spring这是一个配置类  配置类==配置文件   FilterType
@ComponentScans(value = {
        @ComponentScan(value = "org.licslan",
        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {Dao.class})},
                useDefaultFilters = false
)}
)
//xml 中配置的一样   value指定要扫描的包
//excludeFilters = Filter[]:指定扫描包的时候按照什么规则排除哪些组件
//includeFilters = Filter[]:指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION 按照注解
// ....
public class AllConfiguration {
    //给容器注册一个bean  类型为返回值的类型  id默认是把方法名作为id
    @Bean("licslan_xx")
    public Dog dogxx(){
        return new Dog();
    }
}
