package org.licslan;

import org.junit.Test;
import org.licslan.aop.MathCount;
import org.licslan.config.AllConfigAOP;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {

    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AllConfigAOP.class);

        //记住不要自己创建对象 要spring容器自 己创建
//        MathCount mathCount =  new MathCount();
//        mathCount.div(1,1);
        //上述方法不生效
        MathCount bean = ac.getBean(MathCount.class);
        bean.div(1,0);
        //bean.div(1,1);
        //关闭容器  执行销毁方法
        ac.close();
    }
}
