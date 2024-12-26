package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 投诉记录
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ComplaintInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 投诉时间
     */
    private LocalDateTime createDate;

    /**
     * 所属公司
     */
    private Integer merchantId;

    /**
     * 投诉内容
     */
    private String content;

    /**
     * 状态（0.未处理 1.已处理）
     */
    private String status;


}
