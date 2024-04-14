package igoroffline.template.libgdximgui.main.astarsimple;

import igoroffline.template.libgdximgui.main.astarsimple.collections.multidimensional.Grid;

public class WorldGrid extends Grid<Short> {

    public WorldGrid(int height, int width) {
        super(height, width);
    }

    public WorldGrid(Short[][] worldArray) {
        super(worldArray.length, worldArray[0].length);

        for (int row = 0; row < worldArray.length; row++) {
            for (int column = 0; column < worldArray[row].length; column++) {
                set(row, column, worldArray[row][column]);
            }
        }
    }
}
