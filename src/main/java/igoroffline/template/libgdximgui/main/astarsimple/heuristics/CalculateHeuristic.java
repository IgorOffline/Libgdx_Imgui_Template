package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public interface CalculateHeuristic {

    int calculate(Position source, Position destination);
}
