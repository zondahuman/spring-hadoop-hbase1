package com.abin.lee.spring.data.hadoop.hbase.common;

import lombok.*;

/**
 * Created by abin on 2018/2/12 10:57.
 * spring-hadoop-hbase1
 * com.abin.lee.spring.data.hadoop.hbase.common
 * 2、HBaseColumn 数据多字段写入时的辅助实体。family，字段名和值

 http://blog.csdn.net/dreamsigel/article/details/53835013?fps=1&locationNum=8
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HBaseColumn {
    private String family;
    private String qualifier;
    private String value;


}
