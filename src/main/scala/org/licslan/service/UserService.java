package org.licslan.service;

import org.licslan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional  //给方法加注解  事务回滚操作
    public void insertUser(){
        userDao.insert();
        //int i = 10/0;
        System.out.println("insert end....");
    }
}
