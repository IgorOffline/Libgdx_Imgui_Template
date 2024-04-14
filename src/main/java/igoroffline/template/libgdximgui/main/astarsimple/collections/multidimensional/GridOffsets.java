package igoroffline.template.libgdximgui.main.astarsimple.collections.multidimensional;

import java.util.ArrayList;
import java.util.List;

public class GridOffsets {

    private static final List<ByteColumn> cardinalDirectionOffsets = new ArrayList<>();

    static {
        cardinalDirectionOffsets.add(new ByteColumn((byte) 0, (byte) -1));
        cardinalDirectionOffsets.add(new ByteColumn((byte) 1, (byte) 0));
        cardinalDirectionOffsets.add(new ByteColumn((byte) 0, (byte) 1));
        cardinalDirectionOffsets.add(new ByteColumn((byte) -1, (byte) 0));
    }

    private static final List<ByteColumn> diagonalsOffsets = new ArrayList<>();

    static {
        diagonalsOffsets.add(new ByteColumn((byte) 1, (byte) -1));
        diagonalsOffsets.add(new ByteColumn((byte) 1, (byte) 1));
        diagonalsOffsets.add(new ByteColumn((byte) -1, (byte) 1));
        diagonalsOffsets.add(new ByteColumn((byte) -1, (byte) -1));
    }

    public static List<ByteColumn> GetOffsets(boolean withDiagonals) {
        List<ByteColumn> offsets = new ArrayList<>(cardinalDirectionOffsets);
        if (withDiagonals) {
            offsets.addAll(diagonalsOffsets);
        }
        return offsets;
    }

    public static boolean IsCardinalOffset(ByteColumn offset) {
        return offset.row() != 0 && offset.column() != 0;
    }

    public static boolean IsDiagonal(ByteColumn offset) {
        return offset.row() != 0 || offset.column() != 0;
    }
}
