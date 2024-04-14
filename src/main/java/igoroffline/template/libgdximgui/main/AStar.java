package igoroffline.template.libgdximgui.main;

import igoroffline.template.libgdximgui.main.astarsimple.PathFinder;
import igoroffline.template.libgdximgui.main.astarsimple.Position;
import igoroffline.template.libgdximgui.main.astarsimple.WorldGrid;
import igoroffline.template.libgdximgui.main.astarsimple.options.PathFinderOptions;

import java.util.ArrayDeque;
import java.util.Deque;

public class AStar {

    private final PathFinder pathFinder;

    public AStar(Short[][] tiles) {

        var pathfinderOptions = new PathFinderOptions();
        pathfinderOptions.setPunishChangeDirection(true);
        pathfinderOptions.setUseDiagonals(false);

        final var worldGrid = new WorldGrid(tiles);

        this.pathFinder = new PathFinder(worldGrid, pathfinderOptions);
    }

    public Deque<Position> findPath(int startRow, int startColumn, int endRow, int endColumn) {

        var pathReversed = pathFinder.findPath(new Position(startRow, startColumn), new Position(endRow, endColumn));

        Deque<Position> path = new ArrayDeque<>();
        for (Position position : pathReversed) {
            path.push(position);
        }

        return path;
    }
}
