package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StaffInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface StaffInfoMapper extends BaseMapper<StaffInfo> {

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryStaffPage(Page<StaffInfo> page, @Param("staffInfo") StaffInfo staffInfo);

    /**
     * 根据订单编号获取搬运工，司机信息
     *
     * @param orderCode 订单编号
     */
    List<LinkedHashMap<String, Object>> selectStaffByOrder(@Param("orderCode") String orderCode);
}
