package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public class Custom1 implements CalculateHeuristic {

    @Override
    public int calculate(Position source, Position destination) {

        var heuristicEstimate = 2;
        var dxy = new Position(Math.abs(destination.row()) - source.row(), Math.abs(destination.column() - source.column()));
        var orthogonal = Math.abs(dxy.row() - dxy.column());
        var diagonal = Math.abs(((dxy.row() + dxy.column()) - orthogonal) / 2);
        return heuristicEstimate * (diagonal + orthogonal + dxy.row() + dxy.column());
    }
}
