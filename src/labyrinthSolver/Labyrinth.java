package labyrinthSolver;

import javax.swing.*;
import java.awt.*;

public class Labyrinth extends JPanel {

    private int width;
    private int height;
    private int cellSize;
    private Wall[][] walls;

    public Labyrinth(int width, int height, int cellSize) {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;

        configWindow();
        initializeCells();
    }

    private void configWindow() {
        this.setLayout(new GridLayout(height, width));
        this.setFocusable(true);
        this.setVisible(true);
    }

    private void initializeCells() {
        walls = new Wall[width][height];
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                walls[row][column] = new Wall(cellSize, row, column);
                this.add(walls[row][column]);
            }
        }
    }

}
