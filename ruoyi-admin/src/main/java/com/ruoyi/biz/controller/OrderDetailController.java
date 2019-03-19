package com.ruoyi.biz.controller;

import com.google.common.collect.Lists;

import com.ruoyi.biz.domain.OrderDetail;
import com.ruoyi.biz.service.OrderDetailService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * 生产单详情 信息操作处理
 * 
 * @author cloudewide
 * @date 2019-03-19
 */
@Controller
@RequestMapping("/biz/orderDetail")
public class OrderDetailController extends BaseController
{
    private String prefix = "biz/orderDetail";
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequiresPermissions("biz:orderDetail:view")
	@GetMapping()
	public String orderDetail()
	{
	    return prefix + "/orderDetail";
	}
	
	/**
	 * 查询生产单详情列表
	 */
	@RequiresPermissions("biz:orderDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderDetail orderDetail)
	{
		if(Objects.isNull(orderDetail.getOrderId())){
			return getDataTable(Lists.newArrayList());
		}
		startPage();
        List<OrderDetail> list = orderDetailService.selectList(orderDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出生产单详情列表
	 */
	@RequiresPermissions("biz:orderDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderDetail orderDetail)
    {
    	List<OrderDetail> list = orderDetailService.selectList(orderDetail);
        ExcelUtil<OrderDetail> util = new ExcelUtil<OrderDetail>(OrderDetail.class);
        return util.exportExcel(list, "orderDetail");
    }
	
	/**
	 * 新增生产单详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存生产单详情
	 */
	@RequiresPermissions("biz:orderDetail:add")
	@Log(title = "生产单详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrderDetail orderDetail)
	{		
		return toAjax(orderDetailService.insert(orderDetail));
	}

	/**
	 * 修改生产单详情
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		OrderDetail orderDetail = orderDetailService.selectById(id);
		mmap.put("orderDetail", orderDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存生产单详情
	 */
	@RequiresPermissions("biz:orderDetail:edit")
	@Log(title = "生产单详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrderDetail orderDetail)
	{		
		return toAjax(orderDetailService.update(orderDetail));
	}
	
	/**
	 * 删除生产单详情
	 */
	@RequiresPermissions("biz:orderDetail:remove")
	@Log(title = "生产单详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(orderDetailService.deleteByIds(ids));
	}
	
}
