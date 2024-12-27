package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 车辆管理
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VehicleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统编号
     */
    private String vehicleCode;

    /**
     * 所属公司
     */
    private Integer merchantId;

    /**
     * 车牌号码
     */
    private String vehicleNo;

    /**
     * 燃油类型
     */
    private Integer fuleType;

    /**
     * 运营状态（0.未运营 1.运营中）
     */
    private String operationState;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 车辆品牌
     */
    private String vehicleBrand;

    /**
     * 备注
     */
    private String content;

    /**
     * 车辆图片
     */
    private String images;

    /**
     * 车辆类型（1.大型车 2.中型车 3.小型车）
     */
    private Integer vehicleType;


}
