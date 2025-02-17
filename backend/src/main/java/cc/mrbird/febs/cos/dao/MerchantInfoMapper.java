package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.MerchantInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface MerchantInfoMapper extends BaseMapper<MerchantInfo> {

    /**
     * 分页获取公司信息
     *
     * @param page         分页对象
     * @param merchantInfo 公司信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryMerchantPage(Page<MerchantInfo> page, @Param("merchantInfo") MerchantInfo merchantInfo);
}
