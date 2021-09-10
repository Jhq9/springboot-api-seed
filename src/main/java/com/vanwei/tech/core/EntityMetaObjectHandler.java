package com.vanwei.tech.core;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Entity Meta Object Handler
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2021/7/26 15:19
 */
@Component
public class EntityMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String DELETED = "deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(CREATE_TIME, now, metaObject);
        this.setFieldValByName(UPDATE_TIME, now, metaObject);
        this.setFieldValByName(DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(UPDATE_TIME, now, metaObject);
    }
}
