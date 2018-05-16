package com.yinhetianze.core.task;

import javax.servlet.ServletConfig;

/**
 * 系统初始化预置数据接口
 * 子类实现此接口，并配置在spring容器中，通过InitModuleManager加载配置的实现类
 * @author Administrator
 *
 */
public interface InitModuleHandler
{
    /**
     * 初始化预置数据方法
     * @param config 如果是servlet配置加载，可通过config传递参数
     * @throws Exception
     */
    void init(ServletConfig config) throws Exception;
}
