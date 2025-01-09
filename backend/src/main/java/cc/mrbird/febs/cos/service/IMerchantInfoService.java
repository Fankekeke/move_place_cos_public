package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.MerchantInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IMerchantInfoService extends IService<MerchantInfo> {

    /**
     * 分页获取公司信息
     *
     * @param page         分页对象
     * @param merchantInfo 公司信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryMerchantPage(Page<MerchantInfo> page, MerchantInfo merchantInfo);

    /**
     * 所有商家信息入库
     */
    void setUpMerchant2Redis();

    /**
     * 根据位置获取商家信息
     *
     * @param lat 纬度
     * @param lng 经度
     * @return 结果
     */
    MerchantInfo queryMerchantByPosition(Double lat, Double lng);
}
