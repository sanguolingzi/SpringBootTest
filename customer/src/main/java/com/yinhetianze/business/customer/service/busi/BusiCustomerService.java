package com.yinhetianze.business.customer.service.busi;

import com.yinhetianze.business.customer.model.BusiRegeisterModel;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;

public interface BusiCustomerService
{
    int insert(BusiCustomerPojo record);

    int insertSelective(BusiCustomerPojo record);

    int updateByPrimaryKeySelective(BusiCustomerPojo record);

    int updateByPrimaryKey(BusiCustomerPojo record);

    /**
     * 新增用户基本信息
     * @param busiCustomerPojo
     * @return
     * @throws BusinessException
     */
    int addCustomerInfo(BusiCustomerPojo busiCustomerPojo)   throws BusinessException ;

    /**
     * 注册 调用 新增用户信息
     * @param busiRegeisterModel
     * @return
     * @throws BusinessException
     */
    int addRegeisterCustomer(BusiRegeisterModel busiRegeisterModel) throws BusinessException;
}