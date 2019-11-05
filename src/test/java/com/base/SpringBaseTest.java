package com.base;

import com.activity.model.User;
import com.activity.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-05 16:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringBaseTest {



    @Autowired
    private UserService userService;

    public void init(){

    }

    @org.junit.Test
    public void ss(){
        User user = userService.selectByPrimaryKey(1);
        System.out.println(user);
    }

}
