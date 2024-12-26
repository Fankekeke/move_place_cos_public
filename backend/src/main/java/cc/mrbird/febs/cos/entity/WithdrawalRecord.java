package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 提现记录
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WithdrawalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所属商家
     */
    private Integer merchantId;

    /**
     * 提现金额
     */
    private BigDecimal balance;

    /**
     * 申请时间
     */
    private LocalDateTime createDate;

    /**
     * 审核状态 0.未审核 1.审核通过 2.驳回
     */
    private Integer auditStatus;

    /**
     * 状态时间
     */
    private LocalDateTime statusDate;


}
