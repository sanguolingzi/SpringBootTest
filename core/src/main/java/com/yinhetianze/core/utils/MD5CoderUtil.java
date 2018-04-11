package com.yinhetianze.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5CoderUtil
{
    public static char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    public static String ALGORITHM = "MD5";

    /**
     * @return 获取MD5编码字符串值，算法不可逆。
     */
    public static String encode(String content)
    {
        MessageDigest md5;
        try
        {
            md5 = MessageDigest.getInstance(ALGORITHM);
            byte[] md5Password = md5.digest(content.getBytes());
            int j = md5Password.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md5Password[i];
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }
}
