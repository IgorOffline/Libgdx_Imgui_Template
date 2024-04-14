package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public class DiagonalShortcut implements CalculateHeuristic {

    @Override
    public int calculate(Position source, Position destination) {

        var hDiagonal = Math.min(Math.abs(source.row() - destination.row()),
                Math.abs(source.column() - destination.column()));
        var hStraight = (Math.abs(source.row() - destination.row()) + Math.abs(source.column() - destination.column()));
        var heuristicEstimate = 2;
        return (heuristicEstimate * 2) * hDiagonal + heuristicEstimate * (hStraight - 2 * hDiagonal);
    }
}
