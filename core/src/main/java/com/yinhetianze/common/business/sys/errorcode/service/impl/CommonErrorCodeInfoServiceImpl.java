package com.yinhetianze.common.business.sys.errorcode.service.impl;

import com.yinhetianze.common.business.sys.errorcode.service.ErrorCodeInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yinhetianze.common.business.sys.errorcode.mapper.CommonErrorCodeInfoMapper;

import java.util.List;
import java.util.Map;

@Service
public class CommonErrorCodeInfoServiceImpl implements ErrorCodeInfoService
{
    @Autowired
    private CommonErrorCodeInfoMapper mapper;

    public List<Map<String, Object>> getErrorCodeMap(Map<String, Object> params)
    {
        return mapper.getErrorCode(params);
    }

    public Map<String, Object> getErrorCodeByCode(String errorCode)
    {
        return mapper.getErrorCodeByCode(errorCode);
    }
}