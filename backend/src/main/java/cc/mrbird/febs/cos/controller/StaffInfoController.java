package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/staff-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffInfoController {

    private final IStaffInfoService staffInfoService;

    private final IMerchantInfoService merchantInfoService;

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StaffInfo> page, StaffInfo staffInfo) {
        return R.ok(staffInfoService.queryStaffPage(page, staffInfo));
    }

    /**
     * 获取员工信息
     *
     * @return 结果
     */
    @GetMapping("/staff/type/{merchantId}")
    public R selectStaffByType(@PathVariable(value = "merchantId", required = false) Integer merchantId) {
        if (merchantId != null) {
            MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, merchantId));
            if (merchantInfo != null) {
                merchantId = merchantInfo.getId();
            }
        }
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("driver", staffInfoService.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getType, 1).eq(StaffInfo::getStatus, 1).eq(merchantId != null, StaffInfo::getMerchantId, merchantId)));
        result.put("staff", staffInfoService.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getType, 2).eq(StaffInfo::getStatus, 1).eq(merchantId != null, StaffInfo::getMerchantId, merchantId)));
        return R.ok(result);
    }

    /**
     * 根据公司获取员工信息
     *
     * @param merchantId 公司ID
     * @return 结果
     */
    @GetMapping("/queryStaffList")
    public R queryStaffList(@RequestParam(value = "merchantId") Integer merchantId) {
        return R.ok(staffInfoService.queryStaffList(merchantId));
    }

    /**
     * 根据公司获取员工列表
     *
     * @param userId 商家ID
     * @return 结果
     */
    @GetMapping("/selectStaffByMerchant/{userId}")
    public R selectStaffByMerchant(@PathVariable(value = "userId") Integer userId) {
        MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, userId));
        if (merchantInfo == null) {
            return R.ok(Collections.emptyList());
        }
        return R.ok(staffInfoService.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getMerchantId, merchantInfo.getId())));
    }


    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(staffInfoService.getById(id));
    }

    /**
     * 获取员工信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(staffInfoService.list());
    }

    /**
     * 新增员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PostMapping
    public R save(StaffInfo staffInfo) {
        // 设置所属搬家公司
        MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, staffInfo.getMerchantId()));
        if (merchantInfo != null) {
            staffInfo.setMerchantId(merchantInfo.getId());
        }
        staffInfo.setCode("ST-" + System.currentTimeMillis());
        staffInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(staffInfoService.save(staffInfo));
    }

    /**
     * 修改员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PutMapping
    public R edit(StaffInfo staffInfo) {
        return R.ok(staffInfoService.updateById(staffInfo));
    }

    /**
     * 删除员工信息
     *
     * @param ids ids
     * @return 员工信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(staffInfoService.removeByIds(ids));
    }
}
