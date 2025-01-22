package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.EvaluateInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IEvaluateInfoService extends IService<EvaluateInfo> {

    /**
     * 分页获取评价信息
     *
     * @param page         分页对象
     * @param evaluateInfo 评价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryEvaluatePage(Page<EvaluateInfo> page, EvaluateInfo evaluateInfo);

    /**
     * 获取搬家公司详情
     *
     * @param shopId 公司ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryEvaluateByShopId(Integer shopId);
}
