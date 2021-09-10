package com.vanwei.tech.enums;

/**
 * 角色
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/9/27 15:23
 */
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("ROLE_ADMIN", "管理员");

    /**
     * 角色的标识
     */
    private final String name;

    /**
     * 角色的说明
     */
    private final String mark;

    RoleEnum(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getMark() {
        return mark;
    }
}
