import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Board board = new Board(400, 400, 2,3);
        CellCanvas canvas = new CellCanvas(board, board.cells);
        canvas.createWindow("RPS");

        while (true) {
            for (int i=0; i<board.cells.length; i++) {
                for (Cell cell : board.cells[i]) {
                    cell.prepareAttack();
                }
            }
            for (int i=0; i<board.cells.length; i++) {
                for (Cell cell : board.cells[i]) {
                    cell.attack();
                }
            }
            for (int i=0; i<board.cells.length; i++) {
                for (Cell cell : board.cells[i]) {
                    cell.update();
                }
            }
            canvas.repaint();
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
