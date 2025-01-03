package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.PriceRules;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cc.mrbird.febs.cos.service.IPriceRulesService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/price-rules")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceRulesController {

    private final IPriceRulesService priceRulesService;

    private final IMerchantInfoService merchantInfoService;

    /**
     * 分页获取价格规则信息
     *
     * @param page       分页对象
     * @param priceRules 价格规则信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<PriceRules> page, PriceRules priceRules) {
        return R.ok(priceRulesService.queryPriceRulesPage(page, priceRules));
    }

    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(priceRulesService.getById(id));
    }

    /**
     * 获取价格规则信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(priceRulesService.list());
    }

    /**
     * 新增价格规则信息
     *
     * @param priceRules 价格规则信息
     * @return 结果
     */
    @PostMapping
    public R save(PriceRules priceRules) {
        // 设置所属搬家公司
        MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, priceRules.getMerchantId()));
        if (merchantInfo != null) {
            priceRules.setMerchantId(merchantInfo.getId());
        }
        return R.ok(priceRulesService.save(priceRules));
    }

    /**
     * 修改价格规则信息
     *
     * @param priceRules 价格规则信息
     * @return 结果
     */
    @PutMapping
    public R edit(PriceRules priceRules) {
        return R.ok(priceRulesService.updateById(priceRules));
    }

    /**
     * 删除价格规则信息
     *
     * @param ids ids
     * @return 价格规则信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(priceRulesService.removeByIds(ids));
    }
}
