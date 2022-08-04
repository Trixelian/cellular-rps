import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Cell {

    Random rand = new Random();
    Board board;
    ArrayList<Color> colors;
    int num, newNum = -1;
    Color color;
    int x,y;

    ArrayList<Cell> neighbours = new ArrayList<>();
    ArrayList<Cell> enemies = new ArrayList<>();
    Cell target;
    int weakAgainst, strongAgainst;

    public Cell(int _x, int _y, Board board) {
        this.x = _x;
        this.y = _y;
        this.num = rand.nextInt(board.types);
        this.board = board;
        this.colors = board.colors;
        this.color = colors.get(num);
    }

    public void getNeighbours(Cell[][] cells) {
        int[][] nextList = {{1,0},{0,1},{-1,0},{0,-1}}; //Orthogonal
        //int[][] nextList = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; //Diagonals + Orthogonal
        //int[][] nextList = {{1,1},{1,-1},{-1,1},{-1,-1}}; //Diagonals only
        //int[][] nextList = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-2}}; //Knight
        //int[][] nextList = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1},{1,0},{0,1},{-1,0},{0,-1}}; //Knight + Orthogonal
        //int[][] nextList = {{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-2},{1,1},{1,-1},{-1,1},{-1,-1}}; //Diagonals + Knight
        //int[][] nextList = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1},{2,0},{-2,0},{0,2},{0,-2}}; //Radius 2 Diamond
        //int[][] nextList = {{2,0},{-2,0},{0,2},{0,-2}}; //2-Step Orthogonal
        //int[][] nextList = {{1,3},{1,-3},{-1,3},{-1,-3},{3,1},{3,-1},{-3,1},{-3,-1}}; //2-Knight
        this.neighbours.clear();
        for (int i=0; i<nextList.length; i++) {
            this.neighbours.add(cells[Math.floorMod(this.x + nextList[i][0],board.width)][Math.floorMod(this.y + nextList[i][1],board.width)]);
        }
    }

    public void prepareAttack() {
        this.enemies.clear();
        for (Cell neighbour : this.neighbours) {
            if (neighbour.num != this.num) {
                this.enemies.add(neighbour);
            }
        }

        if (this.enemies.size() != 0) {
            this.target = this.enemies.get(rand.nextInt(this.enemies.size()));
        } else {
            this.target = null;
        }
    }

    public void attack() {
        this.weakAgainst = Math.floorMod(this.num - 1, board.types);
        this.strongAgainst = Math.floorMod(this.num + 1, board.types);

        if (this.target != null) {
            if (this.target.num == this.strongAgainst) {
                this.target.convert(this.num);
            } else if (this.target.num == this.weakAgainst) {
                this.convert(this.target.num);
            }
        }
    }

    public void update() {
        this.color = colors.get(this.num);
        if (newNum != -1 && this.newNum != this.num) {
            this.num = this.newNum;
        }
    }

    public void convert(int num) {
        this.newNum = num;
    }
}
