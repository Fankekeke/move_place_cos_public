package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.VehicleInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

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
}
