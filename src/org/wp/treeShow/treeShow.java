package org.wp.treeShow;


import java.util.*;


public class treeShow {
    public static void main(String[] args) {

        List<String> inputList = new ArrayList<>();

//        inputList.add("cc->qq");
//        inputList.add("aa->yy");
//        inputList.add("aa->bb");
//        inputList.add("bb->cc");
//        inputList.add("bb->ee");
//        inputList.add("ff->jjjj");
//        inputList.add("cc->dd");
//        inputList.add("bb->ff");
//        inputList.add("aa->gg");
//        inputList.add("yy->zz");

        inputList.add("select->first");
        inputList.add("select->hhhh");
        inputList.add("second->third");
        inputList.add("select->xxxx");
        inputList.add("select->second");
        inputList.add("first->++");
        inputList.add("first->--");

        MultiTree t = new myutil().inputList_to_MultiTree(inputList);

        //打印结果
        new myutil().displaytree_from_MultiTree_xz(t);

    }
}
