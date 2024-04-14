package igoroffline.template.libgdximgui.main.astarsimple;

public class PositionExtensions {

    public static Point toPoint(Position position) {
        return new Point(position.column(), position.row());
    }

    public static Position toPosition(Point point) {
        return new Position(point.y(), point.x());
    }
}
