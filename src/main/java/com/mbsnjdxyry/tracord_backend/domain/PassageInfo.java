package com.mbsnjdxyry.tracord_backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态信息实体类
 * @data 2021/05/21
 * @auther linorman
 */
@Data
@TableName(value = "tb_passage")
public class PassageInfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer followerNum;
    private String image;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date")
    private Date createDate;
    private String address;
    private String content;
    private int delFlag;
}
