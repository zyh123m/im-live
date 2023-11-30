package org.example.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    /**
     * 
     */

    private Long id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别 1-男 2-女
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户状态;1:正常,2:禁用
     */
    private Integer status;

    /**
     * 用户注册来源;1:PC,2:Android, 3:iOS,4:Wap
     */
    private Integer source;

    /**
     * 推荐人id
     */
    private Long referrer;

    /**
     * 是否会员;0:否,1:是
     */
    private Integer isVip;

    /**
     * 会员过期时间
     */
    private Date vipExpireTime;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区/县
     */
    private String region;

    /**
     * 账户金额
     */
    private BigDecimal balance;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}