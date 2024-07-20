package com.ivan.service.impl;

import com.ivan.entity.User;
import com.ivan.mapper.UserMapper;
import com.ivan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void sava(User user) {
        //如果id是null那么就是新增用户请求
        if(user.getId() == null){
            userMapper.insertUser(user);
        }else{
            //否则为更新用户对象
            userMapper.update(user);
        }

    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }
}
