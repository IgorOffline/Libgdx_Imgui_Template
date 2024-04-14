package igoroffline.template.libgdximgui.main.astarsimple;

public interface FindAPath {

    Position[] findPath(Position start, Position end);

    Point[] findPath(Point start, Point end);
}
