package com.yinhetianze.core.task;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;

import javax.servlet.ServletConfig;
import java.util.Arrays;
import java.util.List;

/**
 * 初始化加载模块预置条件以及数据
 * 伴随系统启动时加载
 * 使用时将此类添加到spring容器中注册即可
 * @author Administrator
 *
 */
public class InitModuleManager
{
    /**
     * InitModuleHandler子类集合，添加的所有子类实现均会在servlet启动时处理预置数据
     */
    private List<InitModuleHandler> initModuleHandlerList;

    /**
     * 自定义无参数构造
     * 适合手动实现加载运行启动项
     * 需要自行设置initModuleHandlerList
     */
    public InitModuleManager(){}

    /**
     * 带参数的构造方法
     * 使用spring自动注入时使用此方法注入所有实现了InitModuleHandler接口的交给sprig托管的实现类
     * @param initModuleHandlers
     */
    public InitModuleManager(InitModuleHandler...initModuleHandlers)
    {
        if (CommonUtil.isNotEmpty(initModuleHandlers))
        {
            initModuleHandlerList = Arrays.asList(initModuleHandlers);
        }
        initial(null);
    }
    
    /**
     * 初始化方法
     * 如果通过servlet加载时可设置servletConfig作为参数
     * @param config
     */
    public void initial(ServletConfig config)
    {
        if (CommonUtil.isNotEmpty(initModuleHandlerList))
        {
            for (InitModuleHandler handler : initModuleHandlerList)
            {
                try
                {
                    handler.init(config);
                }
                catch (Exception e)
                {
                    LoggerUtil.error(InitModuleManager.class, e);
                }
            }
        }
    }

    public List<InitModuleHandler> getInitModuleHandlerList()
    {
        return initModuleHandlerList;
    }

    public void setInitModuleHandlerList(List<InitModuleHandler> initModuleHandlerList)
    {
        this.initModuleHandlerList = initModuleHandlerList;
    }
}
