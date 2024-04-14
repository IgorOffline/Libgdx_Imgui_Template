package igoroffline.template.libgdximgui.main.astarsimple.collections.priorityqueue;

public interface ModelAPriorityQueue<T> {

    int push(T item);
    T pop();
    T peek();

    void clear();
    int count();
}
