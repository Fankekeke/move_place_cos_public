package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ComplaintInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IComplaintInfoService extends IService<ComplaintInfo> {

    /**
     * 分页获取投诉记录
     *
     * @param page          分页对象
     * @param complaintInfo 投诉记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryComplaintPage(Page<ComplaintInfo> page, ComplaintInfo complaintInfo);
}