package igoroffline.template.libgdximgui.main.astarsimple.collections.multidimensional;

import igoroffline.template.libgdximgui.main.astarsimple.Position;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> implements ModelAGrid<T> {

    private T[] grid;
    private final int height;
    private final int width;

    public Grid(int height, int width) {

        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Grid height and width must be greater than 0");
        }

        this.height = height;
        this.width = width;
        grid = (T[]) new Object[height * width];
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public T get(int row, int column) {
        return grid[convertRowColumnToIndex(row, column)];
    }

    @Override
    public void set(int row, int column, T value) {
        grid[convertRowColumnToIndex(row, column)] = value;
    }

    @Override
    public T get(Position position) {
        return get(position.row(), position.column());
    }

    @Override
    public void set(Position position, T value) {
        set(position.row(), position.column(), value);
    }

    private int convertRowColumnToIndex(int row, int column) {
        return width * row + column;
    }

    @Override
    public List<Position> getSuccessorPositions(Position node, boolean optionsUseDiagonals) {

        List<Position> successors = new ArrayList<>();

        for (ByteColumn neighbourOffset : GridOffsets.GetOffsets(optionsUseDiagonals)) {
            int successorRow = node.row() + neighbourOffset.row();
            if (successorRow < 0 || successorRow >= height) {
                continue;
            }

            int successorColumn = node.column() + neighbourOffset.column();
            if (successorColumn < 0 || successorColumn >= width) {
                continue;
            }

            successors.add(new Position(successorRow, successorColumn));
        }

        return successors;
    }
}
