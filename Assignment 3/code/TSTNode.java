// add your imports here

import java.util.ArrayList;
import java.util.List;

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here

    /**
     * 节点的无参数构造器
     */
    public TSTNode() {}

    /**
     *节点的 element 构造器
     * @param element
     */
    TSTNode(T element){
        this.element = element;
    }

    /**
     * 节点的 element 和 children 构造器
     * @param element
     * @param left
     * @param mid
     * @param right
     */
    public TSTNode(T element, TSTNode<T> left, TSTNode<T> mid, TSTNode<T> right) {
        this.element = element;
        this.left = left;
        this.mid = mid;
        this.right = right;
    }

    /**
     * 找最大节点的方法
     * @return 以这个节点为根的三叉树的最大节点
     */
    TSTNode<T> findMax(){
        if(this.right == null) return this;
        return this.right.findMax();
    }

    /**
     * 找最小节点的方法
     * @return 以这个节点为根的三叉树的最小节点
     */
    TSTNode<T> findMin(){
        if(this.left == null) return this;
        return this.left.findMin();
    }


    /**
     * 将所传入的list添加按照大小分好类的节点
     * @param list
     */
    public void addList(List<TSTNode<T>> list){
        if (this.left != null) {
            this.left.addList(list);
        }
        if (this.mid != null) {
            this.mid.addList(list);
        }
        list.add(this);
        if (this.right != null) {
            this.right.addList(list);
        }
    }

    /**
     * 找高度的方法
     * @return 以这个节点为根的三叉树的高度
     */
    int height(){
        if(this.left == null && this.right == null && this.mid == null) return 0;
        int h = 0;
        h = this.left == null ? h : Math.max(h,this.left.height());
        h = this.mid == null ? h : Math.max(h,this.mid.height());
        h = this.right == null ? h : Math.max(h,this.right.height());
        return h + 1;
    }

}