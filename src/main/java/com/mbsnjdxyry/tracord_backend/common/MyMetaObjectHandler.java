package com.mbsnjdxyry.tracord_backend.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mbsnjdxyry.tracord_backend.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus的字段自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Integer userId = null;
        try {
//            1.获取到当前登录的用户id
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1;//表示是自己创建
        }
        this.setFieldValByName("createDate", new Date(), metaObject);
        this.setFieldValByName("updateDate", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateDate", new Date(), metaObject);
        this.setFieldValByName(" ", SecurityUtils.getUserId(), metaObject);
    }
}
