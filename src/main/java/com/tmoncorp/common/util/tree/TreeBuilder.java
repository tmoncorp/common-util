package com.tmoncorp.common.util.tree;

import java.util.*;

public class TreeBuilder<T extends Node<T>> {
    private final TreeSet<T> nodes = new TreeSet<>();
    private final HashMap<Long, T> nodeMap = new HashMap<>();
    private final Root<T> root = new Root<>();
    private boolean isComplete = false;

    public TreeBuilder() {
    }

    public TreeBuilder(Collection<T> nodes) {
        this.nodes.addAll(nodes);
        this.nodes.forEach(node -> nodeMap.put(node.getNo(), node));
    }

    public TreeBuilder append(T element) {
        nodes.add(element);
        nodeMap.put(element.getNo(), element);
        insertNode(element);
        return this;
    }

    public List<T> build() {
        if (isComplete == false) {
            buildTree();
        }
        return new ArrayList<>(root.getChildren());
    }

    private void buildTree() {
        nodes.forEach(this::insertNode);
        isComplete = true;
    }

    private void insertNode(T node) {
        if (node.getDepth() == 1) {
            root.addChild(node);
        } else if (nodeMap.containsKey(node.getParentNo())) {
            nodeMap.get(node.getParentNo()).addChild(node);
        }
    }
}
