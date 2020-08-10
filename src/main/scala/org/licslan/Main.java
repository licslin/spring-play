package org.licslan;

import org.licslan.entity.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LICSLAN
 * */
@SpringBootApplication
@RestController
public class Main{


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        /**
         * SpringBoot应用开发已经非常普遍，的确，SpringBoot给我们的开发带来了很多便利，但其实，SpringBoot并没有增加什么新特性，
         * 只不过是在Spring注解的基础上做了升级版混合使用。SpringBoot的精髓就是自动装配（@EnableAutoConfiguration），我们不
         * 需要像以前使用Maven搭建项目时，引入过多的依赖包，同时还需要处理各种包冲突问题，简直是烦的要死，现在只需依赖SpringBoot
         * 核心包就能完成简单的应用开发了，所以了解Spring核心原理就非常重要了，下面我们开始从源码角度分析Spring底层原理。
         * */
        //手动读取xml配置文件   xml配置方式
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //Dog dog1 = (Dog)classPathXmlApplicationContext.getBean(Dog.class);
        Dog dog1 = (Dog)classPathXmlApplicationContext.getBean("dog");
        System.out.println(dog1);
        String[] beanNamesForType1 = classPathXmlApplicationContext.getBeanNamesForType(Dog.class);
        for (String name : beanNamesForType1) {
            System.out.println(name);
        }
        ConfigurableEnvironment environment = classPathXmlApplicationContext.getEnvironment();
        String property = environment.getProperty("dog.nickName");
        System.out.println("配置文件中的dog.nickName : "+property);

        System.out.println("==================================================================================");

        //xml不用了  使用配置类代替配置文件  注解方式获取bean
//        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AllConfiguration.class);
//        Dog dog2 = (Dog) ac.getBean(Dog.class);
//        dog2.init();
//        String[] beanNamesForType2 = ac.getBeanNamesForType(Dog.class);
//        for (String name : beanNamesForType2) {
//            System.out.println(name);
//        }
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}


