package com.yinhetianze.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 发送短信验证码工具类
 * @author Administrator
 *
 */
public class SendSmsUtil 
{
    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId = "LTAI5ATikrnJch2J";
    private static String accessKeySecret = "FhEWBxBQeT2nZQVkczwn7AnkDkKgDq";
    private static String signName="银合天泽";

	/**
	 * 短信模板号
	 */
	public static String sms_template_regist="SMS_128645757";
	public static String sms_template_verifyCode="SMS_128650672";

    
    private static IClientProfile profile;
    private static IAcsClient acsClient;
    
    private static SendSmsUtil instance = new SendSmsUtil();
    
    private SendSmsUtil()
    {
    	/*
    	Properties prop = new Properties();
		try 
		{
			prop.load(SendSmsUtil.class.getResourceAsStream("/systemConfig.properties"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			LoggerUtil.error(SendSmsUtil.class, e);
		}
		
		// 静态获取短信发送工具配置项

		accessKeyId = prop.getProperty("aliyun_sms_accessKeyId");
		accessKeySecret = prop.getProperty("aliyun_sms_accessKeySecret");
		signName = prop.getProperty("aliyun_sms_signName");

        //可自助调整超时时间
		String connectTimeout = "";
		if (CommonUtil.isNotEmpty(prop.getProperty("aliyun_sms_defaultConnectTimeout")))
		{
			connectTimeout = prop.getProperty("aliyun_sms_defaultConnectTimeout");
			System.setProperty("sun.net.client.defaultConnectTimeout", connectTimeout);
		}
        String readTimeout = "";
        if (CommonUtil.isNotEmpty(prop.getProperty("aliyun_sms_defaultReadTimeout")))
        {
        	readTimeout = prop.getProperty("aliyun_sms_defaultReadTimeout");
        	System.setProperty("sun.net.client.defaultReadTimeout", readTimeout);
        }
        */
        
        // 配置项
        profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try 
		{
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} 
		catch (ClientException e) 
		{
			e.printStackTrace();
			LoggerUtil.error(SendSmsUtil.class, e);
		}
		
		// 实例化短信发送对象
		acsClient = new DefaultAcsClient(profile);
    }
    
    public synchronized static SendSmsUtil getInstance()
    {
    	if (CommonUtil.isNull(instance))
    	{
    		instance = new SendSmsUtil();
    	}
    	return instance;
    }
	
	/**
	 * 通用发送短信模板方法
	 * @param templateCode 短信模板ID
	 * @param phoneNumbers 支持以逗号隔开的手机号码字符串
	 * @param params 参数键值对，最终会转化成json字符串传给短信接口调用
	 * @return
	 * @throws Exception
	 */
	public static boolean sendSms(String templateCode, String phoneNumbers, Map<String, Object> params) throws Exception
	{
		Assert.notNull(templateCode, "模板不能为空");
		Assert.notNull(phoneNumbers, "手机号码不能为空");
		
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setPhoneNumbers(phoneNumbers);
        request.setTemplateParam(transJson(params));
        
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (CommonUtil.isNotEmpty(sendSmsResponse))
        {
        	return sendSmsResponse.getCode().equals("OK");
        }
		return false;
	}
	
	private static String transJson(Object obj)
	{
		if (CommonUtil.isNull(obj))
		{
			return "{}";
		}
		return JSONObject.toJSONString(obj);
	}
}
