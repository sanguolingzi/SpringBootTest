package com.yinhetianze.common.business.sys.errorcode.mapper;

import java.util.List;
import java.util.Map;

public interface CommonErrorCodeInfoMapper
{
    List<Map<String, Object>> getErrorCode(Map<String, Object> params);

    Map<String, Object> getErrorCodeByCode(String errorCode);
}