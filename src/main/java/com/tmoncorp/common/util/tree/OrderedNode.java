package com.tmoncorp.common.util.tree;

public interface OrderedNode<T> extends Node<T> {
    int getOrder();

    void setOrder(int order);

    default int compareTo(OrderedNode o) {
        if (this.getDepth() == o.getDepth()) {
            if (this.getParentNo() == o.getParentNo()) {
                if (this.getOrder() == o.getOrder()) {
                    return (int) (this.getNo() - o.getNo());
                }
                return this.getOrder() - o.getOrder();
            }
            return (int) (this.getParentNo() - o.getParentNo());
        }
        return this.getDepth() - o.getDepth();
    }
}
