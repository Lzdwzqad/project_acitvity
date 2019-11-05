package com.activity.service;

import com.activity.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lzdwzqad
 * @site www.lt.com
 * @create 2019-11-05 16:40
 */

@Repository
public interface UserService {
    User selectByPrimaryKey(Integer userno);

}
