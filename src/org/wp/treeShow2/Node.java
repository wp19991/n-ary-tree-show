package org.wp.treeShow2;

import java.util.*;


/**
 * 一个节点类
 */
public class Node {
    public final String value;
    public Node prev;//这个节点之前的元素
    public int hang;//这个节点所在的行
    public int lie;//这个节点所在的列
    public int lieNum;//这个节点所在的列的个数
    public int String_length;//节点元素的长度

    public int children_first_lie;//子节点第一次出现的列信息
    public int children_num;//子节点的个数

    public int pre_node_hang;//这个元素所属父节点的行信息
    public int pre_node_lie;//这个元素所属父节点的列信息


    public List<Node> children;

    /**
     * 初始化一个节点
     *
     * @param value 节点的值
     */
    Node(String value) {
        this.value = value;
        this.hang=0;
        this.lie=0;
        this.lieNum=0;
        this.String_length=0;
        this.children_first_lie = 0;
        this.children_num = 0;
        this.pre_node_hang = 0;
        this.pre_node_lie = 0;
        this.children = new ArrayList<Node>();
    }

    /**
     * 初始化一个节点
     *
     * @param value    节点的值
     * @param children 这个节点的子节点
     */
    Node(String value, Node children) {
        this.value = value;
        this.children.add(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}