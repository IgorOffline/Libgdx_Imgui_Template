package igoroffline.template.libgdximgui.main.astarsimple;

public record Position(int row, int column) {

    public boolean isDiagonalTo(Position other)
    {
        return row != other.row && column != other.column;
    }
}
