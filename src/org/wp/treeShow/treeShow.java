package org.wp.treeShow;

import java.util.*;

public class treeShow {
    public static void main(String[] args) {

        List<String> inputList = new ArrayList<>();

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
