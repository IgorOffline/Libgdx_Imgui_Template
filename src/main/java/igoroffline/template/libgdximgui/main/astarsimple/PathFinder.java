package igoroffline.template.libgdximgui.main.astarsimple;

import igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder.ModelAGraph;
import igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder.PathFinderGraph;
import igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder.PathFinderNode;
import igoroffline.template.libgdximgui.main.astarsimple.heuristics.CalculateHeuristic;
import igoroffline.template.libgdximgui.main.astarsimple.heuristics.HeuristicFactory;
import igoroffline.template.libgdximgui.main.astarsimple.options.PathFinderOptions;

import java.util.Arrays;
import java.util.Stack;

public class PathFinder implements FindAPath {

    private final int closedValue = 0;
    private final int distanceBetweenNodes = 1;
    private final PathFinderOptions options;
    private final WorldGrid world;
    private final CalculateHeuristic heuristic;

    public PathFinder(WorldGrid worldGrid, PathFinderOptions pathFinderOptions)
    {
        if (worldGrid == null) {
            throw new IllegalArgumentException("worldGrid is null");
        }

        world = worldGrid;

        if (pathFinderOptions == null) {
            options = new PathFinderOptions();
        } else {
            options = pathFinderOptions;
        }

        heuristic = HeuristicFactory.create(options.getHeuristicFormula());
    }

    @Override
    public Point[] findPath(Point start, Point end) {

        return Arrays.stream(findPath(new Position(start.y(), start.x()), new Position(end.y(), end.x())))
                .map(position -> new Point(position.column(), position.row())).toArray(Point[]::new);
    }

    @Override
    public Position[] findPath(Position start, Position end) {

        var nodesVisited = 0;
        ModelAGraph<PathFinderNode> graph = new PathFinderGraph(world.getHeight(), world.getWidth(), options.isUseDiagonals());

        var startNode = new PathFinderNode(start, 0, 2, start);
        graph.openNode(startNode);

        while (graph.hasOpenNodes())
        {
            var q = graph.getOpenNodeWithSmallestF();

            if (q.position().equals(end))
            {
                return orderClosedNodesAsArray(graph, q);
            }

            if (nodesVisited > options.getSearchLimit())
            {
                return new Position[0];
            }

            for (var successor : graph.getSuccessors(q)) {

                if (world.get(successor.position()).intValue() == closedValue) {
                    continue;
                }

                var newG = q.g() + distanceBetweenNodes;

                if (options.isPunishChangeDirection())
                {
                    newG += calculateModifierToG(q, successor, end);
                }

                var updatedSuccessor = new PathFinderNode(successor.position(), newG, heuristic.calculate(successor.position(), end), q.position());

                if (betterPathToSuccessorFound(updatedSuccessor, successor))
                {
                    graph.openNode(updatedSuccessor);
                }
            }

            nodesVisited++;
        }

        return new Position[0];
    }

    private int calculateModifierToG(PathFinderNode q, PathFinderNode successor, Position end) {

        if (q.position() == q.parentNodePosition()) {
            return 0;
        }

        var gPunishment = Math.abs(successor.position().row() - end.row()) + Math.abs(successor.position().column() - end.column());

        var successorIsVerticallyAdjacentToQ = successor.position().row() - q.position().row() != 0;

        if (successorIsVerticallyAdjacentToQ) {
            var qIsVerticallyAdjacentToParent = q.position().row() - q.parentNodePosition().row() == 0;
            if (qIsVerticallyAdjacentToParent) {
                return gPunishment;
            }
        }

        if (options.isUseDiagonals())
        {
            var successorIsDiagonallyAdjacentToQ = (successor.position().column() - successor.position().row()) == (q.position().column() - q.position().row());
            if (successorIsDiagonallyAdjacentToQ)
            {
                var qIsDiagonallyAdjacentToParent = (q.position().column() - q.position().row()) == (q.parentNodePosition().column() - q.parentNodePosition().row())
                        && isStraightLine(q.parentNodePosition(), q.position(), successor.position());
                if (qIsDiagonallyAdjacentToParent)
                {
                    return gPunishment;
                }
            }
        }

        return 0;
    }

    private boolean isStraightLine(Position a, Position b, Position c)
    {
        // area of triangle == 0
        return (a.column() * (b.row() - c.row()) + b.column() * (c.row() - a.row()) + c.column() * (a.row() - b.row())) / 2 == 0;
    }

    private boolean betterPathToSuccessorFound(PathFinderNode updateSuccessor, PathFinderNode currentSuccessor)
    {
        return !currentSuccessor.hasBeenVisited() || updateSuccessor.getF() < currentSuccessor.getF();
    }

    private static Position[] orderClosedNodesAsArray(ModelAGraph<PathFinderNode> graph, PathFinderNode endNode)
    {
        var path = new Stack<Position>();

        var currentNode = endNode;

        while (!currentNode.position().equals(currentNode.parentNodePosition()))
        {
            path.push(currentNode.position());
            currentNode = graph.getParent(currentNode);
        }

        path.push(currentNode.position());

        return path.toArray(new Position[0]);
    }
}
