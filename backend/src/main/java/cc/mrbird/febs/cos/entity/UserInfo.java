package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 用户头像
     */
    private String images;

    /**
     * 系统账户
     */
    private Integer userId;

    /**
     * 用户编号
     */
    private String code;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * OPENID
     */
    private String openId;
    @TableField(exist = false)
    private String userName;
    private String sex;
    private String avatar;
}
