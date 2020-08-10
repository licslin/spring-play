package org.licslan.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LICSLAN
 * */
@Slf4j
public class MathCount {
    public int div(int i,int j){
        log.info("hell aop~~~~~~~~~~");
        return i/j;
    }
}
