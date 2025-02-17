package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 是否需要车辆（1.大型车 2.中型车 3.小型车）
     */
    private String vehicleOptions;

    /**
     * 是否需要搬运工（0.不需要 1.一个 2.两个 3.三个）
     */
    private String staffOptions;

    /**
     * 起始地址
     */
    private String startAddress;

    /**
     * 运输地址
     */
    private String endAddress;

    /**
     * 初始经度
     */
    private BigDecimal startLongitude;

    /**
     * 初始纬度
     */
    private BigDecimal startLatitude;

    /**
     * 运输经度
     */
    private BigDecimal endLongitude;

    /**
     * 运输纬度
     */
    private BigDecimal endLatitude;

    /**
     * 距离（千米）
     */
    private BigDecimal distanceLength;

    /**
     * 图册
     */
    private String images;

    /**
     * 订单状态（0.等待接单 1.等待分配 2.正在赶往 3.运输完成）
     */
    private String status;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 价格
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否有电梯（0：无 1：有）
     */
    private String hasElevator;

    /**
     * 司机编码
     */
    private String driverCode;

    /**
     * 所属公司
     */
    private Integer merchantId;

    /**
     * 优惠券编号
     */
    private String discountCode;

    /**
     * 车辆编号
     */
    private String vehicleCode;

    /**
     * 优惠价格
     */
    private String discountAmount;

    /**
     * 折后价格
     */
    private BigDecimal afterAmount;

    /**
     * 预约时间
     */
    private String appointmentTime;

    @TableField(exist = false)
    private String merchantName;

    @TableField(exist = false)
    private String userName;

    /**
     * 年份
     */
    @TableField(exist = false)
    private Integer year;

    /**
     * 月份
     */
    @TableField(exist = false)
    private Integer month;

    /**
     * 天
     */
    @TableField(exist = false)
    private Integer day;
}
