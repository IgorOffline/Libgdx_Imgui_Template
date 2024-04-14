package igoroffline.template.libgdximgui.main.astarsimple.collections.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimplePriorityQueue<T> implements ModelAPriorityQueue<T> {

    private final List<T> innerList = new ArrayList<>();
    private final Comparator<T> comparator;

    public SimplePriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int push(T item) {
        var p = innerList.size();
        innerList.add(item);

        do {
            if (p == 0) {
                break;
            }

            var p2 = (p - 1) / 2;

            if (onCompare(p, p2) < 0) {
                switchElements(p, p2);
                p = p2;
            } else {
                break;
            }

        } while (true);

        return p;
    }

    @Override
    public T pop() {
        var result = innerList.getFirst();
        var p = 0;

        innerList.set(0, innerList.getLast());
        innerList.removeLast();

        do {
            var pn = p;
            var p1 = 2 * p + 1;
            var p2 = 2 * p + 2;

            if (innerList.size() > p1 && onCompare(p, p1) > 0) {
                p = p1;
            }
            if (innerList.size() > p2 && onCompare(p, p2) > 0) {
                p = p2;
            }

            if (p == pn)
            {
                break;
            }

            switchElements(p, pn);

        } while (true);

        return result;
    }

    @Override
    public T peek() {
        return innerList.isEmpty() ? null : innerList.getFirst();
    }

    @Override
    public void clear() {
        innerList.clear();
    }

    @Override
    public int count() {
        return innerList.size();
    }

    private void switchElements(int i, int j) {
        var h = innerList.get(i);
        innerList.set(i, innerList.get(j));
        innerList.set(j, h);
    }

    private int onCompare(int i, int j) {
        return comparator.compare(innerList.get(i), innerList.get(j));
    }
}
