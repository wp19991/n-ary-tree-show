package org.wp.treeShow1;


import java.util.*;


public class treeShow1 {
    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();

        inputList.add("cc->qq");//
        inputList.add("aa->yy");//
        inputList.add("aa->bb");//
        inputList.add("bb->cc");//
        inputList.add("bb->ee");//
        inputList.add("ff->jjjj");//
        inputList.add("cc->dd");//
        inputList.add("bb->ff");//
        inputList.add("aa->gg");//
        inputList.add("yy->zz");


        MultiTree t = new myutil().inputList_to_MultiTree(inputList);

        //调试使用
        t.printTree();
        System.out.println("------------");
        //new myutil().print_List_List_Node(t);
        //System.out.println("------------");


        //打印的结果2.0  横着显示  没有居中显示
        new myutil().displaytree_from_MultiTree_xz(t);


    }
}
