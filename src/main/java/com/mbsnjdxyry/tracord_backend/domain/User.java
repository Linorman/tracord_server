package com.mbsnjdxyry.tracord_backend.domain;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_user")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 用户名
     */
    @TableField(value = "nickname")
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户性别（0-96 共97种性别）
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "create_date")
    private String createDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "update_date",fill = FieldFill.UPDATE )
    private Date updateDate;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

}