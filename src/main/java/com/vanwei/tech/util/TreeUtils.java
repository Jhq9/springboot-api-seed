package com.vanwei.tech.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Tree Utils
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/8/5 14:18
 */
public class TreeUtils {

    /**
     * 构建节点树
     *
     * @param treeNodeList 节点列表
     * @param <T>          节点类型
     * @return 节点树
     */
    public static <T extends TreeNode> List<T> build(List<T> treeNodeList, String pid) {
        List<T> treeList = new ArrayList<>();

        for (T treeNode : treeNodeList) {
            if (Objects.equals(treeNode.getPid(), pid)) {
                treeList.add(treeNode);
            }

            for (T node : treeNodeList) {
                if (Objects.equals(node.getPid(), treeNode.getId())) {
                    if (Objects.isNull(treeNode.getChildren())) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(node);
                }
            }
        }

        return treeList;
    }
}
