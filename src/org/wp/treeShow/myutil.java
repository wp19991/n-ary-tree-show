package org.wp.treeShow;

import java.util.*;

public class myutil {

    /**
     * 将用户输入的无序列表，变成一颗 n 叉树
     *
     * @param inputList 用户输入的无序列表 例如 ["a->b","a->c","k->a","c->b"]
     * @return 一颗 n 叉树的对象
     */
    public MultiTree inputList_to_MultiTree(List<String> inputList) {
        //从输入过渡到符合规定的输入列表
        List<String> okList = this.inputList_to_okList(inputList);

        //使用根节点初始化一颗 n 叉树
        MultiTree t = new MultiTree(new Node(okList.get(0).split("->")[0]));

        //添加元素
        for (String s : okList) {
            String first_node_name = s.split("->")[0];
            String second_node_name = s.split("->")[1];
            //添加节点(Node)之间的联系
            t.addTreeNode(new Node(first_node_name), new Node(second_node_name));
        }

        return t;
    }

    /**
     * 辅助函数 变换旋转后显示的列表 使其更紧凑
     *
     * @param l 竖着旋转后显示的列表
     */
    public void xz_display_tree_list_new(List<List<Node>> l) {

        //先把所有的#no# 和 #no# 改为 #no#
        for (List<Node> nodes : l) {
            for (Node node : nodes) {
                if (node.value.equals("#no#")) {
                    node.value = "#no#";
                }
            }
        }


//        for (List<Node> nodes : l) {
//            for (Node temp : nodes) {
//                System.out.print(temp.value + "\t");
//            }
//            System.out.println();
//        }


        for (int i = l.size() - 1; i >= 0; i--) {
            //将当前的元素 添加到 右边是空，右边下面有元素 这个右边的地方
            for (int k = 0; k < l.get(i).size() - 1; k++) {
                Node temp = l.get(i).get(k);
                if (temp.value.equals("#no#")) {
                    continue;
                }
                //最后一行不处理
                if (i == l.size() - 1) {
                    continue;
                }

                //temp
                //处理倒数第二行和之前的
                if (!l.get(i).get(k + 1).value.equals("#no#")) {
                    continue;
                }

                if (!l.get(i + 1).get(k + 1).value.equals("#no#") && l.get(i).get(k + 1).value.equals("#no#")) {
                    l.get(i).get(k + 1).setNode(l.get(i).get(k));
                    l.get(i).get(k).setNode(new Node("#no#"));
                    k++;
                    //break;
                }
            }

            //每行结束 对整体进行统计删除  如果一列都是 #no#  删除这一列
            for (int k = 0; k < l.get(0).size(); k++) {
                boolean isdelect = true;
                int delect_lie = k;


                for (List<Node> nodes : l) {
                    if (!nodes.get(k).value.equals("#no#")) {
                        isdelect = false;
                        delect_lie = -1;
                        break;
                    }
                }

                if (isdelect) {
                    for (List<Node> nodes : l) {
                        nodes.remove(delect_lie);
                    }
                }

            }

        }


    }

    /**
     * 打印树的结构 横着显示
     * 表格数据 没有居中显示
     *
     * @param t 输入一颗 MultiTree 的对象
     */
    public void displaytree_from_MultiTree_xz(MultiTree t) {
        //将树的结构 保存成竖向列表的形式
        List<List<Node>> l = get_display_tree_list(t.root);

        //将树结构的 竖向列表 变为 横向向列表
        List<List<Node>> xz_display = xz_display_tree_list(l);

        xz_display_tree_list_new(xz_display);


//        System.out.println("---------xz_display_tree_list_new-----------");
//        for (List<Node> nodes : xz_display) {
//            for (Node temp : nodes) {
//                System.out.print(temp.value + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");


        //每一列文字的最大长度
        HashMap<Integer, Integer> pre = new HashMap<Integer, Integer>();
        //初始化这个列表
        for (int j = 0; j < xz_display.get(0).size(); j++) {
            pre.put(j, 0);
        }

        for (int j = 0; j < xz_display.get(0).size(); j++) {
            for (List<Node> nodes : xz_display) {
                if (nodes.get(j).value.length() > pre.get(j)) {
                    pre.put(j, nodes.get(j).value.length());
                }
            }
        }


        for (int i = 0; i < xz_display.size(); i++) {
            StringBuilder xian_1 = new StringBuilder();
            StringBuilder xian_2 = new StringBuilder();
            StringBuilder sb_1 = new StringBuilder();
            StringBuilder sb_2 = new StringBuilder();
            StringBuilder sb_3 = new StringBuilder();

            for (int j = 0; j < xz_display.get(i).size(); j++) {
                Node temp = xz_display.get(i).get(j);


                //如果这个元素的值为#no# 说明这个元素是空的 留出空白的地方进行填充
                //一个元素的宽度是 6 + 这一列最长元素的长度
                if (temp.value.equals("#no#")) {
                    for (int k = 0; k < 8 + pre.get(j); k++) {
                        xian_1.append(" ");
                        xian_2.append(" ");
                        sb_1.append(" ");
                        sb_2.append(" ");
                        sb_3.append(" ");
                    }
                } else {
                    //如果这个元素的值不是 #no#  开始打印
                    xian_1.append("     ");
                    xian_2.append("     ");
                    sb_1.append("  ╔══");
                    sb_2.append("  ║  ");
                    sb_3.append("  ╚══");


                    if (i == 0) {
                        xian_1.append("+");
                        xian_2.append("|");
                        sb_1.append("═");
                        sb_2.append(temp.value.charAt(0));
                        sb_3.append("═");
                        for (int k = 1; k < temp.value.length(); k++) {
                            xian_1.append(" ");
                            xian_2.append(" ");
                            sb_1.append("═");
                            sb_2.append(temp.value.charAt(k));
                            sb_3.append("═");
                        }

                    } else {
                        //如果这个个元素的上面有元素，线是直的
                        //如果这个个元素的上面没有元素，线是弯的
                        if (!xz_display.get(i - 1).get(j).value.equals("#no#")) {

                            xian_1.append("+");
                            xian_2.append("|");
                            sb_1.append("═");
                            sb_2.append(temp.value.charAt(0));
                            sb_3.append("═");
                            for (int k = 1; k < temp.value.length(); k++) {
                                xian_1.append(" ");
                                xian_2.append(" ");
                                sb_1.append("═");
                                sb_2.append(temp.value.charAt(k));
                                sb_3.append("═");
                            }
                        } else {
                            xian_1.append("╮");
                            xian_2.append("|");
                            sb_1.append("═");
                            sb_2.append(temp.value.charAt(0));
                            sb_3.append("═");
                            for (int k = 1; k < temp.value.length(); k++) {
                                xian_1.append(" ");
                                xian_2.append(" ");
                                sb_1.append("═");
                                sb_2.append(temp.value.charAt(k));
                                sb_3.append("═");
                            }
                        }

                    }


                    //如果太少 补齐当前的空格
                    if (temp.value.length() < pre.get(j)) {
                        for (int k = 0; k < pre.get(j) - temp.value.length(); k++) {
                            xian_1.append(" ");
                            xian_2.append(" ");
                            sb_1.append("═");
                            sb_2.append(" ");
                            sb_3.append("═");
                        }

                    }


                    xian_1.append("   ");
                    xian_2.append("   ");
                    sb_1.append("══╗");
                    sb_2.append("  ║");
                    sb_3.append("══╝");


                }

            }


            int jia_hao_index = 0;
            boolean jahao_ap = false;//加号是否出现过
            boolean xiugai = false;
            for (int k = 0; k < xian_1.length(); k++) {
                if (xian_1.charAt(k) == '+') {
                    jia_hao_index = k;
                    jahao_ap = true;
                    xiugai = false;
                }
                if (xian_1.charAt(k) == '╮' && jahao_ap) {
                    xiugai = true;
                }


                if (xian_1.charAt(k) == '╮') {
                    if (xiugai) {
                        for (int jahao_index = jia_hao_index + 1; jahao_index < k; jahao_index++) {
                            if (xian_1.charAt(jahao_index) != '╮') {
                                xian_1.setCharAt(jahao_index, '-');
                            }

                        }
                    }
                }

            }

            System.out.println(xian_1);
            System.out.println(xian_2);
            System.out.println(sb_1);
            System.out.println(sb_2);
            System.out.println(sb_3);
        }
    }

    /**
     * 从输入过渡到符合规定的输入列表
     * 输入 节点->节点 的次序可以 无序
     * ["a->b","a->c","k->a","c->b"]
     * 里面 k 是根节点
     * 最后形成符合生成 n叉树 的 用户输入的
     * ["k->a","a->b","a->c","c->b"]
     *
     * @param inputList 用户输入的列表
     * @return 符合规定的输入列表
     */
    private List<String> inputList_to_okList(List<String> inputList) {

        //符合规定的输入列表
        List<String> okList = new ArrayList<String>();

        //用 HashMap 暂存序号和输入数据
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

        //先确定根 > 其他的第二个都没有 这个值的第一个
        for (int i = 0; i < inputList.size(); i++) {
            boolean root_flag = false;
            for (int j = 0; j < inputList.size(); j++) {
                if (i != j) {
                    if (inputList.get(i).split("->")[0].equals(inputList.get(j).split("->")[1])) {
                        root_flag = false;
                        break;
                    } else {
                        root_flag = true;
                    }
                }
            }
            if (root_flag) {
                hashMap.put(0, inputList.get(i));
                inputList.remove(i);
                break;
            }
        }

        //从根开始在这个列表里面找后面的节点  有的话添加这个元素和ks，再ks++ ,然后移除这个元素
        //根元素后面可以跟两个元素
        int ks = 1;
        Queue<String> queue = new LinkedList<String>();
        queue.add(hashMap.get(0));

        while (!queue.isEmpty()) {
            String front = queue.poll();

            for (int i = 0; i < inputList.size(); i++) {

                //如果font的第一个和根的第一个 添加到 map 里面
                if (inputList.get(i).split("->")[0].equals(hashMap.get(0).split("->")[0])) {
                    hashMap.put(ks, inputList.get(i));

                    //哟个坑 remove在循环中 需要将 i--
                    //因为for里面一直在计算这个列表的长度，会溢出
                    inputList.remove(i);
                    i--;

                    queue.add(hashMap.get(ks));
                    ks++;
                    continue;
                }

                //如果font的第二个 在列表其他的第一个 添加到 map 里面
                if (inputList.get(i).split("->")[0].equals(front.split("->")[1])) {
                    hashMap.put(ks, inputList.get(i));

                    //哟个坑 remove在循环中 需要将 i--
                    //因为for里面一直在计算这个列表的长度，会溢出
                    inputList.remove(i);
                    i--;

                    queue.add(hashMap.get(ks));
                    ks++;
                }
            }
        }

        //将缓存的 HashMap 按序号填入 要输出的列表里面
        okList.add("root->" + hashMap.get(0).split("->")[0]);
        for (int i = 0; i < hashMap.size(); i++) {
            okList.add(hashMap.get(i));
        }

        return okList;
    }

    /**
     * 将树的结构 保存成竖向列表的形式
     *
     * @param tree_root 树的根节点
     * @return 树结构的竖向列表
     */
    private List<List<Node>> get_display_tree_list(Node tree_root) {

        //将树的结构 保存成竖向列表的形式
        List<Node> temp_node_list = new ArrayList<>();
        displaytree_to_list(tree_root, 0, temp_node_list);

        //返回的竖向列表
        List<List<Node>> list_displaytre = new ArrayList<>();

        int kong_ge = -1;
        for (Node node : temp_node_list) {
            if (node.equals(new Node("#no#"))) {
                kong_ge++;
                continue;
            }
            if (node.equals(new Node("═╝"))) {
                kong_ge--;
                continue;
            }
            List<Node> temp_list = new ArrayList<>();
            for (int j = 0; j < kong_ge; j++) {
                temp_list.add(new Node("#no#"));
            }
            temp_list.add(node);
            list_displaytre.add(temp_list);
        }

        return list_displaytre;
    }

    /**
     * 辅助函数 将树的结构 保存成竖向列表的形式
     *
     * @param tree_root 树的根节点
     * @param level     层级
     * @param list      返回的列表篇
     */
    private void displaytree_to_list(Node tree_root, int level, List<Node> list) {
        //递归函数
        list.add(new Node("#no#"));
        for (int i = 0; i < tree_root.children.size(); i++) {
            Node t = tree_root.children.get(i);

            list.add(t);

            if (!t.children.isEmpty()) {
                displaytree_to_list(t, level + 1, list);
                list.add(new Node("═╝"));
            }
        }
    }

    /**
     * 将树结构的 竖向列表 变为 横向向列表
     *
     * @param l 树结构的竖向列表
     * @return 树结构的横向向列表
     */
    private List<List<Node>> xz_display_tree_list(List<List<Node>> l) {

        //返回的横向向列表
        List<List<Node>> xz_list = new ArrayList<>();

        //这一列的最大宽度
        int max_kuan = 0;
        for (List<Node> nodes : l) {
            int temp_max = 0;
            for (int k = 0; k < nodes.size(); k++) {
                temp_max++;
            }
            if (temp_max > max_kuan) {
                max_kuan = temp_max;
            }
        }

        for (int i = 0; i < max_kuan; i++) {
            List<Node> temp_list = new ArrayList<Node>();
            for (List<Node> nodes : l) {
                try {
                    Node tem_node = nodes.get(i);
                    temp_list.add(tem_node);
                } catch (Exception e) {
                    temp_list.add(new Node("#no#"));
                }
            }
            xz_list.add(temp_list);
        }

        return xz_list;
    }

}
