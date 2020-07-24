package org.wp.treeShow2;


import java.util.*;


public class treeShow2 {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();

        inputList.add("o->aa");
        inputList.add("aa->xoop");
        inputList.add("xoop->xxxx");
        inputList.add("aa->bbb");
        inputList.add("bbb->g");
        inputList.add("bbb->ff");
        inputList.add("bbb->c");
        inputList.add("ff->e");
        inputList.add("c->k");
        inputList.add("c->d");
        inputList.add("d->h");
        inputList.add("ff->t");
        inputList.add("g->xhh");

        MultiTree t = new myutil().inputList_to_MultiTree(inputList);

        //调试使用
        t.printTree();
        new myutil().print_MultiTree_List(t);

        System.out.println("\n");

        //打印的结果
        new myutil().command_show_tree_pre(t);

    }
}


