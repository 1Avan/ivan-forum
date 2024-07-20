package com.ivan.mapper;

import com.ivan.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface UserMapper {
//    查询用户列表
    @Select("select * from user")
    List<User> findAll();

//    插入新用户
    @Insert("insert into user(username, password) values (#{username},#{password})")
    void insertUser(User user);

    //更新用户信息
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(@Param("id") Integer id);

    @Select("select * from user limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize);

    @Select("select count(*) from user")
    Integer selectTotal();
}
