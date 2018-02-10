package com.abin.lee.spring.data.hadoop.hbase.controller;

import com.abin.lee.spring.data.hadoop.hbase.model.User;
import com.abin.lee.spring.data.hadoop.hbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by abin on 2018/2/11 0:22.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.controller
 */
@Controller
@RequestMapping("/user")
public class HbaseController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(@ModelAttribute User user) {
        String result = "FAILURE";
        try {
            this.userService.add(user);
            result = "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("/findById")
    @ResponseBody
    public User findById(String id) {
        User user = null;
        try {
            user = this.userService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(@ModelAttribute User user) {
        List<User> list = null;
        try {
            list = this.userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
