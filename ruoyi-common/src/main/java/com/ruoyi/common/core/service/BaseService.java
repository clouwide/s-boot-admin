package com.ruoyi.common.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.ruoyi.common.core.mapper.BaseMapper;
import com.ruoyi.common.core.text.Convert;

import java.util.List;

/**
 * 基础service
 * @param <M>
 * @param <T>
 * @author cloudWide
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

    /**
     * 查询信息
     *
     * @param id
     * @return 实体
     */
    public T selectById(Long id){
        return baseMapper.selectById(id);
    }

    /**
     * 查询列表
     *
     * @param t 实体
     * @return 集合
     */
    public List<T> selectList(T t){
        return baseMapper.selectList(new QueryWrapper(t));
    }

    /**
     * 新增生产订单
     *
     * @param t 实体
     * @return 结果
     */
    public int insert(T t){
        return baseMapper.insert(t);
    }

    /**
     * 修改生产订单
     *
     * @param t 实体
     * @return 结果
     */
    public int update(T t){
        return baseMapper.insert(t);
    }

    /**
     * 删除生产订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteByIds(String ids){
        return baseMapper.deleteBatchIds(Lists.newArrayList(Convert.toStrArray(ids)));
    }
}
