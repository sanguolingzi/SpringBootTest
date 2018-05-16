package com.yinhetianze.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.http.util.Asserts;
import org.springframework.util.AntPathMatcher;

import java.lang.reflect.Field;
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

    private static AntPathMatcher matcher = new AntPathMatcher();

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

    /**
     * 对象是与否判断
     * @param obj 需要判断的元数据对象
     * @param trueResult 为true的结果字符串
     * @return 当元数据对象的字符串格式内容与trueResult匹配时返回true，否则返回false
     */
    public static Boolean judgement(Object obj, String trueResult)
    {
        if (CommonUtil.isEmpty(obj) && CommonUtil.isEmpty(trueResult))
        {
            return true;
        }
        else if (CommonUtil.isNotEmpty(obj) && obj.toString().equals(trueResult))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断1/0对象的true/false，为1时返回true，为0时返回false
     * @param obj
     * @return
     */
    public static Boolean oneZeroJudge(Object obj)
    {
        return judgement(obj, "1");
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


    public static Field getField(Object obj, String fieldName){
        Field field = null;
        for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Field field = getField(obj,fieldName);
        if(field != null){
            field.setAccessible(true);
            try{
                return field.get(obj);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }


    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //--------------------------luoxiang add end--------------------------------------

    /**
     * 汉字转全拼
     * @param content
     * @return
     */
    public static String toHanyuPinyin(String content)
    {
        if (CommonUtil.isEmptyAfterTrim(content))
        {
            return null;
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 不带声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuffer sb = new StringBuffer();
        String[] obj = null;
        char[] charArr = content.toCharArray();
        for (char c : charArr)
        {
            try
            {
                obj = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (CommonUtil.isEmpty(obj))
                {
                    sb.append(c);
                }
                else
                {
                    sb.append(obj[0]);
                }
            }
            catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination)
            {
                LoggerUtil.warn(CommonUtil.class, "汉字转全拼发生异常：{}", new Object[]{c});
            }
        }

        return sb.toString();
    }

    /**
     * 默认获取中文字符的首字母小写
     * @param content
     * @return
     */
    public static String toHanyuPinyinFirst(String content)
    {
        return toHanyuPinyinFirst(content, false);
    }

    /**
     * 汉字转首字母，通过toupcate控制大小写
     * @param content
     * @param toUpcate
     * @return
     */
    public static String toHanyuPinyinFirst(String content, boolean toUpcate)
    {
        if (CommonUtil.isEmptyAfterTrim(content))
        {
            return null;
        }

        // 汉语拼音格式
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 设置首字母大小写
        defaultFormat.setCaseType(toUpcate ? HanyuPinyinCaseType.UPPERCASE : HanyuPinyinCaseType.LOWERCASE);
        // 不需要音调返回
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer sb = new StringBuffer();
        char[] charArr = content.toCharArray();
        String[] obj = null;
        for (char c : charArr)
        {
            try
            {
                obj = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                if (CommonUtil.isEmpty(obj))
                {
                    sb.append(c);
                }
                else
                {
                    sb.append(obj[0].charAt(0));
                }
            }
            catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination)
            {
                LoggerUtil.warn(CommonUtil.class, "汉字转拼音首字母发生异常：{}", new Object[]{c});
            }
        }

        return sb.toString();
    }

    /**
     * 生成随机token
     * @return
     */
    public static String generateToken()
    {
        return generateToken("", "");
    }

    /**
     * 生成随机token
     * @param header 指定的共同标志头
     * @return
     */
    public static String generateToken(String header)
    {
        return generateToken(header, "");
    }

    /**
     * 生成随机token
     * 由{header} + {TK} + {salt的字节数组首4位组成}
     * @param header
     * @param salt
     * @return
     */
    public static String generateToken(String header, String salt)
    {
        StringBuffer sb = new StringBuffer();

        // 添加标志头
        if (CommonUtil.isNotEmpty(header))
        {
            sb.append(header);
        }

        // 添加-与随机uuid
        sb.append("TK").append(CommonConstant.CHAR_HYPHEN);
        sb.append(UUID.randomUUID());

        // 获取盐值byte数组后的头8位
        if (CommonUtil.isNotEmpty(salt))
        {
            byte[] saltArr = salt.getBytes();
            for (int i = 0; i < 4 && i < saltArr.length; i ++)
            {
                sb.append(saltArr[i]);
            }
        }

        return sb.toString();
    }

    /**
     * 根据指定的路径模板判断请求地址是否匹配
     * @param pattern 路径模板内容，例如：/prod/**
     * @param uri 请求地址，例如：/prod/list
     * @return true为匹配，false为不匹配
     */
    public static Boolean pathMatch(String pattern, String uri)
    {
        Asserts.notBlank(pattern, "模板不能为空");
        Asserts.notBlank(uri, "匹配的路径不能为空");
        return matcher.match(pattern, uri);
    }

    public static void main(String[] args)
    {
        System.err.println(generateToken("", ""));
    }
}
