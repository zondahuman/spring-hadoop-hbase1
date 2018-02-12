package com.abin.lee.spring.data.hadoop.hbase.service.impl;

import com.abin.lee.spring.data.hadoop.hbase.model.User;
import com.abin.lee.spring.data.hadoop.hbase.repository.UserRepository;
import com.abin.lee.spring.data.hadoop.hbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abin on 2018/2/11 0:12.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.service.impl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void add(User user) {
        userRepository.save(user);

    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findByParam(String param) {
        return this.userRepository.findByParam(param);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
