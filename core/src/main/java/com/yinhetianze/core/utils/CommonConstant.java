package com.yinhetianze.core.utils;

public class CommonConstant
{
	/**
	 * 常用语义标识
	 */
    public static final String OK = "ok";
    public static final String FINISH = "finish";
    public static final String DONE = "done";
    public static final String ERROR = "error";
    public static final String FAILED = "failed";
    public static final String SUCCESS = "success";
    
    /**
     * *字符
     */
    public static final String CHAR_STAR = "*";
    
    /**
     * 空字符
     */
    public static final String CHAR_BLANK = " ";
    
    /**
     * 问号字符
     */
    public static final String CHAR_QUESTION = "?";
    
    /**
     * 正斜杠字符
     */
    public static final String CHAR_SLASH = "/";
    
    /**
     * 下划线字符
     */
    public static final String CHAR_UNDERLINE = "_";
    
    /**
     * 中划线字符/连字符
     */
    public static final String CHAR_HYPHEN = "-";
    
    /**
     * 冒号字符
     */
    public static final String CHAR_COLON = ":";

    /**
     * 分号字符
     */
    public static final String CHAR_SEMICOLON = ";";
    
    /**
     * 小于字符
     */
    public static final String CHAR_LT = "<";
    
    /**
     * 大于字符
     */
    public static final String CHAR_GT = ">";
    
    /**
     * 等于字符
     */
    public static final String CHAR_EQ = "=";
    
    /**
     * 并且字符
     */
    public static final String CHAR_AND = "&";
    
    /**
     * 操作系统位数
     */
    public static final String OS_BIT_TYPE_64 = "64";
    public static final String OS_BIT_TYPE_32 = "32";
    
    /**
     * 登陆状态
     */
    public static final String LOGIN_STATUS_SUCCESS = "success";
    public static final String LOGIN_STATUS_TIMEOUT = "timeout";
    public static final String LOGIN_STATUS_LOGOUT = "logout";
    
    /**
     * 数字数组
     */
    public static final int[] NUMBERS_ARRAY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    /**
     * 小写字母数组
     */
    public static final char[] CHARS_ARRAY = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    
    /**
     * 大写字母数组
     */
    public static final char[] CAPS_ARRAY = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'U', 'Z'};
    
    /**
     * 数字与字母
     */
    public static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    /**
     * True or false
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    
    /**
     * 标点符号
     */
    public static final String CHAR_DOT = ".";
    public static final String CHAR_COMMA = ",";
    
    /**
     * 日志级别
     */
    public static final String LOG_LEVEL_DEBUG = "debug";
    public static final String LOG_LEVEL_INFO = "info";
    public static final String LOG_LEVEL_ERR = "error";
    
    /**
     * 使用1与不使用0
     */
    public static final String ENABLE_NUM = "1";
    public static final String DISABLE_NUM = "0";
    
    public static final String LEFT = "left";
    public static final String RIGHT = "right";
    
    /**
     * email正则表达式常量
     */
    public static final String EMAIL_REGEX = "[^0-9][a-zA-Z0-9]*+@[a-zA-Z0-9]++.[^0-9]++";
    public static final String DIGITAL_REGEX = "\\d+"; // 纯数字字符串正则常量
    public static final String CHAR_REGEX = "\\D+"; // 纯字符串正则常量
    public static final String ASCII_REGEX = "[0-9a-zA-Z]+"; // 数字与字符正则常量
    public static final String MONEY_REGEX="^(?!0+(?:\\.0+)?$)(?:[1-9]\\d*|0)(?:\\.\\d{1,2})?$";//金额正则
    
    public static final String EXCEPTION = "EXCEPTION";
    public static final String MESSAGE = "MESSAGE";
    
    /**
     * UTF-8字符集
     */
    public static final String CHARSET_UTF8 = "UTF-8";
    
    /**
     * 语言locale字符串
     */
    public static final String ZH_CN = "zh_CN";
    public static final String EN_US = "en_US";
}
