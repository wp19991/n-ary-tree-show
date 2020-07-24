package org.wp.treeShow2;

import java.util.*;

/*
root->fffff
----
fffff->ro
----
ro->fir
----
fir->xw
----
xw->z
xw->y
xw->a
----
z->e
y->f
a->aaaaaaa
a->eeegtfte
----



加入节点有先后顺序 如果 开始节点(Node) 不在原先的树上面 ，则添加失败

[[(Node)"wxxx"],
[(Node)"y",(Node)"a",(Node)"z",(Node)"kkk"],
[(Node)"e",(Node)"f"]]

 */

public class MultiTree {
    public Node root;
    public List<List<Node>> res;

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
     * 遍历生成的树，增加节点的额外信息用于绘制图形
     */
    private void add_MultiTree_Node_information() {
        for (int i = 0; i < this.res.size(); i++) {
            for (int j = 0; j < this.res.get(i).size(); j++) {
                Node temp_node = this.res.get(i).get(j);

                //更新这一列的元素信息
                temp_node.hang = i;
                temp_node.lie = j;
                temp_node.lieNum = this.res.get(i).size();
                temp_node.String_length = this.res.get(i).get(j).value.length();

                //更新前一列的元素信息
                temp_node.pre_node_hang = get_Root_Node_from_string_name(temp_node.prev.value).hang;
                temp_node.pre_node_lie = get_Root_Node_from_string_name(temp_node.prev.value).lie;

            }
        }

        //更新子节点的信息
        for (int i = 0; i < this.res.size(); i++) {
            for (int j = 0; j < this.res.get(i).size(); j++) {
                Node temp_node = this.res.get(i).get(j);

                if(i!=this.res.size()-1) {
                    //更新前一列的元素子节点的信息
                    temp_node.children_num = temp_node.children.size();
                    if(temp_node.children_num!=0){
                        temp_node.children_first_lie = get_Root_Node_from_string_name(temp_node.children.get(0).value).lie;
                    }else {
                        temp_node.children_first_lie = -1;
                    }
                }
                else {
                    temp_node.children_first_lie = -1;
                    temp_node.children_num = 0;
                }
            }
        }

    }

    /**
     * 生成这颗树的层次遍历的 ArrayList
     * [
     * [(Node)"wxxx"],
     * [(Node)"y",(Node)"a",(Node)"z",(Node)"kkk"],
     * [(Node)"e",(Node)"f"]
     * ]
     */
    public void levelOrder() {
        this.res = new ArrayList<>();
        helper(this.root, 0, this.res);
        add_MultiTree_Node_information();
    }

    private void helper(Node node, int level, List<List<Node>> res) {
        if (node == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node);
        for (Node child : node.children) {
            helper(child, level + 1, res);
        }
    }


    /**
     * 打印层次遍历的这个课树
     */
    public void printTree() {
        this.levelOrder();//生成这颗树的层次遍历的 ArrayList

        for (int i = 0; i < this.res.size(); i++) {
            for (int j = 0; j < this.res.get(i).size(); j++) {
                System.out.println(this.res.get(i).get(j).prev.value + "->" + this.res.get(i).get(j).value);
            }
            System.out.println("----");
        }
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
     * 根据元素的名称查找元素，并返回这个对象
     *
     * @param str 元素的名称
     * @return 返回这个对象，不存在返回 一个空的 null 节点
     */
    private Node get_Root_Node_from_string_name(String str) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node front = queue.peek();
            queue.remove();
            if (front.value.equals(str)) {
                return front;
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

        return new Node("null");
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