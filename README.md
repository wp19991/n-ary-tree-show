# 打印单颗多叉树的结构 java 版
- 输入一个多叉树的节点顺序列表  List
- 其中每个元素的两侧节点名称用 -> 隔开

# 说明
- 支持不按顺序的输入节点（根节点可以写在任何位置）
- 不支持多对一的顺序（多个不同的节点指向一个节点）

# 使用方法
```java
import org.wp.treeShow.*;

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

```

## 打印结果：
```
     +                                                          
     |                                                          
  ╔══════════╗                                                  
  ║  select  ║                                                  
  ╚══════════╝                                                  
     +-------------------------╮-----------╮-----------╮        
     |                         |           |           |        
  ╔══════════╗              ╔════════╗  ╔════════╗  ╔══════════╗
  ║  first   ║              ║  hhhh  ║  ║  xxxx  ║  ║  second  ║
  ╚══════════╝              ╚════════╝  ╚════════╝  ╚══════════╝
     +-------------╮                                   +        
     |             |                                   |        
  ╔══════════╗  ╔════════╗                          ╔══════════╗
  ║  ++      ║  ║  --    ║                          ║  third   ║
  ╚══════════╝  ╚════════╝                          ╚══════════╝

```