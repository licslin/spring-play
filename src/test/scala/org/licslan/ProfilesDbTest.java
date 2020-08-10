package org.licslan;

import org.junit.Test;
import org.licslan.config.AllConfigProfile;
import org.licslan.entity.Dog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class ProfilesDbTest {

    @Test
    public void testing(){
        //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AllConfigProfile.class);
        //1.create ApplicationContext
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //2.setting need to active env
        ac.getEnvironment().setActiveProfiles("test","dev");
        //ac.getEnvironment().setActiveProfiles("dev");
        //3.setting config class
        ac.register(AllConfigProfile.class);
        //4.start and refresh
        ac.refresh();

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        String[] beanNamesForType = ac.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println("datasource is --------> "+s);
        }
        System.out.println("spring 容器创建完成。。。");
        System.out.println(ac.getBean(Dog.class));
        ac.getBean(Dog.class).init();
        //关闭容器  执行销毁方法
        ac.close();
    }
}
