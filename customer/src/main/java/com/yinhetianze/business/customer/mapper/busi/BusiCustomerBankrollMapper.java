package com.yinhetianze.business.customer.mapper.busi;

import com.yinhetianze.mybatis.mapper.BusiMapper;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface BusiCustomerBankrollMapper extends BusiMapper<BusiCustomerBankrollPojo> {

    int updateBankrollAmount(@Param("id") Integer bankrollId, @Param("amount") BigDecimal amount, @Param("oldAmount") BigDecimal oldAmount,@Param("flowType") Short flowType);

    int updateBankrollIntegral(@Param("id") Integer bankrollId, @Param("integral") BigDecimal integral,@Param("oldIntegral") BigDecimal oldIntegral,@Param("flowType") Short flowType);

    int updateBankrollStarCoin(@Param("id") Integer bankrollId, @Param("starCoin") BigDecimal starCoin,@Param("oldStarCoin") BigDecimal oldStarCoin,@Param("flowType") Short flowType);
}