package igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder;

import java.util.List;

public interface ModelAGraph<T> {

    boolean hasOpenNodes();
    List<T> getSuccessors(T node);
    T getParent(T node);
    void openNode(T node);
    T getOpenNodeWithSmallestF();
}
