package org.licslan;

import org.junit.Test;
import org.licslan.service.UserService;
import org.licslan.tx.TxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxTest {
    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService bean = ac.getBean(UserService.class);
        bean.insertUser();
        //关闭容器  执行销毁方法
        ac.close();
    }
}
