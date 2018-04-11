package com.yinhetianze.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator
 * on 2017/12/31.
 */
public class CommonUtil
{
    private static ObjectMapper om = new ObjectMapper();

    /**
     * 判断对象是否为空：null
     * @param obj 目标对象
     * @return true=空，false=非空
     */
    public static Boolean isNull(Object obj)
    {
        return obj == null;
    }

    /**
     * 判断对象是否非空：!=null
     * @param obj 目标对象
     * @return true!=null非空，false==null空
     */
    public static Boolean isNotNull(Object obj)
    {
        return !isNull(obj);
    }

    /**
     * 判断对象是否含有元素
     * 适用于string, collection, map, 数组是否包含子元素
     * string != null 并且 string != ""
     * collection != null 并且size 大于0
     * map != null 并且size大于0
     * 数组不等于null并且length大于0
     * @param obj 判断目标
     * @return true=为空或不包含元素，false=不为空且包含元素
     */
    public static Boolean isEmpty(Object obj)
    {
        if (isNotNull(obj))
        {
            if (obj instanceof Collection)
            {
                return ((Collection) obj).size() == 0;
            }
            else if (obj instanceof Map)
            {
                return ((Map) obj).size() == 0;
            }
            else if (obj instanceof String)
            {
                return ((String) obj).length() == 0;
            }
            else if (obj instanceof Object[])
            {
                return ((Object[]) obj).length == 0;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    /**
     * isEmpty的反向判断
     * @param obj
     * @return
     */
    public static Boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

    /**
     * 判断字符串去除头尾空格后是否为空
     * @param str 目标字符串
     * @return true=为空，false=非空
     */
    public static Boolean isEmptyAfterTrim(String str)
    {
        if (isNotEmpty(str))
        {
            return str.trim().length() == 0 ? true : false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 获取指定长度的随机数字字符串
     * @param length
     * @return
     */
    public static String getRandNum(Integer length)
    {
        return getRandNum(length, null);
    }

    private static String getRandNum(Integer length, String source)
    {
        if (isNull(length))
        {
            return null;
        }

        String str = (Math.random() + (isNull(source) ? "" : source)).substring(2);
        if (str.length() >= length)
        {
            return str.substring(0, length);
        }
        else
        {
            return getRandNum(length, str);
        }
    }

    /**
     * 将对象序列化成json字符串
     * @param obj
     * @return
     */
    public static String objectToJsonString(Object obj)
    {
        if (isNotEmpty(obj))
        {
            try
            {
                return om.writeValueAsString(obj);
            }
            catch (JsonProcessingException e)
            {
                LoggerUtil.error(CommonUtil.class, e);
            }
        }
        return "";
    }

    public static void main(String[] args)
    {
        for (int j =0; j < 100; j ++)
        {
            for (int i = 0; i < 100; i++)
            {
                System.err.println(getRandNum(i, null));
            }
        }
    }

    /**
     * json字符串转指定类型的对象
     * @param content
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T readFromString(String content, Class<T> cls) throws Exception
    {
        return om.readValue(content, cls);
    }

    /**
     * jackson使用javaType将json转为对象
     * @param content
     * @param type
     * @return
     * @throws Exception
     */
    public static <T> T readFromString(String content, JavaType type) throws Exception
    {
        return om.readValue(content, type);
    }
    //--------------------------luoxiang add --------------------------------------
    /**
     * 获取25位流水号(年月日时分秒毫秒+8位随机数)
     */
    public static String getSerialnumber() {
        SimpleDateFormat fmt = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        String dateStr = fmt.format(new java.util.Date());
        String randomStr = String.valueOf(Math.random());
        randomStr = randomStr.substring(2, 10);
        return dateStr + randomStr;
    }


    /**
     * 将List<Map> 映射成 对应的 List<bean>
     * @param list
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> refelctListBean(List<Map> list , Class<T> tClass) throws Exception{
        List<T> returnList = new ArrayList<T>();
        for(Map map:list){
            T temp = refelctBean(map,tClass);
            returnList.add(temp);
        }
        return returnList;
    }


    /**
     * 将Map 映射成 对应的bean
     * 目前只支持基本类型 以及 非嵌套结构
     * @param map  参数Map
     * @param <T>
     * @return 待转换对象 t
     */
    public static <T> T refelctBean(Map map , Class<T> tClass){
        Object obj = null;
        try {
            obj = tClass.newInstance();
            Method[] methods = tClass.getDeclaredMethods();
            for (Method method : methods) {
                if(method.getName().startsWith("set")){
                    String key = method.getName().replace("set", "");
                    key = key.substring(0, 1).toLowerCase().concat(key.substring(1));
                    Object value = map.get(key);
                    if(value==null || value.equals("N/A")) continue;
                    Class<?>[]  paramType = method.getParameterTypes();
                    //根据参数类型执行对应的set方法给vo赋值
                    if(paramType[0] == String.class){
                        method.invoke(obj, String.valueOf(value));
                        continue;
                    }else if(paramType[0] == BigDecimal.class){
                        method.invoke(obj, new BigDecimal(value.toString()));
                        continue;
                    }else if(paramType[0] == Double.class){
                        method.invoke(obj, Double.parseDouble(value.toString()));
                        continue;
                    }else if(paramType[0] == java.util.Date.class){
                        Date d  = new Date();
                        d.setTime(Long.valueOf(value.toString()));
                        method.invoke(obj,d);
                        continue;
                    }else if(paramType[0] == int.class || paramType[0] == Integer.class){
                        method.invoke(obj, Integer.valueOf(value.toString()));
                        continue;
                    }else if(paramType[0] == Boolean.class){
                        method.invoke(obj, Boolean.parseBoolean(value.toString()));
                        continue;
                    }else if(paramType[0] == char.class || paramType[0] == Character.class){
                        method.invoke(obj, value.toString().charAt(0));
                        continue;
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T)obj;
    }


    //--------------------------luoxiang add end--------------------------------------
}
