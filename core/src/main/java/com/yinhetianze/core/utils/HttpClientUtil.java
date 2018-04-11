package com.yinhetianze.core.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 
 * @author Administrator
 *
 */
public final class HttpClientUtil
{

    /**
     * 第三方外部接口httpclient请求调用GET方法
     * @param url
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, Object> requestParams, Map<String, Object> headerParam) throws Exception
    {
        return doBaseService(url, requestParams, headerParam, new HttpGet());
    }

    /**
     * 第三方外部接口httpclient请求调用DELETE方法
     * @param url
     * @param requestParams
     * @param headerParam
     * @return
     * @throws Exception
     */
    public static String doDelete(String url, Map<String, Object> requestParams, Map<String, Object> headerParam) throws Exception
    {
        return doBaseService(url, requestParams, headerParam, new HttpDelete());
    }

    /**
     * 第三方外部接口httpclient请求调用POST方法
     * @param url
     * @param requestParams
     * @param headerParam
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> requestParams, Map<String, Object> headerParam) throws Exception
    {
        return doEntityService(url, requestParams, headerParam, new HttpPost(url));
    }

    /**
     * 第三方外部接口httpclient请求调用PUT方法
     * @param url
     * @param requestParams
     * @param headerParam
     * @return
     * @throws Exception
     */
    public static String doPut(String url, Map<String, Object> requestParams, Map<String, Object> headerParam) throws Exception
    {
        return doEntityService(url, requestParams, headerParam, new HttpPut(url));
    }
    
    public static String doEntityService(String url, Map<String, Object> requestParams,
            Map<String, Object> headerParam, HttpEntityEnclosingRequestBase request) throws Exception
    {
        // 设置请求头参数
        handleHeaderParam(request, headerParam);
        
        // 请求体参数
        handleEntityRequest(request, requestParams);
        
        return handleResponseResult(request);
    }

    /**
     * 通用接口调用处理
     * 适用于地址栏拼凑参数的请求
     * @param url
     * @param requestParam
     * @param headerParam
     * @param request
     * @return
     * @throws Exception
     */
    public static String doBaseService(String url, Map<String, Object> requestParam, Map<String, Object> headerParam, HttpRequestBase request) throws Exception
    {
        // 设置请求头参数
        handleHeaderParam(request, headerParam);
        
        // 请求体参数
        handleBaseRequest(request, url, requestParam);
        
        return handleResponseResult(request);
    }
    
    /**
     * 根据指定的RequestBase进行http远程接口调用
     * @param request
     * @return 请求结果的json字符串
     * @throws Exception
     */
    public static String handleResponseResult(HttpRequestBase request) throws Exception
    {
        Assert.notNull(request, "Request method can not be null!");
        
        // 使用默认的httpclient
        CloseableHttpClient client = HttpClients.createDefault();
        
        CloseableHttpResponse response = null;
        InputStream is = null;
        try
        {
            // 可释放的链接结果响应对象
            response = client.execute(request);
            is = response.getEntity().getContent();
//            String result = IOUtils.toString(is, PropertyUtil.getProperty("system.charset", "UTF-8"));
            String result = IOUtils.toString(is, "UTF-8");
            return result;
        }
        catch (Exception e)
        {
            LoggerUtil.error(HttpClientUtil.class, e);
            throw e;
        }
        finally
        {
            if (CommonUtil.isNotEmpty(is))
            {
                is.close();
            }
            if (CommonUtil.isNotEmpty(response))
            {
                response.close();
            }
        }
    }

    public static void handleHeaderParam(HttpRequestBase request, Map<String, Object> headerParam)
    {
        Assert.notNull(request, "Request method can not be null!");
        
        if (CommonUtil.isNotEmpty(headerParam))
        {
            Set<String> keySet = headerParam.keySet();
            for (String key : keySet)
            {
                request.setHeader(key, String.valueOf(headerParam.get(key)));
            }
        }        
    }

    /**
     * 地址栏拼凑参数
     * @param request
     * @param url
     * @param params
     * @throws URISyntaxException
     */
    public static void handleBaseRequest(HttpRequestBase request, String url, Map<String, Object> params) throws URISyntaxException
    {
        Assert.notNull(request, "Request method can not be null!");
        Assert.notNull(url, "Request url can not be null!");
        
        if (CommonUtil.isNotEmpty(params))
        {
            // 接口路径构造器
            URIBuilder build = new URIBuilder(url);
            List<NameValuePair> reqParam = handleRequestParam(params);
            if (CommonUtil.isNotEmpty(reqParam))
            {
                // 构造路径参数
                build.setParameters(reqParam);
            }

            // 请求方法对象
            request.setURI(build.build());
        }
    }
    
    /**
     * 表单提交封装实体Entity参数
     * @param request
     * @param params
     * @throws UnsupportedEncodingException
     */
    public static void handleEntityRequest(HttpEntityEnclosingRequestBase request, Map<String, Object> params) throws UnsupportedEncodingException
    {
        Assert.notNull(request, "Request method can not be null!");
        
        if (CommonUtil.isNotEmpty(params))
        {
            // 接口参数封装
            List<NameValuePair> list = handleRequestParam(params);
            if (CommonUtil.isNotEmpty(list))
            {
                // 表单form转码
//                request.setEntity(new UrlEncodedFormEntity(list, PropertyUtil.getProperty("system.charset", "UTF-8")));
                request.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }
        }
    }

    /**
     * 封装请求参数
     * @param params
     * @return
     */
    public static List<NameValuePair> handleRequestParam(Map<String, Object> params)
    {
        if (CommonUtil.isNotEmpty(params))
        {
            // 封装请求参数
            List<NameValuePair> list = new LinkedList<NameValuePair>();
            if (CommonUtil.isNotEmpty(params))
            {
                NameValuePair pair = null;

                // 组装请求参数
                Set<String> keys = params.keySet();
                for (String key : keys)
                {
                    pair = new BasicNameValuePair(key, JSON.toJSONString(params.get(key)));
                    list.add(pair);
                }
            }

            return list;
        }

        return null;
    }
    
    public static void main(String[] args) throws Exception
    {
        Map<String, Object> test = new HashMap<String, Object>();
        test.put("userName", "admin");
        test.put("password", "wzh8888");
        
        Map<String, Object> head = new HashMap<String, Object>();
        head.put("contentType", "application/json");
        System.err.println(HttpClientUtil.doPost("http://152x93g856.imwork.net:23948/maijiback/backLogin/checkingUsers.action", test, head));
    }

}
