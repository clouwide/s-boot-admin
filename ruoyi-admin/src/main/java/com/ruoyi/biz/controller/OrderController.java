package com.ruoyi.biz.controller;

import java.util.List;
import java.util.Objects;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.biz.domain.Order;
import com.ruoyi.biz.service.OrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产订单 信息操作处理
 *
 * @author cloudewide
 * @date 2019-03-19
 */
@Controller
@RequestMapping("/biz/order")
public class OrderController extends BaseController {
    private String prefix = "biz/order";

    @Autowired
    private OrderService orderService;

    @RequiresPermissions("biz:order:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    /**
     * 查询生产订单列表
     */
    @RequiresPermissions("biz:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Order order) {
        startPage();
        List<Order> list = orderService.selectList(order);
        return getDataTable(list);
    }


    /**
     * 导出生产订单列表
     */
    @RequiresPermissions("biz:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Order order) {
        List<Order> list = orderService.selectList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增生产订单
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        modelMap.put("order" , new Order());
        return prefix + "/edit";
    }

    /**
     * 新增保存生产订单
     */
    @RequiresPermissions("biz:order:add")
    @Log(title = "生产订单" , businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Order order) {
        return toAjax(orderService.insert(order));
    }

    /**
     * 修改生产订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Order order = orderService.selectById(id);
        mmap.put("order" , order);
        return prefix + "/edit";
    }

    /**
     * 修改保存生产订单
     */
    @RequiresPermissions("biz:order:edit")
    @Log(title = "生产订单" , businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Order order) {
        if (Objects.isNull(order.getId())) {
            return toAjax(orderService.insert(order));
        } else {
            return toAjax(orderService.update(order));

        }
    }

    /**
     * 删除生产订单
     */
    @RequiresPermissions("biz:order:remove")
    @Log(title = "生产订单" , businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orderService.deleteByIds(ids));
    }

}
