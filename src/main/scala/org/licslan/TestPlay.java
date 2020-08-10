package org.licslan;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.licslan.config.AllConfiguration;
import org.licslan.dao.Hello;
import org.licslan.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPlay {

    @Autowired
    private Hello hello;
    @Test
    public void contextLoads() {
        System.out.println("测试类出来了");
        hello.play();
        //TODO 此处调用接口方法
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AllConfiguration.class);
        Dog dog = (Dog) ac.getBean("dog");
        dog.init();
        System.out.println(dog);
    }

}
