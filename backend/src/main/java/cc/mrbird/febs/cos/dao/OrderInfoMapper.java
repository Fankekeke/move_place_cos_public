package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 分页获取订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryOrderPage(Page<OrderInfo> page, @Param("orderInfo") OrderInfo orderInfo);

    /**
     * 根据用户获取订单信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderByUserId(@Param("userId") Integer userId);

    /**
     * 近十天内订单数量统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderNumDays(@Param("merchantId") Integer merchantId);

    /**
     * 近十天内订单收益统计
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderAmountDays(@Param("merchantId") Integer merchantId);

    /**
     * 本月订单信息
     *
     * @return 结果
     */
    List<OrderInfo> selectOrderInfoByMonth(@Param("merchantId") Integer merchantId);

    /**
     * 本年订单信息
     *
     * @return 结果
     */
    List<OrderInfo> selectOrderInfoByYear(@Param("merchantId") Integer merchantId);

    /**
     * 根据状态获取订单信息
     *
     * @param status 订单状态
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderByStatus(@Param("status") Integer status);

    /**
     * 根据时间获取订单信息
     *
     * @param year  年份
     * @param month 月份
     * @return 结果
     */
    List<OrderInfo> selectOrderOutListByDate(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 根据时间获取订单信息
     *
     * @param year  年份
     * @param month 月份
     * @return 结果
     */
    List<OrderInfo> selectOrderPutListByDate(@Param("year") Integer year, @Param("month") Integer month);
}
