package com.activity.mapper;

import com.activity.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userno);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userno);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}