package com.ruoyi.biz.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.biz.domain.Order;
import com.ruoyi.biz.domain.OrderDetail;
import com.ruoyi.biz.mapper.OrderMapper;
import com.ruoyi.common.core.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 生产订单 服务层实现
 * 
 * @author cloudewide
 * @date 2019-03-19
 */
@Service
public class OrderService extends BaseService<OrderMapper,Order>
{

    @Autowired
    private OrderDetailService orderDetailService;

    public int saveOrder(Order order) {
        OrderDetail q = new OrderDetail();
        q.setOrderId(order.getId());
        List<OrderDetail>  list = orderDetailService.list(Wrappers.query(q));
        String standards = list.stream().map(OrderDetail::getStandard).collect(Collectors.joining(","));
        order.setStandards(standards);
        return   this.update(order);
    }
}
