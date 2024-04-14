package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public class Manhattan implements CalculateHeuristic {

    @Override
    public int calculate(Position source, Position destination) {

        var heuristicEstimate = 2;
        return heuristicEstimate * (Math.abs(source.row() - destination.row()) + Math.abs(source.column() - destination.column()));
    }
}
