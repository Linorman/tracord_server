package com.mbsnjdxyry.tracord_backend.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户关注信息实体类
 * @data 2021/05/21
 * @auther linorman
 */
@Data
@TableName(value = "tb_user_follow")
public class UserFollowInfo implements Serializable {
    @TableId(value = "id")
    private Integer id;
    private Integer userId;
    private Integer followerId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
}
