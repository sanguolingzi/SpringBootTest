package com.yinhetianze.mybatis.mapper;

import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;

/**
 * Created by Administrator
 * on 2018/1/27.
 */
public interface InfoMapper<T> extends SelectOneMapper<T>, SelectMapper<T>
{
}
