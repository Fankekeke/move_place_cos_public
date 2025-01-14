package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.OrderDistribute;
import cc.mrbird.febs.cos.service.IOrderDistributeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/order-distribute")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderDistributeController {

    private final IOrderDistributeService orderDistributeService;

    /**
     * 分页获取订单分配信息
     *
     * @param page            分页对象
     * @param orderDistribute 订单分配信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<OrderDistribute> page, OrderDistribute orderDistribute) {
        return R.ok();
    }

    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(orderDistributeService.getById(id));
    }

    /**
     * 获取订单分配信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(orderDistributeService.list());
    }

    /**
     * 新增订单分配信息
     *
     * @param orderDistribute 订单分配信息
     * @return 结果
     */
    @PostMapping
    public R save(OrderDistribute orderDistribute) {
        return R.ok(orderDistributeService.save(orderDistribute));
    }

    /**
     * 修改订单分配信息
     *
     * @param orderDistribute 订单分配信息
     * @return 结果
     */
    @PutMapping
    public R edit(OrderDistribute orderDistribute) {
        return R.ok(orderDistributeService.updateById(orderDistribute));
    }

    /**
     * 删除订单分配信息
     *
     * @param ids ids
     * @return 订单分配信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(orderDistributeService.removeByIds(ids));
    }
}
