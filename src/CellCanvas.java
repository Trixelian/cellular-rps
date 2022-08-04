import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellCanvas extends JPanel {

    Board board;
    Cell[][] cells;

    // Default constructor
    public CellCanvas(Board b, Cell[][] cellArr) {
        board = b;
        cells = cellArr;
        setBackground(Color.BLACK);
    }

    // The method responsible for displaying the contents of the canvas
    @Override
    public void paintComponent(Graphics graphics) {
        // Draw the component as before
        super.paintComponent(graphics);
        // Now draw all cells
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                graphics.setColor(cell.color);
                graphics.fillRect(cell.x*board.pixelSize, cell.y*board.pixelSize,board.pixelSize,board.pixelSize);
            }
        }
    }

    public void createWindow(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(board.width*board.pixelSize,board.height*board.pixelSize));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
}
