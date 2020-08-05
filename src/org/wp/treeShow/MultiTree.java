package org.wp.treeShow;

import java.util.*;

public class MultiTree {

    public Node root;

    /**
     * 使用根节点初始化一颗 n 叉树
     *
     * @param root 根节点
     */
    MultiTree(Node root) {
        root.prev = new Node("root");
        this.root = root;
    }

    /**
     * 判断节点(Node)是否在这颗树里面
     *
     * @param node 节点
     * @return 如果节点在这颗树里面 返回 true 否则返回 false
     */
    private boolean isInRoot(Node node) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node front = queue.peek();
            queue.remove();
            if (front.equals(node)) {
                return true;
            }

            int i = 0;
            while (true) {
                try {
                    queue.add(front.children.get(i));
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

        }
        return false;
    }


    /**
     * 添加节点(Node)之间的联系  (Node)a -> (Node)b
     * 加入节点有先后顺序 如果 开始节点(Node) 不在原先的树上面 ，则添加失败
     *
     * @param first  开始节点(Node)
     * @param second 结束节点(Node)
     */
    public void addTreeNode(Node first, Node second) {
        //System.out.println(first.value+"->"+second.value);

        if (isInRoot(second)) {
            //System.out.println(second.value+" isInRoot");
            return;
        }

        if (first.equals(root)) {
            second.prev = first;
            root.children.add(second);
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node front = queue.peek();
            queue.remove();

            if (front.equals(first)) {
                second.prev = first;
                front.children.add(second);
            }

            int i = 0;
            while (true) {
                try {
                    queue.add(front.children.get(i));
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

        }

    }

}
