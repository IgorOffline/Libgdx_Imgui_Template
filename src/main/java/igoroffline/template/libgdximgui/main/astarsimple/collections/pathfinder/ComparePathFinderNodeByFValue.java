package igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder;

import java.util.Comparator;

public class ComparePathFinderNodeByFValue implements Comparator<PathFinderNode> {

    @Override
    public int compare(PathFinderNode a, PathFinderNode b) {
        return Integer.compare(a.getF(), b.getF());
    }
}
