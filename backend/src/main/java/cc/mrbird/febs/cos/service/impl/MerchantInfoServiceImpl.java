package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.common.service.RedisService;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.dao.MerchantInfoMapper;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class MerchantInfoServiceImpl extends ServiceImpl<MerchantInfoMapper, MerchantInfo> implements IMerchantInfoService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private IMerchantInfoService merchantInfoService;

    /**
     * 分页获取公司信息
     *
     * @param page         分页对象
     * @param merchantInfo 公司信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryMerchantPage(Page<MerchantInfo> page, MerchantInfo merchantInfo) {
        return baseMapper.queryMerchantPage(page, merchantInfo);
    }

    /**
     * 所有公司信息入库
     */
    @Override
    public void setUpMerchant2Redis() {
        // 获取所有公司信息
        List<MerchantInfo> merchantList = this.list(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getStatus, 1));
        if (CollectionUtil.isEmpty(merchantList)) {
            return;
        }

        for (MerchantInfo merchant : merchantList) {
            if (merchant.getLatitude() == null || merchant.getLongitude() == null) {
                continue;
            }
            redisTemplate.opsForGeo().add("merchant:point", new Point(merchant.getLatitude().doubleValue(), merchant.getLongitude().doubleValue()), merchant.getCode());
        }
    }

    /**
     * 根据位置获取公司信息
     *
     * @param lat 纬度
     * @param lng 经度
     * @return 结果
     */
    @Override
    public List<MerchantInfo> queryMerchantByPosition(Double lat, Double lng) {
        // 判断是否为合法的经纬度
        if (!(lng > 0 && lng < 180 && lat > 0 && lat < 180)) {
            return null;
        }

        Circle circle = new Circle(lng, lat, 1000d);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().limit(1);

        GeoResults<RedisGeoCommands.GeoLocation<Object>> geoResults = redisTemplate.opsForGeo().radius("merchant:point", circle, args);
        if (CollectionUtil.isEmpty(geoResults) || CollectionUtil.isEmpty(geoResults.getContent())) {
            return null;
        }

        List<String> merchantCodes = new ArrayList<>();
        Map<String, Double> distanceMap = new LinkedHashMap<>();

        // 只取前10个
        int index = 0;
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> geo : geoResults.getContent()) {
            if (index >= 10) {
                continue;
            }
            RedisGeoCommands.GeoLocation<Object> geoLocation = geoResults.getContent().get(0).getContent();
            String merchantCode = (String) geoLocation.getName();
            merchantCodes.add(merchantCode);

            // 计算距离
            Distance distance = geo.getDistance();
            distanceMap.put(merchantCode, distance.getValue());
            index++;
        }

        // 获取公司信息
        List<MerchantInfo> merchantInfoList = merchantInfoService.list(Wrappers.<MerchantInfo>lambdaQuery().in(MerchantInfo::getCode, merchantCodes));
        merchantInfoList.forEach(merchant -> {
            merchant.setDistance(distanceMap.get(merchant.getCode()));
        });
        return merchantInfoList;
    }
}
