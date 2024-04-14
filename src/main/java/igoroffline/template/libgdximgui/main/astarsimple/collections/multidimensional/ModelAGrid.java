package igoroffline.template.libgdximgui.main.astarsimple.collections.multidimensional;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

import java.util.List;

public interface ModelAGrid<T> {

    int getHeight();
    int getWidth();
    T get(int row, int column);
    void set(int row, int column, T value);
    T get(Position position);
    void set(Position position, T value);
    List<Position> getSuccessorPositions(Position node, boolean optionsUseDiagonals);
}
