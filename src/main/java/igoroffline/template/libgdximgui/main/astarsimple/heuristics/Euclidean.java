package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public class Euclidean implements CalculateHeuristic {

    @Override
    public int calculate(Position source, Position destination) {

        var heuristicEstimate = 2;
        return (int)(heuristicEstimate * Math.sqrt(Math.pow((source.row() - destination.row()), 2) + Math.pow((source.column() - destination.column()), 2)));
    }
}
