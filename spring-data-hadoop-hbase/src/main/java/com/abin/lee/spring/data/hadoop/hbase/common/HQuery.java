package com.abin.lee.spring.data.hadoop.hbase.common;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;

import java.util.List;

/**
 * Created by abin on 2018/2/12 10:57.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.common
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HQuery {
    private String table;
    private String family;
    private String qualifier;
    private String qualifierValue;
    private String row;
    private String startRow;
    private String stopRow;
    private Filter filter;
    private PageFilter pageFilter;
    private Scan scan;

    private List<HBaseColumn> columns = Lists.newArrayList();


}
