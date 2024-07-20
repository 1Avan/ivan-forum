package com.ivan.controller;

import com.ivan.entity.User;
import com.ivan.mapper.UserMapper;
import com.ivan.result.PageResult;
import com.ivan.result.Result;
import com.ivan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private UserService userService;

    /**
     * 获取所有用户列表
     * @return
     */
    @GetMapping
    public Result getAllUser(){
        return Result.success(userMapper.findAll());
    }

    /**
     *新增或更改用户信息
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
        userService.sava(user);
        return Result.success();
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id){
        userService.delete(id);
        return Result.success();
    }

    //分页查询
    //limit第一个参数 = (pageNum - 1) * pageSize （查询开始数）
    //limit第二个参数是pageSize
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        pageNum = (pageNum - 1)*pageSize;
        List<User> pageList = userMapper.selectPage(pageNum,pageSize);
        Integer total = userMapper.selectTotal();
        return Result.success(new PageResult(total,pageList));

    }

}
