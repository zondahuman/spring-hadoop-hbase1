package com.abin.lee.spring.data.hadoop.hbase.test;

import com.abin.lee.spring.data.hadoop.hbase.util.HttpClientUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abin on 2018/1/24 17:42.
 * elasticsearch-svr
 * com.abin.lee.elasticsearch.svr.api.test
 */
public class HbaseAddTest {
    private static final String httpURL = "http://localhost:8099/user/add";

    @Test
    public void testHbaseAdd() {
        try {
            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            String id = (int) (Math.random() * 1000000) + "";
            nvps.add(new BasicNameValuePair("name", "abin"+id));
            nvps.add(new BasicNameValuePair("email", RandomStringUtils.randomAlphabetic(5) + "@gmail.com"));
            nvps.add(new BasicNameValuePair("password", RandomStringUtils.randomAlphabetic(5)));

            HttpPost httpPost = new HttpPost(httpURL);

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            System.out.println("Executing request: " + httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
