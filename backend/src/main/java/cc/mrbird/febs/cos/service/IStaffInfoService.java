package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StaffInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IStaffInfoService extends IService<StaffInfo> {

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryStaffPage(Page<StaffInfo> page, StaffInfo staffInfo);

    /**
     * 根据公司获取员工信息
     *
     * @param merchantId 商户id
     * @return 结果
     */
    Map<Integer, List<StaffInfo>> queryStaffList(Integer merchantId);
}
