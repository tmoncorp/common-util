package com.tmoncorp.common.util.tree;

import java.util.Set;
import java.util.TreeSet;

public interface Node<T> extends Comparable<Node> {
    int getDepth();

    void setDepth(int depth);

    long getNo();

    void setNo(long no);

    long getParentNo();

    void setParentNo(long parentNo);

    Set<T> getChildren();

    void setChildren(Set<T> children);

    default void addChild(T child) {
        if (this.getChildren() == null) {
            this.setChildren(new TreeSet<>());
        }
        this.getChildren().add(child);
    }

    default int compareTo(Node o) {
        if (this.getDepth() == o.getDepth()) {
            if (this.getParentNo() == o.getParentNo()) {
                return (int) (this.getNo() - o.getNo());
            }
            return (int) (this.getParentNo() - o.getParentNo());
        }
        return this.getDepth() - o.getDepth();
    }


    default boolean isRoot() {
        return false;
    }
}