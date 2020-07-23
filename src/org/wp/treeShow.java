package org.wp;

import java.util.*;

/**
 * xwww->y
 * xwww->z
 * xww->a
 * z->e
 */

public class treeShow {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();

        inputList.add("fffff->ro");
        inputList.add("ro->fir");
        inputList.add("fir->xw");
        inputList.add("xw->y");
        inputList.add("xw->a");
        inputList.add("xw->z");
        inputList.add("a->aaaaaaa");
        inputList.add("z->e");
        inputList.add("y->f");
        inputList.add("a->eeegtfte");

        MultiTree t = new myutil().inputList_to_MultiTree(inputList);

        t.printTree();

        new myutil().print_MultiTree_List(t);
        new myutil().command_show_tree(t);


    }


}

class myutil {

    public void add_MultiTree_Node_information(MultiTree t) {
        for (int i = 0; i < t.res.size(); i++) {
            //更新前一列的元素信息
            for (int j = 0; j < t.res.get(i).size(); j++) {
                //更新这一列的元素信息
                t.res.get(i).get(j).hang = i;
                t.res.get(i).get(j).lie = j;
                t.res.get(i).get(j).lieNum = t.res.get(i).size();
                t.res.get(i).get(j).String_length = t.res.get(i).get(j).value.length();
            }
        }

    }

    public void print_MultiTree_List(MultiTree t) {

        add_MultiTree_Node_information(t);

        for (int i = 0; i < t.res.size(); i++) {
            for (int j = 0; j < t.res.get(i).size(); j++) {
                System.out.println(t.res.get(i).get(j).prev.value + "->" + t.res.get(i).get(j).value
                        + " 坐标信息:(行,列) (" + t.res.get(i).get(j).hang + "," + t.res.get(i).get(j).lie + ") "
                        + " 这行一共有元素个数：" + t.res.get(i).get(j).lieNum);
            }
        }

    }

    public void command_show_tree(MultiTree t) {

//        root->ro坐标信息:(行,列) (0,0)  这行一共有元素个数：1
//        ro->fir坐标信息:(行,列) (1,0)  这行一共有元素个数：1
//        fir->xwww坐标信息:(行,列) (2,0)  这行一共有元素个数：1
//        xwww->y坐标信息:(行,列) (3,0)  这行一共有元素个数：3
//        xwww->z坐标信息:(行,列) (3,1)  这行一共有元素个数：3
//        xwww->a坐标信息:(行,列) (3,2)  这行一共有元素个数：3
//        y->f坐标信息:(行,列) (4,0)  这行一共有元素个数：2
//        z->e坐标信息:(行,列) (4,1)  这行一共有元素个数：2
        add_MultiTree_Node_information(t);


        for (int i = 0; i < t.res.size(); i++) {

            StringBuffer sb_1 = new StringBuffer();
            StringBuffer sb_2 = new StringBuffer();
            StringBuffer sb_3 = new StringBuffer();

            StringBuffer sb_xian = new StringBuffer();//方向的线


            for (int j = 0; j < t.res.get(i).size(); j++) {
                //这列元素的线
                if(j!=0){
                    sb_xian.append("   ");
                }
                sb_xian.append("   ");
                int ks=0;
                for (int lie = 0; lie < t.res.get(i).get(j).String_length/2; lie++) {
                    ks++;
                    sb_xian.append(" ");
                }
                sb_xian.append("|");//方向
                for (int lie = ks+1; lie < t.res.get(i).get(j).String_length; lie++) {
                    sb_xian.append(" ");
                }



                //这列的第一行和第三行
                if(j!=0){
                    sb_1.append("   ");
                    sb_2.append("   ");
                    sb_3.append("   ");
                }

                sb_1.append("+--");
                sb_3.append("+--");
                for (int lie = 0; lie < t.res.get(i).get(j).String_length; lie++) {
                    sb_1.append("-");
                    //sb_xian.append(" ");
                    sb_3.append("-");
                }
                sb_1.append("--+");
                sb_3.append("--+");
                sb_xian.append("   ");


                //这列的第二行
                sb_2.append("|  ");
                sb_2.append(t.res.get(i).get(j).value);
                sb_2.append("  |");


            }
            System.out.println(sb_xian);//方向的线
            System.out.println(sb_1);//第一行
            System.out.println(sb_2);//第二行
            System.out.println(sb_3);//第三行


        }

    }


    /**
     * 将用户输入的无序列表，变成一颗 n 叉树
     * <p>
     * 需要先使用 inputList_to_okList() 将用户输入的无序列表转换为 可以使用的列表
     * 加入节点有先后顺序 如果 开始节点(Node) 不在原先的树上面 ，则这个节点添加失败
     *
     * @param inputList 用户输入的无序列表 例如 ["a->b","a->c","k->a","c->b"]
     * @return 一颗 n 叉树
     */
    public MultiTree inputList_to_MultiTree(List<String> inputList) {


        //List<String> okList = this.inputList_to_okList(inputList);
        List<String> okList = inputList;

        MultiTree t = new MultiTree(new Node(okList.get(0).split("->")[0]));

        for (int i = 0; i < okList.size(); i++) {
            String first_node_name = okList.get(i).split("->")[0];
            String second_node_name = okList.get(i).split("->")[1];
            t.addTreeNode(new Node(first_node_name), new Node(second_node_name));
        }

        t.levelOrder();
        return t;
    }


    /**
     * 从输入过渡到树结构
     * 输入 节点->节点 的次序可以 无序
     * ["a->b","a->c","k->a","c->b"]
     * 里面 k 是根节点
     * 最后形成符合生成 n叉树 的 用户输入的
     * ["k->a","a->b","a->c","c->b"]
     *
     * @param inputList 用户输入的列表
     */
    private List<String> inputList_to_okList(List<String> inputList) {
        //test_use_print_list(inputList);

        List<String> temp_list = new ArrayList<>(inputList);


        //遍历每个元素
        for (int i = 0; i < inputList.size(); i++) {
            String this_temp_str = inputList.get(i);


        }


        int pre_index = 0;
        for (int wai = 0; wai < temp_list.size(); wai++) {
            test_use_print_list(temp_list);
            // 这个循环的下标
            pre_index = wai;
            //这个循环开始的 元素
            String temp_list_str = temp_list.get(wai);
            System.out.println("--这个循环的下标:" + pre_index + "-->这个循环开始的元素" + temp_list_str);

            for (int i = pre_index; i < temp_list.size(); i++) {
                //当前元素的开头
                String temp_first_str = temp_list.get(i).split("->")[0];

                //当前元素在这个列表里面出现的下标
                int temp_first_index = from_list_get_first_index(temp_first_str, temp_list);
                System.out.println("当前元素在这个列表里面出现的下标是：" + temp_first_index + "->(" + temp_list.get(i) + ")");

                // 当前的元素 和 这个循环开始的元素 需要替换
                // 当前元素在这个列表里面出现的下标 = 这个列表的大小

                if (temp_first_index == -1) {
                    // 交换这个两个元素
                    swap_list_from_index(pre_index, i, temp_list);

                    System.out.println(pre_index + "进行交换" + i);
                    //test_use_print_list(temp_list);
                    //break;
                    //continue;
                }

                if (temp_first_index != i & i != temp_list.size() - 1) {
                    swap_list_from_index(temp_first_index + 1, i, temp_list);
                    System.out.println(temp_first_index + 1 + "进行交换" + i);
                }
//
//                if(temp_first_index >= i){
//                    // 交换这个两个元素
//                    swap_list_from_index(temp_first_index,i,temp_list);
//                    System.out.println(temp_first_index+"进行交换"+i);
//                    //break;
//                }


            }
        }

        //test_use_print_list(temp_list);


        return temp_list;
    }

    /**
     * 交换 list 下标的元素
     *
     * @param first_index  第一个下标
     * @param second_index 第二个下标
     * @param temp_list    要交换下标元素的
     */
    public void swap_list_from_index(int first_index, int second_index, List<String> temp_list) {
        String temp_str_second_index = temp_list.get(second_index);
        String temp_str_first_index = temp_list.get(first_index);

        temp_list.set(second_index, temp_str_first_index);
        temp_list.set(first_index, temp_str_second_index);
    }

    /**
     * 调试使用 打印输入的列表
     *
     * @param temp_list 需要打印的列表
     */
    public void test_use_print_list(List<String> temp_list) {
        System.out.println("--------------");
        for (int i = 0; i < temp_list.size(); i++) {
            System.out.println(i + ") " + temp_list.get(i));
        }
        System.out.println("--------------" + temp_list.size());
    }


    /**
     * 返回 st 第一次出现在列表元素 “a->b” 中 b 位置的下标
     *
     * @param st        搜索的节点名称
     * @param inputList 用户输入的列表
     * @return 返回 st 第一次出现在列表元素 “a->b” 中 b 位置的下标；不存在返回-1
     */
    private int from_list_get_first_index(String st, List<String> inputList) {

        for (int i = 0; i < inputList.size(); i++) {
//            System.out.println(i+") "+st+"-0-("+inputList.get(i)+")");
//            System.out.println(i+") "+st+"-0-"+inputList.get(i).split("->")[0]);
//            System.out.println(i+") "+st+"-1-"+inputList.get(i).split("->")[1]);
            if (inputList.get(i).split("->")[1].equals(st)) {
                return i;
            }
        }
        return -1;
    }
}


/*

root->wxxx
----
wxxx->y
wxxx->a
wxxx->z
wxxx->kkk
----
z->e
z->f
----


加入节点有先后顺序 如果 开始节点(Node) 不在原先的树上面 ，则添加失败

[[(Node)"wxxx"],
[(Node)"y",(Node)"a",(Node)"z",(Node)"kkk"],
[(Node)"e",(Node)"f"]]

 */


class MultiTree {
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
     * 将这个颗树 生成层次遍历的 ArrayList
     * [
     * [(Node)"wxxx"],
     * [(Node)"y",(Node)"a",(Node)"z",(Node)"kkk"],
     * [(Node)"e",(Node)"f"]
     * ]
     */
    public void levelOrder() {
        this.res = new ArrayList<>();
        helper(this.root, 0, this.res);

    }

    private void helper(Node node, int level, List<List<Node>> res) {
        if (node == null)
            return;

        if (res.size() == level)
            res.add(new ArrayList<>());

        res.get(level).add(node);
        for (Node child : node.children)
            helper(child, level + 1, res);
    }


    /**
     * 打印层次遍历的这个课树
     */
    public void printTree() {
        this.levelOrder();

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
    public boolean isInRoot(Node node) {
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


/**
 * 一个节点类
 */
class Node {
    public final String value;
    public Node prev;//这个节点之前的元素
    public int hang;//这个节点所在的行
    public int lie;//这个节点所在的列
    public int lieNum;//这个节点所在的列的个数
    public int String_length;//节点元素的长度


    public List<Node> children;

    /**
     * 初始化一个节点
     *
     * @param value 节点的值
     */
    Node(String value) {
        this.value = value;
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

