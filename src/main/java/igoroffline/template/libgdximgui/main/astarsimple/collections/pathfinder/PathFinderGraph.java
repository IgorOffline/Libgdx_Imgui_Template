package igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder;

import igoroffline.template.libgdximgui.main.astarsimple.Position;
import igoroffline.template.libgdximgui.main.astarsimple.collections.multidimensional.Grid;
import igoroffline.template.libgdximgui.main.astarsimple.collections.priorityqueue.SimplePriorityQueue;

import java.util.List;

public class PathFinderGraph implements ModelAGraph<PathFinderNode> {

    private final boolean allowDiagonalTraversal;
    private final Grid<PathFinderNode> internalGrid;
    private final SimplePriorityQueue<PathFinderNode> open = new SimplePriorityQueue<>(new ComparePathFinderNodeByFValue());

    public PathFinderGraph(int height, int width, boolean allowDiagonalTraversal) {
        this.allowDiagonalTraversal = allowDiagonalTraversal;
        this.internalGrid = new Grid<>(height, width);
        init();
    }

    public boolean hasOpenNodes() {
        return open.count() > 0;
    }

    private void init() {

        for (var row = 0; row < internalGrid.getHeight(); row++) {

            for (var column = 0; column < internalGrid.getWidth(); column++) {

                internalGrid.set(row, column, new PathFinderNode(new Position(row, column), 0, 0, null));
            }
        }

        open.clear();
    }

    @Override
    public List<PathFinderNode> getSuccessors(PathFinderNode node) {
        return internalGrid.getSuccessorPositions(node.position(), allowDiagonalTraversal).stream().map(internalGrid::get).toList();
    }

    @Override
    public PathFinderNode getParent(PathFinderNode node) {
        return internalGrid.get(node.parentNodePosition());
    }

    @Override
    public void openNode(PathFinderNode node) {
        internalGrid.set(node.position(), node);
        open.push(node);
    }

    @Override
    public PathFinderNode getOpenNodeWithSmallestF() {
        return open.pop();
    }
}
