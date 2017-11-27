package com.tmoncorp.common.util.tree;

import java.util.Set;

public class Root<T> implements Node<T> {
    public static final long ROOT_NO = 0;

    private Set<T> children;


    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public void setDepth(int depth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getNo() {
        return ROOT_NO;
    }

    @Override
    public void setNo(long no) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getParentNo() {
        return ROOT_NO;
    }

    @Override
    public void setParentNo(long parentNo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<T> getChildren() {
        return children;
    }

    @Override
    public void setChildren(Set<T> children) {
        this.children = children;
    }

    @Override
    public int compareTo(Node o) {
        if (o instanceof Root) {
            return 0;
        }
        return -1;
    }

    @Override
    public boolean isRoot() {
        return true;
    }
}