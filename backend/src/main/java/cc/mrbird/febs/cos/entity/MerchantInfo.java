package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商家管理
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 搬运公司名称
     */
    private String name;

    /**
     * 搬运公司编号
     */
    private String code;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 介绍
     */
    private String content;

    /**
     * 营业星期
     */
    private String operateDay;

    /**
     * 开始营业时间
     */
    private LocalTime operateStartTime;

    /**
     * 营业结束时间
     */
    private LocalTime operateEndTime;

    /**
     * 图片
     */
    private String images;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 状态
     */
    private String status;

    /**
     * 余额
     */
    private BigDecimal balance;


}