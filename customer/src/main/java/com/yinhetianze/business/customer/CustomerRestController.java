package com.yinhetianze.business.customer;

import com.yinhetianze.business.customer.model.*;
import com.yinhetianze.core.business.BusinessExecutor;
import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.springmvc.controller.RestfulController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator
 * on 2018/1/28.
 */
@RestController
@RequestMapping("customer")
public class CustomerRestController extends RestfulController
{
    /**---------------------------------POST START   ------------------------------------------------------------**/
    /**
     * 新增消费者/会员 信息
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping
    public Object addCustomerInfoExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("addCustomerInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }


    /**
     * 修改消费者/会员 信息
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/updateCustomerInfo")
    public Object updateCustomerInfoExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("updateCustomerInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }

    /**
     * 删除消费者/会员 信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/deleteCustomer")
    public Object deleteCustomerInfoExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody  BusiCustomerModel busiCustomerModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("deleteCustomerInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiCustomerModel);
    }

    /**
     * 消费者/会员 实名认证
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/checkRealName")
    public Object updateRealNameInfoExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("updateRealNameInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }


    /**
     * 新增 消费者/会员 收货地址
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/addAddress")
    public Object addCustomerAddressExecutor(HttpServletRequest request, HttpServletResponse response, @RequestBody BusiCustomerReceiveaddressModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("addCustomerAddressExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }

    /**
     * 修改 消费者/会员 收货地址
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/updateAddress")
    public Object updateCustomerAddressExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerReceiveaddressModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("updateCustomerAddressExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }


    /**
     * 设置 消费者/会员 默认收货地址
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/updateDefaultAddress")
    public Object updateDefaultAddressExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerReceiveaddressModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("updateDefaultAddressExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }

    /**
     * 删除 消费者/会员 收货地址
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/deleteCustomerAddress")
    public Object DeleteCustomerAddressExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiCustomerReceiveaddressModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("deleteCustomerAddressExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }


    /**
     * 消费者/会员 注册
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value="/regeister")
    public Object regeisterExecutor(HttpServletRequest request, HttpServletResponse response,@RequestBody BusiRegeisterModel model)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("regeisterExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }


    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object LoginExecutor(HttpServletRequest request, HttpServletResponse response, @RequestBody BusiCustomerModel busiCustomerModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("loginExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiCustomerModel);
    }


    /**
     * 修改消费者/会员 账户信息
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/updateBankroll")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object UpdateCustomerBankrollExecutor(HttpServletRequest request, HttpServletResponse response, @RequestBody  BusiUpdateBankrollModel busiUpdateBankrollModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("updateCustomerBankrollExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiUpdateBankrollModel);
    }

    /**
     * 校验短信验证码
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/checkSmsCode")
    public Object CheckSmsCodeExecutor(HttpServletRequest request, HttpServletResponse response, @RequestBody  BusiSmsCodeModel busiSmsCodeModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("checkSmsCodeExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiSmsCodeModel);
    }

    /**---------------------------------POST END ------------------------------------------------------------**/









     /**---------------------------------GET  START   ------------------------------------------------------------**/
    /**
     * 查询消费者/会员 信息
     * @param request
     * @param response
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    //@JSON(type=BusiCustomerPojo.class,filter = "accountStatus,loginPassword,payPassword,delFlag")
    public Object getCustomerInfoExecutor(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, null,id);
    }

    /**
     * 查询账户信息
     * @param request
     * @param response
     * @param customerId 消费者id
     * @return
     */
    @GetMapping(value = "/bankroll/{customerId}")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object getCustomerBankrollInfoExecutor(HttpServletRequest request, HttpServletResponse response, @PathVariable String customerId)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerBankrollInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, null,customerId);
    }


    /**
     * 查询账户流水
     * @param request
     * @param response
     * @return pageSize=10&currentPage=1&customerId=&flowCategory=&flowType=&flow_number=
     */
    @GetMapping(value = "/bankrollFlow/page")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object getCustomerBankrollFlowInfoExecutor(HttpServletRequest request, HttpServletResponse response, BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerBankrollFlowInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiCustomerBankrollFlowModel);
    }

    /**
     * 查询推荐信息
     * @param request
     * @param response
     * @param {"pageSize":10,"currentPage":1,"params":{"recommendId":22}}
     * @return
     */
    @GetMapping(value = "/recommend/page")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object getCustomerRecommendInfoExecutor(HttpServletRequest request, HttpServletResponse response, BusiCustomerRecommendRelationModel busiCustomerRecommendRelationModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerRecommendInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiCustomerRecommendRelationModel);
    }



    /**
     * 查询消费者/会员 收货地址
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/address/{addressId}")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object getCustomerAddressExecutor(HttpServletRequest request, HttpServletResponse response, @PathVariable String addressId)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerAddressExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, null,addressId);
    }

    /**
     * 查询消费者/会员 收货地址列表
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/addressList/{customerId}")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object getCustomerAddressListExecutor(HttpServletRequest request, HttpServletResponse response, @PathVariable String customerId)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerAddressListExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, null,customerId);
    }


    /**
     * 获取短信验证码
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/getSmsCode")
    public Object GetSmsCodeExecutor(HttpServletRequest request, HttpServletResponse response, BusiSmsCodeModel busiSmsCodeModel)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getSmsCodeExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, busiSmsCodeModel);
    }


    /**
     * 查询消费者个人中心
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/center/{customerId}")
    //@JSON(type= BusiCustomerBankrollPojo.class,filter = "createTime,delFlag,updateTime")
    public Object GetCustomerCenterInfoExecutor(HttpServletRequest request, HttpServletResponse response, @PathVariable String customerId)
    {
        // 执行业务
        BusinessExecutor<String> executor = (BusinessExecutor<String>)ApplicationContextFactory.getBean("getCustomerCenterInfoExecutor");
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, null,customerId);
    }
    /**---------------------------------GET  END   ------------------------------------------------------------**/


    /**---------------------------------PUT  START   ------------------------------------------------------------**/

    /**---------------------------------PUT  START   ------------------------------------------------------------**/



    /**---------------------------------DELETE  START   ------------------------------------------------------------**/

    /**---------------------------------DELETE  END   ------------------------------------------------------------**/


    /*
    @RequestMapping(value = "{api_path}", method = RequestMethod.GET)
    public Object get(HttpServletRequest request, HttpServletResponse response, BasicModel model, @PathVariable("api_path") String path)
    {
        // 执行业务
        BusinessExecutor<?> executor = getExecutor(path, RequestMethod.GET);
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }

    @RequestMapping(value = "{api_path}", method = RequestMethod.POST)
    public Object post(HttpServletRequest request, HttpServletResponse response, BasicModel model, @PathVariable("api_path") String path)
    {
        BusinessExecutor<?> executor = getExecutor(path, RequestMethod.POST);
        return CommonUtil.isNull(executor) ? null : executor.execute(request, response, model);
    }

    /**
     * 根据请求路径获取匹配的接口业务执行器
     * @param path
     * @param method
     * @return

    private BusinessExecutor<?> getExecutor(String path, RequestMethod method)
    {
        // 获取接口列表映射
        ApiListProperties properties = (ApiListProperties) ApplicationContextFactory.getBean("apiListProperties");
        if (CommonUtil.isNull(properties))
        {
            LoggerUtil.warn(CustomerRestController.class, "没有获取到配置的接口列表：[apiListProperties]");
            return null;
        }

        // 根据请求方法获取对应的api列表
        Properties apiList = getPropertiesByRequestMethod(method, properties);
        if (CommonUtil.isEmpty(apiList))
        {
            LoggerUtil.warn(CustomerRestController.class, "没有配置Get方法对应的API列表");
            return null;
        }

        // 获取业务处理类
        String executorId = apiList.getProperty(path);
        if (CommonUtil.isEmpty(executorId))
        {
            LoggerUtil.warn(CustomerRestController.class, "没有匹配到Executor：{}", new Object[]{executorId});
            return null;
        }

        BusinessExecutor<?> executor = (BusinessExecutor<?>) ApplicationContextFactory.getBean(executorId);
        return executor;
    }
     */
    /**
     * 根据请求方法获对应的取接口列表
     * @param method
     * @param properties
     * @return
    private Properties getPropertiesByRequestMethod(RequestMethod method, ApiListProperties properties)
    {
        switch (method)
        {
            case GET:
                return properties.getGet();
            case POST:
                return properties.getPost();
            case PUT:
                return properties.getPut();
            case DELETE:
                return properties.getDelete();
            default:
                return null;
        }
    }
    */
}
