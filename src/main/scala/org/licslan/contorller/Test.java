package org.licslan.contorller;

import org.licslan.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Test {
    @Autowired
    private HelloService helloService;
}
