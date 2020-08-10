package org.licslan.service;

import lombok.Data;
import org.licslan.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class HelloService {
    @Autowired
    private Dao dao;

    public String print(){
        System.out.println(dao);
        return dao.toString();
    }
}
