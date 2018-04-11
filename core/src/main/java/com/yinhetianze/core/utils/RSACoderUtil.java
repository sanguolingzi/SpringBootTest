package com.yinhetianze.core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密算法RSA算法组件 非对称算法一般是用来传送对称加密算法的密钥来使用的，
 * 相对于DH算法，RSA算法只需要一方构造密钥，不需要大费周章的构造各自本地的密钥对了。
 * DH算法只能算法非对称算法的底层实现。而RSA算法算法实现起来较为简单
 * 
 * @author zhangwb
 */
public class RSACoderUtil
{

    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static final String PRIVATE_KEY = "RSAPrivateKey";

    private static String publicKey;

    private static String primaryKey;

    /**
     * 非对称密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 密钥长度，DH算法的默认密钥长度是1024 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 生成密钥对
     * 
     * @return Map 甲方密钥的Map
     */
    public static Map<String, Key> genKeyPair() throws Exception
    {
        // 实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        // 初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 密钥对公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 密钥对私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 将密钥存储在map中
        Map<String, Key> keyMap = new HashMap<String, Key>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 通过秘钥对象Key获取秘钥字符串
     * 
     * @param key
     * @return
     */
    public static String getKey(Key key)
    {
        return new String(Base64.encodeBase64(key.getEncoded()));
    }

    /**
     * 将给定的密钥字符串转换成Key getEncode之后的密钥数组
     * 
     * @param keyStr
     * @return
     */
    public static byte[] getKeyByte(String keyStr)
    {
        byte[] encodedKeyByte = keyStr.getBytes();
        byte[] keyByte = Base64.decodeBase64(encodedKeyByte);
        return keyByte;
    }

    /**
     * 私钥加密
     * @param data
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String primaryKey) throws Exception
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        byte[] encodedKeyByte = primaryKey.getBytes();
        byte[] keyByte = Base64.decodeBase64(encodedKeyByte);

        byte[] encodeData = encryptByPrivateKey(data.getBytes(), keyByte);
        return new String(Base64.encodeBase64(encodeData));
    }

    /**
     * 私钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data) throws Exception
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        byte[] encodedKeyByte = getPrivateKey().getBytes();
        byte[] keyByte = Base64.decodeBase64(encodedKeyByte);

        byte[] encData = encryptByPrivateKey(data.getBytes(), keyByte);
        return new String(Base64.encodeBase64(encData));
    }

    /*
     * 私钥加密
     * 
     * @param data 待加密数据byte数组
     * 
     * @param key 密钥byte数组
     * 
     * @return byte[] 加密数据
     */
    private static byte[] encryptByPrivateKey(byte[] data, byte[] primaryKey) throws Exception
    {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(primaryKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 将源数据使用公钥解密
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data, String publicKey) throws Exception
    {
        // 不打开加密，那么也不解密
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        byte[] publicKeyEncByte = publicKey.getBytes();
        byte[] publicKeyByte = Base64.decodeBase64(publicKeyEncByte);

        byte[] dataEncByte = data.getBytes();
        byte[] dataByte = Base64.decodeBase64(dataEncByte);

        return new String(decryptByPublicKey(dataByte, publicKeyByte));
    }

    /**
     * 将源数据使用公钥解密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data) throws Exception
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        return new String(decryptByPublicKey(data, getPublicKey()));
    }

    /*
     * 公钥解密
     * 
     * @param data 待解密数据
     * 
     * @param key 密钥
     * 
     * @return byte[] 解密数据
     */
    private static byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception
    {

        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 初始化公钥
        // 密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);

        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        // 数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * 
     * @param data
     *            待加密数据
     * @return byte[] 加密数据
     */
    public static String encryptByPublicKey(String data)
    {
        return encryptByPublicKey(data, getPublicKey());
    }

    /**
     * 公钥加密, 默认utf-8字符集
     * 
     * @param data
     *            待加密数据
     * @param publicKey
     *            密钥
     * @return byte[] 加密数据
     */
    public static String encryptByPublicKey(String data, String publicKey)
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/
        if (CommonUtil.isEmpty(data))
        {
            return null;
        }
        if (CommonUtil.isEmpty(publicKey))
        {
            return null;
        }
        
        try
        {
            byte[] pubKeyEncByte = publicKey.getBytes();
            byte[] pubKeyByte = Base64.decodeBase64(pubKeyEncByte);
    
            byte[] dataByte = data.getBytes();
            byte[] dataEncbyte = encryptByPublicKey(dataByte, pubKeyByte);
    
            return new String(Base64.encodeBase64(dataEncbyte));
        }
        catch (Exception e)
        {
            LoggerUtil.error(RSACoderUtil.class, "RSA加密发生异常：{}", new Object[]{e.toString()});
            return null;
        }
    }

    /*
     * 公钥加密
     * 
     * @param data 待加密数据
     * 
     * @param key 密钥
     * 
     * @return byte[] 加密数据
     */
    private static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception
    {
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 初始化公钥
        // 密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);

        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        // 数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * 
     * @param data
     *            待解密数据
     * @param primaryKey
     *            密钥
     * @return byte[] 解密数据
     */
    public static String decryptByPrivateKey(String data, String primaryKey) throws Exception
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        byte[] priKeyEncByte = primaryKey.getBytes();
        byte[] priKeyByte = Base64.decodeBase64(priKeyEncByte);

        byte[] dataEncByte = data.getBytes();
        byte[] dataByte = Base64.decodeBase64(dataEncByte);

        byte[] dataDecByte = decryptByPrivateKey(dataByte, priKeyByte);
        return new String(dataDecByte);
    }

    /**
     * 私钥解密
     * 
     * @param data
     *            待解密数据
     * @return byte[] 解密数据
     */
    public static String decryptByPrivateKey(String data) throws Exception
    {
        /*if (!CommonUtil.isEncodeEnable())
        {
            return data;
        }*/

        byte[] priKeyEncByte = getPrivateKey().getBytes();
        byte[] priKeyByte = Base64.decodeBase64(priKeyEncByte);

        byte[] dataEncByte = data.getBytes();
        byte[] dataByte = Base64.decodeBase64(dataEncByte);

        byte[] dataDecByte = decryptByPrivateKey(dataByte, priKeyByte);
        return new String(dataDecByte);
    }

    /*
     * 私钥解密
     * 
     * @param data 待解密数据
     * 
     * @param key 密钥
     * 
     * @return byte[] 解密数据
     */
    private static byte[] decryptByPrivateKey(byte[] data, byte[] primaryKey) throws Exception
    {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(primaryKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * RSA签名
     * 
     * @param content
     *            待签名数据
     * @param encode
     *            字符集编码
     * @return 签名值
     */
    public static String sign(String content, String encode)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(getPrivateKey()));
            KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            if (CommonUtil.isNotEmpty(encode))
            {
                signature.update(content.getBytes(encode));
            }
            else
            {
                signature.update(content.getBytes());
            }

            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(String content)
    {
        return sign(content, null);
    }

    /**
     * RSA验签名检查
     * 
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param publicKey
     *            分配给开发商公钥
     * @param encode
     *            字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            if (CommonUtil.isNotEmpty(encode))
            {
                signature.update(content.getBytes(encode));
            }
            else
            {
                signature.update(content.getBytes());
            }

            return signature.verify(Base64.decodeBase64(sign));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 重载
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean doCheck(String content, String sign, String publicKey)
    {
        return doCheck(content, sign, publicKey, null);
    }

    /**
     * 重载
     * @param content
     * @param sign
     * @return
     * @throws Exception 
     */
    public static boolean doCheck(String content, String sign) throws Exception
    {
        return doCheck(content, sign, getPublicKey(), null);
    }

    /**
     * 获取完整私钥字符串 分解的私钥有助于提高系统加密安全性，但是也增加了操作复杂度
     * 
     * @return
     * @throws Exception
     */
    private static String getPrivateKey() throws Exception
    {
        if (CommonUtil.isEmpty(primaryKey))
        {
            primaryKey = FileUtil.readFileFromClasspath("privateKey.keystore");
        }

        return primaryKey;
    }

    /**
     * 读取公钥文件返回公钥字符串
     * 
     * @return
     * @throws Exception
     */
    private static String getPublicKey()
    {
        try
        {
            if (CommonUtil.isEmpty(publicKey))
            {
                publicKey = FileUtil.readFileFromClasspath("publicKey.keystore");
            }
            return publicKey;
        }
        catch (Exception e)
        {
            LoggerUtil.error(RSACoderUtil.class, "获取RSA公钥失败：{}", new Object[]{e.toString()});
            return null;
        }
    }
    
    /**
     * 更新秘钥对
     * @throws Exception 
     */
    public static void updateKeyPair() throws Exception
    {
        try
        {
            // 生成新的秘钥对
            Map<String, Key> keyPair = genKeyPair();
            
            // 获取公钥内容
            String publicKey = getKey(keyPair.get(PUBLIC_KEY));
            // 获取私钥内容
            String privateKey = getKey(keyPair.get(PRIVATE_KEY));
            
            // 更新公钥信息
            updatePublicKey(publicKey);
            // 更新私钥信息
            updatePrivateKey(privateKey);
            
            RSACoderUtil.publicKey = publicKey;
            RSACoderUtil.primaryKey = privateKey;
        }
        catch (Exception e)
        {
            LoggerUtil.error(RSACoderUtil.class, "更新秘钥对失败：{}", new Object[]{e.toString()});
            throw e;
        }
    }

    /**
     * 主动更新公钥内容
     * 
     * @throws Exception
     */
    private static void updatePublicKey(String publicKey) throws Exception
    {
        // 将秘钥写入公钥的文件中
        String fileName = RSACoderUtil.class.getResource("/").getFile() + CommonConstant.CHAR_SLASH + "publicKey.keystore";
        FileUtil.writeFile(fileName, publicKey);
    }

    /**
     * 主动更新公钥内容
     * @param primaryKey
     * 
     * @throws Exception
     */
    private static void updatePrivateKey(String primaryKey) throws Exception
    {
        String fileName = RSACoderUtil.class.getResource("/").getFile() + CommonConstant.CHAR_SLASH + "privateKey.keystore";
        FileUtil.writeFile(fileName, primaryKey);
    }

}
