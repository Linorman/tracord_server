package com.mbsnjdxyry.tracord_backend.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mbsnjdxyry.tracord_backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章返回前端实体类
 * @data 2021/05/21
 * @auther linorman
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassageToFront implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer followerNum;
    private String image;
    private Date createDate;
    private String address;
    private String content;
    private List<UserToFront> userList;
}
