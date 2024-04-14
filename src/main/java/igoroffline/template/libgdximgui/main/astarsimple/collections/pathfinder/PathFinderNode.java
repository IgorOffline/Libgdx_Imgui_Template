package igoroffline.template.libgdximgui.main.astarsimple.collections.pathfinder;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

public record PathFinderNode(Position position, int g, int h, Position parentNodePosition) {

    public int getF() {
        return g + h;
    }

    public boolean hasBeenVisited() {
        return getF() > 0;
    }
}
