package com.yinhetianze.core.business.httprequest;

import java.io.Serializable;

/**
 * Created by yinhetianze
 * on 2018/1/26.
 * 请求参数封装model基础类
 * 所有请求参数封装对象均需封装成basicmodel子类进行传递
 */
public class BasicModel implements Serializable
{
    /**
     * model名字
     */
    protected String modelName;

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }
}
