package com.ruoyi.common.core.mapper;

import java.util.List;

/**
 * 基础mapper
 * @author cloudwide
 * @param <T>
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    List<T> selectEntityList(T e);
}
