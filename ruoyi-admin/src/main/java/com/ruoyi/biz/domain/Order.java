package com.ruoyi.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 生产订单表 biz_order
 * 
 * @author cloudewide
 * @date 2019-03-19
 */
@Data
@TableName("biz_order")
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	/**  */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date orderDate;
	/** 客户 */
	private String customer;
	/**  */
	private String standards;
	/** 用时 */
	private String timeCost;

}
