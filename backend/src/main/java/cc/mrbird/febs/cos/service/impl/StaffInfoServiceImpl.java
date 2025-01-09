package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.MerchantInfoMapper;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.dao.StaffInfoMapper;
import cc.mrbird.febs.cos.entity.VehicleInfo;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements IStaffInfoService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryStaffPage(Page<StaffInfo> page, StaffInfo staffInfo) {
        return baseMapper.queryStaffPage(page, staffInfo);
    }

    /**
     * 根据公司获取员工信息
     *
     * @param merchantId 商户id
     * @return 结果
     */
    @Override
    public Map<Integer, List<StaffInfo>> queryStaffList(Integer merchantId) {
        MerchantInfo merchantInfo = merchantInfoMapper.selectOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, merchantId));
        if (merchantInfo != null) {
            merchantId = merchantInfo.getId();
        }
        List<StaffInfo> staffInfoList = this.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getMerchantId, merchantId));
        if (CollectionUtil.isEmpty(staffInfoList)) {
            return Collections.emptyMap();
        }
        return staffInfoList.stream().collect(Collectors.groupingBy(StaffInfo::getType));
    }
}
