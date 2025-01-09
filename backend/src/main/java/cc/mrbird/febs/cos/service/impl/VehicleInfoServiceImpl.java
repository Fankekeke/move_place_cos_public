package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.MerchantInfoMapper;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.VehicleInfo;
import cc.mrbird.febs.cos.dao.VehicleInfoMapper;
import cc.mrbird.febs.cos.service.IVehicleInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class VehicleInfoServiceImpl extends ServiceImpl<VehicleInfoMapper, VehicleInfo> implements IVehicleInfoService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    /**
     * 分页获取车辆信息
     *
     * @param page        分页对象
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryVehiclePage(Page<VehicleInfo> page, VehicleInfo vehicleInfo) {
        return baseMapper.queryVehiclePage(page, vehicleInfo);
    }

    /**
     * 根据公司获取车辆信息
     *
     * @param merchantId  公司ID
     * @param vehicleType 车辆类型
     * @return 结果
     */
    @Override
    public List<VehicleInfo> queryVehicleList(Integer merchantId, Integer vehicleType) {
        if (merchantId != null) {
            MerchantInfo merchantInfo = merchantInfoMapper.selectOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUserId, merchantId));
            if (merchantInfo != null) {
                merchantId = merchantInfo.getId();
            }
        }
        return this.list(Wrappers.<VehicleInfo>lambdaQuery().eq(VehicleInfo::getMerchantId, merchantId).eq(vehicleType != null, VehicleInfo::getVehicleType, vehicleType));
    }
}
