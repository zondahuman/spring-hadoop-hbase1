package com.abin.lee.spring.data.hadoop.hbase.service;

import com.abin.lee.spring.data.hadoop.hbase.model.User;

import java.util.List;

/**
 * Created by abin on 2018/2/11 0:12.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.service
 */
public interface UserService {

    void add(User user);

    List<User> findAll();

    User findById(String id);

    List<User> findByParam(String param);

    void update(User user);

    void delete(User user);
}
