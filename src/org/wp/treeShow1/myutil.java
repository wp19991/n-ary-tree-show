package org.wp.treeShow1;

import java.util.*;

public class myutil {


    public void displaytree(Node f, int level) {       //递归显示树
        StringBuilder preStr = new StringBuilder();
        for(int i=0; i<level; i++) {
            for (int j = 0; j < 8+f.value.length(); j++){
                preStr.append(" ");
            }
        }

        for(int i=0; i<f.children.size(); i++) {
            Node t = f.children.get(i);

            //第一行
            System.out.print(preStr + " \\ ╔══");
            for (int j = 0; j < t.value.length(); j++){
                System.out.print("═");
            }
            System.out.println("══╗");

            //第二行
            System.out.println(preStr + " ╰-║  "+t.value+"  ║");

            //第三行
            System.out.print(preStr + "   ╚══");
            for (int j = 0; j < t.value.length(); j++){
                System.out.print("═");
            }
            System.out.println("══╝");

            if(! t.children.isEmpty()) {
                displaytree(t, level + 1);
            }
        }
    }




    /**
     * 将用户输入的无序列表，变成一颗 n 叉树
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
            //System.out.println(first_node_name+"->"+second_node_name);
            t.addTreeNode(new Node(first_node_name), new Node(second_node_name));
        }

        //t.levelOrder();
        return t;
    }


}
