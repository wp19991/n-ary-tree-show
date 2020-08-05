package org.wp.treeShow;

import java.util.*;

public class Node {
    public String value;
    public List<Node> children;
    public Node prev;//这个节点之前的元素

    /**
     * 初始化一个节点
     *
     * @param value 节点的值
     */
    Node(String value) {
        this.value = value;
        this.children = new ArrayList<Node>();
    }

    public void setNode(Node o) {
        this.value = o.value;
        this.children = new ArrayList<Node>(o.children);
        this.prev=o.prev;
    }


    public boolean equals(Node o) {
        return this.value.equals(o.value);

    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}
