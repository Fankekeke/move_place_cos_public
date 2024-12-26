package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 评价信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluateInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 评价图片
     */
    private String images;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 准时得分
     */
    private BigDecimal scheduleScore;

    /**
     * 服务得分
     */
    private BigDecimal serviceScore;

    /**
     * 价格得分
     */
    private BigDecimal priceScore;

    /**
     * 质量得分
     */
    private BigDecimal qualityScore;

    /**
     * 交付得分
     */
    private BigDecimal deliverScore;

    /**
     * 最终得分
     */
    private BigDecimal overScore;

    /**
     * 评价时间
     */
    private LocalDateTime createDate;

    /**
     * 所属公司
     */
    private Integer merchantId;


}