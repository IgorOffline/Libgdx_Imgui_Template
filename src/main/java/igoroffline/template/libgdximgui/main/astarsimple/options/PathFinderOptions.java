package igoroffline.template.libgdximgui.main.astarsimple.options;

import igoroffline.template.libgdximgui.main.astarsimple.heuristics.HeuristicFormula;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PathFinderOptions {

    private HeuristicFormula heuristicFormula;
    private boolean useDiagonals;
    private boolean punishChangeDirection;
    private int searchLimit;

    public PathFinderOptions() {
        heuristicFormula = HeuristicFormula.MANHATTAN;
        useDiagonals = true;
        searchLimit = 2000;
    }
}
