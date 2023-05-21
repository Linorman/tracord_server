package com.mbsnjdxyry.tracord_backend.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 动态信息实体类
 * @data 2021/05/21
 * @auther linorman
 */
@Data
@TableName(value = "tb_passage")
public class PassageInfo implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer followerNum;
    private String image;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date")
    private String createDate;
    private String address;
    private String content;
    private int delFlag;
}
