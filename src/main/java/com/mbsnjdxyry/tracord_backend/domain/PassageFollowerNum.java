package com.mbsnjdxyry.tracord_backend.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 点赞信息实体类
 * @data 2021/05/21
 * @auther linorman
 */
@Data
@TableName(value = "tb_passage_follower")
public class PassageFollowerNum implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer passageId;
    private Integer followerId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;
    private int delFlag;
}
