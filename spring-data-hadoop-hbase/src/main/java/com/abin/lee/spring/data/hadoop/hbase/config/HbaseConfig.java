package com.abin.lee.spring.data.hadoop.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * Created by abin on 2018/2/11 0:08.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.config
 * https://stackoverflow.com/questions/31381615/use-hbase-with-spring-boot
 */
@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
public class HbaseConfig {

    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    public HbaseTemplate hbaseTemplate() {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", this.hbaseProperties.zkQuorum);
        configuration.set("hbase.rootdir", this.hbaseProperties.rootDir);
        configuration.set("zookeeper.znode.parent", this.hbaseProperties.zkBasePath);
        return new HbaseTemplate(configuration);
    }

}