import java.awt.*;
import java.util.ArrayList;

public class Board {

    int width, height, pixelSize, types;
    Cell[][] cells;
    ArrayList<Color> colors;

    public static ArrayList<Color> getColorArray(int count) {
        ArrayList<Color> colors = new ArrayList<>();
        float colorAdd = 1530 / count;
        float colorValue;
        int r, g, b;

        for (int i=0; i<count; i++) {
            colorValue = i * colorAdd;
            r = Math.min(Math.max((int) (Math.abs(765 - colorValue) - 255), 0), 255);
            g = Math.min(Math.max((int) (510 - Math.abs(510 - colorValue)), 0), 255);
            b = Math.min(Math.max((int) (510 - Math.abs(1020 - colorValue)), 0), 255);
            Color tempCol = new Color(r,g,b);
            colors.add(tempCol);
        }
        return colors;
    }

    public Board(int w, int h, int pSize, int t) {
        width = w;
        height = h;
        pixelSize = pSize;
        types = t;
        colors = getColorArray(types);
        cells = new Cell[width][height];

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                cells[i][j] = new Cell(i,j,this);
            }
        }

        for (int i=0; i<cells.length; i++) {
            for (Cell cell : cells[i]) {
                cell.getNeighbours(cells);
            }
        }
    }
}
