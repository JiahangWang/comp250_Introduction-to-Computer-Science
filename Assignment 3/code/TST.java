import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TST<T extends Comparable<T>> implements Iterable<T>{
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }

    // TODO: implement the tree class here

    /**
     * 三叉树插入节点的方法
     * @param element
     */
    public void insert(T element){
        TSTNode<T> troot = root;
        if(troot == null) {
            root = new TSTNode<>(element);
        }
        int cmpV = 0;
        TSTNode<T> parent = new TSTNode<>();
        while (troot != null){
            parent = troot;
            cmpV = element.compareTo(troot.element);
            if(cmpV > 0){
                troot = troot.right;
            }
            else if(cmpV < 0){
                troot = troot.left;
            }
            else {
                troot = troot.mid;
            }
        }
        TSTNode<T> newNode = new TSTNode<>(element);
        if(cmpV > 0){
            parent.right = newNode;
        }
        else if(cmpV < 0){
            parent.left = newNode;
        }
        else {
            parent.mid = newNode;
        }
    }

    /**
     * 删除三叉树中的一个节点
     * @param element
     */
    public void remove(T element){
        root = remove(root,element);
    }

    public TSTNode<T> remove(TSTNode<T> node,T element){
        if(node == null){
            return null;
        }
        else {
            int cmpV = element.compareTo(node.element);
            if(cmpV > 0){
                node.right = remove(node.right,element);
            }
            else if(cmpV < 0){
                node.left = remove(node.left,element);
            }
            else {

                if(node.mid != null){
                    TSTNode<T> parent = new TSTNode<T>();
                    TSTNode<T> tnode = new TSTNode<T>();
                    tnode = node;
                    while (tnode.mid != null){
                        parent = node;
                        tnode = tnode.mid;
                    }
                    parent.mid = null;
                }
                else {
                    if(node.right == null){
                        node = node.left;
                    }
                    else if(node.left == null){
                        node = node.right;
                    }
                    else {
                        TSTNode<T> leftMax = node.left.findMax();
                        int count = 0;
                        while (leftMax.mid != null) {
                            leftMax = leftMax.mid;
                            count++;
                        }
                        TSTNode<T> tnode = node;
                        for (int i = 0; i < count; i++) {
                            node.left = remove(node.left, leftMax.element);
                            tnode.mid = new TSTNode<T>(leftMax.element);
                            tnode = tnode.mid;
                        }
                        node.element = leftMax.element;
                        node.left = remove(node.left, node.element);
                    }
                }

            }
        }
        return node;
    }

    /**
     * 三叉树判断是否包含传入节点的方法
     * @param element
     * @return 是否包含该节点的布尔值
     */
    public boolean contains(T element){
        TSTNode<T> troot = root;
        if(troot == null){
            return false;
        }
        int cmpV = 0;
        while (troot != null){
            cmpV = element.compareTo(troot.element);
            if(cmpV > 0){
                troot = troot.right;
            }
            else if(cmpV < 0){
                troot = troot.left;
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * 将三叉树重新平衡的方法
     */
    public void rebalance(){
        List<T> list = new ArrayList<>();
        List<TSTNode<T>> templist = new ArrayList<>();
        root.addList(templist);
        for (int i = 0; i < templist.size(); i++) {
            list.add(templist.get(i).element);
        }
            ArrayList<T> list2 = new ArrayList<>();
            addMiddle(list,list2);
            this.root = new TSTNode<T>(list2.remove(0));
            for(T t : list2){
                insert(t);
            }
    }


    public void addMiddle(List<T> list1,List<T> list2){
        if(list1.size() == 0){}
        else if(list1.size() == 1){
            list2.add(list1.get(0));
        }
        else {
            int middle = list1.size() / 2;
            list2.add(list1.get(middle));
            List<T> listSmall = list1.subList(0,middle);
            List<T> listBig = list1.subList(middle + 1, list1.size());
            addMiddle(listSmall, list2);
            addMiddle(listBig, list2);
        }
    }



    
    /**
     * Caculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height(){
        if (this.root == null)
            return -1;
        return this.root.height();
    }



    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        Iterator<T> itr = new TSTIterator<>(this);
        return itr;
    }



    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root,"", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     * @param buffer String buffer
     * @param node Root node
     * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
     */
    public void inorderPrintAsList(){
        String buffer = "[";
        for (T element: this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0,len-2);
        buffer += "]";
        System.out.println(buffer);
    }
}