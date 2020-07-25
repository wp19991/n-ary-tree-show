package org.wp.treeShow1;


import java.util.*;


public class treeShow1 {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();

        inputList.add("bb->cccc");
        inputList.add("fffff->t");
        inputList.add("bb->g");
        inputList.add("cccc->k");
        inputList.add("aa->xo");
        inputList.add("xo->xx");
        inputList.add("aa->bb");
        inputList.add("g->xh");
        inputList.add("bb->fffff");
        inputList.add("fffff->e");
        inputList.add("cccc->d");
        inputList.add("d->h");
        inputList.add("o->aa");
        inputList.add("aa->ll");
        inputList.add("h->rr");

        MultiTree t = new myutil().inputList_to_MultiTree(inputList);

        //调试使用
        t.printTree();
        System.out.println("------------");


        //打印的结果1.0  竖着显示  空白的地方不完善
        //new myutil().displaytree_from_MultiTree(t);


        //打印的结果2.0  横着显示  没有居中显示
        new myutil().displaytree_from_MultiTree_xz(t);


    }
}
