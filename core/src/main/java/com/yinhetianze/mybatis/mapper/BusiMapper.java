package com.yinhetianze.mybatis.mapper;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

/**
 * Created by Administrator
 * on 2018/1/27.
 */
public interface BusiMapper<T> extends BaseInsertMapper<T>, BaseUpdateMapper<T>, BaseDeleteMapper<T>
{
}
