package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/merchant-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MerchantInfoController {

    private final IMerchantInfoService merchantInfoService;

    private final IUserInfoService userInfoService;

    /**
     * 分页获取公司信息
     *
     * @param page         分页对象
     * @param merchantInfo 公司信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MerchantInfo> page, MerchantInfo merchantInfo) {
        return R.ok(merchantInfoService.queryMerchantPage(page, merchantInfo));
    }

    /**
     * 商家审核
     *
     * @param merchantId 商家ID
     * @param status     状态
     * @return 结果
     */
    @GetMapping("/audit")
    public R audit(Integer merchantId, Integer status) {
        return R.ok(merchantInfoService.update(Wrappers.<MerchantInfo>lambdaUpdate().set(MerchantInfo::getStatus, status).eq(MerchantInfo::getId, merchantId)));
    }

    /**
     * 根据用户ID获取公司信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/getMerchantByUser")
    public R getMerchantByUser(@RequestParam("userId") Integer userId) {
        MerchantInfo merchantInfo = merchantInfoService.getOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, userId));
        if (merchantInfo == null) {
            return R.ok();
        }
        return R.ok(merchantInfo);
    }

    /**
     * 获取ID获取公司详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        MerchantInfo merchantInfo = merchantInfoService.getById(id);
        Map<String, String> weekMap = new HashMap<String, String>() {
            {
                put("1", "周一");
                put("2", "周二");
                put("3", "周三");
                put("4", "周四");
                put("5", "周五");
                put("6", "周六");
                put("7", "周日");
            }
        };
        if (StrUtil.isNotEmpty(merchantInfo.getOperateDay())) {
            List<String> operateDayList = StrUtil.splitTrim(merchantInfo.getOperateDay(), ",");
            List<String> operateDayResult = new ArrayList<>();
            for (String s : operateDayList) {
                operateDayResult.add(weekMap.get(s));
            }
            merchantInfo.setOperateDayList(operateDayResult);
        }
        return R.ok(merchantInfo);
    }

    /**
     * 获取公司信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(merchantInfoService.list());
    }

    /**
     * 新增公司信息
     *
     * @param merchantInfo 公司信息
     * @return 结果
     */
    @PostMapping
    public R save(MerchantInfo merchantInfo) {
        Map<String, String> weekMap = new HashMap<String, String>() {
            {
                put("周一", "1");
                put("周二", "2");
                put("周三", "3");
                put("周四", "4");
                put("周五", "5");
                put("周六", "6");
                put("周日", "7");
            }
        };
        merchantInfo.setCode("MER-" + System.currentTimeMillis());
        merchantInfo.setCreateDate(DateUtil.formatDateTime(new Date()));

        if (StrUtil.isNotEmpty(merchantInfo.getOperateDay())) {
            List<String> operateDayList = StrUtil.splitTrim(merchantInfo.getOperateDay(), ",");
            List<String> operateDayResult = new ArrayList<>();
            for (String s : operateDayList) {
                operateDayResult.add(weekMap.get(s));
            }
            merchantInfo.setOperateDay(StrUtil.join(",", operateDayResult));
        }
        return R.ok(merchantInfoService.save(merchantInfo));
    }

    /**
     * 修改公司信息
     *
     * @param merchantInfo 公司信息
     * @return 结果
     */
    @PutMapping
    public R edit(MerchantInfo merchantInfo) {
        Map<String, String> weekMap = new HashMap<String, String>() {
            {
                put("周一", "1");
                put("周二", "2");
                put("周三", "3");
                put("周四", "4");
                put("周五", "5");
                put("周六", "6");
                put("周日", "7");
            }
        };
        if (StrUtil.isNotEmpty(merchantInfo.getOperateDay())) {
            List<String> operateDayList = StrUtil.splitTrim(merchantInfo.getOperateDay(), ",");
            List<String> operateDayResult = new ArrayList<>();
            for (String s : operateDayList) {
                operateDayResult.add(weekMap.get(s));
            }
            merchantInfo.setOperateDay(StrUtil.join(",", operateDayResult));
        }
        // 修改绑定用户信息
        MerchantInfo merchantInfo1 = merchantInfoService.getById(merchantInfo.getId());
        if (merchantInfo1.getUserInfoId() != null) {
            UserInfo userInfo = userInfoService.getById(merchantInfo1.getUserInfoId());
            if (userInfo != null) {
                userInfo.setName(merchantInfo.getName());
                userInfo.setImages(merchantInfo.getImages());
                userInfoService.updateById(userInfo);
            }
        }
        return R.ok(merchantInfoService.updateById(merchantInfo));
    }

    /**
     * 删除公司信息
     *
     * @param ids ids
     * @return 公司信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(merchantInfoService.removeByIds(ids));
    }
}
