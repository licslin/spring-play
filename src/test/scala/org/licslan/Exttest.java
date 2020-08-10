package org.licslan;

import org.junit.Test;
import org.licslan.ext.ExtConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Exttest {

    @Test
    public void testing(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtConfig.class);
        ac.close();
    }
}
