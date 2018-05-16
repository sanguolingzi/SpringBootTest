package com.yinhetianze.common.business.sys.channel.service.impl;

import com.yinhetianze.common.business.sys.channel.mapper.SysChannelInfoMapper;
import com.yinhetianze.common.business.sys.channel.service.SysChannelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysChannelInfoServiceImpl implements SysChannelInfoService
{
    @Autowired
    private SysChannelInfoMapper sysChannelInfoMapper;

    @Override
    public List<Map<String, Object>> getChannelInfoList(Map<String, Object> params)
    {
        return sysChannelInfoMapper.getChannelInfoList(params);
    }

    @Override
    public Map<String, Object> findChannelInfo(String channelCode)
    {
        return sysChannelInfoMapper.findChannelInfo(channelCode);
    }
}
