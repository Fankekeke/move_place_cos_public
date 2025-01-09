package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.OrderDistribute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IOrderDistributeService extends IService<OrderDistribute> {

    /**
     * 设置订单员工
     *
     * @param staffListStr 员工编号
     * @param orderCode    订单编号
     * @return 结果
     */
    boolean setOrderStaff(String staffListStr, String orderCode) throws Exception;
}
