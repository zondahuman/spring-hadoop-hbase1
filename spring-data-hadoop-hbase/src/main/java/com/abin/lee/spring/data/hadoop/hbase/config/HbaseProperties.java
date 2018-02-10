package com.abin.lee.spring.data.hadoop.hbase.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by abin on 2018/2/11 0:07.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.config
 * https://stackoverflow.com/questions/31381615/use-hbase-with-spring-boot
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.data.hbase")
public class HbaseProperties {
    // Addresses of all registered ZK servers.
    public String zkQuorum;

    // Location of HBase home directory
    public String rootDir;

    // Root node of this cluster in ZK.
    public String zkBasePath;

    // getters and setters...

}