package com.yinhetianze.common.business.sys.errorcode.service;

import java.util.List;
import java.util.Map;

public interface ErrorCodeInfoService
{
    List<Map<String, Object>> getErrorCodeMap(Map<String, Object> params);

    Map<String, Object> getErrorCodeByCode(String errorCode);
}