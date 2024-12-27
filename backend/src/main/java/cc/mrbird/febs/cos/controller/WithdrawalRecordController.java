package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.WithdrawalRecord;
import cc.mrbird.febs.cos.service.IWithdrawalRecordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/withdrawal-record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WithdrawalRecordController {

    private final IWithdrawalRecordService withdrawalRecordService;

    /**
     * 分页获取提现记录
     *
     * @param page        分页对象
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
     * 获取提现记录列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(withdrawalRecordService.list());
    }

    /**
     * 新增提现记录
     *
     * @param withdrawalRecord 提现记录
     * @return 结果
     */
    @PostMapping
    public R save(WithdrawalRecord withdrawalRecord) {
        return R.ok(withdrawalRecordService.save(withdrawalRecord));
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
