package com.abin.lee.spring.data.hadoop.hbase.repository;

import java.util.List;

import com.abin.lee.spring.data.hadoop.hbase.common.HQuery;
import com.abin.lee.spring.data.hadoop.hbase.model.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.*;
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
                Put put = new Put(Bytes.toBytes(user.getName()));
                put.addColumn(CF_INFO, qUser, Bytes.toBytes(user.getName()));
                put.addColumn(CF_INFO, qEmail, Bytes.toBytes(user.getEmail()));
                put.addColumn(CF_INFO, qPassword, Bytes.toBytes(user.getPassword()));
                table.put(put);
                return user;

            }
        });
    }

    public List<User> findByParam(String param) {
        List<User> list = Lists.newArrayList();
        // read each row from 'MyTable'
//        List<User> rows = hbaseTemplate.get(tableName, "cfInfo", param, RowMapper<User>() {

//                    @Override
//                    public String mapRow(Result result, int rowNum)
//                            throws Exception {
//                        return result.toString();
//                    }
//                });
//        for (String row : rows) {
//            System.out.println("Printing row:" + row);
//        }
        return list;
    }


    /**
     * 通过表名和key获取一行数据
     *
     * @param tableName
     * @param rowName
     * @return
     */
    public <T> T get(HQuery query, Class<T> c) {

        if(StringUtils.isBlank(query.getTable()) || StringUtils.isBlank(query.getRow())){
            return null;
        }

        return this.hbaseTemplate.get(query.getTable(), query.getRow(), new RowMapper<T>() {
            public T mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                T item=c.newInstance();
                JSONObject obj = new JSONObject();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        obj.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                                cell.getQualifierLength()),
                                Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                    }
                }else{
                    return null;
                }
                item=JSON.parseObject(obj.toJSONString(), c);
                return item;
            }
        });

    }




    /**
     * 通过表名 key 和 列族 和列 获取一个数据
     *
     * @param tableName
     * @param rowName
     * @param familyName
     * @param qualifier
     * @return
     */
    public String getColumn(HQuery query) {

        if(StringUtils.isBlank(query.getTable()) || StringUtils.isBlank(query.getRow())
                || StringUtils.isBlank(query.getFamily()) || StringUtils.isBlank(query.getQualifier())){
            return null;
        }

        return this.hbaseTemplate.get(query.getTable(), query.getRow(), query.getFamily(), query.getQualifier(), new RowMapper<String>() {
            public String mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                String res = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        res = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    }
                }
                return res;
            }
        });
    }

    /**
     * 通过表名，开始行键和结束行键获取数据
     *
     * @param HQuery
     * @return
     */
    public <T> List<T> find(HQuery query,Class<T> c) {
        //如果未设置scan,设置scan
        if (query.getScan() == null) {

            //起止搜索
//            if(StringUtils.isNotBlank(query.getStartRow()) && StringUtils.isNotBlank(query.getStopRow())){
//                query.setSearchLimit(query.getStartRow(), query.getStopRow());
//            }

            //主要配合pageFilter，指定起始点
//            if(StringUtils.isNotBlank(query.getStartRow())){
//                query.setScanStartRow(query.getStartRow());
//            }

            //列匹配搜索
//            if(StringUtils.isNotBlank(query.getFamily()) &&StringUtils.isNotBlank(query.getQualifier())
//                    &&StringUtils.isNotBlank(query.getQualifierValue())){
//                query.setSearchEqualFilter(query.getFamily(),query.getQualifier(),query.getQualifierValue());
//            }

            //分页搜索
//            if(query.getPageFilter()!=null){
//                query.setFilters(query.getPageFilter());
//            }

            if(query.getScan()==null){
                query.setScan(new Scan());
            }
        }

        //设置缓存
        query.getScan().setCacheBlocks(false);
        query.getScan().setCaching(2000);

        return this.hbaseTemplate.find(query.getTable(), query.getScan(), new RowMapper<T>() {
            @Override
            public T mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList = result.listCells();
                JSONObject obj = new JSONObject();
                T item =c.newInstance();
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        // String row = Bytes.toString(cell.getRowArray(),
                        // cell.getRowOffset(), cell.getRowLength());
                        // String family = Bytes.toString(cell.getFamilyArray(),
                        // cell.getFamilyOffset(),
                        // cell.getFamilyLength());

                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength());

                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                                cell.getQualifierLength());
                        if(value.startsWith("[")){
                            obj.put(quali, JSONArray.parseArray(value));
                        }else{
                            obj.put(quali, value);
                        }
                    }
                }
                item =JSON.parseObject(obj.toJSONString(), c);
                return item;
            }

        });
    }

    public void delete(HQuery query){
        this.hbaseTemplate.delete(query.getTable(), query.getRow(), query.getFamily());
    }







}

