package com.yinhetianze.configurations.apilist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Administrator
 * on 2018/1/28.
 */
@Component
@PropertySource(value = "classpath:api-list.properties")
@ConfigurationProperties(prefix = "api-list")
public class ApiListProperties
{
    private Properties get;

    private Properties post;

    private Properties put;

    private Properties delete;

    public Properties getGet()
    {
        return get;
    }

    public void setGet(Properties get)
    {
        this.get = get;
    }

    public Properties getPost()
    {
        return post;
    }

    public void setPost(Properties post)
    {
        this.post = post;
    }

    public Properties getPut()
    {
        return put;
    }

    public void setPut(Properties put)
    {
        this.put = put;
    }

    public Properties getDelete()
    {
        return delete;
    }

    public void setDelete(Properties delete)
    {
        this.delete = delete;
    }
}
