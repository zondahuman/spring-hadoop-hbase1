package com.abin.lee.spring.data.hadoop.hbase.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by abin on 2018/2/10 23:14.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private String name;
    private String email;
    private String password;



}