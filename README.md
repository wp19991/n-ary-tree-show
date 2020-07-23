# 目前的问题
## 第一个问题
- 正在想解决方法

必须按图的顺序 从上往下输入节点 的路径

`->` 的左侧一定要在之前出现过，不然会失效

## 第二个问题
- 已经有方法可以解决打印的箭头方向


## 第三个问题
- 结束前会修改
内部的部分函数和类的命名和使用方法有点乱

# 使用方法
```java
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

        //调试使用
        t.printTree();
        new myutil().print_MultiTree_List(t);

        //打印的结果
        new myutil().command_show_tree(t);

    }
}
```
---
打印结果：
```
root->fffff
----
fffff->ro
----
ro->fir
----
fir->xw
----
xw->y
xw->a
xw->z
----
y->f
a->aaaaaaa
a->eeegtfte
z->e
----
root->fffff 坐标信息:(行,列) (0,0)  这行一共有元素个数：1
fffff->ro 坐标信息:(行,列) (1,0)  这行一共有元素个数：1
ro->fir 坐标信息:(行,列) (2,0)  这行一共有元素个数：1
fir->xw 坐标信息:(行,列) (3,0)  这行一共有元素个数：1
xw->y 坐标信息:(行,列) (4,0)  这行一共有元素个数：3
xw->a 坐标信息:(行,列) (4,1)  这行一共有元素个数：3
xw->z 坐标信息:(行,列) (4,2)  这行一共有元素个数：3
y->f 坐标信息:(行,列) (5,0)  这行一共有元素个数：4
a->aaaaaaa 坐标信息:(行,列) (5,1)  这行一共有元素个数：4
a->eeegtfte 坐标信息:(行,列) (5,2)  这行一共有元素个数：4
z->e 坐标信息:(行,列) (5,3)  这行一共有元素个数：4
     |     
+---------+
|  fffff  |
+---------+
    |   
+------+
|  ro  |
+------+
    |    
+-------+
|  fir  |
+-------+
    |   
+------+
|  xw  |
+------+
   |         |         |   
+-----+   +-----+   +-----+
|  y  |   |  a  |   |  z  |
+-----+   +-----+   +-----+
   |            |                |            |   
+-----+   +-----------+   +------------+   +-----+
|  f  |   |  aaaaaaa  |   |  eeegtfte  |   |  e  |
+-----+   +-----------+   +------------+   +-----+

Process finished with exit code 0

```
