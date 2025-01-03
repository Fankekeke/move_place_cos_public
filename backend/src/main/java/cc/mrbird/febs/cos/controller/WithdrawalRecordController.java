package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.WithdrawalRecord;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cc.mrbird.febs.cos.service.IWithdrawalRecordService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/withdrawal-record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WithdrawalRecordController {

    private final IWithdrawalRecordService withdrawalRecordService;

    private final IMerchantInfoService merchantInfoService;

    /**
     * 分页获取提现记录
     *
     * @param page             分页对象
     * @param withdrawalRecord 提现记录
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<WithdrawalRecord> page, WithdrawalRecord withdrawalRecord) {
        return R.ok(withdrawalRecordService.queryWithdrawalPage(page, withdrawalRecord));
    }

    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(withdrawalRecordService.getById(id));
    }

    /**
     * 新增提现记录信息
     *
     * @param withdrawInfo 提现记录信息
     * @return 结果
     */
    @PostMapping
    public R save(WithdrawalRecord withdrawInfo) throws FebsException {
        // 校验此员工是否有提现正在审核中
        int count = withdrawalRecordService.count(Wrappers.<WithdrawalRecord>lambdaQuery().eq(WithdrawalRecord::getAuditStatus, 0));
        if (count > 0) {
            throw new FebsException("存在正在审核的提现记录！");
        }
        // 设置所属搬家公司
        withdrawInfo.setCode("WD-" + System.currentTimeMillis());
        MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, withdrawInfo.getMerchantId()));
        if (merchantInfo != null) {
            withdrawInfo.setMerchantId(merchantInfo.getId());
        }
        withdrawInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(withdrawalRecordService.save(withdrawInfo));
    }

    /**
     * 获取提现记录列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(withdrawalRecordService.list());
    }

    /**
     * 修改提现记录
     *
     * @param withdrawalRecord 提现记录
     * @return 结果
     */
    @PutMapping
    public R edit(WithdrawalRecord withdrawalRecord) {
        return R.ok(withdrawalRecordService.updateById(withdrawalRecord));
    }

    /**
     * 删除提现记录
     *
     * @param ids ids
     * @return 提现记录
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(withdrawalRecordService.removeByIds(ids));
    }
}
