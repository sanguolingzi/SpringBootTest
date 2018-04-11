package com.yinhetianze.core.utils;

import org.slf4j.LoggerFactory;

/**
 * 日志记录工具类
 * 基于slf4j，适配log4j和logback
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class LoggerUtil 
{
    
    /**
     * 记录debug日志
     * @param clazz
     * @param message
     */
    public static void debug(Class clazz, String message)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).debug(message);
        }
    }

    /**
     * 记录debug日志
     * @param clazz
     * @param message
     */
    public static void debug(Class clazz, String message, Object[] params)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).debug(message, params);
        }
    }

    /**
     * 记录debug日志
     * @param clazz
     */
    public static void debug(Class clazz, Throwable e)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).debug(e.toString(), e);
        }
    }

    /**
     * 记录info日志
     * @param clazz
     * @param message
     */
    public static void info(Class clazz, String message)
    {
        info(clazz, message, "");
    }

    /**
     * 记录info日志
     * @param clazz
     * @param message
     */
    public static void info(Class clazz, String message, Object...params)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).info(message, params);
        }
    }

    /**
     * 记录info日志
     * @param clazz
     */
    public static void info(Class clazz, Throwable e)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).info(e.toString(), e);
        }
    }

    /**
     * 记录warn日志
     * @param clazz
     * @param message
     */
    public static void warn(Class clazz, String message)
    {
        warn(clazz, message, null);
    }

    /**
     * 记录warn日志
     * @param clazz
     * @param message
     */
    public static void warn(Class clazz, String message, Object[] params)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).warn(message, params);
        }
    }

    /**
     * 记录warn日志
     * @param clazz
     */
    public static void warn(Class clazz, Throwable e)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).warn(e.toString(), e);
        }
    }

    /**
     * 记录error日志
     * @param clazz
     * @param message
     */
    public static void error(Class clazz, String message)
    {
        error(clazz, message, null);
    }

    /**
     * 记录error日志
     * @param clazz
     * @param message
     */
    public static void error(Class clazz, String message, Object[] params)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).error(message, params);
        }
    }

    /**
     * 记录error日志
     * @param clazz
     */
    public static void error(Class clazz, Throwable e)
    {
        if (CommonUtil.isNotEmpty(clazz))
        {
            LoggerFactory.getLogger(clazz).error(e.toString(), e);
        }
    }
    
}
