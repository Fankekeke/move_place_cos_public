package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.EvaluateInfo;
import cc.mrbird.febs.cos.entity.OrderInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 分页获取订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryOrderPage(Page<OrderInfo> page, OrderInfo orderInfo);

    /**
     * 根据用户获取订单信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderByUserId(Integer userId);

    /**
     * 根据订单状态分类用户获取订单信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> queryOrderByUserIdSort(Integer userId);

    /**
     * 公司接单
     *
     * @param orderId 订单编号
     * @param userId  用户ID
     * @return 结果
     */
    Boolean checkOrder(Integer orderId, Integer userId);

    /**
     * 设置订单状态
     *
     * @param orderCode 订单编号
     * @param status    状态
     * @return 结果
     */
    boolean audit(String orderCode, Integer status);

    /**
     * 管理员对订单分配
     *
     * @param orderCode    订单编号
     * @param driverCode   司机编号
     * @param staffCodeStr 帮运
     * @return 结果
     */
    boolean checkOrder(String orderCode, String vehicleCode, String driverCode, String staffCodeStr) throws Exception;

    /**
     * 添加评价信息
     *
     * @param evaluateInfo 评价信息
     * @return 结果
     */
    boolean orderEvaluate(EvaluateInfo evaluateInfo);

    /**
     * 运输结束回调
     *
     * @param orderId 订单ID
     * @return 结果
     */
    boolean receipt(Integer orderId);

    /**
     * 计算订单价格
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    BigDecimal calculateAmount(OrderInfo orderInfo);

    /**
     * 计算订单价格
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    LinkedHashMap<String, Object> calculateAmountResult(OrderInfo orderInfo);

    /**
     * 根据订单编号获取订单详细信息
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    LinkedHashMap<String, Object> selectDetailByCode(String orderCode);

    /**
     * 获取未接单订单
     *
     * @param userId 公司ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryNotCheckList(Integer userId);

    /**
     * 根据用户ID查询未完成订单
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<OrderInfo> selectOrderByUserId(Integer userId);

    /**
     * 查询待分配和未完成订单
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> selectOrderByStatus();

    /**
     * 主页信息
     *
     * @param merchantId 公司ID
     * @return 结果
     */
    LinkedHashMap<String, Object> homeData(Integer merchantId);

    /**
     * 年统计订单及收益
     *
     * @param date 年份
     * @return 结果
     */
    LinkedHashMap<String, Object> selectStoreStatisticsByYear(String date);

    /**
     * 月统计订单及收益
     *
     * @param date 日期
     * @return 结果
     */
    LinkedHashMap<String, Object> selectStoreStatisticsByMonth(String date);
}
