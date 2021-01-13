package com.vanwei.tech.constant;

/**
 * 通用常量
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/7/22 9:26
 */
public interface CommonConstants {

    /**
     * 时区GMT+8
     */
    String ZONE_ID_ASIA_SHANGHAI = "Asia/Shanghai";

    /**
     * 删除
     */
    Integer STATUS_DEL = 1;

    /**
     * 正常
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 锁定
     */
    Integer STATUS_LOCK = 9;

    /**
     * 响应结果为成功的描述信息
     */
    String SUCCESS = "操作成功";

    /**
     * 操作的结果
     */
    String RESULT = "result";

    /**
     * 代码缺陷错误出现情况的响应结果为失败的描述信息
     */
    String INTERNAL_SERVER_ERROR = "服务器内部错误,请联系管理员";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 树结构的根节点的默认ID值
     */
    String ROOT_TREE_NODE_ID = "0";

    /**
     * 当前页
     */
    String CURRENT = "current";

    /**
     * size
     */
    String SIZE = "size";
}
