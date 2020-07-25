package org.wp.treeShow2;


import java.util.*;

public class myutil {

    public void print_MultiTree_List(MultiTree t) {
        for (int i = 0; i < t.res.size(); i++) {
            for (int j = 0; j < t.res.get(i).size(); j++) {
                System.out.println(t.res.get(i).get(j).prev.value + "->" + t.res.get(i).get(j).value
                        + " 坐标信息:(行,列) (" + t.res.get(i).get(j).hang + "," + t.res.get(i).get(j).lie + ") "
                        + " 父节点的坐标信息:(行,列) (" + t.res.get(i).get(j).pre_node_hang
                        + "," + t.res.get(i).get(j).pre_node_lie + ") "
                        + " 子节点的第一个位置(列):" + t.res.get(i).get(j).children_first_lie
                        + " 子节点的个数:" + t.res.get(i).get(j).children_num
                        + " 这行一共有元素个数：" + t.res.get(i).get(j).lieNum);
            }
        }
    }


    public void command_show_tree(MultiTree t) {
        //r->o 坐标信息:(行,列) (0,0)  父节点的坐标信息:(行,列) (0,0)  子节点的第一个位置(列):0 子节点的个数:1 这行一共有元素个数：1
        //o->a 坐标信息:(行,列) (1,0)  父节点的坐标信息:(行,列) (0,0)  子节点的第一个位置(列):0 子节点的个数:2 这行一共有元素个数：1
        //a->x 坐标信息:(行,列) (2,0)  父节点的坐标信息:(行,列) (1,0)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：2
        //a->b 坐标信息:(行,列) (2,1)  父节点的坐标信息:(行,列) (1,0)  子节点的第一个位置(列):0 子节点的个数:3 这行一共有元素个数：2
        //b->g 坐标信息:(行,列) (3,0)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：3
        //b->f 坐标信息:(行,列) (3,1)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):0 子节点的个数:2 这行一共有元素个数：3
        //b->c 坐标信息:(行,列) (3,2)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):2 子节点的个数:2 这行一共有元素个数：3
        //f->e 坐标信息:(行,列) (4,0)  父节点的坐标信息:(行,列) (3,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //f->t 坐标信息:(行,列) (4,1)  父节点的坐标信息:(行,列) (3,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //c->k 坐标信息:(行,列) (4,2)  父节点的坐标信息:(行,列) (3,2)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //c->d 坐标信息:(行,列) (4,3)  父节点的坐标信息:(行,列) (3,2)  子节点的第一个位置(列):0 子节点的个数:1 这行一共有元素个数：4
        //d->h 坐标信息:(行,列) (5,0)  父节点的坐标信息:(行,列) (4,3)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：1


        //每一行由5行组成
        //第一行到第3行打印外框和这个节点的信息
        //第4行到第5行打印到下一行的线
        for (int i = 0; i < t.res.size(); i++) {

            StringBuffer sb_1 = new StringBuffer("  ");//打印上外框
            StringBuffer sb_2 = new StringBuffer("  ");//打印边外框和元素
            StringBuffer sb_3 = new StringBuffer("  ");//打印下外框

            StringBuffer sb_xian_1 = new StringBuffer("  ");//下面方向的线
            StringBuffer sb_xian_2 = new StringBuffer("  ");//下面方向的线

            StringBuffer full_pre_kong = new StringBuffer("");//根据 这个节点的坐标信息 子节点的个数 子节点的第一个位置 填充空白
            /*
             这个节点的坐标的列 子节点的个数 子节点的第一个位置          框前补的空格数（1 -> 补 6 + 这个元素的长度）
                    0           1           0               ->      0
                    0           2           0               ->      0.5
                    0           3           0               ->      1
                    0           4           0               ->      1.5
                    0           5           0               ->      2

                    0           1           1               ->      0+1
                    0           1           2               ->      0+2

                    0           2           1               ->      1+0.5
                    0           2           2               ->      1+1

                    1           1           0               ->      0
                    1           2           0               ->      0
                    1           3           0               ->      0
                    1           4           0               ->      0
                    1           5           0               ->      0

                    1           1           1               ->      0
                    1           1           2               ->      1
                    1           1           3               ->      2


                    1           2           1               ->      (1-1)+(2-1)*0.5
                    1           2           2               ->      (2-1)+(2-1)*0.5
                    1           2           3               ->      (3-1)+(2-1)*0.5


                    2           1           0               ->      0
                    2           2           0               ->      0
                    2           3           0               ->      0
                    2           4           0               ->      0
                    2           5           0               ->      0


                    2           1           1               ->      (1-1)*0.5
                    2           1           2               ->      (1-1)*0.5
                    2           1           3               ->      (1-1)*0.5


                    2           2           1               ->      (1-1)+0
                    2           2           2               ->      (2-1)+0.5
                    2           2           3               ->      (3-1)+1




            */


            for (int j = 0; j < t.res.get(i).size(); j++) {
                //这个节点
                Node this_temp_node = t.res.get(i).get(j);
                //（1 -> 补 6 + 这个元素的长度）
                int this_node_value_length_ban = (this_temp_node.String_length + 6) / 2;

                int this_node_lie = this_temp_node.lie;
                int this_node_children_nums = this_temp_node.children_num;
                int this_node_children_first_lie = this_temp_node.children_first_lie;

                if (this_node_children_first_lie > this_node_lie) {
                    full_pre_kong.append(" ");
                } else {
                    if (this_node_lie == 0) {
                        int ban_ci_num = 2 * this_node_children_first_lie - 2 * this_node_lie + this_node_children_nums - 1;
                        for (int ci_num = 0; ci_num < (ban_ci_num + 1); ci_num++) {
                            for (int l = 0; l < this_node_value_length_ban; l++) {
                                full_pre_kong.append(" ");
                                sb_1.append(" ");
                                sb_2.append(" ");
                                sb_3.append(" ");
                            }
                        }
                    } else {
                        int ban_ci_num = 2 * this_node_children_first_lie - 2 * this_node_lie + this_node_children_nums - 1;
                        for (int ci_num = 0; ci_num < (ban_ci_num); ci_num++) {
                            for (int l = 0; l < this_node_value_length_ban; l++) {
                                full_pre_kong.append(" ");
                                sb_1.append(" ");
                                sb_2.append(" ");
                                sb_3.append(" ");
                            }
                        }

                    }
                }


                //这列的第一行和第三行
                if (j != 0) {
                    sb_1.append("   ");
                    sb_2.append("   ");
                    sb_3.append("   ");
                }

                sb_1.append("+--");
                sb_3.append("+--");
                for (int lie = 0; lie < this_temp_node.String_length; lie++) {
                    sb_1.append("-");
                    sb_3.append("-");
                }
                sb_1.append("--+");
                sb_3.append("--+");


                //这列的第二行
                sb_2.append("|  ");
                sb_2.append(this_temp_node.value);
                sb_2.append("  |");


            }

            System.out.print(full_pre_kong);
            System.out.println(sb_1);//第一行
            System.out.print(full_pre_kong);
            System.out.println(sb_2);//第二行
            System.out.print(full_pre_kong);
            System.out.println(sb_3);//第三行
            System.out.println("\n");
            //System.out.println(sb_xian);//方向的线

        }


        for (int i = 0; i < t.res.size(); i++) {

            StringBuffer sb_1 = new StringBuffer();
            StringBuffer sb_2 = new StringBuffer();
            StringBuffer sb_3 = new StringBuffer();

            StringBuffer sb_xian = new StringBuffer();//方向的线


            for (int j = 0; j < t.res.get(i).size(); j++) {
                //这列元素的线
                if (j != 0) {
                    sb_xian.append("   ");
                }
                sb_xian.append("   ");
                int ks = 0;
                for (int lie = 0; lie < t.res.get(i).get(j).String_length / 2; lie++) {
                    ks++;
                    sb_xian.append(" ");
                }
                sb_xian.append("|");//方向
                for (int lie = ks + 1; lie < t.res.get(i).get(j).String_length; lie++) {
                    sb_xian.append(" ");
                }


                //这列的第一行和第三行
                if (j != 0) {
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
//            System.out.println(sb_xian);//方向的线
//            System.out.println(sb_1);//第一行
//            System.out.println(sb_2);//第二行
//            System.out.println(sb_3);//第三行


        }

    }


    public void command_show_tree_pre(MultiTree t) {
        //r->o 坐标信息:(行,列) (0,0)  父节点的坐标信息:(行,列) (0,0)  子节点的第一个位置(列):0 子节点的个数:1 这行一共有元素个数：1
        //o->a 坐标信息:(行,列) (1,0)  父节点的坐标信息:(行,列) (0,0)  子节点的第一个位置(列):0 子节点的个数:2 这行一共有元素个数：1
        //a->x 坐标信息:(行,列) (2,0)  父节点的坐标信息:(行,列) (1,0)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：2
        //a->b 坐标信息:(行,列) (2,1)  父节点的坐标信息:(行,列) (1,0)  子节点的第一个位置(列):0 子节点的个数:3 这行一共有元素个数：2
        //b->g 坐标信息:(行,列) (3,0)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：3
        //b->f 坐标信息:(行,列) (3,1)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):0 子节点的个数:2 这行一共有元素个数：3
        //b->c 坐标信息:(行,列) (3,2)  父节点的坐标信息:(行,列) (2,1)  子节点的第一个位置(列):2 子节点的个数:2 这行一共有元素个数：3
        //f->e 坐标信息:(行,列) (4,0)  父节点的坐标信息:(行,列) (3,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //f->t 坐标信息:(行,列) (4,1)  父节点的坐标信息:(行,列) (3,1)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //c->k 坐标信息:(行,列) (4,2)  父节点的坐标信息:(行,列) (3,2)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：4
        //c->d 坐标信息:(行,列) (4,3)  父节点的坐标信息:(行,列) (3,2)  子节点的第一个位置(列):0 子节点的个数:1 这行一共有元素个数：4
        //d->h 坐标信息:(行,列) (5,0)  父节点的坐标信息:(行,列) (4,3)  子节点的第一个位置(列):-1 子节点的个数:0 这行一共有元素个数：1


        for (int i = 0; i < t.res.size(); i++) {

            StringBuffer sb_1 = new StringBuffer();//这列的第一行
            StringBuffer sb_2 = new StringBuffer();//这列的第二行
            StringBuffer sb_3 = new StringBuffer();//这列的第三行

            StringBuffer sb_xian = new StringBuffer();//方向的线

            for (int j = 0; j < t.res.get(i).size(); j++) {
                Node temp_node = t.res.get(i).get(j);
                //这列的第一行和第三行
                if (j != 0) {
                    sb_1.append("   ");
                    sb_2.append("   ");
                    sb_3.append("   ");
                }

                sb_1.append("╔══");
                sb_3.append("╚══");
                for (int lie = 0; lie < temp_node.String_length; lie++) {
                    sb_1.append("═");
                    sb_3.append("═");
                }
                sb_1.append("══╗");
                sb_3.append("══╝");

                //这列的第二行
                sb_2.append("║  ");
                sb_2.append(temp_node.value);
                sb_2.append("  ║");

            }

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
        List<String> okList = this.inputList_to_okList(inputList);
        //List<String> okList = inputList;

        MultiTree t = new MultiTree(new Node(okList.get(0).split("->")[0]));

        for (int i = 0; i < okList.size(); i++) {
            String first_node_name = okList.get(i).split("->")[0];
            String second_node_name = okList.get(i).split("->")[1];
            t.addTreeNode(new Node(first_node_name), new Node(second_node_name));
        }

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
    private List<String> inputList_to_okList(List<String> inputList){
        List<String> temp = new ArrayList<String> ();


        HashMap<Integer,String > tem = new HashMap<Integer,String > ();

        //先确定根 > 其他的第二个都没有 这个值的第一个
        for (int i = 0; i < inputList.size(); i++){
            boolean root_flag = false;
            for (int j = 0; j < inputList.size(); j++){
                if (i!=j){
                    if (inputList.get(i).split("->")[0].equals(inputList.get(j).split("->")[1])){
                        root_flag = false;
                        break;
                    }else {
                        root_flag = true;
                    }
                }
            }
            if (root_flag) {
                tem.put(0,inputList.get(i));
                inputList.remove(i);
                break;
            }
        }

        //从根开始在这个列表里面找后面的节点  有的话添加这个元素和ks，再ks++ ,然后移除这个元素
        int ks = 1;
        Queue<String> que= new LinkedList<String>();
        que.add(tem.get(0));

        while (!que.isEmpty()){
            String front = que.poll();
            //System.out.println(front);
            //que.remove();


            for(int i = 0; i < inputList.size(); i++){
                //如果font的第二个 在列表其他的第一个 添加到 map 里面

                if (inputList.get(i).split("->")[0].equals(front.split("->")[1])){
                    tem.put(ks,inputList.get(i));
                    //System.out.println(ks+" que) -> "+inputList.get(i));
                    inputList.remove(i);
                    i--;
                    que.add(tem.get(ks));
                    //System.out.println(ks+" tem) -> "+tem.get(ks));
                    ks++;
                }
            }
        }

        temp.add("root->"+tem.get(0).split("->")[0]);
        for (int i = 0; i < tem.size(); i++) {
            temp.add(tem.get(i));
            //System.out.println(temp.get(i)+"===============");
        }

        //System.out.println("---+++++-----");
        return temp;
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