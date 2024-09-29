import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
// add your imports here

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    TST<T> tst;
    List<TSTNode<T>> list;
    int pointer;
    // TODO: implement the iterator class here
    // add your own helper methods if necessary


    public TSTIterator(TST<T> tst) {
        list = new ArrayList<>();
        this.tst = tst;
        TSTNode<T> troot = tst.root;
        troot.addList(list);
        pointer = 0;
    }

    public TSTIterator(TSTNode<T> root){
        list = new ArrayList<>();
        TSTNode<T> troot = root;
        troot.addList(list);
        pointer = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return list.size() >= pointer + 1;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
        if (hasNext()) {
            int i = pointer;
            pointer ++;
            return list.get(i).element;
        }
        else return null;
    }


}