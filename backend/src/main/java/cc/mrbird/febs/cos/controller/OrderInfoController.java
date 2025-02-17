package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/order-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderInfoController {

    private final IOrderInfoService orderInfoService;

    /**
     * 分页获取订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<OrderInfo> page, OrderInfo orderInfo) {
        return R.ok(orderInfoService.queryOrderPage(page, orderInfo));
    }

    /**
     * 公司接单
     *
     * @param orderId 订单编号
     * @param userId  用户ID
     * @return 结果
     */
    @GetMapping("/checkOrder")
    public R checkOrder(Integer orderId, Integer userId) {
        return R.ok(orderInfoService.checkOrder(orderId, userId));
    }

    /**
     * 管理员对订单分配
     *
     * @param orderCode    订单编号
     * @param driverCode   司机编号
     * @param staffCodeStr 帮运
     * @return 结果
     */
    @GetMapping("/checkOrderStaff")
    public R checkOrderStaff(@RequestParam("orderCode") String orderCode, @RequestParam("vehicleCode") String vehicleCode, @RequestParam("driverCode") String driverCode, @RequestParam(value = "staffCodeStr", required = false) String staffCodeStr) throws Exception {
        return R.ok(orderInfoService.checkOrder(orderCode, vehicleCode, driverCode, staffCodeStr));
    }

    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(orderInfoService.getById(id));
    }

    /**
     * 获取订单详情
     *
     * @param code 订单编号
     * @return 结果
     */
    @GetMapping("/detail/{code}")
    public R queryDetail(@PathVariable("code") String code) {
        return R.ok(orderInfoService.selectDetailByCode(code));
    }

    /**
     * 获取订单详情
     *
     * @param code 订单编号
     * @return 结果
     */
    @GetMapping("/queryOrderDetail/{code}")
    public R queryOrderDetail(@PathVariable("code") String code) {
        return R.ok(orderInfoService.selectDetailByCode(code));
    }

    /**
     * 获取未接单订单
     *
     * @param userId 公司ID
     * @return 结果
     */
    @GetMapping("notCheckList")
    public R queryNotCheckList(Integer userId) {
        return R.ok(orderInfoService.queryNotCheckList(userId));
    }

    /**
     * 获取订单信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(orderInfoService.list());
    }

    /**
     * 新增订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @PostMapping
    public R save(OrderInfo orderInfo) {
        return R.ok(orderInfoService.save(orderInfo));
    }

    /**
     * 修改订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @PutMapping
    public R edit(OrderInfo orderInfo) {
        return R.ok(orderInfoService.updateById(orderInfo));
    }

    /**
     * 删除订单信息
     *
     * @param ids ids
     * @return 订单信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(orderInfoService.removeByIds(ids));
    }

    /**
     * 首页统计
     *
     * @param userId 账户ID
     * @return 结果
     */
    @GetMapping("/homeData")
    public R queryHomeData(@RequestParam(value = "userId", required = false) Integer userId) {
        return R.ok(orderInfoService.homeData(userId));
    }


    /**
     * 年统计订单及收益
     *
     * @param date 年份
     * @return 结果
     */
    @GetMapping("/statistics/year")
    public R selectStoreStatisticsByYear(String date) {
        return R.ok(orderInfoService.selectStoreStatisticsByYear(date));
    }

    /**
     * 月统计订单及收益
     *
     * @param date 日期
     * @return 结果
     */
    @GetMapping("/statistics/month")
    public R selectStoreStatisticsByMonth(String date) {
        return R.ok(orderInfoService.selectStoreStatisticsByMonth(date));
    }
}
