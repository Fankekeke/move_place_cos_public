package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 消息管理
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发送人
     */
    private Integer sendUser;

    /**
     * 接收人
     */
    private Integer takeUser;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送时间
     */
    private LocalDateTime createDate;

    /**
     * 状态 0.未读 1.已读
     */
    private Integer taskStatus;


}
