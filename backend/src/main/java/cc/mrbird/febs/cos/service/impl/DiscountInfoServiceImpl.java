package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.DiscountInfo;
import cc.mrbird.febs.cos.dao.DiscountInfoMapper;
import cc.mrbird.febs.cos.service.IDiscountInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
public class DiscountInfoServiceImpl extends ServiceImpl<DiscountInfoMapper, DiscountInfo> implements IDiscountInfoService {

    /**
     * 分页获取优惠券信息
     *
     * @param page         分页对象
     * @param discountInfo 优惠券信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectDiscountPage(Page<DiscountInfo> page, DiscountInfo discountInfo) {
        return baseMapper.selectDiscountPage(page, discountInfo);
    }

    /**
     * 根据用户ID获取优惠券信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryDiscountByUserId(Integer userId) {
        return baseMapper.queryDiscountByUserId(userId);
    }

    /**
     * 根据状态用户ID获取优惠券信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> queryDiscountSortByUserId(Integer userId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("notUse", Collections.emptyList());
                put("used", Collections.emptyList());
            }
        };
        List<LinkedHashMap<String, Object>> list = baseMapper.queryDiscountByUserId(userId);
        if (CollectionUtil.isEmpty(list)) {
            return result;
        }
        List<LinkedHashMap<String, Object>> notUseList = new ArrayList<>();
        List<LinkedHashMap<String, Object>> usedList = new ArrayList<>();
        for (LinkedHashMap<String, Object> mapItem : list) {
            String status = (String) mapItem.get("status");
            if (StrUtil.isEmpty(status)) {
                continue;
            }
            switch (status) {
                case "0":
                    notUseList.add(mapItem);
                    break;
                case "1":
                    usedList.add(mapItem);
                    break;
                default:
            }
        }
        result.put("notUse", notUseList);
        result.put("used", usedList);
        return result;
    }
}
