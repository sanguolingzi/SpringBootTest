package com.yinhetianze.common.business.sys.channel.service;

import java.util.List;
import java.util.Map;

public interface SysChannelInfoService
{
    List<Map<String, Object>> getChannelInfoList(Map<String, Object> params);

    Map<String, Object> findChannelInfo(String channelCode);
}
