package org.licslan;


import org.licslan.config.AllConfiguration;
import org.licslan.config.AllConfiguration2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    @org.junit.Test
    public void test(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AllConfiguration2.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        Object pppp = ac.getBean("pppp");
        Object pppp1 = ac.getBean("pppp");
        System.out.println(pppp==pppp1);

        System.out.println("======================================");
        Object licslan_xx = ac.getBean("licslan_xx");
        //Object licslan_xx1 = ac.getBean("licslan_xx");
        //System.out.println(licslan_xx==licslan_xx1);
    }



}
