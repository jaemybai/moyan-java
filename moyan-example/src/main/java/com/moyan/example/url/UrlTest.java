package com.moyan.example.url;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UrlTest {

    private static Logger logger = LoggerFactory.getLogger(UrlTest.class);

    @Test
    public void testHttp() throws Exception{
        String webUrl = "http://www.cnblogs.com/fangg/p/5886233.html";
        webUrl = "https://www.baidu.com/s?wd=mockito%20%20maven&rsv_spt=1&rsv_iqid=0x941f87ad00010288&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=org.mockito.cglib.proxy.MethodInterceptor%2520maven&rsv_t=c0fdg%2BeA5YliHq0Fbzyb%2FN3oQiEPvZXPW8iQlu3wbFv9n4rJorge1mWdonT5rbmtXbTC&inputT=6565&rsv_pq=99e25cbc0000fbaf&rsv_sug3=18&rsv_n=2&rsv_sug4=7713&rsv_sug=1";
        URL url = new URL(webUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = httpURLConnection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while(-1 != (len = inputStream.read(buffer))){
            baos.write(buffer,0,len);
            baos.flush();
        }
        logger.info(baos.toString("utf-8"));
    }

    @Test
    public void testHttps() throws Exception{
        String webUrl = "http://www.cnblogs.com/fangg/p/5886233.html";
        webUrl = "https://www.baidu.com/s?wd=mockito%20%20maven&rsv_spt=1&rsv_iqid=0x941f87ad00010288&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=org.mockito.cglib.proxy.MethodInterceptor%2520maven&rsv_t=c0fdg%2BeA5YliHq0Fbzyb%2FN3oQiEPvZXPW8iQlu3wbFv9n4rJorge1mWdonT5rbmtXbTC&inputT=6565&rsv_pq=99e25cbc0000fbaf&rsv_sug3=18&rsv_n=2&rsv_sug4=7713&rsv_sug=1";
        webUrl = "https://leancloud.cn/docs/realtime_rest_api.html#hash2055218541";
        URL url = new URL(webUrl);
        HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
//        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = httpURLConnection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while(-1 != (len = inputStream.read(buffer))){
            baos.write(buffer,0,len);
            baos.flush();
        }
        logger.info(baos.toString("utf-8"));
    }

//    @Test
//    public void testLeanCLoud() throws Exception{
//        String webUrl = "http://www.cnblogs.com/fangg/p/5886233.html";
//        webUrl = "https://www.baidu.com/s?wd=mockito%20%20maven&rsv_spt=1&rsv_iqid=0x941f87ad00010288&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=org.mockito.cglib.proxy.MethodInterceptor%2520maven&rsv_t=c0fdg%2BeA5YliHq0Fbzyb%2FN3oQiEPvZXPW8iQlu3wbFv9n4rJorge1mWdonT5rbmtXbTC&inputT=6565&rsv_pq=99e25cbc0000fbaf&rsv_sug3=18&rsv_n=2&rsv_sug4=7713&rsv_sug=1";
//        webUrl = "https://ehxbcd6n.api.lncld.net/1.1/classes/_Conversation";
//        URL url = new URL(webUrl);
//        HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
//        httpURLConnection.setRequestMethod("POST");
//        Map<String,String> headers = new HashMap<String,String>();
//        headers.put("X-LC-Id","EHXbCD6nWDAh01gflnTy83Rd-gzGzoHsz");
//        headers.put("X-LC-Key","9TIuacdhPpTrew0UPKo5sjaG,master");
//        headers.put("Content-Type","application/json;charset=utf-8");
//        String data = "{\"name\": \"每个 Java 程序员必备的 8 个开发工具\"}";
//        for(Map.Entry<String,String> entry : headers.entrySet()) {
//            httpURLConnection.setRequestProperty(entry.getKey(),entry.getValue());
//        }
//        httpURLConnection.setDoOutput(true);
//        OutputStream outputStream = httpURLConnection.getOutputStream();
//        outputStream.write(data.getBytes());
//        outputStream.flush();
//        InputStream inputStream = httpURLConnection.getInputStream();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while(-1 != (len = inputStream.read(buffer))){
//            baos.write(buffer,0,len);
//            baos.flush();
//        }
//        logger.info(baos.toString("utf-8"));
//    }

//    @Test
//    public void getHttpsLeanCLoud() throws Exception{
//        String webUrl = "http://www.cnblogs.com/fangg/p/5886233.html";
//        webUrl = "https://ehxbcd6n.api.lncld.net/1.1/classes/_Conversation";
//        Map<String,String> headers = new HashMap<String,String>();
//        headers.put("X-LC-Id","EHXbCD6nWDAh01gflnTy83Rd-gzGzoHsz");
//        headers.put("X-LC-Key","9TIuacdhPpTrew0UPKo5sjaG,master");
//        headers.put("Content-Type","application/json");
//        String data = "{\"name\": \"每个 Java 程序员必备的 8 个开发工具\"}";
//        HttpPost httpPost = new HttpPost(webUrl);
//        Header[] headers1 = new Header[headers.size()];
//        int i = 0;
//        for(Map.Entry<String,String> entry : headers.entrySet()) {
//            headers1[i] = new BasicHeader(entry.getKey(),entry.getValue());
//            i++;
//        }
//        httpPost.setHeaders(headers1);
////        StringRequestEntity requestEntity = new StringRequestEntity(data,"application/json","UTF-8");
//        StringEntity stringEntity = new StringEntity(data,"utf-8");
////        stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(data.getBytes());
//        httpPost.setEntity(byteArrayEntity);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpResponse httpResponse = httpClient.execute(httpPost);
//        logger.info(httpResponse);
//        String result = null;
//        httpResponse.getEntity().getContent();
//        if(httpResponse.getStatusLine().getStatusCode() == 200)
//        {
//            HttpEntity httpEntity = httpResponse.getEntity();
//            result = EntityUtils.toString(httpEntity);//取出应答字符串
//            // 一般来说都要删除多余的字符
//            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
//        }
//        logger.info(result);
//    }
}
