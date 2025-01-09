package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.VehicleInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IVehicleInfoService extends IService<VehicleInfo> {

    /**
     * 分页获取车辆信息
     *
     * @param page        分页对象
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryVehiclePage(Page<VehicleInfo> page, VehicleInfo vehicleInfo);

    /**
     * 根据公司获取车辆信息
     *
     * @param merchantId  公司ID
     * @param vehicleType 车辆类型
     * @return 结果
     */
    List<VehicleInfo> queryVehicleList(Integer merchantId, Integer vehicleType);
}
