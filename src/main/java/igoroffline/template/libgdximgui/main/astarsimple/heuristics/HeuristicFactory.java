package igoroffline.template.libgdximgui.main.astarsimple.heuristics;

public class HeuristicFactory {

    public static CalculateHeuristic create(HeuristicFormula heuristicFormula) {

        return switch (heuristicFormula) {
            case MANHATTAN -> new Manhattan();
            case MAX_DXDY -> new MaxDXDY();
            case DIAGONAL_SHORT_CUT -> new DiagonalShortcut();
            case EUCLIDEAN -> new Euclidean();
            case EUCLIDEAN_NO_SQR -> new EuclideanNoSQR();
            case CUSTOM_1 -> new Custom1();
        };
    }
}
