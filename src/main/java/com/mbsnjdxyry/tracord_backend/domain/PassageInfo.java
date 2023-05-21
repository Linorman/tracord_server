package com.mbsnjdxyry.tracord_backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class PassageInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private int followerNum;
    private String image;
    private String publishDate;
    private String address;
    private String content;
    private int delFlag;
}