package com.vanwei.tech.util;

import java.util.ArrayList;
import java.util.List;

/**
 *  树节点
 *
 *  @author     jin_huaquan
 *  @date      2020/8/5 14:14
 *  @version   1.0
 */
public class TreeNode {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String pid;

    /**
     * 子节点列表
     */
    private List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node) {
        children.add(node);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
