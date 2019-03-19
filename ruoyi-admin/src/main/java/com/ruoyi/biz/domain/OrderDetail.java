package com.ruoyi.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;

/**
 * 生产单详情表 biz_order_detail
 * 
 * @author cloudewide
 * @date 2019-03-19
 */
@Data
@TableName("biz_order_detail")
public class OrderDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 订单id */
	private Long orderId;
	/** 类别 */
	private String category;
	/** 规格 */
	private String standard;
	/** 数量 */
	private Integer quantity;
	/** 重量 */
	private Double weight;
	/** 单价 */
	private Double price;

}
