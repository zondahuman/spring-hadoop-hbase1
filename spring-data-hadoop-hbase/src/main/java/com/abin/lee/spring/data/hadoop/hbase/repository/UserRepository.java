package com.abin.lee.spring.data.hadoop.hbase.repository;

import java.util.List;

import com.abin.lee.spring.data.hadoop.hbase.model.User;
import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;
/**
 * Created by abin on 2018/2/10 23:12.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase
 * https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.data.hadoop.hbase.TableCallback
 * https://www.programcreek.com/java-api-examples/?api=org.springframework.data.hadoop.hbase.RowMapper
 * https://www.programcreek.com/java-api-examples/index.php?source_dir=JavaStudy-master/Spring/spring-data-book-master/hadoop/hbase/src/main/java/com/oreilly/springdata/hadoop/hbase/UserRepository.java#
 * https://www.programcreek.com/java-api-examples/index.php?source_dir=pinpoint-master/web/src/main/java/com/navercorp/pinpoint/web/dao/hbase/HbaseMapStatisticsCallerDao.java#
 *
 * 
 */


@Repository
public class UserRepository {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "users";

    public static byte[] CF_INFO = Bytes.toBytes("cfInfo");

    private byte[] qUser = Bytes.toBytes("user");
    private byte[] qEmail = Bytes.toBytes("email");
    private byte[] qPassword = Bytes.toBytes("password");

    public List<User> findAll() {
        return hbaseTemplate.find(tableName, "cfInfo", new RowMapper<User>() {
            @Override
            public User mapRow(Result result, int rowNum) throws Exception {
                return new User(Bytes.toString(result.getValue(CF_INFO, qUser)),
                        Bytes.toString(result.getValue(CF_INFO, qEmail)),
                        Bytes.toString(result.getValue(CF_INFO, qPassword)));
            }
        });

    }

    public User save(User user) {
        return hbaseTemplate.execute(tableName, new TableCallback<User>() {
            public User doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(user.getName()));
                p.add(CF_INFO, qUser, Bytes.toBytes(user.getName()));
                p.add(CF_INFO, qEmail, Bytes.toBytes(user.getEmail()));
                p.add(CF_INFO, qPassword, Bytes.toBytes(user.getPassword()));
                table.put(p);
                return user;

            }
        });
    }

    public List<User> findByParam(User user) {
        List<User> list = Lists.newArrayList();
        // read each row from 'MyTable'
        List<String> rows = hbaseTemplate.find("MyTable", "name",
                new RowMapper<String>() {
                    @Override
                    public String mapRow(Result result, int rowNum)
                            throws Exception {
                        return result.toString();
                    }
                });
        for (String row : rows) {
            System.out.println("Printing row:" + row);
        }
        return list;
    }




}

