package com.activity.service.impl;

import com.activity.mapper.UserMapper;
import com.activity.model.User;
import com.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-05 16:41
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer userno) {
        return userMapper.selectByPrimaryKey(userno);
    }

}
