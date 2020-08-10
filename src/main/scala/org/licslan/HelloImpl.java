package org.licslan;

import org.licslan.dao.Hello;
import org.springframework.stereotype.Service;

@Service
public class HelloImpl implements Hello {
    public void play() {
        System.out.println("hello world!");
    }
}
