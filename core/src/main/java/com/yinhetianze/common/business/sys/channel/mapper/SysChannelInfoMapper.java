package com.yinhetianze.common.business.sys.channel.mapper;

import java.util.List;
import java.util.Map;

public interface SysChannelInfoMapper
{
    Map<String, Object> findChannelInfo(String channelCode);

    List<Map<String, Object>> getChannelInfoList(Map<String, Object> params);
}
